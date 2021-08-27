package com.wky.demo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.holder.ReadSheetHolder;
import com.wky.demo.model.vo.ExcelVO;
import com.wky.demo.service.DemoService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangkunyang
 * @date 2021/08/08 11:15
 * 该listener不能被spring管理，要每次读取excel都要new,
 * 然后里面用到spring可以构造方法传进去
 */
@Slf4j
public class DemoListener extends AnalysisEventListener<ExcelVO> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5000;
    /**
     * 已处理的excel总行数（不包含表头）
     */
    private volatile int totalCount = 0;
    List<ExcelVO> list = new ArrayList<>();
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private DemoService demoService;
    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     * @param demoService
     */
    public DemoListener(DemoService demoService) {
        this.demoService = demoService;
    }

    /**
     * 这个每一条数据解析都会来调用
     * @param data
     *   one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(ExcelVO data, AnalysisContext context) {
        ReadSheetHolder readSheetHolder = context.readSheetHolder();
        if (totalCount == 0) {
            // 会算上表头数据
            Integer approximateTotalRowNumber = readSheetHolder.getApproximateTotalRowNumber();
            log.info("approximateTotalRowNumber：{}", approximateTotalRowNumber);
        }
        String sheetName = readSheetHolder.getSheetName();
        // easyexcel会自动帮我们排除表头数据
        list.add(data);
        totalCount++;
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            try {
                saveData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 存储完成清理 list
            list.clear();
        }
    }
    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @SneakyThrows
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        log.info("处理数据的总行数：{}", totalCount);
        saveData();
        // 读取完一个sheet的数据就清一次list，防止存在旧数据
        list.clear();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private synchronized void saveData() throws InterruptedException {
        log.info("本次处理{}条数据，开始处理数据并存储到数据！", list.size());
        demoService.processData(list);
        log.info("存储数据库成功！");
    }
}

package com.wky.demo.interview.meituan.first;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 题目：编写一个Java函数，实现批量获取数据的功能（BService.get(List<Integer> ids)）。具体要求如下：
 * 1)提供一个函数BService.get(List<Integer> ids)，支持最多传入100个id；
 * 2)在BService.get((List<Integer> ids)函数内部，需要将传入的id列表分批（每批10个id）进行调用AService.get(List<Integer> ids)函数获取数据；
 * 3)BService.get((List<Integer> ids)函数需要返回所有批次获取的数据的合并结果，即一个包含所有数据的List<Integer>；
 * @author wuming
 * @date 2023/8/3/08/03 15:25
 */
public class BService {

    private final AService aService = new AService();

    private final int batchSize = 10;

    public List<Integer> get(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }
        if (ids.size() > 100) {
            throw new RuntimeException("最多只支持传入100个id");
        }
        List<Integer> allIds = new ArrayList<>();
        // 对id进行分批处理
        for (int i = 0; i < ids.size(); i += batchSize) {
            // 取出对应批次的id
            List<Integer> subIds = ids.subList(i, Math.min(i + batchSize, ids.size()));
            allIds.addAll(aService.get(subIds));
        }
        return allIds;
    }

}

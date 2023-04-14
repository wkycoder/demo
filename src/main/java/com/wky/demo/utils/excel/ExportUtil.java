package com.wky.demo.utils.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.wky.demo.exception.GlobalRuntimeException;
import org.apache.commons.collections4.ListUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author wuyimin
 * @date 2020/11/9 10:45
 */
public class ExportUtil {

    private static final String BROWSER_FIREFOX = "firefox";
    private static final String EXCEL_SUFFIX = ".xlsx";
    private static final Integer INT_65535 = 65535;
    private static final String DEFAULT_SHEET = "sheet";

    /**
     * 导出
     *
     * @param data 导出的数据
     * @param fileName  文件名称
     * @param sheetName  sheet名称
     * @param clazz 导出数据对应的类
     * @throws Exception
     */
    public static void writeExcel(List<? extends Object> data, String fileName, String sheetName, Class clazz) throws Exception {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new GlobalRuntimeException(500, "获取参数失败");
        }
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
        //表头样式
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置表头居中对齐
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //内容样式
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置内容靠左对齐
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        EasyExcel.write(getOutputStream(fileName, response), clazz).excelType(ExcelTypeEnum.XLSX).sheet(sheetName).registerWriteHandler(horizontalCellStyleStrategy).doWrite(data);
    }

    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        return response.getOutputStream();
    }

    /**
     * 导出excel(单个sheet)
     *
     * @param fileName
     * @param list
     * @param <T>
     */
    public static <T> void writeEasyExcel(String fileName, List<T> list) {
        try {
            OutputStream outputStream = getOutputStream(fileName);
            ExcelWriter excelWriter = EasyExcel.write(outputStream, list.get(0).getClass())
                    .registerConverter(new ExcelLocalDateConverter())
                    .registerWriteHandler(new ExcelCustomHandler()).build();
            List<List<T>> lists = ListUtils.partition(list, INT_65535);
            for (int i = 0; i < lists.size(); i++) {
                WriteSheet writeSheet = EasyExcel.writerSheet(i, String.valueOf(i)).build();
                excelWriter.write(lists.get(i), writeSheet);
            }
            excelWriter.finish();
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalRuntimeException(500, "IO失败导致批量导入失败");
        }
    }


    public static <T> void writeEasyExcelWithMultiSheets(String fileName, List<ExcelSheet> sheets) {
        try {
            OutputStream outputStream = getOutputStream(fileName);
            writeEasyExcelWithMultiSheets(outputStream, sheets);
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalRuntimeException(500, "IO失败导致写入失败");
        }
    }

    public static void writeEasyExcelWithMultiSheets(OutputStream outputStream, List<ExcelSheet> sheets) {
        ExcelWriter excelWriter = EasyExcel.write(outputStream)
                .registerConverter(new ExcelLocalDateConverter())
                .registerWriteHandler(new ExcelCustomHandler()).build();
        int i = 0;
        for (ExcelSheet sheet : sheets) {
            List<?> sheetData = sheet.getSheetData();
            WriteSheet writeSheet = EasyExcel.writerSheet(i, sheet.getSheetName())
                    .head(sheet.getSheetHead())
                    .build();
            excelWriter.write(sheetData, writeSheet);
            i++;
        }
        excelWriter.finish();
    }

    private static OutputStream getOutputStream(String fileName) throws IOException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new GlobalRuntimeException(500, "获取参数失败");
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
        if (response == null) {
            throw new GlobalRuntimeException(500, "未获取的返回导致失败");
        }
        String agent = request.getHeader("USER-AGENT").toLowerCase();
        String codedFileName = URLEncoder.encode(fileName, "UTF-8");
        response.setCharacterEncoding("utf-8");
        if (agent.contains(BROWSER_FIREFOX)) {
            response.setContentType("application/octet-stream");
            // 设置字符集
            response.setCharacterEncoding("utf-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + EXCEL_SUFFIX);
        } else {
            response.setContentType("application/vnd.ms-excel");
            // 设置字符集
            response.setCharacterEncoding("utf-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + EXCEL_SUFFIX);
        }
        return response.getOutputStream();
    }

    /**
     * 获取并解析excel文件，返回一个二维集合
     *
     * @param file 上传的文件
     * @return 二维集合（第一重集合为行，第二重集合为列，每一行包含该行的列集合，列集合包含该行的全部单元格的值）
     */
    public static List<Map<String, Object>> analysis(MultipartFile file) throws IOException {
        Workbook workbook = null;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheet("0");
            //行数
            int num = sheet.getLastRowNum();
            //列数
            int col = sheet.getRow(0).getLastCellNum();
            String[] colName = new String[col];
            //获取列名
            Row row = sheet.getRow(0);
            for (int i = 0; i < col; i++) {
                String[] s = row.getCell(i).getStringCellValue().split("-");
                colName[i] = s[0];
            }
            //将一行中每列数据放入一个map中,然后把map放入list
            for (int i = 1; i <= num; i++) {
                Map<String, Object> map = new HashMap<>(num);
                Row row1 = sheet.getRow(i);
                if (row1 != null) {
                    for (int j = 0; j < col; j++) {
                        Cell cell = row1.getCell(j);
                        if (cell != null) {
                            String tmp = cell.toString();
                            if (tmp.indexOf(".") > 0) {
                                tmp = tmp.replace(".0", "");
                                cell.setCellValue(tmp);
                            }
                            cell.setCellType(CellType.STRING);
                            map.put(colName[j], cell.getStringCellValue());
                        }
                    }
                }
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(workbook)) {
                workbook.close();
            }
        }
        return list;
    }

    public static <T> void writeEasyExcel(String fileName,
                                          List<T> list,
                                          Class<?> clazz) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        Assert.notNull(requestAttributes, "获取参数失败");
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
        Assert.notNull(response, "未获取的返回导致失败");
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        String agent = request.getHeader("User-Agent").toUpperCase();
        final String ie = "MSIE";
        final String firefox = "GECKO";
        final String rv = "RV:11";
        try {
            boolean utf8 = agent.indexOf(ie) > 0 || (agent.indexOf(firefox) > 0 && agent.indexOf(rv) > 0);
            if (utf8) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {
                fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
            }
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + EXCEL_SUFFIX);

            EasyExcel.write(response.getOutputStream(), clazz)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet(DEFAULT_SHEET)
                    .doWrite(list);
        } catch (Exception e) {
            throw new GlobalRuntimeException(500, "IO失败导致批量导入失败");
        }
    }


}

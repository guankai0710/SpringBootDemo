package com.demo.springbootdemo.utils;

import com.demo.springbootdemo.vo.JsonResult;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * excel表格导出工具类
 *
 * @author: guan.kai
 * @date: 2019/8/16 15:18
 **/
public class ExcelUtil {

    private ExcelUtil(){}

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * excel文件拓展名
     **/
    private static final String XLS =".xls";
    private static final String XLSX =".xlsx";

    /**
     * excel文件拓展名正则表达式
     **/
    private static final String XLS_REGEX ="^.+\\.(?i)(xls)$";
    private static final String XLSX_REGEX ="^.+\\.(?i)(xlsx)$";

    /**
     * 导入表格数据，默认第1行为表头，第2行开始为数据，统一转成String字符串类型
     *
     * @param file 待导入文件
     * @return 文件内容
     */
    public static String[][] importExcel(MultipartFile file){
        Workbook wb = null;
        String filename;
        try(InputStream inputStream = file.getInputStream()) {
            filename = file.getOriginalFilename();
            //根据不同的文件格式生成不同的workbook
            if (filename.matches(XLS_REGEX)){
                wb = new HSSFWorkbook(inputStream);
            }else if (filename.matches(XLSX_REGEX)){
                wb = new XSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        Sheet sheet = wb.getSheetAt(0);

        //获取文件数据行数
        int rowLength = sheet.getPhysicalNumberOfRows();
        //获取文件数据表头列数
        int cellLength = sheet.getRow(0).getPhysicalNumberOfCells();
        //解析数据
        String[][] content = new String[rowLength][cellLength];
        for (int i = 1; i < rowLength; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            for (int j = 0; j < cellLength; j++) {
                Cell cell = row.getCell(j);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String cellValue = null == cell.getStringCellValue() ? "" : cell.getStringCellValue();
                content[i-1][j] = cellValue;
            }
        }
        return content;
    }

    /**
     * 导入表格数据，默认统一转成String字符串类型
     *
     * @param file 导入Excel文件
     * @param startRow 起始导入行,该参数为null时默认从表格文件第二行开始导入数据
     * @return
     */
    public static JsonResult importExcel(MultipartFile file, Integer startRow){
        JsonResult result = new JsonResult();
        result.setSuccess(false);
        Workbook wb = null;
        String filename;
        try(InputStream inputStream = file.getInputStream()) {
            filename = file.getOriginalFilename();
            //判断文件格式
            if (filename.matches(XLS_REGEX)){
                wb = new HSSFWorkbook(inputStream);
            }else if (filename.matches(XLSX_REGEX)){
                wb = new XSSFWorkbook(inputStream);
            }else {
                result.setMsg("上传文件格式错误！");
                return result;
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        Sheet sheet = wb.getSheetAt(0);
        //起始行数为null时默认从第2行开始导入数据
        if (startRow == null){
            startRow = 1;
        }
        //获取文件数据行数
        int rowLength = sheet.getPhysicalNumberOfRows();
        if (rowLength < startRow){
            result.setMsg("起始解析行数不能小于总行数！");
            return result;
        }
        //获取文件数据最大列数
        int cellLength = 0;
        for (int i = 0; i < rowLength; i++){
            int cells = sheet.getRow(i).getPhysicalNumberOfCells();
            if (cells > cellLength){
                cellLength = cells;
            }
        }
        //解析数据
        String[][] content = new String[rowLength][cellLength];
        for (int i = startRow; i < rowLength; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            for (int j = 0; j < cellLength; j++) {
                Cell cell = row.getCell(j);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String cellValue = null == cell.getStringCellValue() ? "" : cell.getStringCellValue();
                content[i-startRow][j] = cellValue;
            }
        }
        result.setSuccess(true);
        result.setObj(content);
        return result;
    }

    /**
     * 导出数据到excel表格,统一导出文本格式
     *
     * @param response 响应体
     * @param fileName 导出文件名
     * @param sheetName sheet名称
     * @param title 导出表头内容
     * @param content 导出内容
     * @param colWidth 单元格宽度
     */
    public static void exportExcel(HttpServletResponse response, String fileName, String sheetName, String[] title, String[][] content, int[] colWidth){
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(sheetName);
        XSSFCellStyle cellStyle = getCellStyle(wb);
        //创建表头
        XSSFRow titleRow = sheet.createRow(0);
        //设置行高
        titleRow.setHeightInPoints(20);
        cellStyle.setFont(getFont(wb,true));
        for (int i=0;i<title.length;i++){
            //设置单元格宽度
            sheet.setColumnWidth(i, colWidth[i]*256);
            XSSFCell titleCell = titleRow.createCell(i);
            titleCell.setCellStyle(cellStyle);
            titleCell.setCellValue(title[i]);
        }
        //创建内容
        cellStyle.setFont(getFont(wb,false));
        for (int i=0;i<content.length;i++){
            XSSFRow row = sheet.createRow(i+1);
            //设置行高
            row.setHeightInPoints(20);
            for (int j=0;j<content[i].length;j++){
                XSSFCell cell = titleRow.createCell(i);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(title[i]);
            }
        }
        //判断导出文件是否为xlsx拓展名
        if (!XLSX.equals(fileName.substring(fileName.length()-XLSX.length()))){
            fileName += XLSX;
        }
        //响应到客户端
        setResponseHeader(response,wb,fileName);
    }

    /**
     * 获取单元格样式
     *
     * @param wb
     * @return
     */
    private static XSSFCellStyle getCellStyle(XSSFWorkbook wb){
        XSSFCellStyle cellStyle = wb.createCellStyle();
        //水平居中
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        //垂直居中
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        //实线边框
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        return cellStyle;
    }

    /**
     * 获取单元格字体
     *
     * @param wb
     * @param isTitle 是否为表头
     * @return
     */
    private static XSSFFont getFont(XSSFWorkbook wb, boolean isTitle){
        XSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setBold(isTitle);
        font.setFontHeightInPoints((short)12);
        return font;
    }

    /**
     * 发送响应流
     *
     * @param response
     * @param wb
     * @param fileName 文件名称
     */
    private static void setResponseHeader(HttpServletResponse response, XSSFWorkbook wb, String fileName) {
        try {
            fileName = new String(fileName.getBytes(),"ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        response.setContentType("application/octet-stream;charset=ISO8859-1");
        response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        //响应到客户端
        try(ServletOutputStream os = response.getOutputStream()) {
            wb.write(os);
        } catch ( Exception e) {
            logger.error(e.getMessage());
        }
    }

}

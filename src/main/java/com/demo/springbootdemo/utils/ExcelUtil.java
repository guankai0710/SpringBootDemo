package com.demo.springbootdemo.utils;

import com.demo.springbootdemo.vo.JsonResult;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
 * @Author: guan.kai
 * @CreateTime: 2019/8/16 15:18
 * @Version: 1.0.0
 **/
public class ExcelUtil {

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
     * @Author: kai.guan
     * @CreateTime: 2019/8/19 22:49
     * @Param: [file]
     * @return: java.lang.String[][]
     **/
    public static String[][] importExcel(MultipartFile file){
        Workbook wb = null;
        InputStream inputStream = null;
        String filename;
        try {
            inputStream = file.getInputStream();
            filename = file.getOriginalFilename();
            //根据不同的文件格式生成不同的workbook
            if (filename.matches(XLS_REGEX)){
                wb = new HSSFWorkbook(inputStream);
            }else if (filename.matches(XLSX_REGEX)){
                wb = new XSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
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
     * @Author: kai.guan
     * @CreateTime: 2019/8/19 22:21
     * @Param: [file：导入Excel文件, startRow：起始导入行,该参数为null时默认从表格文件第二行开始导入数据]
     * @return: com.demo.springbootdemo.vo.JsonResult
     **/
    public static JsonResult importExcel(MultipartFile file, Integer startRow){
        JsonResult result = new JsonResult();
        result.setSuccess(false);
        Workbook wb = null;
        InputStream inputStream = null;
        String filename;
        try {
            inputStream = file.getInputStream();
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
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
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
     * @Author: guan.kai
     * @CreateTime: 2019/8/16 16:05
     * @Param: [response, fileName：导出文件名, sheetName：sheet名称, title：导出表头内容, content：导出内容, colWidth：单元格宽度]
     * @Return: org.apache.poi.xssf.usermodel.XSSFWorkbook
     **/
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
     * @Author: guan.kai
     * @CreateTime: 2019/8/16 16:34
     * @Param: [wb]
     * @Return: org.apache.poi.xssf.usermodel.XSSFCellStyle
     **/
    private static XSSFCellStyle getCellStyle(XSSFWorkbook wb){
        XSSFCellStyle cellStyle = wb.createCellStyle();
        //水平居中
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        //垂直居中
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        //实线边框
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        return cellStyle;
    }

    /**
     * 获取单元格字体
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/16 16:39
     * @Param: [wb, isTitle：是否为表头]
     * @Return: org.apache.poi.xssf.usermodel.XSSFFont
     **/
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
     * @Author: guan.kai
     * @CreateTime: 2019/8/16 16:56
     * @Param: [response, fileName：文件名称]
     * @Return: void
     **/
    private static void setResponseHeader(HttpServletResponse response, XSSFWorkbook wb, String fileName) {
        try {
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
            ServletOutputStream os = null;
            try {
                os = response.getOutputStream();
                wb.write(os);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }finally {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

}

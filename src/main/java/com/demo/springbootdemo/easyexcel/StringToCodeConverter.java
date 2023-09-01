//package com.demo.springbootdemo.easyexcel;
//
//import com.alibaba.excel.converters.Converter;
//import com.alibaba.excel.enums.CellDataTypeEnum;
//import com.alibaba.excel.metadata.GlobalConfiguration;
//import com.alibaba.excel.metadata.data.CellData;
//import com.alibaba.excel.metadata.data.ReadCellData;
//import com.alibaba.excel.metadata.data.WriteCellData;
//import com.alibaba.excel.metadata.property.ExcelContentProperty;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.ss.formula.functions.T;
//import org.springframework.data.domain.Page;
//
///**
// * 描述：TODO
// *
// * @author guankai
// * @date 2022/12/14
// **/
//public class StringToCodeConverter implements Converter<Integer> {
//
//    private Class clzss;
//
//    public StringToCodeConverter(Class clzss) {
//        this.clzss = clzss;
//    }
//
//    public static StringToCodeConverter of(Class<T> clazz){
//        return new StringToCodeConverter(clazz);
//    }
//
//    @Override
//    public Class supportJavaTypeKey() {
//        return String.class;
//    }
//
//    @Override
//    public CellDataTypeEnum supportExcelTypeKey() {
//        return CellDataTypeEnum.STRING;
//    }
//
//    @Override
//    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
//        Integer code = 0;
//        String enumType = contentProperty.getField().getName();//枚举类型
//        String cellValue = cellData.getStringValue();//cell值
//        if (StringUtils.isNotEmpty(cellValue)) {
//            if ("billType".equals(enumType)) {
//                //这里根据枚举类及其对应的中文找到code
//                //xxxEnum enumCode = xxxEnum.getEnumCode(cellValue);
//                //code = enumCode.getCode();
//            }
//        }
//        return code;
//    }
//
//    @Override
//    public WriteCellData<?> convertToExcelData(String s, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
//        return new WriteCellData<>(s);
//    }
//
//}

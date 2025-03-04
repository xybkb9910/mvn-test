package org.example.until;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class ExcelUntil {
    private static XSSFSheet excelWSheet;
    private static XSSFWorkbook excelWBook;
    private static XSSFCell cell;
    private static XSSFRow row;
    public static Method method[];
    static Class clazz;
    public ExcelUntil() throws Exception {
        clazz=Class.forName("org.example.until.until");
    }

    @Test
    public void test() throws Exception {
        getTestDate("Sheet1");
    }

    public static void setExcelWSheet(String sheetName)throws  Exception{
        FileInputStream excelFile;
        try {
            excelFile = new FileInputStream("F:/test.xlsx");
            excelWBook = new XSSFWorkbook(excelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //读取excel某个单元格数据
    public static String getCellData(int rowNum,int cokNUM)throws  Exception{
        try {
            cell=excelWSheet.getRow(rowNum).getCell(cokNUM);
            String cellData=cell.getStringCellValue();
            return cellData;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    //判断数据类型
    public static Object parseParam(String param) {
        // 如果是数字
        if (param.matches("\\d+")) {
            return Integer.parseInt(param);
        }
        // 默认返回字符串
        return param;
    }


    public static void getTestDate(String sheetName)throws  Exception{
        setExcelWSheet(sheetName);
        for (int row = 1; row <= excelWSheet.getLastRowNum(); row++) {
                String keyword = getCellData(row, 0);
                String paramString = getCellData(row, 1);
                String[] paramArray = paramString.split(",");
                List<Object> params = new ArrayList<>();
                for (String param : paramArray) {
                    params.add(parseParam(param.trim())); // 去除空格并解析参数
                }
                invokeKeyword(keyword, params.toArray());
            }

        }


    public static void invokeKeyword(String methodName, Object... params) throws Exception {
        // 获取参数类型
        Class<?>[] paramTypes = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            paramTypes[i] = params[i].getClass();
        }
        // 获取方法
        Method method = clazz.getMethod(methodName, paramTypes);
        // 调用方法
        method.invoke(new until(),params);
    }


}


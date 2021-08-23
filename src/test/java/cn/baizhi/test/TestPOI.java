package cn.baizhi.test;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.jupiter.api.Test;


import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestPOI {
    @Test
    public void test9(){
        //创建Excel
        Workbook workbook = new HSSFWorkbook();

        //创建工作表
        Sheet sheet1 = workbook.createSheet("学生信息表");
        Sheet sheet2 = workbook.createSheet("学生成绩表");

        Row row =sheet1.createRow(1);//代表第二行

        //创建单元格   cell代表单元格对象
        Cell cell = row.createCell(2);

        cell.setCellValue("Hello poi");


        try {
            workbook.write(new FileOutputStream("D:\\poi.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Test
    public void test2(){
        List<Student> list = new ArrayList<>();
        list.add(new Student("1", "Lzg", 9, new Date()));
        list.add(new Student("2", "Lzgg", 10, new Date()));
        list.add(new Student("3", "Lzggg", 11, new Date()));
        list.add(new Student("4", "Lzgggg", 12, new Date()));
        list.add(new Student("5", "Lzggggg", 13, new Date()));

        HSSFWorkbook workbook = new HSSFWorkbook();



        HSSFFont font = workbook.createFont();
        font.setBold(true);//加粗


        //创建样式对象
        HSSFCellStyle cellStyle = workbook.createCellStyle();

        HSSFDataFormat dataFormat = workbook.createDataFormat();

        cellStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd"));

        HSSFSheet sheet = workbook.createSheet("学生信息列表");


        HSSFRow row2 = sheet.createRow(0);
        HSSFCell hssfCell = row2.createCell(0);
        hssfCell.setCellStyle(cellStyle);
        hssfCell.setCellValue("学生信息");

        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 3);
        sheet.addMergedRegion(region);


        //设置列宽
        sheet.setColumnWidth(3, 20*256);

        HSSFRow row = sheet.createRow(1);//创建行下标

        String[] content = {"id","姓名","年龄","生日"};

        for (int i =0; i<=content.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(content[i]);
        }

        for (int i = 0; i<=list.size(); i++){
            HSSFRow row1 = sheet.createRow(i + 2);
            HSSFCell cell = row1.createCell(0);
            cell.setCellValue(list.get(i).getId());

            HSSFCell cell1 = row1.createCell(1);
            cell1.setCellValue(list.get(i).getAge());

            HSSFCell cell2 = row1.createCell(2);
            cell2.setCellValue(list.get(i).getName());

            HSSFCell cell3 = row1.createCell(3);
            cell3.setCellValue(list.get(i).getAge());


        }

            try {
                workbook.write(new FileOutputStream("D:\\poi.xls"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    @Test
    public void test3() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("D://poi.xls")));

        HSSFSheet sheet = workbook.getSheet("学生信息表");

        List<Student> list = new ArrayList<>();
        for (int i = 2; i<=sheet.getLastRowNum(); i++){
            HSSFRow row = sheet.getRow(i);

            HSSFCell cell = row.getCell(0);
            String id = cell.getStringCellValue();

            HSSFCell cell1 = row.getCell(1);
            String name = cell1.getStringCellValue();

            HSSFCell cell2 = row.getCell(2);
            double age = cell2.getNumericCellValue();

            HSSFCell cell3 = row.getCell(3);
            Date bir = cell3.getDateCellValue();

            Student student = new Student(id, name, (int)age, bir);
            list.add(student);

            for (Student student1 : list) {
                System.out.println(student1);
            }






        }
    }
}



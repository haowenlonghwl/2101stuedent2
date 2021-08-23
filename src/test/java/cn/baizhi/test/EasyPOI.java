package cn.baizhi.test;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class EasyPOI {
    @Test
    public void test9() throws IOException {

        List<Student> list = new ArrayList<>();
        list.add(new Student("1", "MingMing", 20, new Date()));
        list.add(new Student("2", "MingMing2", 19, new Date()));
        list.add(new Student("3", "MingMing3", 18, new Date()));

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生","学生"),
                Student.class, list);

        workbook.write(new FileOutputStream("D:\\easypoi.xls"));


    }

    @Test
    public void test99() throws IOException {

        List<Student> list = new ArrayList<>();
        list.add(new Student("1", "MingMing", 20, new Date()));
        list.add(new Student("2", "MingMing2", 19, new Date()));
        list.add(new Student("3", "MingMing3", 18, new Date()));

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("1", "红红", 10000.0,list));
        teachers.add(new Teacher("2", "红红2", 1000.0,list));
        teachers.add(new Teacher("3", "红红3", 100.0,list));


        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("学生信息","学生"),
                Teacher.class, teachers);

        workbook.write(new FileOutputStream("D:\\easypoi.xls"));



    }


    @Test
    public void test999(){
        ImportParams params = new ImportParams();
        params.setTitleRows(1);//表格标题
        params.setHeadRows(2);//表头

        List<Teacher> teachers = ExcelImportUtil.importExcel(new File("D:\\easypoi.xls"), Teacher.class, params);

        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }

    }

    @Test
    public void test6() throws IOException {

        List<Product>products = new ArrayList();
        products.add(new Product("22", "123", "D:\\photo\\1.png"));
        products.add(new Product("33", "1234", "D:\\photo\\2.jpg"));
        products.add(new Product("11", "12345", "D:\\photo\\4.jpg"));
        products.add(new Product("44", "123456", "D:\\photo\\28.jpg"));

        List<User> list = new ArrayList<>();
        list.add(new User("1", "D:\\photo\\1.png", "歌歌",products));
        list.add(new User("2", "D:\\photo\\2.jpg", "歌歌歌",products));
        list.add(new User("3", "D:\\photo\\4.jpg", "歌歌歌歌",products));
        list.add(new User("4", "D:\\photo\\28.jpg", "歌歌歌歌歌",products));



        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息","学生"),
                User.class, list);

        workbook.write(new FileOutputStream("D:\\easypoi-user.xls"));

    }
    @Test
    public void test8() throws IOException {

        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        List<User> list = ExcelImportUtil.importExcel(new File("D:\\easypoi-user.xls"), User.class, params);
        for (User user : list) {
            System.out.println(user);
        }
    }
   /* @Test
    public void test7() throws IOException {
        List<Product>products = new ArrayList();
        products.add(new Product("22", "123", "D:\\photo\\1.png"));
        products.add(new Product("33", "1234", "D:\\photo\\2.jpg"));
        products.add(new Product("11", "12345", "D:\\photo\\4.jpg"));
        products.add(new Product("44", "123456", "D:\\photo\\28.jpg"));

        List<User> list = new ArrayList<>();
        list.add(new User("1", "D:\\photo\\1.png", "歌歌",products));
        list.add(new User("2", "D:\\photo\\2.jpg", "歌歌歌",products));
        list.add(new User("4", "D:\\photo\\28.jpg", "歌歌歌歌歌",products));


        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息","学生"),
                User.class, list);

        workbook.write(new FileOutputStream("D:\\easypoi-user-product.xls"));


    }*/
}

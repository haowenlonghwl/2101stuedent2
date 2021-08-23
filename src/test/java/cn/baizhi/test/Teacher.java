package cn.baizhi.test;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Excel(name = "教师的id",needMerge = true)
    private String id;
    @Excel(name = "教师的姓名",needMerge = true)
    private String name;
    @Excel(name = "教师的薪资",needMerge = true)
    private Double salary;
    @ExcelCollection(name = "对应的学员")
    private List<Student> stus;
}

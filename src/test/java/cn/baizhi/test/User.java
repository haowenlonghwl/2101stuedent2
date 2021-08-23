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
public class User {

    @Excel(name = "用户的id")
    private String id;
    @Excel(name = "用户的头像", type = 2)
    private String headpath;
    @Excel(name = "用户的名字")
    private String username;
    @ExcelCollection(name = "产品信息")
    private List<Product> products;

}

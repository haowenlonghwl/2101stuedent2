package cn.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Excel(name = "用户id")
    private String id;
    @Excel(name = "用户姓名")
    private String username;
    @Excel(name = "用户电话")
    private String phone;
    @Excel(name = "用户头像")
    private String heading;
    @Excel(name = "用户描述")
    private String brief;
    @Excel(name = "用户微信")
    private String wechat;
    @Excel(name = "用户创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdate;
    @Excel(name = "用户状态")
    private Integer status;

//    public String getCreatedate() {
//        return new SimpleDateFormat("yyyy-MM-dd").format(createdate);
//    }
}

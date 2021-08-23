package cn.baizhi.entity;

/*管理员实体类*/


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Setter   //生成set方法
//@Getter   //生成get方法
//@ToString   //toString 方法
//@AllArgsConstructor //生成全参构造方法
//@NoArgsConstructor  //无参构造
@Data   //@Setter  +  @Getter + @ToString
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

  private String id;
  private String username;
  private String password;
  private long status;

}

package cn.baizhi.dao;


import cn.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    // 范围查询
    List<User> queryRange(@Param("start") int start, @Param("end") int end);

//    查总条数
    int queryPageNum();

//    修改状态
    void updateStatus(@Param("id") String id, @Param("status") int status);

//    添加
    void add(User user);

//    删除  根据 id
    void deleteById(String id);
}

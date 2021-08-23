package cn.baizhi.dao;

import cn.baizhi.entity.Category;

import java.util.List;

public interface CategoryDao {

    // 根据级别 查询 类别
    List<Category> queryByLevels(int levens);

    //根据 父项 id 查询所有的二级类别
    List<Category> queryByParendId(String id);

    //添加类别
    void save(Category category);

    //根据id删除类别
    void delete(String id);

//    根据 id 查询出这条数据的信息
    Category queryById(String id);
}

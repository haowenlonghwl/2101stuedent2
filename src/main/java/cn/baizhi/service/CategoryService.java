package cn.baizhi.service;

import cn.baizhi.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    //    根据级别 查 类别  的业务
    List<Category> queryByLevels(int levens);

    //    根据父项id 查2级类别
    List<Category> queryByParentId(String id);

//    添加类别
    void save(Category category);

//    删除类别
    Map<String,Object> delete(String id);
}

package cn.baizhi.service;

import cn.baizhi.dao.CategoryDao;
import cn.baizhi.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao cd;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryByLevels(int levens) {
        return cd.queryByLevels(levens);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryByParentId(String id) {
        return cd.queryByParendId(id);
    }

    @Override
    public void save(Category category) {
        category.setId(UUID.randomUUID().toString());
        cd.save(category);
    }

    @Override
    public Map<String, Object> delete(String id) {
            /*
                    1.先根据id查询当前类别信息
                    2.如果是二级类型  直接删
                      如果是1级类别  判断该一级类别下有没有 二级类型
                            有 不能删
                            没有 可以删
             */
        Map<String, Object> map = new HashMap<>();
        Category category = cd.queryById(id);
        Integer levels = category.getLevels();
        if (levels == 1) {
            //1级类别
            List<Category> categories = cd.queryByParendId(id);
            if (categories.size() == 0) {
                cd.delete(id);
                map.put("flag", true);
            } else {
                map.put("flag", false);
                map.put("msg", "无法删除因为内部存在二级类别");
            }
        } else {
//        无论传递的是一级类别 还是二级类别 目前都是直接删除
            cd.delete(id);
        }

        return map;
    }
}

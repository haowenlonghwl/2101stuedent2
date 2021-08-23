package cn.baizhi.service;

import cn.baizhi.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface UserService {
    // 分页查业务
    Map<String,Object> queryByPage(int page, int size);

    //    修改状态的业务
    void updateStatus(String id, int status);


//    添加业务      给一个头像文件      user对象
    void save(MultipartFile file, User user);

//    删除 用户 业务   并 删除 头像
    void delete(String id, String headingurl);

    void excels(String path) throws IOException;
}

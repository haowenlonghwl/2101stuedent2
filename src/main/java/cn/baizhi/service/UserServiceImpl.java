package cn.baizhi.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.baizhi.dao.UserDao;
import cn.baizhi.entity.User;
import cn.baizhi.util.AliYun;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao ud;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryByPage(int page, int size) {////第二页2 的3条数据
        Map<String, Object> map = new HashMap<>();
        //总页数
//        map.put("", );
//        当前是第几页

        //   page-1*size
        //    1页 0    2页   3
        List<User> list = ud.queryRange((page - 1) * size, size);
        //分页查的数据
        map.put("data", list);

        //存储当前页数
        map.put("page", page);//2

        //存储总页数       总条数 / 每页显示的条数 = 总页数
        int countNum = ud.queryPageNum(); //总条数
        //总页数
        int pageNum = countNum % size == 0 ? countNum / size : countNum / size + 1;
        map.put("pageNum", pageNum);
        return map;
    }

    @Override
    public void updateStatus(String id, int status) {
        ud.updateStatus(id, status);
    }

    @Override
    public void save(MultipartFile file, User user) {
        //        头像的上传

        //得到上传时文件的名字
        String fileName = file.getOriginalFilename();
        //   a.jpg
        Date date = new Date();
        long time = date.getTime();
        String finalName = time + fileName;
        AliYun.uploadByBytes(file, finalName);

        //user 对象中的数据入库
        //user  是 controller 传递过来的  已经有 username phone brief
        user.setId(UUID.randomUUID().toString());
        user.setCreatedate(new Date());
//  http://2101class.oss-cn-beijing.aliyuncs.com/1.jpg
//  http://2101class.oss-cn-beijing.aliyuncs.com/17.jpg
        user.setHeading("http://2101class.oss-cn-beijing.aliyuncs.com/" + finalName);
        user.setStatus(1);
        ud.add(user);
    }

    @Override
    public void delete(String id, String headingurl) {
        /*
             有用户id  : 通过用户id删除表中的数据
             有图片的url: 删除图片

             问题: oss数据存储中存储了该用户的头像
                 头像怎么删除? 通过头像的url去删除
             怎么得到url?
         */
//        删除图片
//         http://2101class.oss-cn-beijing.aliyuncs.com/162908039557118.jpg
        int i = headingurl.lastIndexOf('/'); //  20
        String fileName = headingurl.substring(i + 1);// 21
        AliYun.deleteFile(fileName);

//        删除用户信息
        ud.deleteById(id);
    }
//
    @Override
    public void excels(String path) throws IOException {
        List<User> list = ud.queryRange(0, 99999);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息", "学生"),
                User.class, list);
        workbook.write(new FileOutputStream(path));
        workbook.close();
    }

}

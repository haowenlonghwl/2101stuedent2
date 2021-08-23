package cn.baizhi.controller;

import cn.baizhi.entity.User;
import cn.baizhi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService us;


    @RequestMapping("/excel")
    public Map<String, Object> Excel(){
        Map<String,Object> map = new HashMap<>();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File home = fsv.getHomeDirectory();
        String savePath = home.getPath();
        map.put("flage", false);
        try {
            us.excels(savePath+"\\easypoi.xls");
            map.put("flage", true);
            map.put("msg", "导出到桌面自行查看");

        } catch (IOException e) {
            map.put("flage", false);
            map.put("msg", "出错了");
            e.printStackTrace();
        }
        return map;

    }



    @RequestMapping("/queryByPage")
    public Map<String, Object> queryByPage(int page){//page = 2
        int size = 3;
        return us.queryByPage(page, size); //第二页2 的3条数据
    }


    @RequestMapping("/updateStatus")
    public void updateStatus(String id,int status){
        log.debug(id+"========"+status);
        us.updateStatus(id, status);
    }


    @RequestMapping("/add")
    public void add(MultipartFile photo,String username,String phone,String brief){
        System.out.println(photo.getOriginalFilename());
        System.out.println(username);
        System.out.println(phone);
        System.out.println(brief);

        User user = new User(null,username,phone,null,brief,null,null,null);
        us.save(photo, user);
    }


    @RequestMapping("/delete")
    public void delete(String id,String headingurl){
        log.debug(id);
        log.debug(headingurl);

        us.delete(id,headingurl);

    }

    @RequestMapping("/registCount")
    public Map<String,Object> registCount() {
        System.out.println("执行了");
        List<String> data = new ArrayList<>();
        /*List<Integer> manCount =  ArrayList.asList(5,6,0,20,36,40,50,80,40,99,66);
        List<Integer> womanCount =  ArrayList.asList(5,0,20,36,50,80,40,66);*/

        for (int i = 1; i<=12; i++){
            data.add(i+"月");
        }

        Map<String,Object> map = new HashMap<>();
        map.put("data", data);
        return map;

    }
}

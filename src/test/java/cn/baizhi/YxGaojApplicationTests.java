package cn.baizhi;

import cn.baizhi.dao.AdminDao;
import cn.baizhi.dao.VideoDao;
import cn.baizhi.entity.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class YxApplicationTests {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private VideoDao vd;



    @Test
    void contextLoads(){
        System.out.println();
        List<Video> videos = vd.queryByPage(0, 1);
        for (Video video : videos) {
            System.out.println(video);
        }
    }

  /*  @Test
    void contextLoads() {
        Admin admin = adminDao.queryByUserName("2021");
        System.out.println(admin);
    }*/

}

package cn.baizhi.test;

import cn.baizhi.dao.UserDao;
import cn.baizhi.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao ud;

    @Test
    public void testQueryRange(){

        //1 0 1 2   2页 3
        List<User> list = ud.queryRange(3, 3);
        for (User user : list) {
            System.out.println(user);
        }
    }
}

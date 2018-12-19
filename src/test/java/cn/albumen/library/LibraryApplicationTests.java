package cn.albumen.library;

import cn.albumen.library.bean.UserSecurity;
import cn.albumen.library.dao.UserSecurityDao;
import cn.albumen.library.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryApplicationTests {
    @Autowired
    UserSecurityDao userSecurityDao;
    @Autowired
    RedisUtil redisUtil;

    @Test
    public void contextLoads() {
        UserSecurity userSecurity = new UserSecurity();
        userSecurity.setId(2);
        long startTime = System.currentTimeMillis();
        long t1 = 0, t2 = 0, t3 = 0;
        userSecurity = userSecurityDao.selectById(userSecurity);
        for (int i = 0; i < 1000; i++) {
            long startTime1 = System.currentTimeMillis();
            userSecurity.setUserNo((Integer.parseInt(userSecurity.getUserNo()) + 1) + "");
            userSecurityDao.addUser(userSecurity);
            long startTime2 = System.currentTimeMillis();
            t1 += startTime2 - startTime1;
            userSecurity = userSecurityDao.selectByNo(userSecurity);
            long startTime3 = System.currentTimeMillis();
            t2 += startTime3 - startTime2;
            userSecurityDao.delete(userSecurity);
            long startTime4 = System.currentTimeMillis();
            t3 += startTime4 - startTime3;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
        System.out.println(t1 / 1000);
        System.out.println(t2 / 1000);
        System.out.println(t3 / 1000);

    }

}


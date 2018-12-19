package cn.albumen.library;

import cn.albumen.library.bean.UserSecurity;
import cn.albumen.library.dao.UserSecurityDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DruidTest {
    @Autowired
    UserSecurityDao userSecurityDao;

    @Test
    public void druidTest() {
        UserSecurity userSecurity = new UserSecurity();
        userSecurity.setId(1);
        UserSecurity userSecurity1 = userSecurityDao.selectById(userSecurity);
        System.out.println(userSecurity1);
    }
}

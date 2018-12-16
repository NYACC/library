package cn.albumen.library;

import cn.albumen.library.util.Jwt;
import org.junit.Test;

public class JwtTest {
    @Test
    public void test() {
        String token = Jwt.create("1", new String[]{"1", "2"});
        System.out.println(token);
        String[] permission = Jwt.verifyWithPermission(token, "1");
        System.out.println(permission.length);
    }
}

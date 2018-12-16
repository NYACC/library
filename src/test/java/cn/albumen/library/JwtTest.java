package cn.albumen.library;

import cn.albumen.library.util.JwtUtil;
import org.junit.Test;

public class JwtTest {
    @Test
    public void test() {
        String token = JwtUtil.create("1", new String[]{"1", "2"});
        System.out.println(token);
        String[] permission = JwtUtil.verifyWithPermission(token, "1");
        System.out.println(permission.length);
    }
}

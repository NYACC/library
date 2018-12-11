import cn.albumenj.util.Jwt;
import org.junit.jupiter.api.Test;

public class TestJwt {
    @Test
    public void testTime(){
        for(int i = 0;i<1000;i++) {
            long startTime = System.currentTimeMillis();
            String token = Jwt.create(i+"");
            long endTime = System.currentTimeMillis();
            System.out.println(i+"次1程序运行时间： " + (endTime - startTime) + "ms");

            startTime = System.currentTimeMillis();
            boolean ret = Jwt.verify(token, i+"");
            endTime = System.currentTimeMillis();
            System.out.println(i+"次2程序运行时间： " + (endTime - startTime) + "ms");

            startTime = System.currentTimeMillis();
            ret = Jwt.verify(token, "2");
            endTime = System.currentTimeMillis();
            System.out.println(i+"次2程序运行时间： " + (endTime - startTime) + "ms");
        }
    }

    @Test
    public void test() {
        String token = Jwt.create("1");
        boolean ret = Jwt.verify(token,"1");
        System.out.println(ret);
    }
}

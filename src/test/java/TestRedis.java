import cn.albumenj.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-redis.xml")
public class TestRedis {
    @Autowired
    RedisUtil redisUtil;

    @Test
    public void redis(){
        try{
            redisUtil.set("1","1");
            System.out.println(redisUtil.get("1"));
        }catch (Exception e){

        }


    }
}

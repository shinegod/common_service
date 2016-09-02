import com.fx.cache.redis.RedisManager;

/**
 * Created by Michael on 8/31/2016.
 */
public class RedisTest {

    public static void main(String[] args) {

        RedisManager redisManager = new RedisManager();
        redisManager.setPort(6379);
        redisManager.setHost("192.168.1.217");
        redisManager.setMaxIdel(10);
        redisManager.setMaxTotal(30);
        redisManager.setMaxWaitMillis(3000);
        redisManager.setExpire(10800);
        redisManager.init();
    }

}

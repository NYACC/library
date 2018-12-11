package cn.albumenj.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Albumen
 */
public class TokenCache {
    private static TokenCache instance;

    private Map<String, Integer> tokenMap;

    private TokenCache() {
        tokenMap = new HashMap<>();
    }

    public static TokenCache getInstance() {
        if (instance == null) {
            synchronized (TokenCache.class) {
                if (instance == null) {
                    instance = new TokenCache();
                }
            }
        }
        return instance;
    }

    /**
     * 保存token与对应的用户ID
     *
     * @param token
     * @param id 用户ID
     */
    public void save(String token, Integer id) {
        tokenMap.put(token, id);
    }

    /**
     * 根据token获取用户信息(用户ID)
     * @param token
     * @return 用户ID
     */
    public Integer getPhone(String token) {
        return tokenMap.get(token);
    }
}

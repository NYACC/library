package cn.albumen.library.bean;

/**
 * @author Albumen
 */
public class BaseBean {
    private String token;
    private Integer loginedUserId;
    private Integer start;
    private Integer count;
    private Integer itemNum;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getLoginedUserId() {
        return loginedUserId;
    }

    public void setLoginedUserId(Integer loginedUserId) {
        this.loginedUserId = loginedUserId;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }
}

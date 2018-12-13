package cn.albumenj.bean;

import java.util.Date;
import java.util.UUID;

/**
 * @author Albumen
 */
public class Log {
    private String id;
    private Integer user;
    private String ip;
    private String content;
    private Date createTime;

    public Log(){
        this.id = UUID.randomUUID().toString();
        this.createTime = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

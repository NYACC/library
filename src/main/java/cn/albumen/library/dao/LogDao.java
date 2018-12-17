package cn.albumen.library.dao;

import cn.albumen.library.bean.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Albumen
 */
@Mapper
@Repository
public interface LogDao {
    /**
     * 添加日志
     *
     * @param log
     * @return
     */
    @Insert({"insert into log(id, user, ip, content, createTime, level)" +
            "   VALUES (#{id}, #{user}, #{ip}, #{content}, #{createTime}, #{level})"})
    int insert(Log log);
}

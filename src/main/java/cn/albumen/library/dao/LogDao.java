package cn.albumen.library.dao;

import cn.albumen.library.bean.Log;
import org.springframework.stereotype.Repository;

/**
 * @author Albumen
 */
@Repository
public interface LogDao {
    /**
     * 添加日志
     *
     * @param log
     * @return
     */
    int insert(Log log);
}

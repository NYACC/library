package cn.albumenj.dao;

import cn.albumenj.bean.Log;

/**
 * @author Albumen
 */
public interface LogDao {
    /**
     * 添加日志
     * @param log
     * @return
     */
    int insert(Log log);
}

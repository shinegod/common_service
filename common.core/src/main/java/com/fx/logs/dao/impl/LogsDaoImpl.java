package com.fx.logs.dao.impl;

import com.fx.logs.dao.ILogsDao;
import com.fx.logs.model.Logs;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by Michael on 8/24/2016.
 */
@Repository
public class LogsDaoImpl extends BaseDao<Logs> implements ILogsDao {

    public LogsDaoImpl() {
        super(ILogsDao.class.getName());
    }

}

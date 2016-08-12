package com.fx.crm.sys.log.dao.impl;

import com.fx.crm.sys.log.dao.IOperationLoggerDao;
import com.fx.crm.sys.log.model.OperationLogger;
import com.fx.util.Pagination;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OperationLoggerDaoImpl extends BaseDao<OperationLogger> implements IOperationLoggerDao {

    public OperationLoggerDaoImpl() {
        super(IOperationLoggerDao.class.getName());
    }

    @Override
    public List<OperationLogger> queryByEntity(Pagination<OperationLogger> pagination) {
        return findList("queryByEntity", pagination);
    }

    @Override
    public void doInsertTraderLog(OperationLogger log) {
        doInsert("insertTraderLog", log);
    }

}
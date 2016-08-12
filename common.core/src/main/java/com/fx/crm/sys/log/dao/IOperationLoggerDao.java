package com.fx.crm.sys.log.dao;

import com.fx.crm.sys.log.model.OperationLogger;
import com.fx.util.Pagination;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;

public interface IOperationLoggerDao extends IValueObjectDao<OperationLogger> {
    List<OperationLogger> queryByEntity(Pagination<OperationLogger> pagination);

    void doInsertTraderLog(OperationLogger log);
}
package com.fx.crm.sys.log.dao.impl;

import com.fx.crm.sys.log.dao.IOperationReferenceDao;
import com.fx.crm.sys.log.model.OperationReference;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class OperationReferenceDaoImpl extends BaseDao<OperationReference> implements IOperationReferenceDao {

    public OperationReferenceDaoImpl() {
        super(IOperationReferenceDao.class.getName());
    }
}
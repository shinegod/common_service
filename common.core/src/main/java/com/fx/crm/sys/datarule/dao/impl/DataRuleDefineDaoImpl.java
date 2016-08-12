package com.fx.crm.sys.datarule.dao.impl;

import com.fx.crm.sys.datarule.dao.IDataRuleDefineDao;
import com.fx.crm.sys.datarule.model.DataRuleDefine;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class DataRuleDefineDaoImpl extends BaseDao<DataRuleDefine> implements IDataRuleDefineDao {

    public DataRuleDefineDaoImpl() {
        super(IDataRuleDefineDao.class.getName());
    }
}
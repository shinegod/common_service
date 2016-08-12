package com.fx.crm.sys.datarule.dao.impl;

import com.fx.crm.sys.datarule.dao.IRoleDataRuleDao;
import com.fx.crm.sys.datarule.model.RoleDataRule;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDataRuleDaoImpl extends BaseDao<RoleDataRule> implements IRoleDataRuleDao {

    public RoleDataRuleDaoImpl() {
        super(IRoleDataRuleDao.class.getName());
    }
}
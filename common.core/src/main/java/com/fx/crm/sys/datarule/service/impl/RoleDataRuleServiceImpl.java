package com.fx.crm.sys.datarule.service.impl;

import com.fx.crm.sys.datarule.dao.IRoleDataRuleDao;
import com.fx.crm.sys.datarule.model.RoleDataRule;
import com.fx.crm.sys.datarule.service.IRoleDataRuleService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleDataRuleServiceImpl extends BaseVOService<RoleDataRule> implements IRoleDataRuleService {
    @Autowired
    private IRoleDataRuleDao roleDataRuleDao;
}
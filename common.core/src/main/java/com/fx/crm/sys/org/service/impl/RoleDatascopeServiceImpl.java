package com.fx.crm.sys.org.service.impl;

import com.fx.crm.sys.org.dao.IRoleDatascopeDao;
import com.fx.crm.sys.org.model.RoleDatascope;
import com.fx.crm.sys.org.service.IRoleDatascopeService;
import com.google.common.collect.Lists;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleDatascopeServiceImpl extends BaseVOService<RoleDatascope> implements IRoleDatascopeService {
    @Autowired
    private IRoleDatascopeDao roleDatascopeDao;

    @Override
    public List<String> findByRoleId(Integer id) {
        List<RoleDatascope> list = roleDatascopeDao.findByRoleId(id);
        List<String> orgList = Lists.newArrayList();
        for (RoleDatascope rd : list) {
            orgList.add("" + rd.getOrgId());
        }
        return orgList;
}
}
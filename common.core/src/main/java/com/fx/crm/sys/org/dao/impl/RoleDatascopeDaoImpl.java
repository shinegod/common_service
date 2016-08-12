package com.fx.crm.sys.org.dao.impl;

import com.fx.crm.sys.org.dao.IRoleDatascopeDao;
import com.fx.crm.sys.org.model.RoleDatascope;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDatascopeDaoImpl extends BaseDao<RoleDatascope> implements IRoleDatascopeDao {

    public RoleDatascopeDaoImpl() {
        super(IRoleDatascopeDao.class.getName());
    }

    @Override
    public List<RoleDatascope> findByRoleId(Integer id) {
        RoleDatascope rd = new RoleDatascope();
        rd.setRoleId(id);
        return super.findList("selectByRoleId", id);
    }
}
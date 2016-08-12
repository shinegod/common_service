package com.fx.crm.sys.org.service.impl;

import com.fx.crm.sys.org.dao.IOrganizationDao;
import com.fx.crm.sys.org.model.Organization;
import com.fx.crm.sys.org.service.IOrganizationService;
import com.fx.util.Constants;
import mybatis.framework.core.service.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrganizationServiceImpl extends BaseTreeService<IOrganizationDao, Organization> implements IOrganizationService {

    @Autowired
    private IOrganizationDao organizationDao;

    @Override
    public List<Organization> findByParentId(String parentId, String name) {
        Organization org = new Organization();
        org.setParentId(parentId);
        if(name != null && name !="") {
            org.getSqlMap().put("name", "%"+name+"%");
        }
        return getDao().findByParentId(org);
    }

    @Override
    public List<Organization> findAllByStatus(String status, String name) {
        Organization org = new Organization();
        org.getSqlMap().put("status", status);
        if(name != null && name !="") {
            org.getSqlMap().put("name", "%"+name+"%");
        }
        org.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", ""));
        return getDao().findAllByStatus(org);
    }

    @Override
    public List<Organization> findByIds(Map<String, Object> params) {
        params.put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", ""));
        return getDao().findList("findByIds",params);
    }

    @Override
    public List<Integer> getOrgIdsByName(Map<String, Object> params1) {
        return getDao().findList("getOrgIdsByName",params1);
    }

    @Override
    public int deleteByOrgId(String orgId) {
        return organizationDao.deleteByOrgId(orgId);
    }

    @Override
    public int getCountByOrgId(String orgId) {
        return organizationDao.getCountByOrgId(orgId);
    }

    @Override
    public int getCountByNameAndOrgIdAndParentId(String orgName, int orgId, int id) {
        return organizationDao.getCountByNameAndOrgIdAndParentId(orgName, orgId, id);
    }

    @Override
    public List<Organization> getAllOrgByRule() {
        Organization organization = new Organization();
        organization.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u"));
        return organizationDao.getAllOrgByRule(organization);
    }

    @Override
    public List<Organization> findByCondition() {
        return organizationDao.findByCondition();
    }

    @Override
    public List<Organization> findByOrgId(Organization s) {
        return organizationDao.findByOrgId(s);
    }

}
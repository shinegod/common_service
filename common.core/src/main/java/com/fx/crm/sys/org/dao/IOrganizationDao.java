package com.fx.crm.sys.org.dao;

import com.fx.crm.sys.org.model.Organization;
import mybatis.framework.core.dao.TreeDao;

import java.util.List;

public interface IOrganizationDao extends TreeDao<Organization> {
    public int deleteByOrgId(String orgId);

    public int getCountByOrgId(String orgId);

    List<Organization> findByParentId(String parentId);

    public List<Organization> findByParentId(Organization org);

    List<Organization> findAllByStatus(Organization org);

    public int getCountByNameAndOrgIdAndParentId(String orgName, int orgId, int id);

    public List<Organization> getAllOrgByRule(Organization organization);

    List<Organization> findByCondition();

    List<Organization> findByOrgId(Organization s);
}
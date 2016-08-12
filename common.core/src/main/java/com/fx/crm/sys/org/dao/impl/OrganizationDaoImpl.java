package com.fx.crm.sys.org.dao.impl;

import com.fx.crm.sys.org.dao.IOrganizationDao;
import com.fx.crm.sys.org.model.Organization;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrganizationDaoImpl extends BaseDao<Organization> implements IOrganizationDao {

    public OrganizationDaoImpl() {
        super(IOrganizationDao.class.getName());
    }

    @Override
    public List<Organization> findByParentIdsLike(Organization entity) {
        return findList("findByParentIdsLike", entity);
    }

    @Override
    public int updateParentIds(Organization entity) {
        return 0;
    }

    @Override
    public int deleteByOrgId(String orgId) {
        Map<String, Object> params = new HashMap<>();
        params.put("orgId", orgId);
        return super.doUpdate("deleteByOrgId", params);
    }

    @Override
    public int getCountByOrgId(String orgId) {
        Map<String, Object> params = new HashMap<>();
        params.put("orgId", orgId);
        return (Integer) findOne("getCountByOrgId", params);
    }

    @Override
    public List<Organization> findByParentId(String parentId) {
        return findList("findByParentId", Integer.valueOf(parentId));
    }

    @Override
    public List<Organization> findByParentId(Organization org) {
        return findList("findByParentIdAndName", org);
    }

    @Override
    public List<Organization> findAllByStatus(Organization org) {
        return findList("findAllByStatus", org);
    }

    @Override
    public int getCountByNameAndOrgIdAndParentId(String orgName, int orgId, int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("orgName", orgName);
        params.put("orgId", orgId);
        params.put("id", id);
        return (Integer) findOne("getCountByNameAndOrgIdAndParentId", params);
    }

    @Override
    public List<Organization> getAllOrgByRule(Organization organization) {
        return findList("getAllOrgByRule", organization);
    }

    @Override
    public List<Organization> findByCondition() {
        return findList("findByCondition", null);
    }

    @Override
    public List<Organization> findByOrgId(Organization s) {
        Map<String, Object> params = new HashMap<>();
        List<Organization> organizationList = new ArrayList<>();
        if(s.getId() == 1){
            params.put("orgId",s.getParentIds()+s.getId()+",");
            organizationList = findList("findByOrgId1", params);
        }else{
            params.put("orgId",s.getParentIds()+s.getId()+",%");
            organizationList = findList("findByOrgId", params);
        }

        return organizationList;
    }
}
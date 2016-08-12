package com.fx.crm.sys.org.service;

import com.fx.crm.sys.org.model.Organization;
import mybatis.framework.core.service.ITreeObjectService;

import java.util.List;
import java.util.Map;

public interface IOrganizationService extends ITreeObjectService<Organization> {
    List<Organization> findByParentId(String parentId, String name);

    List<Organization> findAllByStatus(String status, String name);
    public int deleteByOrgId(String orgId);

    public int getCountByOrgId(String orgId);

    public int getCountByNameAndOrgIdAndParentId(String orgName, int orgId, int id);

    public List<Organization> getAllOrgByRule();

    List<Organization> findByCondition();

    List<Organization> findByOrgId(Organization s);

    List<Organization> findByIds(Map<String, Object> params);

    List<Integer> getOrgIdsByName(Map<String, Object> params1);
}
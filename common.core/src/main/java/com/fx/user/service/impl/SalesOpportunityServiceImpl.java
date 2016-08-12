package com.fx.user.service.impl;

import com.fx.user.dao.ISalesOpportunityDao;
import com.fx.user.model.SalesOpportunity;
import com.fx.user.model.User;
import com.fx.user.service.ISalesOpportunityService;
import com.fx.util.Pagination;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalesOpportunityServiceImpl extends BaseVOService<SalesOpportunity> implements ISalesOpportunityService {
    @Autowired
    private ISalesOpportunityDao salesOpportunityDao;

    @Override
    public List<SalesOpportunity> findByUserId(Integer userId) {
        return salesOpportunityDao.findByUserId(userId);
    }

    @Override
    public PageIterator<SalesOpportunity> pageQueryByCondition(Pagination pagination) {
        Map<String, Object> params = new HashMap<String, Object>();
        String username = pagination.getSearch();

        if (StringUtils.isNotBlank(username)) {
            username = "%" + username + "%";
            params.put("cnName", username);
        }

        int totalCount = salesOpportunityDao.queryCountByCondition(params);
        int offset = pagination.getOffset();
        int pageSize = pagination.getLimit();
        int pageNo = (offset / pageSize) + 1;
        params.put("offset", offset);
        params.put("limit", pageSize);
        List<SalesOpportunity> salesOpportunityList = salesOpportunityDao.queryByCondition(params);
        PageIterator<SalesOpportunity> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
        page.setData(salesOpportunityList);
        return page;
    }
}
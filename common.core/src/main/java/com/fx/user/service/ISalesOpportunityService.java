package com.fx.user.service;

import com.fx.user.model.SalesOpportunity;
import com.fx.util.Pagination;
import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import java.util.List;

public interface ISalesOpportunityService extends IValueObjectService<SalesOpportunity> {
    public List<SalesOpportunity> findByUserId(Integer userId);

    public PageIterator<SalesOpportunity> pageQueryByCondition(Pagination pagination);
}
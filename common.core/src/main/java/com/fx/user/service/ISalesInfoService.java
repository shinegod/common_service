package com.fx.user.service;

import com.fx.user.model.SalesInfo;
import mybatis.framework.core.service.IValueObjectService;

import java.util.List;

public interface ISalesInfoService extends IValueObjectService<SalesInfo> {
    public List<SalesInfo> findByUserId(Integer id);

    public void updateIsLast(SalesInfo salesInfo);

    public void updateIsLastYes(SalesInfo salesInfo);

    public SalesInfo findByUserIdAndIsLastYes(SalesInfo salesInfo);
}
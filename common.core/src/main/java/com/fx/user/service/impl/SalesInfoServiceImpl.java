package com.fx.user.service.impl;

import com.fx.user.dao.ISalesInfoDao;
import com.fx.user.model.SalesInfo;
import com.fx.user.service.ISalesInfoService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesInfoServiceImpl extends BaseVOService<SalesInfo> implements ISalesInfoService {
    @Autowired
    private ISalesInfoDao salesInfoDao;

    @Override
    public List<SalesInfo> findByUserId(Integer id) {
        return salesInfoDao.findByUserId(id);
    }

    @Override
    public void updateIsLast(SalesInfo salesInfo) {
        salesInfoDao.doUpdate("updateIsLast",salesInfo);
    }

    @Override
    public void updateIsLastYes(SalesInfo salesInfo) {
        salesInfoDao.doUpdate("updateIsLastYes",salesInfo);
    }

    @Override
    public SalesInfo findByUserIdAndIsLastYes(SalesInfo salesInfo) {
        return (SalesInfo) salesInfoDao.findOne("findByUserIdAndIsLastYes",salesInfo);
    }
}
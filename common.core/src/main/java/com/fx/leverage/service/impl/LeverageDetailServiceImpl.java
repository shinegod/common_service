package com.fx.leverage.service.impl;

import com.fx.leverage.dao.ILeverageDetailDao;
import com.fx.leverage.model.LeverageDetail;
import com.fx.leverage.service.ILeverageDetailService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeverageDetailServiceImpl extends BaseVOService<LeverageDetail> implements ILeverageDetailService {
    @Autowired
    private ILeverageDetailDao leverageDetailDao;

    @Override
    public List<LeverageDetail> selectByMt4account(int mt4account) {
        return leverageDetailDao.selectByMt4account(mt4account);
    }

    @Override
    public List<LeverageDetail> selectByUid(int uid) {
        return leverageDetailDao.selectByUid(uid);
    }

    @Override
    public Integer countLeverage(Integer mt4account) {
        return leverageDetailDao.countLeverage(mt4account);
    }
}
package com.fx.imp.service.impl;

import com.fx.imp.dao.ICommissionSetImpDao;
import com.fx.imp.model.CommissionSetImp;
import com.fx.imp.service.ICommissionSetImpService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommissionSetImpServiceImpl extends BaseVOService<CommissionSetImp> implements ICommissionSetImpService {
    @Autowired
    private ICommissionSetImpDao commissionSetImpDao;

    @Override
    public List<CommissionSetImp> getCommGroup() {
        return commissionSetImpDao.getCommGroup();
    }

    @Override
    public List<CommissionSetImp> getCommCoefficient(CommissionSetImp imp) {
        return commissionSetImpDao.getCommCoefficient(imp);
    }
}
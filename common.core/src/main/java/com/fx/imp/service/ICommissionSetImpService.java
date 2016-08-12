package com.fx.imp.service;

import com.fx.imp.model.CommissionSetImp;
import mybatis.framework.core.service.IValueObjectService;

import java.util.List;

public interface ICommissionSetImpService extends IValueObjectService<CommissionSetImp> {
    List<CommissionSetImp> getCommGroup();

    List<CommissionSetImp> getCommCoefficient(CommissionSetImp imp);
}
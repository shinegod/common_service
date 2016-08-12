package com.fx.imp.dao;

import com.fx.imp.model.CommissionSetImp;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;

public interface ICommissionSetImpDao extends IValueObjectDao<CommissionSetImp> {

    List<CommissionSetImp> getCommGroup();

    void updateBatch(Integer groupId, CommissionSetImp commGroup);

    List<CommissionSetImp> getCommCoefficient(CommissionSetImp imp);

    void updateBatch(CommissionSetImp imp);
}
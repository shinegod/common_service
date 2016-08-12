package com.fx.imp.dao.impl;

import com.fx.imp.dao.ICommissionSetImpDao;
import com.fx.imp.model.CommissionSetImp;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommissionSetImpDaoImpl extends BaseDao<CommissionSetImp> implements ICommissionSetImpDao {

    public CommissionSetImpDaoImpl() {
        super(ICommissionSetImpDao.class.getName());
    }

    @Override
    public List<CommissionSetImp> getCommGroup() {
        return super.findList("selectGroup",null);
    }

    @Override
    public void updateBatch(Integer groupId, CommissionSetImp commGroup) {
        super.doUpdate("updateBatchByGroup", commGroup);
    }

    @Override
    public List<CommissionSetImp> getCommCoefficient(CommissionSetImp imp) {
        return super.findList("selectCommCoefficient",imp);
    }

    @Override
    public void updateBatch(CommissionSetImp imp) {
        super.doUpdate("updateBatchByGroup2", imp);
    }
}
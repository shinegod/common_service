package com.fx.crm.comm.dao.impl;

import com.fx.crm.comm.dao.ICommissionAccountDetailDao;
import com.fx.crm.comm.model.CommissionAccountDetail;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class CommissionAccountDetailDaoImpl extends BaseDao<CommissionAccountDetail> implements ICommissionAccountDetailDao {

    public CommissionAccountDetailDaoImpl() {
        super(ICommissionAccountDetailDao.class.getName());
    }
}
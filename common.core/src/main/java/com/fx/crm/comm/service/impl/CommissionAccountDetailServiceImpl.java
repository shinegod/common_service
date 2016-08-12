package com.fx.crm.comm.service.impl;

import com.fx.crm.comm.dao.ICommissionAccountDetailDao;
import com.fx.crm.comm.model.CommissionAccountDetail;
import com.fx.crm.comm.service.ICommissionAccountDetailService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommissionAccountDetailServiceImpl extends BaseVOService<CommissionAccountDetail> implements ICommissionAccountDetailService {
    @Autowired
    private ICommissionAccountDetailDao CommissionAccountDetailDao;
}
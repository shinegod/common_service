package com.fx.bonus.service;

import com.fx.bonus.model.CommissionFeeLog;
import mybatis.framework.core.service.IValueObjectService;

import java.util.HashMap;
import java.util.List;

public interface ICommissionFeeLogService extends IValueObjectService<CommissionFeeLog> {
    /**
     * 按照参数条件查询出符合的返佣手续费记录
     * @param paramTrade
     * @return
     */
    public List<CommissionFeeLog> findByCondition(HashMap<String, Object> paramTrade);
}
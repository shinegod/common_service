package com.fx.bonus.service;

import com.fx.bonus.model.IbcommissionPaymentLog;
import mybatis.framework.core.service.IValueObjectService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface IIbcommissionPaymentLogService extends IValueObjectService<IbcommissionPaymentLog> {
    /**
     * 查询指定时间内已支付返佣记录
     * @param paramCommissionLog
     * @return
     */
    public List<IbcommissionPaymentLog> findIBCommissionPaymentLogByParams(IbcommissionPaymentLog paramCommissionLog);

    public List<Integer> getMT4ListByParams(IbcommissionPaymentLog paramCommissionLog);

    public BigDecimal getTotalAmountByParams(IbcommissionPaymentLog paramCommissionLog);

    public BigDecimal getTotalApplyAmountByParams(IbcommissionPaymentLog paramCommissionLog);

    /**
     * 查找佣金申请，审核返佣记录 --
     * @param paramCommission
     * @return
     */
    public List<IbcommissionPaymentLog> getIbCommissionByCondition(HashMap<String, Object> paramCommission);

    public Integer getCountByUserIdAndMT4Account(int userId, int mt4account);
}
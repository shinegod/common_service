package com.fx.crm.cron.comm;

import java.math.BigDecimal;

/**
 * Created by bei2love@gmail.com on 15/6/10.
 */
public interface ICommissionRuleHandler {

    public BigDecimal calculate(BigDecimal handCommission, BigDecimal pipCommission);
}

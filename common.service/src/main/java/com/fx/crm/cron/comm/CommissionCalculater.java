package com.fx.crm.cron.comm;

import java.math.BigDecimal;

/**
 * Created by bei2love@gmail.com on 15/6/10.
 */
public class CommissionCalculater {

    private ICommissionRuleHandler handler ;

    private BigDecimal handCommission;

    private BigDecimal pipCommission;

    public CommissionCalculater(ICommissionRuleHandler handler, BigDecimal handCommission, BigDecimal pipCommission){
        this.handler = handler;
        this.handCommission = handCommission;
        this.pipCommission = pipCommission;
    }

    public BigDecimal calculateCommission(){
        return handler.calculate(handCommission, pipCommission);
    }
}

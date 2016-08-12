package com.fx.crm.cron.comm;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.fx.payment.exception.PayException;

/**
 * Created by bei2love@gmail.com on 15/6/1.
 */
public interface ICommCalculateService {

    /**
     * 计算指定时间段内的佣金（只生成佣金计算基数）
     * @param begin 佣金计算的开始时间，为空默认取当天的0时
     * @param end  佣金计算的结束时间，为空默认为当天的24时
     */
    public void calCommission(Date begin, Date end) throws ParseException;

    /**
     * 基于佣金计算基数计算各账户佣金
     * @param begin 佣金计算的开始时间，为空默认取当天的0时
     * @param end 佣金计算的结束时间，为空默认为当天的24时
     */
    public void calCommissionAccount(Date begin, Date end);
    
    /**
     * 佣金每月汇总
     * @param begin
     * @param end
     */
    public void calculateMonthlyIbCommission(Calendar begin) throws ParseException, PayException;
    
    public void commissionDay() throws ParseException, PayException;
    
}

package com.fx.bonus.service.impl;

import com.fx.bonus.dao.IIbcommissionPaymentLogDao;
import com.fx.bonus.model.IbcommissionPaymentLog;
import com.fx.bonus.service.IIbcommissionPaymentLogService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.HashMap;
import java.util.List;

@Service
public class IbcommissionPaymentLogServiceImpl extends BaseVOService<IbcommissionPaymentLog> implements IIbcommissionPaymentLogService {
    @Autowired
    private IIbcommissionPaymentLogDao ibcommissionPaymentLogDao;

    @Override
    public List<IbcommissionPaymentLog> findIBCommissionPaymentLogByParams(IbcommissionPaymentLog paramCommissionLog) {
        return ibcommissionPaymentLogDao.findList("findIBCommissionPaymentLogByParams",paramCommissionLog);
    }

    @Override
    public List<Integer> getMT4ListByParams(IbcommissionPaymentLog paramCommissionLog) {
        return ibcommissionPaymentLogDao.findList("getMT4ListByParams",paramCommissionLog);
    }

    @Override
    public BigDecimal getTotalAmountByParams(IbcommissionPaymentLog paramCommissionLog) {
        return (BigDecimal) ibcommissionPaymentLogDao.findOne("getTotalAmountByParams",paramCommissionLog);
    }

    @Override
    public BigDecimal getTotalApplyAmountByParams(IbcommissionPaymentLog paramCommissionLog) {
        return (BigDecimal) ibcommissionPaymentLogDao.findOne("getTotalApplyAmountByParams",paramCommissionLog);
    }

    @Override
    public Integer getCountByUserIdAndMT4Account(int userId, int mt4account) {
        Map<String, Object> params = new HashMap<>();
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = " 00:00:00";
        String end = " 23:59:59";
        // 获取当月第一天
        Calendar first = Calendar.getInstance();
        first.add(Calendar.MONTH, 0);
        first.set(Calendar.DAY_OF_MONTH, 1);
        // 获取当月最后一天
        Calendar last = Calendar.getInstance();
        last.set(Calendar.DAY_OF_MONTH, last.getActualMaximum(Calendar.DAY_OF_MONTH));
        String startStr = dayFormat.format(first.getTime());
        String endStr = dayFormat.format(last.getTime());
        Date startDate = new Date();
        Date endDate = new Date();
        try {
            startDate = timeFormat.parse(startStr + start);
            endDate = timeFormat.parse(endStr + end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        params.put("userId", userId);
        params.put("mt4account", mt4account);
        params.put("startTime", startDate);
        params.put("endTime", endDate);
        return (Integer) ibcommissionPaymentLogDao.findOne("getCountByUserIdAndMT4Account", params);
    }

    @Override
    public List<IbcommissionPaymentLog> getIbCommissionByCondition(HashMap<String, Object> paramCommission) {
        return ibcommissionPaymentLogDao.findList("getIbCommissionByCondition",paramCommission);
    }
}
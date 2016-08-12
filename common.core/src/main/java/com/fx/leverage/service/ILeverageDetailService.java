package com.fx.leverage.service;

import com.fx.leverage.model.LeverageDetail;
import mybatis.framework.core.service.IValueObjectService;

import java.util.List;

public interface ILeverageDetailService extends IValueObjectService<LeverageDetail> {

    public List<LeverageDetail> selectByMt4account(int mt4account);

    public List<LeverageDetail> selectByUid(int uid);

    public Integer countLeverage(Integer mt4account);
}
package com.fx.leverage.dao;

import com.fx.leverage.model.LeverageDetail;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;

public interface ILeverageDetailDao extends IValueObjectDao<LeverageDetail> {

    public List<LeverageDetail> selectByMt4account(int mt4account);

    public List<LeverageDetail> selectByUid(int uid);

    public Integer countLeverage(Integer mt4account);
}
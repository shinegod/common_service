package com.fx.leverage.dao.impl;

import com.fx.leverage.dao.ILeverageDetailDao;
import com.fx.leverage.model.LeverageDetail;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeverageDetailDaoImpl extends BaseDao<LeverageDetail> implements ILeverageDetailDao {

    public LeverageDetailDaoImpl() {
        super(ILeverageDetailDao.class.getName());
    }

    @Override
    public List<LeverageDetail> selectByMt4account(int mt4account) {
        return findList("selectByMt4account",mt4account);
    }

    @Override
    public List<LeverageDetail> selectByUid(int uid) {
        return findList("selectByUid",uid);
    }

    @Override
    public Integer countLeverage(Integer mt4account) {
        return (Integer)findOne("countLeverage",mt4account);
    }
}
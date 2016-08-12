package com.fx.bonus.dao.impl;

import java.util.List;
import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.IBonusDao;
import com.fx.bonus.model.Bonus;

@Repository
public class BonusDaoImpl extends BaseDao<Bonus> implements IBonusDao {

    public BonusDaoImpl() {
        super(IBonusDao.class.getName());
    }


	@Override
	public List<Bonus> findByUid(int Uid) {
		return (List<Bonus>) super.findList("findByUid", Uid);
	}


	@Override
	public List<Bonus> findByDate(String date) {
		return (List<Bonus>) super.findList("findByDate", date);
	}


	@Override
	public int bonusPaid(int id) {
		return super.update("bonusPaid", id);
	}


	@Override
	public List<Bonus> getThisMonthBonusByUid(int uid) {
		return super.findList("getThisMonthBonusByUid", uid);
	}


	
}
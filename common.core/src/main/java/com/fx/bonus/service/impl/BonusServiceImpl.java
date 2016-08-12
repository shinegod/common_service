package com.fx.bonus.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fx.MT4.vo.MT4TradeRecord;
import com.fx.bonus.dao.IBonusDao;
import com.fx.bonus.model.Bonus;
import com.fx.bonus.service.IBonusService;

import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BonusServiceImpl extends BaseVOService<Bonus> implements IBonusService {
    
	@Autowired
	private IBonusDao	bonusDao;
	
	
	@Override
	public List<Bonus> findByUid(int uid) {
		return bonusDao.findList("findByUid", uid);
	}

	@Override
	public List<Bonus> findByDate(String date) {
		return  bonusDao.findList("findByDate", date);
	}

	@Override
	public int bonusPaid(int id) {
		return bonusDao.bonusPaid(id);
	}

	@Override
	public int createBonus(Bonus bonus) {
		return bonusDao.doInsert("insert", bonus);
		
	}

	@Override
	public PageIterator<Bonus> pageQueryUnpaidBonus(int pageNo, int pageSize) {
		List<Bonus> bonusList=bonusDao.findList("selectUnpaidBonus", null);
		
		int offset = (pageNo -1) * pageSize;
		int totalCount=bonusList.size();
		PageIterator<Bonus> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		List<Bonus> resList;

		if (offset+pageSize>totalCount)
			resList=new ArrayList<Bonus>(bonusList.subList(offset, totalCount));     //只用sublist不会创建新的内存空间，必须new一个
		else 
			resList=new ArrayList<Bonus>(bonusList.subList(offset, offset+pageSize));
		page.setData(resList);
		return page;
	}

	@Override
	public PageIterator<Bonus> pageQueryByCondition(int pageNo, int pageSize,Map<String, Object> queryConditionMap) {
		List<Bonus> bonusList=bonusDao.findList("findByConditions", queryConditionMap);
		int offset = (pageNo -1) * pageSize;
		int totalCount=bonusList.size();
		PageIterator<Bonus> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		List<Bonus> resList;

		if (offset+pageSize>totalCount)
			resList=new ArrayList<Bonus>(bonusList.subList(offset, totalCount));     //只用sublist不会创建新的内存空间，必须new一个
		else 
			resList=new ArrayList<Bonus>(bonusList.subList(offset, offset+pageSize));
		page.setData(resList);
		return page;
	}

	@Override
	public List<Bonus> getThisMonthBonusByUid(int uid) {
		return bonusDao.getThisMonthBonusByUid(uid);
	}
    
}
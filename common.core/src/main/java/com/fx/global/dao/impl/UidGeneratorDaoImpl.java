package com.fx.global.dao.impl;


import com.fx.global.dao.IUidGeneratorDao;
import com.fx.global.model.UidGenerator;

import mybatis.framework.core.dao.BaseDao;
import mybatis.framework.util.DBContants;

import org.springframework.stereotype.Repository;

@Repository
public class UidGeneratorDaoImpl extends BaseDao<UidGenerator> implements IUidGeneratorDao {

    public UidGeneratorDaoImpl() {
        super(DBContants.DB_GLOBAL, IUidGeneratorDao.class.getName());
    }

	@Override
	public int selectNextIdLive(int companyId) {
		
		return (Integer) super.selectOne("selectNextIdLive",companyId);
	}

	@Override
	public int selectNextIdDemo(int companyId) {
		return (Integer) super.selectOne("selectNextIdDemo",companyId);
	}

	@Override
	public void updateNextIdLive(int companyId,int nextId) {
		UidGenerator ug=new UidGenerator();
		ug.setCompanyId(companyId);
		ug.setNextIdLive(nextId);
		super.update("updateNextIdLive", ug);
		
	}

	@Override
	public void updateNextIdDemo(int companyId,int nextId) {
		UidGenerator ug=new UidGenerator();
		ug.setCompanyId(companyId);
		ug.setNextIdDemo(nextId);
		super.update("updateNextIdDemo",ug);
		
	}
}
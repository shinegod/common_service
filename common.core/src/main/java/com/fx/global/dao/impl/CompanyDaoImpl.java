package com.fx.global.dao.impl;

import org.springframework.stereotype.Repository;

import mybatis.framework.core.dao.BaseDao;
import mybatis.framework.util.DBContants;

import com.fx.global.dao.ICompanyDao;
import com.fx.global.model.Company;


@Repository
public class CompanyDaoImpl extends BaseDao<Company> implements ICompanyDao {
	
	
	public CompanyDaoImpl() {
	        super(DBContants.DB_GLOBAL,ICompanyDao.class.getName());
	    }


	@Override
	public Company findCompanyByID(int id) {
		return (Company) super.findOne("findCompanyById", id);
	}

		

}

package com.fx.global.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.global.dao.ICompanyDao;
import com.fx.global.model.Company;
import com.fx.global.service.ICompanyService;

import mybatis.framework.core.service.BaseVOService;

@Service
public class CompanyServiceImpl extends BaseVOService<Company> implements ICompanyService{
	
	@Autowired
	private ICompanyDao companyDao;
	
	@Override
	public Company findCompanyByID(int id) {
		return companyDao.findCompanyByID(id);
	}

	
}

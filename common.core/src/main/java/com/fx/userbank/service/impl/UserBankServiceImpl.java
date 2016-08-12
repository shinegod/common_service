package com.fx.userbank.service.impl;

import java.util.List;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.userbank.dao.IUserBankDao;
import com.fx.userbank.model.UserBank;
import com.fx.userbank.service.IUserBankService;

@Service
public class UserBankServiceImpl extends BaseVOService<UserBank> implements IUserBankService {
    @Autowired
    private IUserBankDao userBankDao;

	@Override
	public UserBank getUserBankByCondition(UserBank userBank) {
		// TODO Auto-generated method stub
		List<UserBank> userbanks =userBankDao.findList("selectUserBankInfo", userBank);
	    UserBank user=null;
		if(userbanks !=null && !userbanks.isEmpty())
			user= userbanks.get(0);
	  return user;
	}

	@Override
	public void upateById(UserBank ubank) {
		 userBankDao.doInsert("updateUBankInfo", ubank);		
	}
}
package com.fx.userbank.service;

import com.fx.userbank.model.UserBank;
import mybatis.framework.core.service.IValueObjectService;

public interface IUserBankService extends IValueObjectService<UserBank> {
	
	public UserBank  getUserBankByCondition(UserBank userBank);

	public void upateById(UserBank ubank);
	
}
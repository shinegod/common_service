package com.fx.payment.service;

import java.util.List;

import com.fx.bonus.model.Bonus;
import com.fx.payment.exception.PayException;
import com.fx.payment.model.UserBankCard;

import mybatis.framework.core.service.IValueObjectService;

public interface IBonusPaymentService extends IValueObjectService<UserBankCard> {
	
	public int bonusToMT4Account(Bonus bonus,int mt4Account) throws PayException;
}
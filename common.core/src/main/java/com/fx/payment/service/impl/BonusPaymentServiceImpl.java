package com.fx.payment.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.fx.MT4.enums.QueryMtT4GroupIdEnum;
import com.fx.MT4.util.MT4DepositUtil;
import com.fx.bonus.dao.IBonusDao;
import com.fx.bonus.enums.BonusEnum;
import com.fx.bonus.model.Bonus;
import com.fx.payment.dao.IUserBankCardDao;
import com.fx.payment.exception.PayException;
import com.fx.payment.model.UserBankCard;
import com.fx.payment.service.IBonusPaymentService;
import com.fx.payment.service.IUserBankCardService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BonusPaymentServiceImpl extends BaseVOService<UserBankCard> implements IBonusPaymentService {
    @Autowired
    private IBonusDao bonusDao;

	@Override
	public int bonusToMT4Account(Bonus bonus, int mt4Account) throws PayException {
		
		if (bonus.getIsPaid()==BonusEnum.Paid)
			return -1;
		int res=MT4DepositUtil.deposit(mt4Account, bonus.getAmount().setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue(), "Bonus Payment #"+bonus.getId(), QueryMtT4GroupIdEnum.LIVE,null);
		
		return res;
	}
}
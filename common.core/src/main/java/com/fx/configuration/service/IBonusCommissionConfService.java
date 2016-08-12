package com.fx.configuration.service;

import java.math.BigDecimal;

import com.fx.MT4.enums.MT4GroupIdEnum;
import com.fx.configuration.model.BonusCommissionConf;

import mybatis.framework.core.service.IValueObjectService;

public interface IBonusCommissionConfService extends IValueObjectService<BonusCommissionConf> {
	
	public BigDecimal getBonusCommissionConfByMT4Group(MT4GroupIdEnum groupEnum);
}
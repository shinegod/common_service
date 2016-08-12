package com.fx.configuration.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.MT4.enums.MT4GroupIdEnum;
import com.fx.configuration.dao.IBonusCommissionConfDao;
import com.fx.configuration.model.BonusCommissionConf;
import com.fx.configuration.service.IBonusCommissionConfService;

@Service
public class BonusCommissionConfServiceImpl extends BaseVOService<BonusCommissionConf> implements IBonusCommissionConfService {
    @Autowired
    private IBonusCommissionConfDao bonusCommissionConfDao;

	@Override
	public BigDecimal getBonusCommissionConfByMT4Group(MT4GroupIdEnum groupEnum) {
		Map<String,Object> parameter = new HashMap<String, Object>();
		parameter.put("mt4GroupCode", groupEnum.getValue());
		BonusCommissionConf vo = (BonusCommissionConf)bonusCommissionConfDao.findOne("getBonusCommissionConfByMT4Group", parameter);
		BigDecimal commission = new BigDecimal(0);
		if (vo != null) {
			commission = vo.getBonusCommission();
		}
		return commission;
	}
}
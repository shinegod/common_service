package com.fx.user.service.impl;

import com.fx.user.dao.IMT4TransferLogDao;
import com.fx.user.model.MT4TransferLog;
import com.fx.user.service.IMT4TransferLogService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MT4TransferLogServiceImpl extends BaseVOService<MT4TransferLog> implements IMT4TransferLogService {
    @Autowired
    private IMT4TransferLogDao mT4TransferLogDao;

	@Override
	public MT4TransferLog getByMt4Id(int mt4TransferId) {
		return mT4TransferLogDao.getByMt4Id(mt4TransferId);
	}
}
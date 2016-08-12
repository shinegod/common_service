package com.fx.user.service;

import com.fx.user.model.MT4TransferLog;
import com.fx.user.model.User;

import mybatis.framework.core.service.IValueObjectService;

public interface IMT4TransferLogService extends IValueObjectService<MT4TransferLog> {
	public MT4TransferLog getByMt4Id(int mt4TransferId);
}
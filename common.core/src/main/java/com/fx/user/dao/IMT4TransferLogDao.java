package com.fx.user.dao;

import com.fx.user.model.MT4TransferLog;
import mybatis.framework.core.dao.IValueObjectDao;

public interface IMT4TransferLogDao extends IValueObjectDao<MT4TransferLog> {
	public MT4TransferLog getByMt4Id(int mt4TransferId);
}
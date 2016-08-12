package com.fx.user.dao.impl;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.user.dao.IMT4TransferLogDao;
import com.fx.user.model.MT4TransferLog;

@Repository
public class MT4TransferLogDaoImpl extends BaseDao<MT4TransferLog> implements IMT4TransferLogDao {

    public MT4TransferLogDaoImpl() {
        super(IMT4TransferLogDao.class.getName());
    }

	@Override
	public MT4TransferLog getByMt4Id(int mt4TransferId) {
		return (MT4TransferLog)super.findOne("getByMt4Id", mt4TransferId);
	}
}
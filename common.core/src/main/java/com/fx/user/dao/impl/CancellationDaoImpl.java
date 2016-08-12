package com.fx.user.dao.impl;

import java.util.List;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.user.dao.ICancellationDao;
import com.fx.user.model.Cancellation;

@Repository
public class CancellationDaoImpl extends BaseDao<Cancellation> implements ICancellationDao {

    public CancellationDaoImpl() {
        super(ICancellationDao.class.getName());
    }

	@Override
	public List<Cancellation> getByUid(int uid) {
		return (List<Cancellation>)super.findOne("getByUid", uid);
	}
}
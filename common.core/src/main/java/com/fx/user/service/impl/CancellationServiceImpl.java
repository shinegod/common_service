package com.fx.user.service.impl;

import java.util.List;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.user.dao.ICancellationDao;
import com.fx.user.model.Cancellation;
import com.fx.user.service.ICancellationService;

@Service
public class CancellationServiceImpl extends BaseVOService<Cancellation> implements ICancellationService {
    @Autowired
    private ICancellationDao cancellationDao;

	@Override
	public List<Cancellation> getByUid(int uid) {
		return (List<Cancellation>)cancellationDao.getByUid(uid);
	}
}
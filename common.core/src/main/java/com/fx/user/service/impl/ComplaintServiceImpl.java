package com.fx.user.service.impl;

import java.util.List;

import com.fx.user.dao.IComplaintDao;
import com.fx.user.model.Complaint;
import com.fx.user.service.IComplaintService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintServiceImpl extends BaseVOService<Complaint> implements IComplaintService {
    @Autowired
    private IComplaintDao complaintDao;

	@Override
	public List<Complaint> getByUid(int uid) {
		return (List<Complaint>)complaintDao.getByUid(uid);
	}
}
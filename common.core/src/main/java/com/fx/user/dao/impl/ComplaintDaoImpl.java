package com.fx.user.dao.impl;

import java.util.List;

import com.fx.user.dao.IComplaintDao;
import com.fx.user.model.Complaint;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class ComplaintDaoImpl extends BaseDao<Complaint> implements IComplaintDao {

    public ComplaintDaoImpl() {
        super(IComplaintDao.class.getName());
    }

	@Override
	public List<Complaint> getByUid(int uid) {
		return  (List<Complaint>)super.findOne("getByUid", uid);
	}
}
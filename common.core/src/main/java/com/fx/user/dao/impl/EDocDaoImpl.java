package com.fx.user.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.user.dao.IEDocDao;
import com.fx.user.model.EDoc;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class EDocDaoImpl extends BaseDao<EDoc> implements IEDocDao {

    public EDocDaoImpl() {
        super( IEDocDao.class.getName());
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<EDoc> getByUid(int uid) {
		return (List<EDoc>) findList("getByUid", uid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EDoc> getByUidList(List<Integer> uidList) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uidList", uidList);
		return super.findList("getByUidList", params);
	}

	@Override
	public List<EDoc> getByUidAndType(Map map) {
		return findList("getByUidAndType",map);
	}

	@Override
	public List<EDoc> findListByUserId(Integer userId) {
		return findList("edocsByUserId",userId);
	}
	
	
	
	
	
	
}
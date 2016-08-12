package com.fx.user.service.impl;

import com.fx.user.dao.IEDocDao;
import com.fx.user.model.EDoc;
import com.fx.user.service.IEDocService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EDocServiceImpl extends BaseVOService<EDoc> implements IEDocService {
    @Autowired
    private IEDocDao eDocDao;

	@Override
	public List<EDoc> getByUid(int uid) {
		return eDocDao.getByUid(uid);
	}
	
	@Override
	public List<EDoc> getByUidList(List<Integer> uidList) {
		if(null == uidList || uidList.size() <= 0){
			return null;
		}
		return eDocDao.getByUidList(uidList);
	}

	@Override
	public List<EDoc> getByUidAndType(Map map) {
		return eDocDao.getByUidAndType(map);
	}
  
	@Override
	public List<EDoc> findListByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return eDocDao.findListByUserId(userId);
	}

    @Override
    public List<EDoc> findOtherFilesByUserId(int userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        return eDocDao.findList("findOtherFilesByUserId", params);
    }

	@Override
	public EDoc findEdocByUserIdAndTypeId(int userId, int edocTypeId) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("edocTypeId", edocTypeId);
		return (EDoc) eDocDao.findOne("findEdocByUserIdAndTypeId", params);
	}

	@Override
	public int doUpdateByUserIdAndTypeId(int userId, int typeId){
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("edocTypeId", typeId);
		return eDocDao.doUpdate("doUpdateByUserIdAndTypeId", params);
	}

	@Override
	public int doDeleteOldByUserIdAndTypeId(int userId, int typeId){
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("edocTypeId", typeId);
		return eDocDao.doUpdate("doDeleteOldByUserIdAndTypeId", params);
	}

	@Override
	public int doDeleteAllByUserIdAndTypeId(int userId, int typeId){
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("edocTypeId", typeId);
		return eDocDao.doUpdate("doDeleteAllByUserIdAndTypeId", params);
	}

	@Override
	public List<EDoc> findEdocsByUserIdAndType(int userId, int type){
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("edocTypeId", type);
		return eDocDao.findList("findEdocsByUserIdAndType", params);
	}

	@Override
	public int doUpdateCheckPassByFilePath(String filePath){
		return eDocDao.doUpdate("doUpdateCheckPassByFilePath", filePath);
	}
	@Override
	public int doDeleteByFilePath(String filePath){
		return eDocDao.doDelete("doDeleteByFilePath",filePath);
	}
}
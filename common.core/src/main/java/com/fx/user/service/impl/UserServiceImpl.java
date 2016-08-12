package com.fx.user.service.impl;

import com.fx.user.dao.IUserDao;
import com.fx.user.model.User;
import com.fx.user.service.IUserService;
import com.fx.util.Constants;
import com.fx.util.DateUtil;
import com.fx.util.Pagination;
import com.fx.util.UserUtil;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends BaseVOService<User> implements IUserService {
    @Autowired
    private IUserDao userDao;

	@Override
	public User getByUid(int uid) {
		return userDao.getByUid(uid);
	}
	
	public PageIterator<User> pageQueryByCondition(int countryCode, int status, int pageNo, int pageSize){
		Map<String, Object> params = new HashMap<String, Object>();
		if(countryCode > 0){
			params.put("countryCode", countryCode);
		}
		if(status > 0){
			params.put("status", status);
		}
		int totalCount = userDao.queryCountByCondition(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<User> userList = userDao.queryByCondition(params);
		PageIterator<User> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userList);
		return page;
	}

	public PageIterator<User> pageQueryByCondition(int countryCode, int status, Pagination pagination){
		Map<String, Object> params = new HashMap<String, Object>();
		if(countryCode > 0){
			params.put("countryCode", countryCode);
		}
		if(status > 0){
			params.put("status", status);
		}

		String username = pagination.getSearch();

		if (StringUtils.isNotBlank(username)) {
			username = "%" + username + "%";
			params.put("cnName", username);
		}

		int totalCount = userDao.queryCountByCondition(params);
		int offset = pagination.getOffset();
		int pageSize = pagination.getLimit();
		int pageNo = (offset / pageSize) + 1;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<User> userList = userDao.queryByCondition(params);
		PageIterator<User> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userList);
		return page;
	}

	public List<User> pageQueryByCondition(int countryCode, int status, Pagination pagination, int is_ib,int support){
        Map<String, Object> params = new HashMap<String, Object>();
        
        User user = new User();
        if(status > 0){
            params.put("status", status);
        }
        user.setStatus(status);
        if (is_ib > 0) {
            params.put("is_ib", is_ib);
        }
        user.setIs_ib(is_ib);

		if(pagination.getIbId_all() != -1 && pagination.getIbId()==-2) {
			user.setIb_Id(pagination.getIbId_all());
		} else if(pagination.getIbId()== -1) {
			int ibId = pagination.getIbId();
			user.setIb_Id(ibId);
		} else{
			int ibId = pagination.getIbId();

			user.setIb_Id(ibId);
			user.getSqlMap().put("flagIbid", 1);
		}


        
        int dataSource = pagination.getDataSource();
        if (dataSource > 0) {
            params.put("dataSouce", dataSource);
            user.getSqlMap().put("dataSouce", dataSource);
        }

		Integer ibUserType = pagination.getIbUserType();
		if(ibUserType != null && ibUserType >= 0) {
			user.getSqlMap().put("ibUserType", ibUserType);
		}

		Integer commissionType = pagination.getCommissionType();
		if(commissionType != null && commissionType >= 0) {
			user.getSqlMap().put("commissionType", commissionType);
		}
		if(support ==-1){
			String username = pagination.getSearch();
			if (StringUtils.isNotBlank(username)) {
				username = "%" + username + "%";
				params.put("email", username);
				user.getSqlMap().put("username", username);
			}
			if (StringUtils.isNotBlank(username)) {
				username = "%" + username + "%";
				params.put("cnName", username);
			}
		}else{
			user.getSqlMap().put("support",support);
			String username = pagination.getSearch();
			if (StringUtils.isNotBlank(username)) {
				params.put("email", username);
				user.getSqlMap().put("username", username);
			}
			if (StringUtils.isNotBlank(username)) {
				params.put("cnName", username);
			}
		}

        user.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u1"));
        
//        int totalCount = userDao.queryCountByCondition1(user);
        int offset = pagination.getOffset();
        int pageSize = pagination.getLimit();
//        int pageNo = (offset / pageSize) + 1;
//        params.put("offset", offset);
//        params.put("limit", pageSize);
//        user.getSqlMap().put("offset", offset);
//        user.getSqlMap().put("limit", pageSize);
		//获取第pagination.getOffset()/pagination.getLimit() + 1页， pagination.getLimit()条内容，默认查询总数count
	//	PageHelper.startPage((pagination.getOffset() / pagination.getLimit() + 1), pagination.getLimit());
        user.getSqlMap().put("offset", offset);
        user.getSqlMap().put("limit", pageSize);
        List<User> userList = userDao.queryByCondition1(user);
//        PageIterator<User> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
//        page.setData(userList);
        return userList;
	}

	@Override
	public List<User> getParentUsersByRoleId(int roleId) {
		return userDao.getParentUsersByRoleId(roleId);
	}

	@Override
	public List<User> getUserByRoleId(HashMap<String, Object> roleIdMap) {
		return userDao.getIBIdUser(roleIdMap);
	}

	@Override
	public int queryEmailRepeat(String email) {
		return userDao.queryEmailRepeat(email);
	}

	@Override
	public List<User> getByIsIB(int is_ib) {
		return userDao.getIBUser(is_ib);
	}

	@Override
	public PageIterator<User> pageQueryByConditionByIsIb(int is_ib, int countryCode,
			int status, int pageNo, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("is_ib", is_ib);
		if(countryCode > 0){
			params.put("countryCode", countryCode);
		}
		if(status > 0){
			params.put("status", status);
		}
		int totalCount = userDao.queryCountByConditionByIsIB(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<User> userList = userDao.pageQueryByConditionByIsIb(params);
		PageIterator<User> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userList);
		return page;
	}

	@Override
	public PageIterator<User> pageQueryByConditionByIsIbIbID(int ib_Id,
			int is_ib, int countryCode, int status, int pageNo, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("is_ib", is_ib);
		params.put("ib_Id", ib_Id);
		if(countryCode > 0){
			params.put("countryCode", countryCode);
		}
		if(status > 0){
			params.put("status", status);
		}
		int totalCount = userDao.queryCountByConditionByIsIB(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<User> userList = userDao.pageQueryByConditionByIsIb(params);
		PageIterator<User> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userList);
		return page;
	}

	@Override
	public PageIterator<User> pageQueryByConditionByIbID(int ib_Id,
			int countryCode, int status, int pageNo, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ib_Id", ib_Id);
		if(countryCode > 0){
			params.put("countryCode", countryCode);
		}
		if(status > 0){
			params.put("status", status);
		}
		int totalCount = userDao.queryCountByConditionByIbId(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<User> userList = userDao.pageQueryByConditionByIbId(params);
		PageIterator<User> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userList);
		return page;
	}

	@Override
	public List<User> getByIBId(int ib_id) {
		
		return userDao.getIBIdUser(ib_id);
	}

	@Override
	public List<User> getByIBIdIsIb(int ib_id, int is_ib) {
		return userDao.getIBIdIBUser(ib_id, is_ib);
	}

	@Override
	public void modifyIbUserTypeById(int id, int ibUserType) {
		userDao.modifyIbUserTypeById(id,ibUserType);
	}

    @Override
    public void deleteById(User user) {
        userDao.deleteById(user);
    }

	@Override
	public int pageCountByCondition(int countryCode, int status, Pagination pagination, int is_ib,int support){
		Map<String, Object> params = new HashMap<String, Object>();
        
        User user = new User();
        if(status > 0){
            params.put("status", status);
        }
        user.setStatus(status);
        if (is_ib > 0) {
            params.put("is_ib", is_ib);
        }
        user.setIs_ib(is_ib);

		if(pagination.getIbId_all() != -1 && pagination.getIbId()==-2) {
			user.setIb_Id(pagination.getIbId_all());
		} else if(pagination.getIbId()== -1) {
			int ibId = pagination.getIbId();
			user.setIb_Id(ibId);
		} else{
			int ibId = pagination.getIbId();

			user.setIb_Id(ibId);
			user.getSqlMap().put("flagIbid", 1);
		}
       /* int ibId = pagination.getIbId();

        user.setIb_Id(ibId);
		if(ibId == 0 || ibId == UserUtil.getCurrAdmin().getUserId()) {
			user.getSqlMap().put("flagIbid", 1);
		}*/

        int dataSource = pagination.getDataSource();
        if (dataSource > 0) {
            user.getSqlMap().put("dataSouce", dataSource);
        }
		Integer ibUserType = pagination.getIbUserType();
		if(ibUserType != null && ibUserType >= 0) {
			user.getSqlMap().put("ibUserType", ibUserType);
		}

		Integer commissionType = pagination.getCommissionType();
		if(commissionType != null && commissionType >= 0) {
			user.getSqlMap().put("commissionType", commissionType);
		}
		if(support ==-1){
			String username = pagination.getSearch();
			if (StringUtils.isNotBlank(username)) {
				username = "%" + username + "%";
				user.getSqlMap().put("username", username);
			}
		}else{
			user.getSqlMap().put("support",support);
			String username = pagination.getSearch();
			if (StringUtils.isNotBlank(username)) {
				user.getSqlMap().put("username", username);
			}
		}

        user.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u1"));
        int offset = pagination.getOffset();
        int pageSize = pagination.getLimit();
        user.getSqlMap().put("offset", offset);
        user.getSqlMap().put("limit", pageSize);
       return userDao.queryCountByCondition1(user);
	}

	@Override
	public void updateIbidByUserIdList(List<String> idArray, Integer agent) {
		User user = new User();
		user.getSqlMap().put("ids", idArray);
		user.setIb_Id(agent);
		user.setUpdateUser("" + UserUtil.getCurrAdmin().getUserId());
		user.setUpdateTime(DateUtil.getCurrentTime());
		userDao.updateIbidByUserIdList(user);
	}

}
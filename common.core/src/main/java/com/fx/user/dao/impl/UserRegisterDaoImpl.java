package com.fx.user.dao.impl;

import com.fx.user.dao.IUserRegisterDao;
import com.fx.user.model.UserRegister;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRegisterDaoImpl extends BaseDao<UserRegister> implements IUserRegisterDao {

    public UserRegisterDaoImpl() {
        super( IUserRegisterDao.class.getName());
    }

	@Override
	public UserRegister getDemoByEmail(String email,int mt4dataSourceType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		params.put("mt4dataSourceType", mt4dataSourceType);
		return (UserRegister) super.findOne("getByDemoEmail", params);
	}

	@Override
	public List<UserRegister> getByIdList(List<Integer> idList) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idList", idList);
		return super.findList("getByIdList", params);
	}

	@Override
	public UserRegister getLiveByEmail(String email) {
		return (UserRegister) super.findOne("getByLiveEmail", email);
	}

	@Override
	public int queryCountIbByCondition(Map<String, Object> params) {
		return (Integer)findOne("queryCountIbByCondition", params);
	}
	@Override
	public List<UserRegister> pageQueryIbByCondition(Map<String, Object> params) {
		return findList("pageQueryIbByCondition", params);
	}
	@Override
	public List<UserRegister> getIBUserRegister(int websiteUserType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("websiteUserType", websiteUserType);
		return findList("getIBUserRegister", params);
	}
	@Override
	public int queryCountDemoByCondition(Map<String, Object> params) {
		return (Integer)findOne("queryCountDemoByCondition", params);
	}
	@Override
	public List<UserRegister> pageQueryDemoByCondition(
			Map<String, Object> params) {
		return findList("pageQueryDemoByCondition", params);
	}

	@Override
	public int queryRegEmailRepeat(String email, int dataSourceType) {
		Map<String, Object> params = new HashMap<>();
		params.put("email", email);
		params.put("dataSourceType", dataSourceType);
		return (Integer) findOne("queryRegEmailRepeat", params);
	}

	@Override
	public int queryRegenNameRepeat(String enName) {
		return (Integer)findOne("queryRegenNameRepeat",enName);
	}

	@Override
	public int queryCountByCondition(UserRegister userRegister){
		return (Integer)findOne("pageQueryCount", userRegister);
	}

	@Override
	public List<UserRegister> queryByCondition(UserRegister userRegister) {
		return findList("pageQueryList", userRegister);
	}

	@Override
	public void deleteById(UserRegister userRegister) {
		super.doUpdate("deleteById",userRegister);
	}

	@Override
	public void modifyIbUserTypeById(int id, int ibUserType) {
		Map map = new HashMap();
		map.put("id",id);
		map.put("ibUserType",ibUserType);
		super.doUpdate("modifyIbUserTypeById",map);
	}

    @Override
    public UserRegister selectUserRegister(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return (UserRegister)findOne("selectUserRegister", params);
    }

	@Override
	public int selectCountByIsIbId(int isIbId) {
		return (Integer)findOne("selectCountByIsIbId",isIbId);
	}

	@Override
	public UserRegister getLiveByEmailAndMt4DataSourceType(String userName, Integer mt4dataSourceType) {
		Map<String, Object> params = new HashMap<>();
		params.put("email", userName);
		params.put("mt4dataSourceType", mt4dataSourceType);
		return (UserRegister)findOne("getLiveByEmailAndMt4DataSourceType", params);
	}

	@Override
	public List<UserRegister> getAllSales() {
		return findList("getAllSales", null);
	}

	@Override
	public List<UserRegister> getParentUsersByRoleIdAndDataSourceId(int roleId, int dataSourceId) {
		Map<String, Object> params = new HashMap<>();
		params.put("roleId", roleId);
		params.put("dataSourceId", dataSourceId);
		return findList("getParentUsersByRoleIdAndDataSourceId", params);
	}

	@Override
	public List<UserRegister> getUpperIBAndSalesByDataSource(int dataSourceId) {
		Map<String, Object> params = new HashMap<>();
		params.put("dataSourceId", dataSourceId);
		return findList("getUpperIBAndSalesByDataSource", params);
	}

    @Override
    public List<UserRegister> getIBUsersByIbId(int ibId) {
        Map<String, Object> params = new HashMap<>();
        params.put("ibId", ibId);
        return findList("getIBUsersByIbId", params);
    }

	@Override
	public UserRegister getById(Integer uid, Integer dataSourceId) {
		Map<String, Object> params = new HashMap<>();
        params.put("uid", uid);
		params.put("dataSourceId",dataSourceId);
		return (UserRegister)findOne("getById", params);
	}

	@Override
	public List<UserRegister> getAllUsers() {
		Map<String, Object> params = new HashMap<>();
		return findList("getAllUsers",params);
	}

    @Override
    public List<UserRegister> getAllIBUserByDataSource(int dataSource) {
        Map<String, Object> params = new HashMap<>();
        params.put("dataSource", dataSource);
        return findList("getAllIBUserByDataSource", params);
    }

	@Override
	public List<UserRegister> getUserRegister(UserRegister userRegister) {
		return findList("getUserRegister",userRegister);
	}

	@Override
	public List<UserRegister> findUsersByOrgId(UserRegister user) {
		return findList("findUserByOrgId", user);
	}

	@Override
	public List<UserRegister> getIBByParentId(UserRegister user) {
		return findList("findIBByParentId", user);
	}

    @Override
    public UserRegister getLiveByEmailAndMt4DataSourceId(String email, int mt4dataSourceId) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("mt4dataSourceId", mt4dataSourceId);
        return (UserRegister)findOne("getLiveByEmailAndMt4DataSourceId", params);
    }
	@Override
	public Integer getUserRegisterCount(UserRegister userRegister){
		return (Integer)findOne("getUserRegisterCount", userRegister);
	}

    @Override
    public List<UserRegister> customersExportDownload(UserRegister userRegister) {
        return findList("customersExportDownload", userRegister);
    }

	@Override
	public List<UserRegister> findNoIb() {
		return findList("findNoIb",null);
	}

    @Override
    public List<UserRegister> getExperienceAct(UserRegister userRegister) {
        return findList("getExperienceAct", userRegister);
    }

    @Override
    public int getExperienceActCount(UserRegister userRegister) {
        return (Integer) findOne("getExperienceActCount", userRegister);
    }

	@Override
	public UserRegister selectById(Integer id) {
		return (UserRegister)findOne("selectById", id);
	}

	@Override
	public List<Integer> findUidsByCondition(UserRegister userRegister) {
		return super.findList("findUidsByCondition", userRegister);
	}

	@Override
	public List<Integer> findUidsByConditionTrader(UserRegister userRegister) {
		return super.findList("findUidsByConditionTrader", userRegister);
	}

	@Override
	public List<UserRegister> selectSalesRepeat(Map parms) {
		return super.findList("selectSalesRepeat", parms);
	}

    @Override
    public List<UserRegister> getAllExpUsers() {
        Map<String, Object> params = new HashMap<>();
        return findList("getAllExpUsers", params);
    }

    @Override
	public List<UserRegister> getbyUids(HashMap<String, Object> params) {
		return findList("getbyUids", params);
	}

	@Override
	public List<UserRegister> selectIBAndDemoAndPersonal(Map parms) {
		return findList("selectIBAndDemoAndPersonal",parms);
	}

	@Override
	public List<UserRegister> getUidsByCondition(Map<String, Object> params) {
		return findList("getUidsByCondition",params);
	}

	@Override
	public List<UserRegister> getByIBId(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ibId",id);
		return findList("getByIBId",params);
	}

	@Override
	public List<UserRegister> getByNameId(HashMap<String, Object> params) {
		return findList("getByNameId",params);
	}

	@Override
	public List<UserRegister> getUserRegisterByName(Map<String, Object> params) {
		return findList("getUserRegisterByName",params);
	}

	@Override
	public List<UserRegister> getURByName(Map<String, Object> param) {
		return findList("getURByName",param);
	}
	@Override
	public List<UserRegister> findUserIdBySqlmapConditions(Map<String, Object> param) {
		return findList("findUserIdBySqlmapConditions",param);
	}

	@Override
	public List<Integer> findUserIdByLevelPathisOne(Map<String, Object> param) {
		return findList("findUserIdByLevelPathisOne", param);
	}

	@Override
	public List<Integer> findUserRegisterByCname(Map<String, Object> param) {
		return findList("findUserRegisterByCname", param);
	}
	@Override
	public List<UserRegister> getUserByIbNameAndAgentName(Map<String, String> params) {
		return findList("findUserByIbNameAndAgentName", params);
	}

	@Override
	public List<UserRegister> findCustomersByUpper(UserRegister userRegister) {
		return findList("findCustomersByUpper", userRegister);
	}

	@Override
	public int getUserRegisterCountBystus(UserRegister userRegister) {
		return (Integer)findOne("getUserRegisterCountBystus", userRegister);
	}

	@Override
	public List<UserRegister> getUserRegisterStatus(UserRegister userRegister) {
		return findList("getUserRegisterStatus",userRegister);
	}

	@Override
	public List<UserRegister> findUserByEntity(UserRegister userRegister) {
		return findList("findUserByEntity",userRegister);
	}

	@Override
	public List<UserRegister> findInnerUserByEntity(UserRegister userRegister) {
		return findList("findInnerUserByEntity",userRegister);
	}

	@Override
	public List<UserRegister> getLeadsExist(UserRegister userRegister) {
		return findList("getLeadsExist",userRegister);
	}

	@Override
	public List<UserRegister> getByIdListAndDatasource(Map<String, Object> param) {
		return findList("getByIdListAndDatasource",param);
	}

	@Override
	public List<UserRegister> getUserRegisterByWebSiteType(List<Integer> idList){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idList", idList);
		return findList("getUserRegisterByWebSiteType",params);

	}


}
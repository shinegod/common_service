package com.fx.crm.comm.service.impl;

import java.text.ParseException;
import com.fx.crm.comm.dao.ICommissionBaseDao;
import com.fx.crm.comm.model.CommissionBase;
import com.fx.crm.comm.service.ICommissionBaseService;
import com.fx.util.Constants;
import com.fx.util.Pagination;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CommissionBaseServiceImpl extends BaseVOService<CommissionBase> implements ICommissionBaseService {
    @Autowired
    private ICommissionBaseDao commissionBaseDao;

	@Override
	public List<CommissionBase> getCommissionBaseByIbId(
			HashMap<String, Object> params) {
		return commissionBaseDao.findList("getCommissionBaseByIbId", params);
	}

	@Override
	public List<CommissionBase> getCommissionBaseByAccount(
			HashMap<String, Object> params) {
		return commissionBaseDao.findList("getCommissionBaseByAccount", params);
	}


	@Override
	public PageIterator<CommissionBase> pageQueryByAccount(
			HashMap<String, Object> params, Pagination pagination) {
		int totalCount = commissionBaseDao.queryCountByConditionByAccount(params);
		int offset = pagination.getOffset();
		int pageSize = pagination.getLimit();
		int pageNo = (offset / pageSize) + 1;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<CommissionBase> userMT4AccountList = commissionBaseDao.queryByConditionByAccount(params);
		PageIterator<CommissionBase> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userMT4AccountList);
		return page;
	}


	@Override
	public PageIterator<CommissionBase> pageQueryByConditiontAndGroupByTradeCateId(
			Pagination pagination, Map<String, Object> params) {
		int totalCount = commissionBaseDao.queryCountByConditiontAndGroupByTradeCateId(params);
		int offset = pagination.getOffset();
		int pageSize = pagination.getLimit();
		int pageNo = (offset / pageSize) + 1;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<CommissionBase> userMT4AccountList = commissionBaseDao.pageQueryByConditiontAndGroupByTradeCateId(params);
		PageIterator<CommissionBase> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userMT4AccountList);
		return page;
	}

	@Override
	public PageIterator<CommissionBase> pageQueryByConditiont(
			Pagination pagination, Map<String, Object> params) {
		int totalCount = commissionBaseDao.queryCountByConditiont(params);
		int offset = pagination.getOffset();
		int pageSize = pagination.getLimit();
		int pageNo = (offset / pageSize) + 1;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<CommissionBase> userMT4AccountList = commissionBaseDao.pageQueryByConditiont(params);
		PageIterator<CommissionBase> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userMT4AccountList);
		return page;
	}

	@Override
	public PageIterator<CommissionBase> pageQueryGroupByAgentAccount(
			Pagination pagination, String queryFrom, String queryTo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", queryFrom);
		params.put("end", queryTo);
		int totalCount = commissionBaseDao.queryCountByGroupByAgentAccount(params);
		int offset = pagination.getOffset();
		int pageSize = pagination.getLimit();
		int pageNo = (offset / pageSize) + 1;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<CommissionBase> userMT4AccountList = commissionBaseDao.pageQueryGroupByAgentAccount(params);
		PageIterator<CommissionBase> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userMT4AccountList);
		return page;
	}

	@Override
	public List<CommissionBase> getCommissionBaseByTradingGroup(
			Map<String, Object> params) {
		CommissionBase commissionBase = new CommissionBase();
		commissionBase.getSqlMap().put("params",params);
		commissionBase.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u"));
		return commissionBaseDao.findList("getCommissionBaseByTradingGroup", commissionBase);
	}

	@Override
	public List<CommissionBase> getCommissionBaseByAccountGroup(
			HashMap<String, Object> params) {
		CommissionBase commissionBase = new CommissionBase();
		commissionBase.getSqlMap().put("params",params);
		commissionBase.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return commissionBaseDao.findList("getCommissionBaseByAccountGroup", commissionBase);
	}

	@Override
	public List<CommissionBase> getCommissionBaseByAgentAccountTrader(HashMap<String, Object> params) {
		return commissionBaseDao.findList("getCommissionBaseByAgentAccountTrader", params);
	}

	@Override
	public List<CommissionBase> getCommissionBaseByAgentAccount(
			HashMap<String, Object> params) {
		return commissionBaseDao.findList("getCommissionBaseByAgentAccount", params);
	}

	@Override
	 public List<CommissionBase> getCommissionBaseByTradingAccount(
			HashMap<String, Object> params) {
		return commissionBaseDao.findList("getCommissionBaseByTradingAccount", params);
	}

	@Override
	public List<CommissionBase> getCommissionBasedDetailByTradingAccountByMT4Account(
			HashMap<String, Object> params) {
		return commissionBaseDao.findList("getCommissionBasedDetailByTradingAccountByMT4Account", params);
	}

	@Override
	public List<CommissionBase> getCommissionBaseByAgentUser(
			HashMap<String, Object> params) {
		return commissionBaseDao.findList("getCommissionBaseByAgentUser", params);
	}

	@Override
	public List<CommissionBase> getCommissionBasedDetailByTradingAccount(
			HashMap<String, Object> params) {
		return commissionBaseDao.findList("getCommissionBasedDetailByTradingAccount", params);
	}

	@Override
	public List<CommissionBase> getCommissionBaseByTradingGroupTrader(HashMap<String, Object> params) {
		CommissionBase commissionBase = new CommissionBase();
		commissionBase.getSqlMap().put("params",params);
		commissionBase.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return commissionBaseDao.findList("getCommissionBaseByTradingGroupTrader", commissionBase);
	}

    @Override
    public List<CommissionBase> getCommissionBaseByIBIdAndAgentAccount(HashMap<String, Object> params) {
        return commissionBaseDao.findList("getCommissionBaseByIBIdAndAgentAccount", params);
    }

    @Override
    public List<CommissionBase> getCommissionBaseByIBIdAndMT4Account(HashMap<String, Object> params) {
        return commissionBaseDao.findList("getCommissionBaseByIBIdAndMT4Account", params);
    }

    @Override
    public List<CommissionBase> getCommissionBaseByMT4Account(HashMap<String, Object> params) {
        return commissionBaseDao.findList("getCommissionBaseByMT4Account", params);
    }

	@Override
	public List<CommissionBase> getCommissionBaseByinnerIbId (
			String interviewDate1,String interviewDate2,HashMap<String, Object> params) throws ParseException {
		params = putStartAndEndDate(interviewDate1,interviewDate2,params);
		CommissionBase commissionBase = new CommissionBase();
		if(null!=(String)params.get("login")){
			commissionBase.getSqlMap().put("name", params.get("login"));
		}
		commissionBase.getSqlMap().put("params",params);
		commissionBase.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return commissionBaseDao.findList("getCommissionBaseByinnerIbId", commissionBase);
	}

	@Override
	public List<CommissionBase> getCommissionBaseByLogin(int ibIds, List<Integer> userIdList, int ib_ids, String interviewDate1, String interviewDate2,
			Integer dataSourceId, String login) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        HashMap<String,Object> params = new HashMap<String,Object>();
        params = dateParams(params,interviewDate1,interviewDate2,simpleDateFormat);
        params.put("dataSourceId", dataSourceId);
        params.put("ibIds", ibIds);
        if(null!=userIdList && userIdList.size()>0){
        	params.put("userIdList", userIdList);
        }
       
        //�����˺Ŵ���
        if(null!=login && (!login.equals(""))){
        	try{
        		params.put("login", Integer.parseInt(login));
        	}catch(Exception e){
				params.put("login", 0);
        		logger.info("mt4�˺�login��{}",login);
        	}
        }
        //��Ӷ�����û�ID
        if(ib_ids>0){
        	params.put("ibId", ib_ids);
        	params.put("ibIds", ib_ids);
        }else{
        	params.put("ibId", ibIds);
        	params.put("ibIds", ibIds);
        }
        params = putStartAndEndDate(interviewDate1,interviewDate2,params);
        CommissionBase commissionBase = new CommissionBase();
		commissionBase.getSqlMap().put("params",params);
		commissionBase.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return commissionBaseDao.findList("getCommissionBaseByLogin", commissionBase);
	}
	
	private HashMap<String,Object> putStartAndEndDate(String interviewDate1,String interviewDate2,HashMap<String,Object> params) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String start = interviewDate1+" 00:00:01";
	    Date date = simpleDateFormat.parse(interviewDate2);
	    Calendar date1=Calendar.getInstance();
	    date1.setTime(date);
	    date1.add(Calendar.DAY_OF_MONTH, 1);
	    String dateStr = simpleDateFormat.format(date1.getTime());
	    String end = dateStr+" 00:00:00";
	    Date startDate = sdf.parse(start);
	    Date endDate = sdf.parse(end);
	    params.put("startDate", startDate);
        params.put("endDate", endDate);
        
        return params;
	}

	@Override
	public List<CommissionBase> getCommissionBaseInnerByTradingGroup(
			String interviewDate1, String interviewDate2, Integer dataSourceId,int ibIds) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        HashMap<String,Object> params = new HashMap<String,Object>();
        params = dateParams(params,interviewDate1,interviewDate2,simpleDateFormat);
        params.put("dataSourceId", dataSourceId);
        if(ibIds>0){
        	params.put("ibId", ibIds+"");
        }
        params = putStartAndEndDate(interviewDate1,interviewDate2,params);
		return commissionBaseDao.findList("getCommissionBaseInnerByTradingGroup", params);
	}

	@Override
	public List<CommissionBase> getCommissionBasedDetailByTradingGroup(
			String interviewDate1, String interviewDate2, int ibIds,
			int ib_ids, String login, Integer dataSourceId)
			throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		HashMap<String,Object> params = new HashMap<String,Object>();
		params = dateParams(params,interviewDate1,interviewDate2,simpleDateFormat);
        params.put("dataSourceId", dataSourceId);
        //�����˺�
        if(null!=login && (!login.equals(""))){
        	try{
        		params.put("login", Integer.parseInt(login));
        	}catch(Exception e){
        		params.put("login", 0);
        		logger.info("mt4�˺�login��{}",login);
        	}
        }
        //��Ӷ��Ib_id
        if(ib_ids>0){
        	params.put("ibIds", ib_ids+"");
        }else{
        	params.put("ibIds", ibIds+"");
        }
        params = putStartAndEndDate(interviewDate1,interviewDate2,params);
        
		return commissionBaseDao.findList("getCommissionBasedDetailByTradingGroup", params);
	}
	
	private HashMap<String,Object> dateParams(HashMap<String,Object> params,String interviewDate1,String interviewDate2,SimpleDateFormat simpleDateFormat){
		if (interviewDate1!=null&&interviewDate2!=null&&interviewDate1.equals("") && interviewDate2 .equals("") && interviewDate1.length() > 0 && interviewDate2.length() > 0) {
        } else{
        	if (null==interviewDate1 ||interviewDate1.length()<1){
    			Calendar oneMonthAgo=Calendar.getInstance();
    			oneMonthAgo.set(Calendar.DAY_OF_MONTH, 1);
    			interviewDate1=simpleDateFormat.format(oneMonthAgo.getTime());
    		}
    		if (null==interviewDate2 ||interviewDate2.length()<1){
    			Calendar oneMonthAgo=Calendar.getInstance();
    			interviewDate2=simpleDateFormat.format(oneMonthAgo.getTime());
    		}
        }
        params.put("interviewDate1", interviewDate1);
        params.put("interviewDate2", interviewDate2);
        
        return params;
	}
}
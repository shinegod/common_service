package com.fx.crm.comm.service.impl;


import com.fx.crm.comm.dao.ICommissionMonthDao;
import com.fx.crm.comm.model.CommissionMonth;
import com.fx.crm.comm.service.ICommissionMonthService;
import com.fx.util.Constants;
import com.fx.util.Pagination;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommissionMonthServiceImpl extends BaseVOService<CommissionMonth> implements ICommissionMonthService {
    @Autowired
    private ICommissionMonthDao commissionMonthDao;

    private static final Logger logger = LoggerFactory.getLogger(CommissionMonthServiceImpl.class);
	@Override
	public PageIterator<CommissionMonth> pageQueryByConditiont(
			Pagination pagination, String queryFrom, String queryTo,
			Integer status) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("queryFrom", queryFrom);
		params.put("queryTo", queryTo);
		params.put("status", status);
		Integer totalCount = commissionMonthDao.queryCountByConditionByStatus(params);
		int offset = pagination.getOffset();
		int pageSize = pagination.getLimit();
		int pageNo = (offset / pageSize) + 1;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<CommissionMonth> commissionMontList = commissionMonthDao.queryByCondition(params);
		PageIterator<CommissionMonth> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(commissionMontList);
		return page;
	}


	@Override
	public List<CommissionMonth> getCommissionMonthByIbId(
			HashMap<String, Object> params) {
		return commissionMonthDao.findList("getCommissionMonthByIbId", params);
	}


	@Override
	public PageIterator<CommissionMonth> pageQueryByIbId(
			HashMap<String, Object> params, Pagination pagination) {
		int totalCount = commissionMonthDao.queryCountByConditionByIbId(params);
		int offset = pagination.getOffset();
		int pageSize = pagination.getLimit();
		int pageNo = (offset / pageSize) + 1;
		
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<CommissionMonth> userMT4AccountList = commissionMonthDao.queryByConditionByIbId(params);
		PageIterator<CommissionMonth> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userMT4AccountList);
		return page;
	}


	@Override
	public List<CommissionMonth> findCommissionByCondition(String queryFrom,
			String queryTo, Integer status) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("queryFrom", queryFrom);
		params.put("queryTo", queryTo);
		params.put("status", status);
		return commissionMonthDao.findCommissionByCondition(params);
	}


	@Override
	public List<CommissionMonth> getCommissionMonthByUser(
			Pagination pagination, String interviewDate1,
			String interviewDate2, String login) {
		Map<String,Object> params = new HashMap<String,Object>();
		params = setParamsTime( params,interviewDate1,interviewDate2,login);
		params.put("ibId", pagination.getIbId());
		CommissionMonth commissionMonth = new CommissionMonth();
		commissionMonth.getSqlMap().put("params",params);
		commissionMonth.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return commissionMonthDao.getCommissionMonthByUser(commissionMonth);
	}


	@Override
	public List<CommissionMonth> getCommissionMonthByUserStatus(
			Pagination pagination, String interviewDate1,
			String interviewDate2, String login, String status, int uid) {
		Map<String,Object> params = new HashMap<String,Object>();
		params = setParamsTime( params,interviewDate1,interviewDate2,login);
		if(null!=status && !status.trim().equals("")){
			try{
				params.put("login", Integer.parseInt(login));
			}catch(Exception e){}
		}
		params.put("ibId", pagination.getIbId());
		CommissionMonth commissionMonth = new CommissionMonth();
		commissionMonth.getSqlMap().put("params",params);
		commissionMonth.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return commissionMonthDao.getCommissionMonthByUserStatus(commissionMonth);
	}

	@Override
	public List<CommissionMonth> findIBCommissionByParams(HashMap<String, Object> paramCommission) {
		CommissionMonth commissionMonth = new CommissionMonth();
		commissionMonth.getSqlMap().put("params",paramCommission);
		commissionMonth.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return commissionMonthDao.findList("findIBCommissionByParams",commissionMonth);
	}

	@Override
	public BigDecimal getTotalNoReturnByUidAndMt4(CommissionMonth commissionMonth) {
		return (BigDecimal) commissionMonthDao.findOne("getTotalNoReturnByUidAndMt4",commissionMonth);
	}

	@Override
	public BigDecimal getTotalReturnByUidAndMt4(CommissionMonth commissionMonth) {
		return (BigDecimal) commissionMonthDao.findOne("getTotalReturnByUidAndMt4",commissionMonth);
	}

	@Override
	public List<CommissionMonth> findIBCommissionDetailsByParams(HashMap<String, Object> paramCommission) {
		return commissionMonthDao.findList("findIBCommissionDetailsByParams", paramCommission);
	}

	@Override
	public BigDecimal getTotalByUidAndMt4(CommissionMonth commissionMonth) {
		return (BigDecimal) commissionMonthDao.findOne("getTotalByUidAndMt4", commissionMonth);
	}

	@Override
	public BigDecimal getTotalVolumeByParams(HashMap<String, Object> paramCommission) {
		return (BigDecimal) commissionMonthDao.findOne("getTotalVolumeByParams", paramCommission);
	}

	@Override
	public BigDecimal getTotalCommissionByParams(HashMap<String, Object> paramCommission) {
		return (BigDecimal) commissionMonthDao.findOne("getTotalCommissionByParams", paramCommission);
	}

	@Override
	public List<CommissionMonth> getCommissionMonthByParams(HashMap<String, Object> paramCommission) {
		return commissionMonthDao.findList("getCommissionMonthByParams", paramCommission);
	}

    @Override
    public PageIterator<CommissionMonth> pageQueryByIserIdAndMT4Account(HashMap<String, Object> params, Pagination pagination) {
        int totalCount = commissionMonthDao.queryCountByUserIdAndMT4Account(params);
        int offset = pagination.getOffset();
        int pageSize = pagination.getLimit();
        int pageNo = (offset / pageSize) + 1;

        params.put("offset", offset);
        params.put("limit", pageSize);
        List<CommissionMonth> commissionMonths = commissionMonthDao.queryByUserIdAndMT4Account(params);
        PageIterator<CommissionMonth> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
        page.setData(commissionMonths);
        return page;
    }

    private Map<String,Object> setParamsTime(Map<String,Object> params,String interviewDate1,
			String interviewDate2,String login){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (interviewDate1!=null&&interviewDate2!=null&&!interviewDate1.equals("") && !interviewDate2 .equals("") && interviewDate1.length() > 0 && interviewDate2.length() > 0) {
        } else{
        	if (null==interviewDate1 ||interviewDate1.length()<1){
    			Calendar oneMonthAgo=Calendar.getInstance();
    			oneMonthAgo.set(Calendar.DAY_OF_MONTH, 1);
    			interviewDate1=simpleDateFormat.format(oneMonthAgo.getTime());
    			params.put("interviewDate1", interviewDate1);
    		}
    		if (null==interviewDate2 ||interviewDate2.length()<1){
    			Calendar oneMonthAgo=Calendar.getInstance();
    			interviewDate2=simpleDateFormat.format(oneMonthAgo.getTime());
    			params.put("interviewDate2", interviewDate2);
    		}
        }
		if(null!=login && !(login.trim().equals(""))){
			try{
				params.put("login", Integer.parseInt(login));
			}catch(Exception e){}
		}
		return params;
	}

}
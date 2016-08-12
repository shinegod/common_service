package com.fx.payment.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.payment.dao.ITradeDetailDao;
import com.fx.payment.model.TradeDetail;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class TradeDetailDaoImpl extends BaseDao<TradeDetail> implements ITradeDetailDao {

    public TradeDetailDaoImpl() {
        super( ITradeDetailDao.class.getName());
    }

	@Override
	public int queryCountByCondition(Map<String, Object> params) {
		return (Integer)findOne("pageQueryCount", params);
	}

	@Override
	public List<TradeDetail> queryByCondition(Map<String, Object> params) {
		return findList("pageQueryList", params);
	}

	@Override
	public List<TradeDetail> selectDetail(int opertype, int payertyoe,int statusq ,String userq) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("operType",opertype);
		params.put("payerType",payertyoe);
		params.put("statusq",statusq);
		params.put("userq",userq);
		return findList("selectDetail",params);
	}

	@Override
	public int selectDetailBySuccess(int mt4Account) {
		return (Integer)findOne("selectDetailBySuccess",mt4Account);
	}

	@Override
	public List<TradeDetail> selectDetailByType(Map map) {
		return findList("selectDetailByType",map);
	}

	@Override
	public List<TradeDetail> findAllByMT4acconut(Integer account) {
		return findList("findAllByMT4acconut",account);
	}

	@Override
	public List<TradeDetail> findAllByMT4acconutReturnFee(Integer account) {
		return findList("findAllByMT4acconutReturnFee",account);
	}

	@Override
	public TradeDetail findByMerOrderNum(String merOrderNum) {
		return (TradeDetail)findOne("findByMerOrderNum",merOrderNum);
	}

	@Override
	public Double findDepositAmountByDate(Map<String, Object> params){
		return (Double) findOne("findDepositAmountByDate", params);
	}
	@Override
	public Double findWithDrawAmountByDate(Map<String, Object> params){
		return (Double) findOne("findWithDrawAmountByDate", params);
	}
	@Override
	public Double findFirstDepsoitAmount(Map<String, Object> params){
		return (Double) findOne("findFirstDepsoitAmount", params);
	}
	@Override
	public Date findFirstDepsoitDate(Map<String, Object> params){
		return (Date) findOne("findFirstDepsoitDate", params);
	}
	@Override
	public List<Integer> findDepositMt4AccountByDate(Map<String, Object> params){
		return findList("findDepositMt4AccountByDate", params);
	}
	@Override
	public List<TradeDetail> findTradedByMT4acconut(Map<String, Object> params) {
		return findList("findTradedByMT4acconut",params);
	}
	@Override
	public Double getTotalWithDrawAmount(Map<String, Object> params){
		return (Double) findOne("getTotalWithDrawAmount", params);
	}
	@Override
	public Double getTotalDeposit(Map<String, Object> params){
		return (Double) findOne("getTotalDeposit", params);
	}
}
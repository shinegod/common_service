package com.fx.payment.service;

import com.fx.payment.exception.PayException;
import com.fx.payment.model.TradeDetail;
import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ITradeDetailService extends IValueObjectService<TradeDetail> {
	
	/**
	 * createTradeDetail4AddMoney:初始化一条入金的交易记录
	 *
	 * @param tradeDetail
	 * @return TradeDetail    初始化后的交易记录对象
	*/
	public TradeDetail createTradeDetail4AddMoney(TradeDetail tradeDetail) throws PayException;
	
	/**
	 * createTradeDetail4WithdrawMoney:初始化一条出金的交易记录
	 *
	 * @param tradeDetail
	 * @return TradeDetail    初始化后的交易记录对象
	*/
	public TradeDetail createTradeDetail4WithdrawMoney(TradeDetail tradeDetail) throws PayException;
	
	/**
	 * updateTradeStatusById:根据交易ID修改交易状态
	 *
	 * @param tradeDetail		交易详情
	 * @return int    1:成功		0:失败
	*/
	public int updateTradeStatusById(TradeDetail tradeDetail) throws PayException;
	
	public PageIterator<TradeDetail> pageQueryByCondition(Map<String, Object> params, int pageNo, int pageSize);

	public List<TradeDetail> selectDetail(int opertype, int payertyoe,int statusq ,String userq);

	public int selectDetailBySuccess(int mt4Account);

	public List<TradeDetail> selectDetailByType(Map map);

	public TradeDetail createTradeDetail(TradeDetail tradeDetail);

	public List<TradeDetail> findAllByMT4acconut(Integer account);

	public List<TradeDetail> findAllByMT4acconutReturnFee(Integer account);

	public TradeDetail findByMerOrderNum(String merOrderNum);

	/**
	 * 获取客户时间段内的入金总数
	 * @param id
	 * @param begin
	 * @param end
	 * @return
	 */
	BigDecimal getCustomerDeposit(Integer id, Date begin, Date end);

	public Double findDepositAmountByDate(Map<String, Object> params);

	public Double findWithDrawAmountByDate(Map<String, Object> params);
	public Double findFirstDepsoitAmount(Map<String, Object> params);
	public Date findFirstDepsoitDate(Map<String, Object> params);
	public List<Integer> findDepositMt4AccountByDate(Map<String, Object> params);
	public List<TradeDetail> findTradedByMT4acconut(Map<String, Object> params);
	public Double getTotalDeposit(Map<String, Object> params);
	public Double getTotalWithDrawAmount(Map<String, Object> params);

}
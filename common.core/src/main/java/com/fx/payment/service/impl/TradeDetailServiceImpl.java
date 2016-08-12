package com.fx.payment.service.impl;

import com.fx.enums.IsDelEnum;
import com.fx.payment.dao.ITradeDetailDao;
import com.fx.payment.enums.OperTypeEnum;
import com.fx.payment.enums.PayerTypeEnum;
import com.fx.payment.enums.TradeStatusEnum;
import com.fx.payment.exception.PayException;
import com.fx.payment.model.TradeDetail;
import com.fx.payment.service.ITradeDetailService;
import com.fx.user.dao.IUserRegisterDao;
import com.fx.user.model.UserRegister;
import com.fx.util.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TradeDetailServiceImpl extends BaseVOService<TradeDetail> implements ITradeDetailService {
    @Autowired
    private ITradeDetailDao tradeDetailDao;

	@Autowired
	private IUserRegisterDao userRegisterDao;
	@Override
	public TradeDetail createTradeDetail4AddMoney(TradeDetail tradeDetail) throws PayException {
		return createNewTradeDetail(tradeDetail, OperTypeEnum.ADD_MONEY);
	}

	@Override
	public TradeDetail createTradeDetail4WithdrawMoney(TradeDetail tradeDetail) throws PayException {
		return createNewTradeDetail(tradeDetail, OperTypeEnum.WITHDRAW_MONEY);
	}


	@Override
	public BigDecimal getCustomerDeposit(Integer id, Date begin, Date end) {
		Map params = Maps.newHashMap();
		params.put("uid", id);
		params.put("beginDate", DateUtil.getDateyyyyMMddHHmmssS(begin));
		params.put("endDate", DateUtil.getDateyyyyMMddHHmmssS(end));
		params.put("status", 130);
		params.put("operType", OperTypeEnum.ADD_MONEY.getValue());
		List<Integer> payerTypeList = Lists.newArrayList();
		payerTypeList.add(PayerTypeEnum.ATM_DIRECT_PAYMENT.getValue());
		payerTypeList.add(PayerTypeEnum.THIRD_PARTY_PAYMENT.getValue());
		payerTypeList.add(PayerTypeEnum.BANK_DIRECT_CONNECTION.getValue());
		params.put("payerTypeList", payerTypeList);
		List<TradeDetail> depositList = tradeDetailDao.selectDetailByType(params);
		BigDecimal result = new BigDecimal(0);
		for (TradeDetail tradeDetail : depositList) {
			result.add(tradeDetail.getOperMoney());
		}
		return result;
	}

	@Override
	public int updateTradeStatusById(TradeDetail tradeDetail) throws PayException{
		if(null == tradeDetail){
			throw new PayException("-1:交易详情为空");
		}
		if(null == tradeDetail.getId() || tradeDetail.getId() <= 0){
			throw new PayException("-2:交易详情ID为空");
		}
		if(null == TradeStatusEnum.valueOf(tradeDetail.getStatus())){
			throw new PayException("-3:交易状态为空");
		}
		//todo 判断隐藏
		/*if(StringUtils.isBlank(tradeDetail.getUpdateUser())||StringUtils.isBlank(tradeDetail.getCreateUser())){
			throw new PayException("-4:操作者为空");
		}
		if(StringUtils.isBlank(tradeDetail.getUpdateIp())){
			throw new PayException("-5:操作者IP为空");
		}*/
		TradeDetail td = tradeDetailDao.findById("selectByPrimaryKey", tradeDetail.getId().intValue());
		if(null == td){
			throw new PayException("-6:交易数据不存在 ");
		}
		td.setStatus(tradeDetail.getStatus());
		td.setMt4Account(tradeDetail.getMt4Account());
		td.setUpdateUser(tradeDetail.getUpdateUser());
		td.setUpdateTime(DateUtil.getCurrentTime());
		td.setUpdateIp(tradeDetail.getUpdateIp());
		int result = tradeDetailDao.doUpdate("updateByPrimaryKey", td);
		if(result <= 0){
			throw new PayException("-7:更新交易详情出错");
		}
		return result;
		
	}
	
	private TradeDetail createNewTradeDetail(TradeDetail tradeDetail, OperTypeEnum operTypeEnum) throws PayException{
        logger.info("create new trade detail method");
        if(null == tradeDetail){
            logger.info("-1:交易详情为空");
			throw new PayException("-1:交易详情为空");
        }
		if(null == tradeDetail.getUid() || tradeDetail.getUid() <= 0){
            logger.info("-2:交易详情中的交易人Uid为空");
            throw new PayException("-2:交易详情中的交易人Uid为空");
		}
//		if((null == tradeDetail.getPayerId() || tradeDetail.getPayerId() <= 0) 
//				&& StringUtils.isBlank(tradeDetail.getBankName())){
//			throw new PayException("-3:交易详情中的支付方为空");
//		}
		if(null == PayerTypeEnum.valueOf(tradeDetail.getPayerType())){
            logger.info("-4:交易详情中的支付类型为空");
            throw new PayException("-4:交易详情中的支付方类型为空");
		}
		if(null == tradeDetail.getOperMoney() || tradeDetail.getOperMoney().compareTo(new BigDecimal(0.00D)) <= 0){
            logger.info("-5:操作金额不得小于0");
			throw new PayException("-5:操作金额不得小于0");
		}
		if(null == OperTypeEnum.valueOf(tradeDetail.getOperType()) || tradeDetail.getOperType() != operTypeEnum.getValue()){
            logger.info("-6:");
			throw new PayException("-6:操作类型与业务操作不一致");
		}
		if(StringUtils.isBlank(tradeDetail.getCreateUser())){
			throw new PayException("-7:操作者为空");
		} 
		if(StringUtils.isBlank(tradeDetail.getCreateIp())){
			throw new PayException("-8:操作者IP为空");
		}
//		double numCurrency = GetRatesUtil.getMoney(tradeDetail.getCurrency(), "USD");
//		BigDecimal bigCurrency = new BigDecimal(numCurrency);
		TradeDetail td = new TradeDetail();
		td.setUid(tradeDetail.getUid());
		td.setPayerId(tradeDetail.getPayerId());
		td.setBankName(tradeDetail.getBankName());
		td.setPayerType(tradeDetail.getPayerType());
		td.setOperMoney(tradeDetail.getOperMoney());
		td.setBakName(tradeDetail.getBakName());
		td.setRate(tradeDetail.getRate());
		td.setBranchName(tradeDetail.getBranchName());
		td.setCardNumber(tradeDetail.getCardNumber());
		td.setMt4Account(tradeDetail.getMt4Account());
		td.setActualMoney(tradeDetail.getActualMoney());
		td.setCurrency(tradeDetail.getCurrency());
		td.setSwiftCode(tradeDetail.getSwiftCode());
		td.setOperType(operTypeEnum.getValue());
		td.setRemark(tradeDetail.getRemark());
		td.setEdocIds(tradeDetail.getEdocIds());
		td.setFee(tradeDetail.getFee());
		td.setRemark(tradeDetail.getRemark());
		td.setMerOrderNum(tradeDetail.getMerOrderNum());
		if(operTypeEnum.getValue() == OperTypeEnum.ADD_MONEY.getValue()){
			if(td.getPayerType()==1){
				td.setStatus(tradeDetail.getStatus());
			}else{
				td.setStatus(TradeStatusEnum.ADD_MONEY_INIT.getValue());
			}
		} else if (operTypeEnum.getValue() == OperTypeEnum.WITHDRAW_MONEY.getValue()){
			td.setStatus(TradeStatusEnum.WITHDRAW_MONEY_INIT.getValue());
		}
		td.setCreateUser(tradeDetail.getCreateUser());
		td.setCreateTime(DateUtil.getCurrentTime());
		td.setCreateIp(tradeDetail.getCreateIp());
		td.setUpdateUser("");
		td.setUpdateTime(DateUtil.getCurrentTime());
		td.setUpdateIp("");
		td.setIsDel(IsDelEnum.NO.getValue());
        td.setRealMoney(tradeDetail.getRealMoney());
        td.setActualFee(tradeDetail.getActualFee());
		int result = tradeDetailDao.doInsert("insert", td);
		if(result <= 0){
			throw new PayException("-9:增加交易详情出错");
		}
		return td;
	}

	@Override
	public PageIterator<TradeDetail> pageQueryByCondition(
			Map<String, Object> params, int pageNo, int pageSize) {
		int totalCount = tradeDetailDao.queryCountByCondition(params);
		
		int offset = (pageNo-1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<TradeDetail> list = tradeDetailDao.queryByCondition(params);
		PageIterator<TradeDetail> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(list);
		return page;
	}
	@Override
	public List<TradeDetail> selectDetailByType(Map map) {
		return tradeDetailDao.selectDetailByType(map);
	}

	@Override
	public List<TradeDetail> selectDetail(int opertype, int payertyoe,int statusq ,String userq) {
		return tradeDetailDao.selectDetail(opertype, payertyoe, statusq, userq);
	}

	@Override
	public int selectDetailBySuccess(int mt4Account) {
		return tradeDetailDao.selectDetailBySuccess(mt4Account);
	}

	public TradeDetail createTradeDetail(TradeDetail tradeDetail){
		UserRegister userRegister = userRegisterDao.findById("selectByPrimaryKey", tradeDetail.getUid());
		if(userRegister!=null){
			tradeDetail.setCreateUser(userRegister.getCnName());
		}
		tradeDetail.setPayerId(1004);
		tradeDetail.setCreateTime(DateUtil.getCurrentTime());
		tradeDetail.setUpdateUser("");
		tradeDetail.setUpdateTime(DateUtil.getCurrentTime());
		tradeDetail.setIsDel(IsDelEnum.NO.getValue());
		this.doInsert(tradeDetail);
		return tradeDetail;
	}

	@Override
	public List<TradeDetail> findAllByMT4acconut(Integer account) {
		return tradeDetailDao.findAllByMT4acconut(account);
	}

	@Override
	public List<TradeDetail> findAllByMT4acconutReturnFee(Integer account) {
		return tradeDetailDao.findAllByMT4acconutReturnFee(account);
	}

	@Override
	public TradeDetail findByMerOrderNum(String merOrderNum) {
		return tradeDetailDao.findByMerOrderNum(merOrderNum);
	}

	public Double findDepositAmountByDate(Map<String, Object> params){
		return tradeDetailDao.findDepositAmountByDate(params);
	}

	public Double findWithDrawAmountByDate(Map<String, Object> params){
		return tradeDetailDao.findWithDrawAmountByDate(params);
	}
	public Double findFirstDepsoitAmount(Map<String, Object> params){
		return tradeDetailDao.findFirstDepsoitAmount(params);
	}
	public Date findFirstDepsoitDate(Map<String, Object> params){
		return tradeDetailDao.findFirstDepsoitDate(params);
	}
	public List<Integer> findDepositMt4AccountByDate(Map<String, Object> params){
		return tradeDetailDao.findDepositMt4AccountByDate(params);
	}
	public List<TradeDetail> findTradedByMT4acconut(Map<String, Object> params){
		return tradeDetailDao.findTradedByMT4acconut(params);
	}
	public Double getTotalDeposit(Map<String, Object> params){
		return tradeDetailDao.getTotalDeposit(params);
	}
	public Double getTotalWithDrawAmount(Map<String, Object> params){
		return tradeDetailDao.getTotalWithDrawAmount(params);
	}
}
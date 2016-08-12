package com.fx.payment.service.impl;

import java.util.List;
import java.util.Map;

import com.fx.enums.IsDelEnum;
import com.fx.payment.dao.IApplyWithDrawMoneyDao;
import com.fx.payment.enums.TradeStatusEnum;
import com.fx.payment.enums.WithDrawApplyStatusEnum;
import com.fx.payment.exception.PayException;
import com.fx.payment.model.ApplyWithDrawMoney;
import com.fx.payment.model.TradeDetail;
import com.fx.payment.service.IApplyWithDrawMoneyService;
import com.fx.util.DateUtil;

import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyWithDrawMoneyServiceImpl extends BaseVOService<ApplyWithDrawMoney> implements IApplyWithDrawMoneyService {
    @Autowired
    private IApplyWithDrawMoneyDao applyWithDrawMoneyDao;

	@Override
	public int createApplyWithdrawMoney(ApplyWithDrawMoney applyWithdrawMoney)
			throws PayException {
		if(null == applyWithdrawMoney){
			throw new PayException("-1:出金申请单为空");
		}
		if(null == applyWithdrawMoney.getUid() || applyWithdrawMoney.getUid() <= 0){
			throw new PayException("-2:交易者为空");
		}
		if(null == applyWithdrawMoney.getTradeId() || applyWithdrawMoney.getTradeId() <= 0){
			throw new PayException("-3:交易详情ID为空");
		}
		if(StringUtils.isBlank(applyWithdrawMoney.getBankName())){
			throw new PayException("-4:银行名称为空");
		}
		if(StringUtils.isBlank(applyWithdrawMoney.getBranchName())){
			throw new PayException("-5:分行名称为空");
		}
/*		if(StringUtils.isBlank(applyWithdrawMoney.getBankSwiftCode())){
			throw new PayException("-6:银行Swift码为空");
		}*/
		if(StringUtils.isBlank(applyWithdrawMoney.getBeneficiaryAccountNo())){
			throw new PayException("-7:收款人账号为空");
		}
		if(StringUtils.isBlank(applyWithdrawMoney.getBeneficiaryAccountName())){
			throw new PayException("-8:收款人名称为空");
		}
		if(StringUtils.isBlank(applyWithdrawMoney.getCreateUser())){
			throw new PayException("-9:操作者为空");
		}
		/*if(StringUtils.isBlank(applyWithdrawMoney.getCreateIp())){
			throw new PayException("-10:操作者IP为空");
		}*/
		ApplyWithDrawMoney applyWithDrawMoney = new ApplyWithDrawMoney();
		applyWithDrawMoney.setUid(applyWithdrawMoney.getUid());
		applyWithDrawMoney.setTradeId(applyWithdrawMoney.getTradeId());
		applyWithDrawMoney.setWithdrawMoney(applyWithdrawMoney.getWithdrawMoney());
		applyWithDrawMoney.setBankName(applyWithdrawMoney.getBankName());
		applyWithDrawMoney.setBankAddress(applyWithdrawMoney.getBankAddress());
		if(applyWithdrawMoney.getBankSwiftCode()!=null){
			applyWithDrawMoney.setBankSwiftCode(applyWithdrawMoney.getBankSwiftCode());
		}
		applyWithDrawMoney.setBeneficiaryAccountNo(applyWithdrawMoney.getBeneficiaryAccountNo());
		applyWithDrawMoney.setBeneficiaryAccountName(applyWithdrawMoney.getBeneficiaryAccountName());		
		applyWithDrawMoney.setStatus(WithDrawApplyStatusEnum.INIT.getValue());
		applyWithDrawMoney.setCreateUser(applyWithdrawMoney.getCreateUser());
		applyWithDrawMoney.setCreateTime(DateUtil.getCurrentTime());
		applyWithDrawMoney.setCreateIp(applyWithdrawMoney.getCreateIp());
		applyWithDrawMoney.setUpdateUser("");
		applyWithDrawMoney.setUpdateTime(DateUtil.getCurrentTime());
		applyWithDrawMoney.setUpdateIp("");
		applyWithDrawMoney.setBranchName(applyWithdrawMoney.getBranchName());
		applyWithDrawMoney.setIsDel(IsDelEnum.NO.getValue());
		applyWithDrawMoney.setReason(applyWithdrawMoney.getReason());
		int result = this.doInsert(applyWithDrawMoney);
		return result;
	}

	@Override
	public PageIterator<ApplyWithDrawMoney> pageQueryByCondition(
			Map<String, Object> params, int pageNo, int pageSize) {
		int totalCount = applyWithDrawMoneyDao.queryCountByCondition(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<ApplyWithDrawMoney> list = applyWithDrawMoneyDao.queryByCondition(params);
		PageIterator<ApplyWithDrawMoney> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(list);
		return page;
	}

	@Override
	public List<ApplyWithDrawMoney> querylist(int statusq, String userq) {
		return applyWithDrawMoneyDao.queryList(statusq,userq);
	}

	@Override
	public List<ApplyWithDrawMoney> selsectNopage(Map map) {
		return applyWithDrawMoneyDao.selsectNopage(map);
	}

	@Override
	public List<ApplyWithDrawMoney> queryListMoreTable(int statusq, String userq) {
		return applyWithDrawMoneyDao.queryListMoreTable(statusq,userq);
	}


}
package com.fx.payment.service;

import java.util.List;
import java.util.Map;

import com.fx.payment.exception.PayException;
import com.fx.payment.model.ApplyWithDrawMoney;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

public interface IApplyWithDrawMoneyService extends IValueObjectService<ApplyWithDrawMoney> {
	
	/**
	 * createApplyWithdrawMoney:创建出金申请单
	 *
	 * @param detail
	 * @return int    1:成功	0:失败
	 * @throws 	异常信息说明
	*/
	public int createApplyWithdrawMoney(ApplyWithDrawMoney applyWithdrawMoney)  throws PayException;
	
	public PageIterator<ApplyWithDrawMoney> pageQueryByCondition(Map<String, Object> params, int page, int pageSize);

	public List<ApplyWithDrawMoney> querylist(int statusq,String userq);

	public List<ApplyWithDrawMoney> selsectNopage(Map map);

	public List<ApplyWithDrawMoney> queryListMoreTable(int statusq,String userq);
}
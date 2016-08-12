package com.fx.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.user.model.MT4Transfer;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

public interface IMT4TransferService extends IValueObjectService<MT4Transfer> {
	public PageIterator<MT4Transfer> pageQueryByConditionByIsIb(int status, int pageNo, int pageSize);

	public PageIterator<MT4Transfer> pageQueryByConditionByUserId(
			Integer userId, int status, int pageNo, int pageSize);
	/**
	 * 根据状态以及用户名字，邮箱和账号查询
	 * @param params
	 * @return
	 */
	public List<MT4Transfer> queryListByCondition(HashMap<String, Object> params);


	List<MT4Transfer> findbyUserId(Integer uid);

	/**
	 * 根据状态查找List
	 * @param params
	 * @return
     */
	public List<MT4Transfer> queryListByStatus(HashMap<String, Object> params);

	double  findTotalAmout(Integer mt4Accont);
}
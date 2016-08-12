package com.fx.user.service;

import com.fx.user.model.ReturnedMoney;
import com.fx.util.Pagination;
import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import java.util.List;

public interface IReturnedMoneyService extends IValueObjectService<ReturnedMoney> {
   public List<ReturnedMoney> findByUserId(Integer userId);

   public PageIterator<ReturnedMoney> pageQueryByCondition(Pagination pagination);
}
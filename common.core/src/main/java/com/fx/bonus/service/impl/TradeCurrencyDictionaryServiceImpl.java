package com.fx.bonus.service.impl;

import com.fx.bonus.dao.ITradeCurrencyDictionaryDao;
import com.fx.bonus.model.TradeCurrencyDictionary;
import com.fx.bonus.service.ITradeCurrencyDictionaryService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeCurrencyDictionaryServiceImpl extends BaseVOService<TradeCurrencyDictionary> implements ITradeCurrencyDictionaryService {
    @Autowired
    private ITradeCurrencyDictionaryDao tradeCurrencyDictionaryDao;
}
package com.fx.bonus.service.impl;

import com.fx.bonus.dao.ITradeDictionaryDao;
import com.fx.bonus.model.TradeDictionary;
import com.fx.bonus.service.ITradeDictionaryService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeDictionaryServiceImpl extends BaseVOService<TradeDictionary> implements ITradeDictionaryService {
    @Autowired
    private ITradeDictionaryDao tradeDictionaryDao;
}
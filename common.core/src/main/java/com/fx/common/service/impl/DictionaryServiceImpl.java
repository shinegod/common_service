package com.fx.common.service.impl;

import com.fx.common.dao.IDictionaryDao;
import com.fx.common.model.Dictionary;
import com.fx.common.service.IDictionaryService;
import com.fx.leverageBalance.model.LeverageBalance;
import com.fx.leverageBalance.service.ILeverageBalanceService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DictionaryServiceImpl extends BaseVOService<Dictionary> implements IDictionaryService {
    @Autowired
    private IDictionaryDao dictionaryDao;

    @Autowired
    private ILeverageBalanceService leverageBalanceService;

    @Override
    public List<Dictionary> findByParentId(String parentId) {
        return dictionaryDao.findByParentId(parentId);
    }

    @Override
    public Dictionary findByCode(String dataCode) {
        return dictionaryDao.findByCode(dataCode);
    }

    @Override
    public List<Dictionary> queryByParentId(String parentId) {
        return dictionaryDao.findByParentId(parentId);
    }

    @Override
    public List<Dictionary> queryByParentCode(String dataCode) {
        return dictionaryDao.queryByParentCode(dataCode);
    }

    //取全部启用杠杆
    @Override
    public List<Dictionary> selectByparentIdAndStatus(Map map) {
        return dictionaryDao.selectByparentIdAndStatus(map);
    }

    //取符合余额全部启用杠杆
    @Override
    public List<Dictionary> selectByparentIdAndStatusBalance(Map map,double balance) {
        List<Dictionary> dictionaryList = dictionaryDao.selectByparentIdAndStatus(map);
        List<Dictionary> dictionaryList2 = new ArrayList<>();
        LeverageBalance leverageBalance = new LeverageBalance();
        for(Dictionary dictionary:dictionaryList){
            leverageBalance = leverageBalanceService.selectByLeverage(Integer.parseInt(dictionary.getDescription()));
            if(leverageBalance.getMinBalance()!=null){
                if(leverageBalance.getMaxBalance()!=null){
                    if(balance>=leverageBalance.getMinBalance()&&balance<=leverageBalance.getMaxBalance()){
                        dictionaryList2.add(dictionary);
                    }
                } else {
                    if(balance>=leverageBalance.getMinBalance()){
                        dictionaryList2.add(dictionary);
                    }
                }
            }
        }
        return dictionaryList2;
    }


    @Override
    public List<Dictionary> selectByParentCodeAndStatusNoZero(String dataCode) {
        return dictionaryDao.selectByParentCodeAndStatusNoZero(dataCode);
    }

    @Override
    public Integer selectCountCode(String dataCode) {
        return dictionaryDao.selectCountCode(dataCode);
    }

    public List<Dictionary> selectValueByDatatype(String dataType) {
        return dictionaryDao.selectValueByDatatype(dataType);
    }
}
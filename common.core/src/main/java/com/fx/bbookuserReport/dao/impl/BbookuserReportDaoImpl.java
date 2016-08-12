package com.fx.bbookuserReport.dao.impl;

import com.fx.bbookuserReport.dao.IBbookuserReportDao;
import com.fx.bbookuserReport.model.BbookuserReport;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BbookuserReportDaoImpl extends BaseDao<BbookuserReport> implements IBbookuserReportDao {

    public BbookuserReportDaoImpl() {
        super(IBbookuserReportDao.class.getName());
    }

    @Override
    public List<BbookuserReport> selectBycreateDate(String date) {
        Map map = new HashMap();
        map.put("createDate",date);
        return findList("selectBycreateDate",map);
    }

    @Override
    public BbookuserReport selectByLoginAndDate(Map map) {
        return (BbookuserReport)findOne("selectByLoginAndDate",map);
    }
}
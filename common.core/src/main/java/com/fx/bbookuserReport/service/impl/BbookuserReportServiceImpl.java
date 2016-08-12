package com.fx.bbookuserReport.service.impl;

import com.fx.bbookuserReport.dao.IBbookuserReportDao;
import com.fx.bbookuserReport.model.BbookuserReport;
import com.fx.bbookuserReport.service.IBbookuserReportService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BbookuserReportServiceImpl extends BaseVOService<BbookuserReport> implements IBbookuserReportService {
    @Autowired
    private IBbookuserReportDao bbookuserReportDao;

    @Override
    public List<BbookuserReport> selectBycreateDate(String date) {
        return bbookuserReportDao.selectBycreateDate(date);
    }

    @Override
    public BbookuserReport selectByLoginAndDate(Integer login ,String date) {
        Map map = new HashMap();
        map.put("login",login);
        map.put("date",date);
        return bbookuserReportDao.selectByLoginAndDate(map);
    }
}
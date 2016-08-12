package com.fx.bbookuserReport.service;

import com.fx.bbookuserReport.model.BbookuserReport;
import mybatis.framework.core.service.IValueObjectService;

import java.util.List;
import java.util.Map;

public interface IBbookuserReportService extends IValueObjectService<BbookuserReport> {

    public List<BbookuserReport> selectBycreateDate(String date);

    public BbookuserReport selectByLoginAndDate(Integer login ,String date);
}
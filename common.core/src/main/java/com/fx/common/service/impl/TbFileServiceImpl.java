package com.fx.common.service.impl;

import com.fx.common.dao.ITbFileDao;
import com.fx.common.model.TbFile;
import com.fx.common.service.ITbFileService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbFileServiceImpl extends BaseVOService<TbFile> implements ITbFileService {
    @Autowired
    private ITbFileDao tbFileDao;
}
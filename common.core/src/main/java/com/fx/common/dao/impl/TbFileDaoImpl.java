package com.fx.common.dao.impl;

import com.fx.common.dao.ITbFileDao;
import com.fx.common.model.TbFile;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class TbFileDaoImpl extends BaseDao<TbFile> implements ITbFileDao {

    public TbFileDaoImpl() {
        super(ITbFileDao.class.getName());
    }
}
package com.fx.RPQueryVolume.dao.impl;

import com.fx.RPQueryVolume.dao.IRPQueryVolumeDao;
import com.fx.RPQueryVolume.model.RPQueryVolume;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RPQueryVolumeDaoImpl extends BaseDao<RPQueryVolume> implements IRPQueryVolumeDao {

    public RPQueryVolumeDaoImpl() {
        super(IRPQueryVolumeDao.class.getName());
    }

    @Override
    public List<RPQueryVolume> selectByDate(Map map) {
        return findList("selectByDate",map);
    }
}
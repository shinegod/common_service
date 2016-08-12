package com.fx.RPQueryVolume.dao;

import com.fx.RPQueryVolume.model.RPQueryVolume;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;
import java.util.Map;

public interface IRPQueryVolumeDao extends IValueObjectDao<RPQueryVolume> {

    public List<RPQueryVolume> selectByDate(Map map);
}

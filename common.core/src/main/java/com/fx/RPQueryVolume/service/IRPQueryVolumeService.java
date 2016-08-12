package com.fx.RPQueryVolume.service;

import com.fx.RPQueryVolume.model.RPQueryVolume;
import mybatis.framework.core.service.IValueObjectService;

import java.util.List;
import java.util.Map;

public interface IRPQueryVolumeService extends IValueObjectService<RPQueryVolume> {

    public List<RPQueryVolume> selectByDate(Map map);
}
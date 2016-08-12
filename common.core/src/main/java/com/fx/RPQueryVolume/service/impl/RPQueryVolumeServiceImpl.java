package com.fx.RPQueryVolume.service.impl;

import com.fx.RPQueryVolume.dao.IRPQueryVolumeDao;
import com.fx.RPQueryVolume.model.RPQueryVolume;
import com.fx.RPQueryVolume.service.IRPQueryVolumeService;
import mybatis.framework.core.service.BaseVOService;
import org.mockito.internal.debugging.FindingsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RPQueryVolumeServiceImpl extends BaseVOService<RPQueryVolume> implements IRPQueryVolumeService {
    @Autowired
    private IRPQueryVolumeDao rPQueryVolumeDao;

    @Override
    public List<RPQueryVolume> selectByDate(Map map) {
        return rPQueryVolumeDao.selectByDate(map);
    }
}
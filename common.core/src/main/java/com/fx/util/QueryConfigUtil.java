package com.fx.util;

import com.fx.queryConfig.model.SysQueryConfig;
import com.fx.queryConfig.service.ISysQueryConfigService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.List;

/**
 * Created by Administrator on 2015/9/15.
 */
public class QueryConfigUtil {


    public static List<SysQueryConfig> getSysQueryConfig(int menuId){
        ISysQueryConfigService sysQueryConfigService = SpringUtils.getBean(ISysQueryConfigService.class);
        return sysQueryConfigService.selectByMenuid(menuId);
    }
}

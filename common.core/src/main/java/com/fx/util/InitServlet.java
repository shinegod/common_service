package com.fx.util;

import com.fx.MT4.util.MT4Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.*;

/**
 * Created by bei2love@gmail.com on 15/5/30.
 */
@WebServlet(name = "InitServlet", urlPatterns = "/InitServlet", loadOnStartup = 2)
public class InitServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(InitServlet.class);

    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see javax.servlet.Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {

        initMT4Config();

        //刷新字典表缓存
        CacheMgr.refreshDictionaryCache();
        CacheMgr.refreshOperationReference();
        CacheMgr.refreshAllOrgCache();
        CacheMgr.cleanCache();
        CacheMgr.refreshCountryCache();
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
        sb.append("\n|========================================================|");
        sb.append("\n| CRM Web Core Initialization\n");
        sb.append("| 1.autoload config\n");
        Config.load();
        sb.append("| 2.autoload i18n: ");
        I18N.load();
        sb.append("\n\tENGLISH = ");
        sb.append(I18N.getLangByCode(I18N.CONST_LANG_CODE_ENGLISH).size());
        sb.append(" items,CHINESE = ");
        sb.append(I18N.getLangByCode(I18N.CONST_LANG_CODE_CHINESE).size());
        sb.append(" items,CHINESE TW = ");
        sb.append(I18N.getLangByCode(I18N.CONST_LANG_CODE_CHINESE_TW).size());
        sb.append(" items\n");
        sb.append("| 3.加载MT4 Config:\n");
        sb.append("\tMT4Configuration.demoGroupId: " + MT4Configuration.demoGroupId);
        sb.append("\n\tMT4Configuration.demoGroupName: " + MT4Configuration.demoGroupName);
        sb.append("\n\tMT4Configuration.lgn: " + MT4Configuration.lgn);
        sb.append("\n\tMT4Configuration.pwd: " + MT4Configuration.pwd);
        sb.append("\n\tMT4Configuration.ipDemo: " + MT4Configuration.ipDemo);
        sb.append("\n\tMT4Configuration.ipLive: " + MT4Configuration.ipLive);
        sb.append("\n\tMT4Configuration.update_interval: " + MT4Configuration.update_interval);
        sb.append("\n\tMT4Configuration.companyId: " + MT4Configuration.companyId);
        sb.append("\n|========================================================|");
        logger.info(sb.toString());
        clearOnlineUser();

    }

    private void initMT4Config() {
        MT4Configuration.demoGroupId = Integer.valueOf(Config.getConfig(MT4Configuration.MT4_CONFIG_DEMOGROUPID));

        MT4Configuration.demoGroupName=Config.getConfig(MT4Configuration.MT4_CONFIG_DEMOGROUPNAME);
        MT4Configuration.lgn = Integer.valueOf(Config.getConfig(MT4Configuration.MT4_CONFIG_LGN));
        MT4Configuration.pwd = Config.getConfig(MT4Configuration.MT4_CONFIG_PWD);
        MT4Configuration.ipDemo = Config.getConfig(MT4Configuration.MT4_CONFIG_IPDEMO);
        MT4Configuration.ipLive = Config.getConfig(MT4Configuration.MT4_CONFIG_IPLIVE);
        MT4Configuration.update_interval = Integer.valueOf(Config.getConfig(MT4Configuration.MT4_CONFIG_UPDATE_INTERVAL));
        MT4Configuration.companyId = Integer.valueOf(Config.getConfig(MT4Configuration.MT4_CONFIG_COMPANYID));
    }

    private void clearOnlineUser(){
        Map<String, String> userMap = CacheMgr.getOnlineUser();
        logger.info("重启系统清理用户缓存，当前存在{}个用户缓存。", userMap.size());
        for (Map.Entry<String, String> m : userMap.entrySet()) {
            logger.info("清理user:{},cachekey:{}", m.getKey(),m.getValue());
            CacheMgr.offlineUser(m.getKey());
        }
    }
}

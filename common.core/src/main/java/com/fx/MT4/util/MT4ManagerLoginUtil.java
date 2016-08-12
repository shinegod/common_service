package com.fx.MT4.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MT4ManagerLoginUtil {
    private static Logger logger = LoggerFactory.getLogger(MT4ManagerLoginUtil.class);

    public static String checkLogin(int managerLogin,String managerPassword,String serverIP){
        logger.info("Check Manager Login {}",managerLogin,new Date());
        String res="passed";
        try {
            int r=DLLAgent.login(managerLogin, managerPassword, serverIP);
            if (r>0){
                res= "Login matched, but below manager rights are missing:";
                if (r%2>=1) res=res+" Create Accounts;";
                if (r%4>=2) res=res+" Accountant;";
                if (r%8>=4) res=res+" Supervise Trades;";
                if (r%16>=8) res=res+" Personal Information;";
                if (r%32>=16) res=res+" Report;";
                res = res+" Please contact MT4 Server Provider";
            }
        } catch (Exception e) {
            logger.error("MT4Manager "+managerLogin+" login failed: {}",e.getMessage(),new Date());
            return e.getMessage();
        }
        logger.info("MT4Manager {} Login Passed",managerLogin,new Date());
        return res;

    }
}

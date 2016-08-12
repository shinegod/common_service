package com.fx.timedTask;


import com.fx.MT4.enums.QueryMtT4GroupIdEnum;
import com.fx.MT4.util.MT4AccountUtil;
import com.fx.MT4.vo.MT4User;
import com.fx.dataSourceBean.model.DataSourceBean;
import com.fx.dataSourceBean.service.IDataSourceBeanService;
import com.fx.payment.exception.PayException;
import com.fx.payment.model.UserMT4Account;
import com.fx.payment.service.IUserMT4AccountService;
import com.fx.user.model.UserRegister;
import com.fx.user.service.IUserRegisterService;
import com.fx.util.Config;
import com.fx.util.SaltCreateUtil;
import com.fx.util.SpringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class ExperienceAccountChangePwd {

    /**
     * 有效期到后，修改体验账户密码
     */
    public void changePwd() throws PayException {
        IUserRegisterService userRegisterService = SpringUtils.getBean(IUserRegisterService.class);
        IUserMT4AccountService userMT4AccountService = SpringUtils.getBean(IUserMT4AccountService.class);
        IDataSourceBeanService dataSourceBeanService = SpringUtils.getBean(IDataSourceBeanService.class);
        long nowDate = new Date().getTime();
        // 取所有的体验用户
        List<UserRegister> userRegisterList = userRegisterService.getAllExpUsers();
        for (UserRegister userRegister : userRegisterList) {
            if (nowDate >= userRegister.getIndate().getTime()) {

                // 更改用户密码
                String simpleText = Config.getExpActPwd();
                String email = userRegister.getEmail();
                String salt = SaltCreateUtil.createSecureSalt();
                String sha512Password = DigestUtils.sha512Hex(salt + simpleText + email);
                userRegister.setPassword(sha512Password);
                userRegister.setSalt(salt);
                userRegisterService.doUpdateById(userRegister);

                List<UserMT4Account> userMT4AccountList = userMT4AccountService.getUserMT4AccountByUid(userRegister.getId());

                DataSourceBean dataSourceBean = dataSourceBeanService.findDataSourceBeanById(userRegister.getMt4DataSourceId());

                for (UserMT4Account userMT4Account : userMT4AccountList) {
                    MT4User mt4User = MT4AccountUtil.getMT4ClientInfo(userMT4Account.getMt4Account(), QueryMtT4GroupIdEnum.LIVE, dataSourceBean);
                    MT4AccountUtil.resetMT4Password(mt4User.getLogin(), Config.getExpActPwd(), Config.getExpActPwd(), QueryMtT4GroupIdEnum.LIVE, dataSourceBean);
                }

            }
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext.xml");
        ExperienceAccountChangePwd experienceAccountChangePwd = new ExperienceAccountChangePwd();
        try {
            experienceAccountChangePwd.changePwd();
        } catch (PayException e) {
            e.printStackTrace();
        }
    }

}

import com.fx.payment.model.UserMT4Account;
import com.fx.payment.service.IUserMT4AccountService;
import com.fx.payment.util.UserMT4StatusEnum;
import com.fx.user.model.UserRegister;
import com.fx.user.service.IUserRegisterService;
import com.fx.util.SpringUtils;
import com.fx.util.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Michael on 6/16/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class ChangeAccountSort {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext.xml");
        ChangeAccountSort changeAccountSort = new ChangeAccountSort();
        changeAccountSort.updateMt4Sort();
    }

    @Test
    public void updateMt4Sort() {
        IUserRegisterService userRegisterService = SpringUtils.getBean(IUserRegisterService.class);
        IUserMT4AccountService userMT4AccountService = SpringUtils.getBean(IUserMT4AccountService.class);
        List<UserRegister> userRegisters = userRegisterService.getAllLivesAndAllIbs();
        for (UserRegister ur : userRegisters) {
            List<UserMT4Account> userMT4AccountList = userMT4AccountService.getUserMT4AccountByUIdAndUserStatus(ur.getId(), UserMT4StatusEnum.MONEY_NOBACK.getValue());
            Collections.sort(userMT4AccountList, new Comparator<UserMT4Account>() {
                public int compare(UserMT4Account arg0, UserMT4Account arg1) {
                    return arg0.getUpdateTime().compareTo(arg1.getUpdateTime());
                }
            });
            int auditSort = 0;
            for (UserMT4Account userMT4Account : userMT4AccountList) {
                auditSort++;
                if (userMT4Account.getAuditSort() == "" || userMT4Account.getAuditSort() == null || StringUtils.equals(userMT4Account.getAuditSort(), "0")) {
                    if (auditSort == 1) {
                        userMT4Account.setAuditSort(auditSort + "st");
                    } else if (auditSort == 2) {
                        userMT4Account.setAuditSort(auditSort + "nd");
                    } else if (auditSort == 3) {
                        userMT4Account.setAuditSort(auditSort + "rd");
                    } else {
                        userMT4Account.setAuditSort(auditSort + "th");
                    }
                }
                userMT4AccountService.doUpdateById(userMT4Account);
            }
        }
    }

}

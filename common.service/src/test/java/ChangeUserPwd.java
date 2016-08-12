import com.fx.admin.model.Admin;
import com.fx.admin.service.IAdminService;
import com.fx.user.model.UserRegister;
import com.fx.user.service.IUserRegisterService;
import com.fx.util.SaltCreateUtil;
import com.fx.util.SpringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Michael on 6/16/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class ChangeUserPwd {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext.xml");
        ChangeUserPwd changeUserPwd = new ChangeUserPwd();
        changeUserPwd.changeUserPwd();
    }

    @Test
    public void changeUserPwd() {


        IUserRegisterService userRegisterService = SpringUtils.getBean(IUserRegisterService.class);
        IAdminService adminService = SpringUtils.getBean(IAdminService.class);
        // 获取所有外部用户、live用户、内部用户
        List<UserRegister> userRegisters = userRegisterService.findAllUsers();
        for (UserRegister userRegister : userRegisters) {

            if (userRegister.getWebsiteUserType() == 6) {
                Admin admin = adminService.findByUserId(userRegister.getId());
                if (admin != null) {
                    String salt = SaltCreateUtil.createSecureSalt();
                    String newpwd = DigestUtils.sha512Hex(salt + admin.getPassword() + admin.getName());
                    admin.setSalt(salt);
                    admin.setPassword(newpwd);
                    adminService.doUpdateById(admin);
                    userRegister.setSalt(salt);
                    userRegister.setPassword(newpwd);
                    userRegisterService.doUpdateById(userRegister);
                }
            } else {
                String salt = SaltCreateUtil.createSecureSalt();
                userRegister.setSalt(salt);
                String newpwd = DigestUtils.sha512Hex(salt + userRegister.getPassword() + userRegister.getEmail());
                userRegister.setPassword(newpwd);
                userRegisterService.doUpdateById(userRegister);
            }
        }

    }

}

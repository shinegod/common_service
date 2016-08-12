package com.fx.util;

import com.fx.crm.sys.log.model.OperationLogger;
import com.fx.crm.sys.log.model.OperationReference;
import com.fx.user.model.LoginUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.UUID;

/**
 * 日志记录工具类
 * Created by bei2love@gmail.com on 15/9/6.
 */
public class OperationLoggerUtil {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

    public static final Logger logger = LoggerFactory.getLogger(OperationLoggerUtil.class);

    private static class LOG_TYPE{

        public static final String LOG_TYPE_ACCESS = "ACCESS";

        public static final String LOG_TYPE_EXCEPTION = "EXCEPTION";

    }

    private static Map<String, OperationReference> referenceMap = CacheMgr.getOperationReference();

    public static void LogToDB(HttpServletRequest request, Object handler, Exception ex, String title) {
        OperationReference or = referenceMap.get(request.getRequestURI());
//        UserRegister user = UserUtil.getCurrUser();
//        Admin user = UserUtil.getCurrAdmin();
        //TODO: 缓存用户对象修改后修改此处代码
//        if (user != null && user.getId() != null){
            OperationLogger log = new OperationLogger();
            log.setId(UUID.randomUUID().toString());
            log.setSystemId(Config.getSystemId());
            if(Constants.SYSTEM_CRM.equals(Config.getSystemId())){
                log.setOperator(String.valueOf(UserUtil.getCurrAdmin().getUserId()));
                log.setLoginname(UserUtil.getCurrAdmin().getLoginName());
            }else if(Constants.SYSTEM_TRADER.equals(Config.getSystemId())){
                LoginUserVo loginUserVo = SessionUtil.getLoginSession(request);
                if (loginUserVo != null) {
                    log.setOperator("" + loginUserVo.getUserId());
                    log.setLoginname(loginUserVo.getEmail());
                }
            } else {
                logger.info("not support systemid , please check configs.properties config");
                return;
            }
            log.setException(Exceptions.getStackTraceAsString(ex));
            log.setOperateTime(sdf.format(System.currentTimeMillis()));
            log.setOperationip(StringUtils.getRemoteAddr(request));
            log.setLogType(ex == null ? LOG_TYPE.LOG_TYPE_ACCESS : LOG_TYPE.LOG_TYPE_EXCEPTION);
            log.setUserAgent(request.getHeader("user-agent"));
            log.setOperationUrl(request.getRequestURI());
            log.setReqParams(StringUtils.toRequestParams(request.getParameterMap()));
            log.setReqMethod(request.getMethod());
            if (or != null) {
                log.setModule(or.getMenuid());
                log.setOperationType(or.getOperationType());
            }
            // 异步保存日志
            new OperationLoggerThread(log, handler, ex).start();
//        }
    }


}

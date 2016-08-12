package com.fx.crm.online;

import com.fx.shiro.UserCacheEntity;
import com.fx.util.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by bei2love@gmail.com on 15/9/22.
 */
@Service
public class UserOnlineService {

    private static final Logger logger = LoggerFactory.getLogger(UserOnlineService.class);

    /**
     * 批量删除在线用户
     * @param needOfflineIdList
     */
    public void batchOffline(List<String> needOfflineIdList) {
        logger.debug("批量离线用户 {}" , needOfflineIdList.size());
    }

    /**
     * 查询过期会话
     * @param expiredDate
     * @param pageRequest
     * @return
     */
    public List<UserCacheEntity<?>> findExpiredUserOnlineList(Date expiredDate, Pagination pageRequest) {
        logger.debug("查找过期用户:{}", expiredDate);
        return null;
    }
}

package com.lbc.mo.init;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbc.mo.bean.GroupUsers;
import com.lbc.mo.constants.MonitorConstants;
import com.lbc.mo.netty.StreamService;
import com.lbc.mo.service.AsyncService;
import com.lbc.mo.utils.HttpUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Init implements ApplicationRunner {
    private static final Log LOG = LogFactory.getLog(Init.class);

    @Autowired
    AsyncService asyncService;
    @Value("${user_list}")
    String userListUrl;
    @Value("${server.port}")
    int port;


    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        // period system
        if (MonitorConstants.PERIOD_SERVICE == port) {
            LOG.info("Periodc service Initializing caches");
            String groupUsersResponse = HttpUtil.httpGet(userListUrl.replace(MonitorConstants.GROUPID_PARM, MonitorConstants.AI_LAB.toString()));
            ObjectMapper mapper = new ObjectMapper();
            GroupUsers groupUsers = mapper.readValue(groupUsersResponse, GroupUsers.class);
            List<GroupUsers.User> users = groupUsers.getUsers();
            List<String> collect = users.stream().map(GroupUsers.User::getId).collect(Collectors.toList());
            collect.forEach(s -> {
                asyncService.initAiLabUserCache(s);
            });
            LOG.info("Initialization complete");

        } else if (MonitorConstants.CONTAINERS_STATISTICS_SERVICE == port) {
            LOG.info("Containers_statistics service Initializing caches");
            LOG.info("Initialization complete");
        } else {
            StreamService streamService = new StreamService();
            streamService.serviceInit();
        }
    }
}

package com.lbc.mo.service;

import com.lbc.mo.bean.GroupUsers;
import org.springframework.stereotype.Service;

public interface TestService {

    GroupUsers.User getUserInfoCache(String user);

    String testCache(String user);

    void conNetty();
}

package com.lbc.mo.service;

import com.lbc.mo.bean.GroupUsers;

public interface TestService {

    GroupUsers.User getUserInfoCache(String user);

    String testCache(String user);

    public String testEvictCache(String user);

    void conNetty();
}

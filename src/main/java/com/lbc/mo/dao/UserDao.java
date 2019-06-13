package com.lbc.mo.dao;

import com.lbc.mo.entity.User;

public interface UserDao {

    User selectByPrimaryKey(Integer id);

}

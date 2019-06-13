package com.lbc.mo.dao.impl;

import com.lbc.mo.constants.DataSourceKey;
import com.lbc.mo.dao.UserDao;
import com.lbc.mo.dao.mapper.UserMapper;
import com.lbc.mo.entity.User;
import com.lbc.mo.utils.TargetDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    UserMapper userMapper;

    @Override
    @TargetDataSource(dataSourceKey = DataSourceKey.DB_slave)
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}

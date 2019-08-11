package com.lbc.corgi;

import com.lbc.mo.Application;
import com.lbc.mo.dao.UserDao;
import com.lbc.mo.entity.JpaUser;
import com.lbc.mo.entity.User;
import com.lbc.mo.repository.UserRepository;
import com.lbc.mo.utils.ExcelUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class UserTest {

    @Autowired
    UserDao userDao;
    @Autowired
    UserRepository userRepository;

    @Test
    public void switchDataSource() {
        User user = userDao.selectByPrimaryKey(1);
        Assert.assertEquals(user.getUserName(), "changlb");
    }

    @Test
    public void createExcel() {
        User user = userDao.selectByPrimaryKey(1);
        String excel = ExcelUtil.createExcel(Arrays.asList(user));
        System.out.println(excel);
    }

    @Test
    public void getUser() {
        JpaUser one = userRepository.getOne(1);
        System.out.printf(one.getName());
    }
}

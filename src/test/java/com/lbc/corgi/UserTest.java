package com.lbc.corgi;

import com.lbc.mo.Application;
import com.lbc.mo.dao.AppStateRepository;
import com.lbc.mo.dao.AppStateSpecifications;
import com.lbc.mo.dao.UserDao;
import com.lbc.mo.entity.AppState;
import com.lbc.mo.entity.User;
import com.lbc.mo.service.TestService;
import com.lbc.mo.utils.DateCtrlUtil;
import com.lbc.mo.utils.ExcelUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class UserTest {

    @Autowired
    UserDao userDao;
    @Autowired
    AppStateRepository appStateRepository;
    @Autowired
    TestService testService;

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
    public void testRepository() {
        AppState one = appStateRepository.findOne(new AppStateSpecifications.StartTimeEqualSpec(DateCtrlUtil.strToDate("2019-08-12 14:38:17")));

        System.out.println(one.getUser());
    }

    @Test
    public void saveRepository() {
        testService.saveTest();
    }
}

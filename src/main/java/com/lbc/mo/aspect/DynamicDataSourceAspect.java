package com.lbc.mo.aspect;

import com.lbc.mo.sharding.DynamicDataSourceContextHolder;
import com.lbc.mo.utils.TargetDataSource;
import com.lbc.mo.constants.DataSourceKey;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DynamicDataSourceAspect {
    //指定执行数据库操作的位置
    @Pointcut("execution(* com.lbc.mo.dao.impl.*(..))")
    public void pointCut() {

    }

    @Before("@annotation(targetDataSource)")
    public void before(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        DataSourceKey dataSourceKey = targetDataSource.dataSourceKey();
        if (dataSourceKey == DataSourceKey.DB_master) {
            System.out.println(String.format("current dataSource：%s", DataSourceKey.DB_master));
            DynamicDataSourceContextHolder.set(dataSourceKey);
        } else {
            System.out.println(String.format("current dataSource：%s", DataSourceKey.DB_slave));
            DynamicDataSourceContextHolder.set(dataSourceKey);
        }
    }

    @After("@annotation(targetDataSource)")
    public void doAfter(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        System.out.println(String.format("current dataSource  %s  execute clean", targetDataSource.dataSourceKey()));
        DynamicDataSourceContextHolder.clear();
    }

}

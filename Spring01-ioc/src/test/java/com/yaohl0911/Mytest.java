package com.yaohl0911;

import com.yaohl0911.dao.UserDao;
import com.yaohl0911.dao.UserDaoImpl;
import com.yaohl0911.dao.UserDaoMysqlImpl;
import com.yaohl0911.dao.UserDaoOracleImpl;
import com.yaohl0911.service.UserService;
import com.yaohl0911.service.UserServiceImpl;
import org.junit.Test;

public class Mytest {
    @Test
    public void UserServiceImplTest() {
        UserService UserService= new UserServiceImpl();
//        UserDao userDao = new UserDaoImpl();
//        UserDao userDao = new UserDaoMysqlImpl();
        UserDao userDao = new UserDaoOracleImpl();
        ((UserServiceImpl) UserService).setUserDao(userDao);
        UserService.getUser();
    }
}

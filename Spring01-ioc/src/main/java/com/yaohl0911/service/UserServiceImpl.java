package com.yaohl0911.service;

import com.yaohl0911.dao.UserDao;
import com.yaohl0911.dao.UserDaoImpl;
import com.yaohl0911.dao.UserDaoMysqlImpl;
import com.yaohl0911.dao.UserDaoOracleImpl;

import javax.jws.soap.SOAPBinding;

public class UserServiceImpl implements UserService{
//    private UserDao userDao = new UserDaoImpl();
//    private UserDao userDao = new UserDaoMysqlImpl();
//    private UserDao userDao = new UserDaoOracleImpl();


    // 利用set方法可以动态实现对Dao层实现的控制
    // 具体要使用那个Dao层实现，可以在用户层控制，而不需要每次修改service的层的代码
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {  // 封装一层，用户直接用service接口，不需要感知dao层实现
        userDao.getUser();
    }
}

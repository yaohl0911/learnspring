package com.yaohl0911.service;

import com.yaohl0911.dao.UserDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService{
    private UserDao userDao;
    private String name;


    public void sayHello() {
        System.out.println("Hello, " + name);
    }

    @Override
    public void getUser() {  // 封装一层，用户直接用service接口，不需要感知dao层实现
        userDao.getUser();
    }
}

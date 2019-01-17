package com.linkjb.service.Impl;

import com.linkjb.dao.UserDao;
import com.linkjb.entity.User;
import com.linkjb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sharkshen
 * @data 2019/1/17 14:35
 * @Useage
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public Integer RegistUser(User user) {
        return userDao.RegistUser(user);
    }
}

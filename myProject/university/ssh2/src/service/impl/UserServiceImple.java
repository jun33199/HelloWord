package service.impl;

import dao.UserDao;
import bean.User;
import service.UserService;

public class UserServiceImple implements UserService {
    private UserDao userDao;
   
    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean check(User user) {
        if(this.userDao.check(user)){
            return true;
        }else{
            return false;
        }
    }
}

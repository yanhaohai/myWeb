package xin.yanhaohai.myweb.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.yanhaohai.myweb.dao.UserDao;
import xin.yanhaohai.myweb.domain.User;
import xin.yanhaohai.myweb.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao dao;
    @Override
    public User getUser(String username, String password) {
        User user = dao.getUser(username, password);
        return user;
    }
}

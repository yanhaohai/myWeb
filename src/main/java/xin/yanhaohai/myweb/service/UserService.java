package xin.yanhaohai.myweb.service;

import xin.yanhaohai.myweb.domain.User;

public interface UserService {
    User getUser(String username,String password);
}

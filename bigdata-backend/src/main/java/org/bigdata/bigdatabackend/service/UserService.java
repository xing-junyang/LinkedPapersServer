package org.bigdata.bigdatabackend.service;

import org.bigdata.bigdatabackend.vo.UserVO;

public interface UserService {
    boolean register(UserVO userVO);
    String login(String email, String password);
    UserVO getUserInfo();
    boolean updateUserRole(String userId, String role);

}
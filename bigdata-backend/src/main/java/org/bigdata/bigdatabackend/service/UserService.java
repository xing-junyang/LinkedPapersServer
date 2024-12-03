package org.bigdata.bigdatabackend.service;

import org.bigdata.bigdatabackend.vo.HistoryVO;
import org.bigdata.bigdatabackend.vo.UserVO;

import java.util.List;

public interface UserService {
    boolean register(UserVO userVO);
    String login(String email, String password);
    UserVO getUserInfo();
    boolean updateUserRole(Integer userId, String role);
    List<HistoryVO> getUserHistory(Integer userId);

}
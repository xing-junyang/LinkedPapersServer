package org.bigdata.bigdatabackend.controller;

import org.bigdata.bigdatabackend.enums.RoleEnum;
import org.bigdata.bigdatabackend.exception.BigDataException;
import org.bigdata.bigdatabackend.service.UserService;
import org.bigdata.bigdatabackend.util.SecurityUtil;
import org.bigdata.bigdatabackend.util.TokenUtil;
import org.bigdata.bigdatabackend.vo.HistoryVO;
import org.bigdata.bigdatabackend.vo.ResultVO;
import org.bigdata.bigdatabackend.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SecurityUtil securityUtil;

    @PostMapping("/register")
    public ResultVO<Boolean> register(@RequestBody UserVO userVO) {
        return ResultVO.buildSuccess(userService.register(userVO));
    }

    @PostMapping("/login")
    public ResultVO<String> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        return ResultVO.buildSuccess(userService.login(email, password));
    }

    @GetMapping("/getUserInfo")
    public ResultVO<UserVO> getUserInfo() {
        return ResultVO.buildSuccess(userService.getUserInfo());
    }

    @GetMapping("/updateUserRole")
    public ResultVO<Boolean> updateUserRole(@RequestParam("userId") Integer userId, @RequestParam("role") String role) {
        UserVO userVO = userService.getUserInfo();
        if (userVO.getRole()== RoleEnum.VIP) {
            throw BigDataException.permissionDenied();
        }
        return ResultVO.buildSuccess(userService.updateUserRole(userId, role));
    }

    @GetMapping("/getUserHistory")
    public ResultVO<List<HistoryVO>> getUserHistory(@RequestParam("userId") Integer userId) {
        return ResultVO.buildSuccess(userService.getUserHistory(userId));
    }


}
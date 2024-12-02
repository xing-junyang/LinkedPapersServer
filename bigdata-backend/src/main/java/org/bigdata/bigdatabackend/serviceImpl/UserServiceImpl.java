package org.bigdata.bigdatabackend.serviceImpl;

import org.bigdata.bigdatabackend.BigdataBackendApplication;
import org.bigdata.bigdatabackend.enums.RoleEnum;
import org.bigdata.bigdatabackend.exception.BigDataException;
import org.bigdata.bigdatabackend.po.User;
import org.bigdata.bigdatabackend.repository.UserRepository;
import org.bigdata.bigdatabackend.service.UserService;
import org.bigdata.bigdatabackend.util.SecurityUtil;
import org.bigdata.bigdatabackend.util.TokenUtil;
import org.bigdata.bigdatabackend.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    SecurityUtil securityUtil;


    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private BigdataBackendApplication bigdataBackendApplication;

    @Override
    public boolean register(UserVO userVO) {
        User user = userRepository.findByEmail(userVO.getEmail());
        if (user != null) {
            throw BigDataException.userAlreadyExists();
        }
        User newUser = userVO.toPO();
        newUser.setRegisterTime(new Date());
        newUser.setPassword(passwordEncoder.encode(userVO.getPassword()));
        newUser.setRole(RoleEnum.NORMAL);
        userRepository.save(newUser);
        return true;
    }

    @Override
    public String login(String email, String password) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                user.setLastLoginTime(new Date());
                return tokenUtil.getToken(user);
            } else {
                throw BigDataException.passwordError();
            }
        }
        throw BigDataException.emailNotFound();
    }

    @Override
    public UserVO getUserInfo() {
        User user = securityUtil.getCurrentUser();
        return user.toVO();
    }

    @Override
    public boolean updateUserRole(String userId, String role) {
        Optional<User> userOptional = userRepository.findById(Integer.parseInt(userId));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setRole(RoleEnum.valueOf(role));
            if (role.equals("VIP")) {
                Date vipExpireTime = new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000); // 1 month
                user.setVipExpireTime(vipExpireTime);
            } else {
                user.setVipExpireTime(null);
            }
            userRepository.save(user);
            return true;
        } else {
            throw BigDataException.userNotFound();
        }
    }
}
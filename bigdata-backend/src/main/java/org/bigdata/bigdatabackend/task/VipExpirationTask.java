package org.bigdata.bigdatabackend.task;

import org.bigdata.bigdatabackend.enums.RoleEnum;
import org.bigdata.bigdatabackend.po.User;
import org.bigdata.bigdatabackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class VipExpirationTask {

    @Autowired
    private UserRepository userRepository;

    @Scheduled(cron = "0 0 0 * * ?") // 每天午夜执行一次
    public void checkVipExpiration() {
        List<User> users = userRepository.findAll();
        Date now = new Date();
        for (User user : users) {
            if (user.getRole() == RoleEnum.VIP && user.getVipExpireTime() != null && user.getVipExpireTime().before(now)) {
                user.setRole(RoleEnum.NORMAL);
                user.setVipExpireTime(null);
                userRepository.save(user);
            }
        }
    }
}
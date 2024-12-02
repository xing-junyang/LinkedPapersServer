package org.bigdata.bigdatabackend.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bigdata.bigdatabackend.enums.RoleEnum;
import org.bigdata.bigdatabackend.po.User;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserVO {

    private Integer userId;

    private String username;

    private String email;

    private String password;

    private RoleEnum role;

    private Date registerTime;

    private Date lastLoginTime;


    private Date vipExpireTime;

    public User toPO() {
        User user = new User();
        user.setUserId(this.userId);
        user.setUsername(this.username);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setRole(this.role);
        user.setRegisterTime(this.registerTime);
        user.setLastLoginTime(this.lastLoginTime);
        user.setVipExpireTime(this.vipExpireTime);
        return user;
    }
}
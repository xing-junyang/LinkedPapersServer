package org.bigdata.bigdatabackend.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bigdata.bigdatabackend.enums.UserEnum;
import org.bigdata.bigdatabackend.po.User;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserVO {

    private Integer userId;

    private String username;

    private String email;

    private String password;

    private Date createTime;

    private UserEnum role;

    private List<String> phones;

    private List<String> addresses;

    public User toPO() {
        User user = new User();
        user.setUserId(this.userId);
        user.setUsername(this.username);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setCreateTime(this.createTime);
        user.setRole(this.role);
        return user;
    }
}
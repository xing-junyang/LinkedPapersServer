package org.bigdata.bigdatabackend.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bigdata.bigdatabackend.enums.RoleEnum;
import org.bigdata.bigdatabackend.vo.UserVO;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user", indexes = {
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_email_password", columnList = "email,password"),
        @Index(name = "idx_email", columnList = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    private Integer userId;

    @Basic
    @Column(name = "username")
    private String username;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Basic
    @Column(name = "register_time")
    private Date registerTime;

    @Basic
    @Column(name = "last_login_time")
    private Date lastLoginTime;


    @Basic
    @Column(name = "vip_expire_time")
    private Date vipExpireTime;

    public UserVO toVO() {
        UserVO userVO = new UserVO();
        userVO.setUserId(this.userId);
        userVO.setUsername(this.username);
        userVO.setEmail(this.email);
        userVO.setPassword(this.password);
        userVO.setRole(this.role);
        userVO.setRegisterTime(this.registerTime);
        userVO.setLastLoginTime(this.lastLoginTime);
        userVO.setVipExpireTime(this.vipExpireTime);
        return userVO;
    }
}
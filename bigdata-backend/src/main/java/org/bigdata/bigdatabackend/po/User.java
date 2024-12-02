package org.bigdata.bigdatabackend.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bigdata.bigdatabackend.enums.UserEnum;
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
    @Column(name = "create_time")
    private Date createTime;

    @Basic
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserEnum role;



    public UserVO toVO() {
        UserVO userVO = new UserVO();
        userVO.setUserId(this.userId);
        userVO.setUsername(this.username);
        userVO.setEmail(this.email);
        userVO.setPassword(this.password);
        userVO.setCreateTime(this.createTime);
        userVO.setRole(this.role);

        return userVO;
    }
}
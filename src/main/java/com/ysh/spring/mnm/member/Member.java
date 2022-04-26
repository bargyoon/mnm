package com.ysh.spring.mnm.member;

import java.util.Date;


import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Member {

    @Id
    @GeneratedValue
    private Long userIdx;

    private String userName;
    private String userId;
    private String password;
    private String email;
    private String gender;
    private String address;
    private String phone;
    private String nickname;
    private String userRole;

    @Column(columnDefinition = "TIMESTAMP DEFAULT now()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDate;

    @Column(columnDefinition = "integer default 0")
    private Boolean isLeave;
    private String kakaoJoin;
}

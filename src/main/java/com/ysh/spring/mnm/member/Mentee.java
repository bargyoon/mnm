package com.ysh.spring.mnm.member;

import javax.persistence.*;

import com.sun.istack.NotNull;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Mentee {

    @Id
    @GeneratedValue
    private Long menteeIdx;
    @NotNull
    private String userName;
    @NotNull
    private String userId;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private String gender;
    @NotNull
    private String address;
    @NotNull
    private String phone;
    private String userRole;

    @Column(columnDefinition = "TIMESTAMP DEFAULT now()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDate;

    @Column(columnDefinition = "integer default 0")
    private Boolean isLeave;
    private String kakaoJoin;
    private String schoolName;
    private String major;
    private int grade;
    private String hopeUniversity;
    private String hopeMajor;
}

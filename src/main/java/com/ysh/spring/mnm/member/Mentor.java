package com.ysh.spring.mnm.member;

import javax.persistence.*;

import com.ysh.spring.mnm.common.util.file.FileInfo;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Mentor {

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
    private String universityName;
    private String universityType;
    private int grade;
    private String major;
    private String wantDay;
    private String wantTime;
    private String requirement;
    private String history;

    @Column(columnDefinition = "integer default 0")
    private int mentoringCnt;

    @Column(columnDefinition = "integer default 0")
    private Boolean profileImg;
    private String accountNum;
    private String bank;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FileInfo fileInfo;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FileInfo qrInfo;

}

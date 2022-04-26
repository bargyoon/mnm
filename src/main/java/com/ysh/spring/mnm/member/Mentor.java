package com.ysh.spring.mnm.member;

import javax.persistence.*;

import com.ysh.spring.mnm.common.util.file.FileInfo;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Mentor {

    @Id
    @GeneratedValue
    private Long mentorIdx;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    private Member member;
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

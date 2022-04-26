package com.ysh.spring.mnm.member;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Mentee {

    @Id
    @GeneratedValue
    private Long menteeIdx;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    private Member member;
    private String schoolName;
    private String major;
    private int grade;
    private String hopeUniversity;
    private String hopeMajor;
}

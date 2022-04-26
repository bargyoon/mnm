package com.ysh.spring.mnm.mentoring;

import java.time.LocalDate;

import javax.persistence.*;

import com.ysh.spring.mnm.member.Member;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import lombok.Data;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class MentoringHistory {

    @Id
    @GeneratedValue
    private int mentoringIdx;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    private Member member;

    private String state;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate epDate;
    private int price;
    private String menteeName;


}

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
public class MentorRating {

    @Id
    @GeneratedValue
    private Long ratingIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    private Member member;
    @Column(columnDefinition = "integer default 0")
    private Boolean kindness;
    @Column(columnDefinition = "integer default 0")
    private Boolean communication;
    @Column(columnDefinition = "integer default 0")
    private Boolean professional;
    @Column(columnDefinition = "integer default 0")
    private Boolean processRate;
    @Column(columnDefinition = "integer default 0")
    private Boolean appointment;
    @Column(columnDefinition = "integer default 0")
    private Boolean explanation;
    private String rate_comment;
    @Column(columnDefinition = "integer default 0")
    private Boolean isDeleted;


}

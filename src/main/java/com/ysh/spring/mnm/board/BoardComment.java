package com.ysh.spring.mnm.board;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ysh.spring.mnm.member.Member;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import lombok.Data;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class BoardComment {

    @Id
    @GeneratedValue
    private Long coIdx;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userIdx")
    private Member member;
    private String coContent;
    @Column(columnDefinition = "TIMESTAMP DEFAULT now()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;
    @Column(columnDefinition = "integer default 0")
    private int recCount;
    @Column(columnDefinition = "integer default 0")
    private int cmType;
    @Column(columnDefinition = "integer default 0")
    private int prIdx;
    private Long bdIdx;

}

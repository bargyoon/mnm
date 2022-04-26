package com.ysh.spring.mnm.todo;

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
public class Todo {

    @Id
    @GeneratedValue
    private Long todoIdx;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    private Member member;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    @Column(columnDefinition = "integer default 0")
    private Boolean isDone;
    private String todoColor;


}

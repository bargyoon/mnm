package com.ysh.spring.mnm.board;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.sun.istack.NotNull;
import com.ysh.spring.mnm.member.Member;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import lombok.Data;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Board {

    @Id
    @GeneratedValue
    private Long bdIdx;
    @NotNull
    private String bdTitle;
    @Column(columnDefinition = "TIMESTAMP DEFAULT now()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;
    @Column(columnDefinition = "integer default 0")
    private int viewCount;
    @Column(columnDefinition = "integer default 0")
    private int recCount;
    @NotNull
    private String bdContent;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userIdx")
    private Member member;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bdIdx")
    private List<BoardComment> boardComment = new ArrayList<>();

}

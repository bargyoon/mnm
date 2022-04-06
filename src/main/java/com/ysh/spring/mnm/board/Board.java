package com.ysh.spring.mnm.board;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

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
	private String bdTitle;
	@Column(columnDefinition = "TIMESTAMP DEFAULT now()")
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate;
	@Column(columnDefinition = "integer default 0")
	private int viewCount;
	@Column(columnDefinition = "integer default 0")
	private int recCount;
	private String bdContent;
	@ManyToOne
	@JoinColumn(name = "userIdx")
	private Member member;
	
}

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
@DynamicUpdate
@DynamicInsert
public class BoardComment {

	@Id
	@GeneratedValue
	private Long coIdx;
	@ManyToOne
	@JoinColumn(name = "userIdx")
	private Member member;
	@ManyToOne
	@JoinColumn(name = "bdIdx")
	private Board board;
	private String coContent;
	@Column(columnDefinition = "TIMESTAMP DEFAULT now()")
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate;
	@Column(columnDefinition = "integer default 0")
	private int recCount;
	
	
}

package com.ysh.spring.mnm.board;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	@Column(columnDefinition = "date default sysdate")
	private LocalDate regDate;
	@Column(columnDefinition = "number default 0")
	private int recCount;
	
	
}

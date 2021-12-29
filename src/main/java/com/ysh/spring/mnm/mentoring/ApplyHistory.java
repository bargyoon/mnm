package com.ysh.spring.mnm.mentoring;

import java.util.Date;

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
@DynamicInsert
@DynamicUpdate
public class ApplyHistory {

	@Id
	@GeneratedValue
	private int applyIdx;
	@ManyToOne
	@JoinColumn(name = "userIdx")
	private Member member;
	
	@Column(columnDefinition = "date default sysdate")
	private Date applyDate;
	@Column(columnDefinition = "date default sysdate")
	private Date epDate;
	@Column(columnDefinition = "number default 0")
	private int reapplyCnt;
	


}

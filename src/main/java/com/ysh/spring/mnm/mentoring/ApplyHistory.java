package com.ysh.spring.mnm.mentoring;

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
public class ApplyHistory {

	@Id
	@GeneratedValue
	private int applyIdx;
	@ManyToOne
	@JoinColumn(name = "userIdx")
	private Member member;

	@Column(columnDefinition = "TIMESTAMP DEFAULT now()")
	@Temporal(TemporalType.TIMESTAMP)
	private Date applyDate;
	@Column(columnDefinition = "TIMESTAMP DEFAULT now()")
	@Temporal(TemporalType.TIMESTAMP)
	private Date epDate;
	@Column(columnDefinition = "integer default 0")
	private int reapplyCnt;
	


}

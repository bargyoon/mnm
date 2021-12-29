package com.ysh.spring.mnm.mentoring;

import java.time.LocalDate;

import javax.persistence.CascadeType;
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
public class MentoringHistory {

	@Id
	@GeneratedValue
	private int mentoringIdx;
	@ManyToOne
	@JoinColumn(name = "userIdx")
	private Member member;

	private String state;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate epDate;
	private int price;
	private String menteeName;
	

}

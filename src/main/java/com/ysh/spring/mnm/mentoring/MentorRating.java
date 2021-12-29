package com.ysh.spring.mnm.mentoring;

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
@DynamicInsert
@DynamicUpdate
public class MentorRating {

	@Id
	@GeneratedValue
	private Long ratingIdx;
	
	@ManyToOne
	@JoinColumn(name = "userIdx")
	private Member member;
	@Column(columnDefinition = "number default 0")
	private Boolean kindness;
	@Column(columnDefinition = "number default 0")
	private Boolean communication;
	@Column(columnDefinition = "number default 0")
	private Boolean professional;
	@Column(columnDefinition = "number default 0")
	private Boolean processRate;
	@Column(columnDefinition = "number default 0")
	private Boolean appointment;
	@Column(columnDefinition = "number default 0")
	private Boolean explain;
	private String rate_comment;
	@Column(columnDefinition = "number default 0")
	private Boolean isDel;
	
	
	
	
	
}

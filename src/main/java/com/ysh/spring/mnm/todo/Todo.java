package com.ysh.spring.mnm.todo;

import java.time.LocalDate;

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
public class Todo {

	@Id
	@GeneratedValue
	private Long todoIdx;
	@ManyToOne
	@JoinColumn(name = "userIdx")
	private Member member;
	private LocalDate startDate;
	private LocalDate endDate;
	private String title;
	@Column(columnDefinition = "integer default 0")
	private Boolean isDone;
	private String todoColor;
	
	
}

package com.ysh.spring.mnm.member.validator;




import com.ysh.spring.mnm.member.Member;
import lombok.Data;

@Data
public class JoinForm {

	private String userId;
	private String password;
	private String email;
	private String phone;
	
	public Member convertToMember() {
		Member member = new Member();
		member.setUserId(userId);
		member.setPassword(password);
		member.setEmail(email);
		member.setPhone(phone);
		return member;
	}
	
	
	
	
	
	
}

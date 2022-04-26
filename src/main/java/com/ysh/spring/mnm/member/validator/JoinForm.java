package com.ysh.spring.mnm.member.validator;


import com.ysh.spring.mnm.member.Member;
import lombok.Data;

@Data
public class JoinForm {

    private String userName;
    private String userId;
    private String password;
    private String rePassword;
    private String email;
    private String address;
    private String phone;


    public Member convertToMember() {
        Member member = new Member();
        member.setUserName(userName);
        member.setUserId(userId);
        member.setPassword(password);

        member.setEmail(email);
        member.setAddress(address);
        member.setPhone(phone);
        return member;
    }


}

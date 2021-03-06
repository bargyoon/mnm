package com.ysh.spring.mnm.member.validator;

import com.ysh.spring.mnm.member.MemberRepository;
import com.ysh.spring.mnm.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class JoinFormValidator implements Validator {

    @Autowired
    private final MemberRepository memberRepository;


    public JoinFormValidator(MemberRepository memberRepository) {
        super();
        this.memberRepository = memberRepository;

    }

    @Override
    public boolean supports(Class<?> clazz) {

        return clazz.equals(JoinFormMentor.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        JoinFormMentor mentor = (JoinFormMentor) target;

        // 비밀번호 확인이 비밀번호와 같은지 확인
//        if (!mentor.getRePassword().equals(mentor.getPassword())) {
//            errors.rejectValue("rePassword", "err-rePassword", "비밀번호가 일치하지 않습니다.");
//        }


        //비밀번호가 8글자 이상, 숫자 영문자 특수문자 조합인 지 확인
        if (!Pattern.matches("(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9]).{8,}", mentor.getPassword())) {
            errors.rejectValue("password", "err-password", "비밀번호는 숫자 영문자 특수문자 조합인 8글자 이상의 문자열 입니다.");
        }
        //전화번호가 9-11자리의 숫자
        if (!Pattern.matches("\\d{9,11}", mentor.getPhone())) {
            errors.rejectValue("phone", "err-phone", "전화번호는 9-11자리의 숫자입니다.");
        }
    }

}

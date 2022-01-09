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
    @Autowired
    private final MemberService memberService;

    public JoinFormValidator(MemberRepository memberRepository, MemberService memberService) {
        super();
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return clazz.equals(JoinForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        JoinForm form = (JoinForm)target;

        //1. 아이디 존재 유무
// 사용자 아이디가 DB에 이미 존재하는 지 확인
//        if (memberService.selectMemberById(userId) != null || userId.equals("")) {
//            errors.rejectValue("userId", "err-userId", "아이디를 입력해주세요.");
//        }

        // 사용자 이메일이 DB에 이미 존재하는 지 확인
//        if (memberService.selectMemberByEmail(form.getEmail()) != null || form.getEmail().equals("")) {
//            errors.rejectValue("email", "err-email", "존재하는 이메일입니다..");
//        }

        // 비밀번호 확인이 비밀번호와 같은지 확인
        if (!rePassword.equals(password)) {
            failedValidation.put("passwordConfirmation", rePassword);
            isFailed = true;
        }




        //2. 비밀번호가 8글자 이상, 숫자 영문자 특수문자 조합인 지 확인
        if(!Pattern.matches("(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9]).{8,}", form.getPassword())) {
            errors.rejectValue("password", "err-password", "비밀번호는 숫자 영문자 특수문자 조합인 8글자 이상의 문자열 입니다.");
        }
        //3. 전화번호가 9-11자리의 숫자
        if(!Pattern.matches("\\d{9,11}", form.getPhone())) {
            errors.rejectValue("phone", "err-phone", "전화번호는 9-11자리의 숫자입니다.");
        }
    }

}

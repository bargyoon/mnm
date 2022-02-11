package com.ysh.spring.mnm.member.validator;

import com.ysh.spring.mnm.member.Member;
import com.ysh.spring.mnm.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class ModifyPasswordValidator implements Validator {

    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public ModifyPasswordValidator(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(ModifyPassword.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ModifyPassword modifyPw = (ModifyPassword) target;
        Member certifiedMember = new Member();
        if(!passwordEncoder.matches(modifyPw.getCurPw(), certifiedMember.getPassword())){
            errors.rejectValue("curPw", "err-curPw", "비밀번호가 틀렸습니다.");
        }

        //이전 비밀번호와 일치 확인
        if(passwordEncoder.matches(modifyPw.getNewPw(), certifiedMember.getPassword())){
            errors.rejectValue("newPw", "err-newPw", "이전과 비밀번호가 동일합니다 이전과 다른 비밀번호로 설정해주세요.");
        }

        // 비밀번호가 8글자 이상, 숫자 영문자 특수문자 조합인지 확인
        boolean valid = Pattern.matches("(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9]).{8,}", modifyPw.getNewPw());
        if(!valid){
            errors.rejectValue("newPw", "err-newPw", "비밀번호는 8글자 이상의 숫자 영문자 특수문자 조합입니다.");
        }

        //
        if(!modifyPw.getNewPw().equals(modifyPw.getConfirmNewPw())){
            errors.rejectValue("newPw", "err-newPw", "비밀번호가 일치하지 않습니다.");
        }


    }
}

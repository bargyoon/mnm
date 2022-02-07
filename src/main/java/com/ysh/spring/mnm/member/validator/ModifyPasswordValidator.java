package com.ysh.spring.mnm.member.validator;

import com.ysh.spring.mnm.member.Member;
import com.ysh.spring.mnm.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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



    }
}

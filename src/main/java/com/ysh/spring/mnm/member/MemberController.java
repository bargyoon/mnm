package com.ysh.spring.mnm.member;

import com.ysh.spring.mnm.common.validator.ValidatorResult;
import com.ysh.spring.mnm.member.validator.JoinForm;
import com.ysh.spring.mnm.member.validator.JoinFormValidator;
import com.ysh.spring.mnm.member.validator.ModifyPasswordValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.UUID;


@Controller
@RequestMapping("member")
public class MemberController {

    private MemberService memberService;
    private JoinFormValidator joinFormValidator;
    private ModifyPasswordValidator modifyPasswordValidator;

    public MemberController(MemberService memberService, JoinFormValidator joinFormValidator, ModifyPasswordValidator modifyPasswordValidator) {
        super();
        this.memberService = memberService;
        this.joinFormValidator = joinFormValidator;
        this.modifyPasswordValidator = modifyPasswordValidator;
    }

    @InitBinder(value="joinForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(joinFormValidator);
    }

    @GetMapping("login")
    public void login(){

    }

    @GetMapping("join")
    public String join(){

        return "member/join-rule";
    }

    @GetMapping("join-mentee")
    public void menteeJoin(){

    }

    @GetMapping("join-mentor")
    public void mentorJoin(){

    }

    @GetMapping("mypage")
    public void myPage(){

    }

    @GetMapping("forget-password")
    public void forgetPassword(){

    }

    @GetMapping("confirm-pw")
    public void confirmPassword(){

    }

    @GetMapping("change-pw")
    public void changePassword(){

    }

    @PostMapping("join-mentor")
    public String joinMentor(@Validated JoinForm form
            , Errors errors
            , Model model
            , HttpSession session
            , RedirectAttributes redirectAttr){
        ValidatorResult vr = new ValidatorResult();
        model.addAttribute("error", vr.getError());

        if(errors.hasErrors()) {
            vr.addErrors(errors);
            return "member/join-mentor";
        }
        String token = UUID.randomUUID().toString();
        session.setAttribute("persistToken", token);
        session.setAttribute("persistUser", form);

        redirectAttr.addFlashAttribute("message","회원가입완료를 위한 이메일이 발송되었습니다.");

        //memberService.authenticateByEmail(form,token);

        return "redirect:/";

    }



}

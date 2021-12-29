package com.ysh.spring.mnm.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
public class MemberController {

    @GetMapping("login")
    public void login(){

    }

    @GetMapping("join")
    public String join(){
        return "member/join-rule";
    }


}

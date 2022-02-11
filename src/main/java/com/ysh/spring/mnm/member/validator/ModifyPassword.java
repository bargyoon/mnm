package com.ysh.spring.mnm.member.validator;

import lombok.Data;

@Data
public class ModifyPassword {

    private String userId;
    private String email;
    private String curPw;
    private String newPw;
    private String confirmNewPw;

}

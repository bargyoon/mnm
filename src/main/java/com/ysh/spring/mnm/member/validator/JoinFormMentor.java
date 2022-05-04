package com.ysh.spring.mnm.member.validator;


import com.ysh.spring.mnm.common.util.file.FileInfo;
import com.ysh.spring.mnm.member.Member;
import com.ysh.spring.mnm.member.Mentor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class JoinFormMentor {

    private Long userIdx;
    private String userName;
    private String userId;
    private String password;
    private String email;
    private String gender;
    private String address;
    private String phone;
    private String userRole;
    private Boolean isLeave;
    private String kakaoJoin;
    private String universityName;
    private String universityType;
    private int grade;
    private String major;
    private String wantDay;
    private String wantTime;
    private String requirement;
    private String history;
    private int mentoringCnt;
    private Boolean profileImg;
    private String accountNum;
    private String bank;
    private FileInfo fileInfo;
    private FileInfo qrInfo;


    public Mentor convertToMentor() {
        Mentor mentor = new Mentor();
        mentor.setMentorIdx(userIdx);
        mentor.setUserName(userName);
        mentor.setUserId(userId);
        mentor.setPassword(password);
        mentor.setEmail(email);
        mentor.setAddress(address);
        mentor.setPhone(phone);
        mentor.setGender(gender);
        mentor.setUserRole(userRole);
        mentor.setIsLeave(isLeave);
        mentor.setKakaoJoin(kakaoJoin);
        mentor.setUniversityName(universityName);
        mentor.setUniversityType(universityType);
        mentor.setGrade(grade);
        mentor.setMajor(major);
        mentor.setWantDay(wantDay);
        mentor.setWantTime(wantTime);
        mentor.setRequirement(requirement);
        mentor.setHistory(history);
        mentor.setMentoringCnt(mentoringCnt);
        mentor.setProfileImg(profileImg);
        mentor.setAccountNum(accountNum);
        mentor.setBankName(bank);
        mentor.setFileInfo(fileInfo);
        mentor.setQrInfo(qrInfo);
        return mentor;
    }


}

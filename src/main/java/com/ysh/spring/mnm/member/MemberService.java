package com.ysh.spring.mnm.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public Optional<Mentor> checkId(String userId) {
        return memberRepository.findByUserId(userId);

    }

    public Mentor persistMentor(Mentor mentor) {
        return memberRepository.save(mentor);
    }
}

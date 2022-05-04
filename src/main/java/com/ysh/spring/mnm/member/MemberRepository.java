package com.ysh.spring.mnm.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Mentor, Long> {
    Optional<Mentor> findByUserId(String userId);
}

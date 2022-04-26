package com.ysh.spring.mnm.mentoring;

import com.ysh.spring.mnm.member.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentoringRepository extends JpaRepository<Mentor, Long> {
}

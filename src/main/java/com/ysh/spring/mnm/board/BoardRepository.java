package com.ysh.spring.mnm.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAllByBdTitleContains(String bdTitle, Pageable paging);
    Page<Board> findAllByMember_UserIdxLike(Long userIdx, Pageable paging);
}

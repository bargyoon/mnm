package com.ysh.spring.mnm.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
    List<BoardComment> findAllByBdIdx(Long bdIdx);

    void deleteAllByPrIdx(Integer idx);
}

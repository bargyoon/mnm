package com.ysh.spring.mnm.common.util.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<FileInfo, Long> {
    List<FileInfo> findAllByTypeAndTypeIdx(String type, Long typeIdx);
}

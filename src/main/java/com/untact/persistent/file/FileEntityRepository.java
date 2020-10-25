package com.untact.persistent.file;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.file.FileEntity;

public interface FileEntityRepository extends JpaRepository<FileEntity, Long>, FileEntityCustomRepository {
}

package com.untact.persistent.file;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.file.FileEntity;

public class FileEntityCustomRepositoryImpl extends QuerydslRepositorySupport implements FileEntityCustomRepository {
	public FileEntityCustomRepositoryImpl() {
		super(FileEntity.class);
	}
}

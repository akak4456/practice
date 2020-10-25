package com.untact.vo;

import java.util.List;

import com.untact.domain.file.FileEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileEntityListVO {
	private List<FileEntity> files;
}

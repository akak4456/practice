package com.untact.service.filesystemmanipulate;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileSystemManipulateService {
	public String saveFile(MultipartFile file) throws IllegalStateException, IOException;
	public byte[] getFile(String year,String month,String date,String name) throws IOException;
}

package com.udacity.jwdnd.course1.cloudstorage.services.imp;

import com.udacity.jwdnd.course1.cloudstorage.entity.UpFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UpFileImp {
	public List<UpFile> findAllFiles(Integer userId);

	public int addFiles(MultipartFile file, Integer userId) throws IOException;

	public int deleteFileById(Integer fileId, Integer userId);

	public int isFileExist(MultipartFile files, Integer userId);

	public UpFile getFileById(Integer fileId, Integer userId);
}

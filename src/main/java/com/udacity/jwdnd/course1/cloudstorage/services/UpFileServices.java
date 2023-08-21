package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.common.Common;
import com.udacity.jwdnd.course1.cloudstorage.entity.UpFile;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UpFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UpFileServices {

	@Autowired
	UpFileMapper filesSUploadMapper;

	public List<UpFile> findAllFiles(Integer userId) {
		return filesSUploadMapper.findAllFiles(userId);
	}

	public int addFiles(MultipartFile file, Integer userId) throws IOException {
		UpFile upFile = new UpFile();
		upFile.setFileName(Common.getURLFiles(file));
		upFile.setContentType(file.getContentType());
		upFile.setFileSize(String.valueOf(file.getSize()));
		upFile.setFileData(file.getBytes());
		upFile.setUserId(userId);
		return filesSUploadMapper.addFiles(upFile);
	}

	public int deleteFileById(Integer fileId, Integer userId) {
		return filesSUploadMapper.deleteById(fileId, userId);
	}

	public int isFileExist(MultipartFile files, Integer userId) {
		return filesSUploadMapper.isFileExist(Common.getURLFiles(files), userId);
	}

	public UpFile getFileById(Integer fileId, Integer userId) {
		return filesSUploadMapper.findByUserId(fileId, userId);
	}

}

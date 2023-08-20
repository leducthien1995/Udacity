package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.common.Common;
import com.udacity.jwdnd.course1.cloudstorage.entity.UpFile;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UpFileMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.imp.UpFileImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UpFileServices implements UpFileImp {

	@Autowired
	UpFileMapper filesSUploadMapper;

	@Override
	public List<UpFile> findAllFiles(Integer userId) {
		return filesSUploadMapper.findAllFiles(userId);
	}

	@Override
	public int addFiles(MultipartFile file, Integer userId) throws IOException {
		UpFile upFile = new UpFile();
		upFile.setFileName(Common.getURLFiles(file));
		upFile.setContentType(file.getContentType());
		upFile.setFileSize(String.valueOf(file.getSize()));
		upFile.setFileData(file.getBytes());
		upFile.setUserId(userId);
		return filesSUploadMapper.addFiles(upFile);
	}

	@Override
	public int deleteFileById(Integer fileId, Integer userId) {
		return filesSUploadMapper.deleteById(fileId, userId);
	}

	@Override
	public int isFileExist(MultipartFile files, Integer userId) {
		return filesSUploadMapper.isFileExist(Common.getURLFiles(files), userId);
	}
	
	@Override
	public UpFile getFileById(Integer fileId, Integer userId) {
		return filesSUploadMapper.findByUserId(fileId, userId);
	}

}

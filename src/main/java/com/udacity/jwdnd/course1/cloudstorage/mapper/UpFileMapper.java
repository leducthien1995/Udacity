package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.UpFile;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UpFileMapper {

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<UpFile> findAllFiles(Integer userId);

    @Select("SELECT COUNT(1) FROM FILES WHERE filename = #{fileName} AND userid = #{userId}")
    int isFileExist(String fileName, Integer userId);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId} AND userid = #{userId}")
    UpFile findByUserId(Integer fileId, Integer userId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    int addFiles(UpFile upFile);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId} AND userid = #{userId}")
    int deleteById(Integer fileId, Integer userId);
}

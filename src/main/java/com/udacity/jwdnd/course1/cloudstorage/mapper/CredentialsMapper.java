package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {
	@Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
	List<Credentials> getAllListCredentials(Integer userId);

	@Select("SELECT 1 FROM CREDENTIALS WHERE userid = #{userId} and credentialid = #{credentialId}")
	int isExistCredentialById(Integer credentialId, Integer userId);

	@Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId} and credentialid = #{credentialId}")
	Credentials getCreById(Integer credentialId, Integer userId);

	@Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{url}, #{username}, #{key}, #{password}, #{userId})")
	boolean addCredentials(Credentials credential);

	@Delete("DELETE FROM CREDENTIALS WHERE userid = #{userId} and credentialid = #{credentialId}")
	boolean deleteCreById(Integer credentialId, Integer userId);

	@Update("UPDATE CREDENTIALS SET url = #{url}, key = #{key}, username = #{username}, password = #{password} WHERE credentialid = #{credentialId} and userid = #{userId}")
	boolean editCreById(Credentials credential);
}

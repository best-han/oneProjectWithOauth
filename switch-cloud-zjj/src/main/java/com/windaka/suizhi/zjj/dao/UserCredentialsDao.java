package com.windaka.suizhi.zjj.dao;

import com.windaka.suizhi.zjj.model.AppUser;
import com.windaka.suizhi.zjj.model.UserCredential;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserCredentialsDao {

	/**
	 * 保存用户凭证信息-支持多账号类型登录
	 * @param userCredential
	 * @return
	 */
	@Insert("INSERT INTO ht_user_credentials(username, type, user_id) VALUES (#{username}, #{type}, #{userId})")
	int saveUserCredential(UserCredential userCredential);

	/**
	 * 修改用户凭证信息
	 * @param userCredential
	 * @return
	 */
	@Update("UPDATE ht_user_credentials SET username = #{username}, type = #{type} WHERE user_id = #{userId}")
	int updateUserCredential(UserCredential userCredential);

	/**
	 * 查询用户凭证
	 * @param username
	 * @return
	 */
	@Select("SELECT * FROM ht_user_credentials t WHERE t.username = #{username}")
	UserCredential queryUserCredentialByUsername(String username);

	/**
	 * 查询用户凭证
	 * 不是当前userId，是否用了此username
	 * 主要是在修改用户信息的username时候进行判断
	 * @param username
	 * @return
	 */
	@Select("SELECT * FROM ht_user_credentials t WHERE t.user_id <> #{userId} AND t.username = #{username}")
	UserCredential queryUserCredentialByUsernameAndNotId(String userId, String username);

	/**
	 * 根据用户凭证查询用户
	 * @param username
	 * @return
	 */
	@Select("SELECT u.* FROM ht_user u INNER JOIN " +
			"(SELECT user_id FROM ht_user_credentials WHERE username = #{username}) c ON u.user_id = c.user_id " +
			"WHERE u.del_flag =1")
	AppUser queryUserByUsername(String username);
}

package com.windaka.suizhi.zjj.dao;

import com.windaka.suizhi.zjj.model.AppUser;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * OSS用户维护
 * @author hjt
 * @version 1.0
 */
@Mapper
public interface AppUserDao {

	/**
	 * 新增OSS用户
	 * @param appUser
	 * @return
	 */
	@Insert("INSERT INTO ht_user(user_id, username, password, cname, phone, sys_level, sys_admin, cre_by, cre_time, upd_by, upd_time, image) "
			+" VALUES(#{userId},#{username}, #{password}, #{cname}, #{phone}, #{sysLevel}, #{sysAdmin}, #{creBy}, #{creTime}, #{updBy}, #{updTime}, #{image})")
	int saveAppUser(AppUser appUser);

	/**
	* @Author: hjt
	* @Description: 伪删除用户
	*/
	@Update("UPDATE ht_user SET del_flag=0, upd_by=#{updBy}, upd_time=#{updTime} WHERE user_id=#{userId}")
	int deleteAppUser(AppUser appUser);

	/**
	 * 修改OSS用户信息
	 * @param appUser
	 * @return
	 */
	int updateAppUserByUserId(AppUser appUser);
	/**
	 * 修改OSS用户信息
	 * @param appUser
	 * @return
	 */
	int updateAppUserByUsername(AppUser appUser);

	/**
	 * 根据用户登录名查询用户信息
	 * @param username
	 * @return
	 */
	@Deprecated
	@Select("SELECT * FROM ht_user t WHERE t.username = #{username}")
	AppUser queryByUsername(String username);

	/**
	 * 根据用户ID获取用户信息
	 * @param userId
	 * @return
	 */
	@Select("SELECT user_id userId,username,cname,phone,sys_level sysLevel,sys_admin*1 sysAdmin, enabled*1 enabled" +
			",date_format(cre_time,'%Y-%m-%d %H:%i:%s') creTime, image " +
			" FROM ht_user t WHERE t.user_id = #{userId} and t.del_flag = 1")
	Map<String, Object> queryByUserId(String userId);

	/**
	 * 根据用户ID获取用户信息-登录专用
	 * @param userId
	 * @return
	 */
	@Select("SELECT * FROM ht_user t WHERE t.del_flag = 1 AND t.user_id = #{userId}")
	AppUser queryByUserIdForAppUser(String userId);

	/**
	 * 获取全部用户量（只查未删除的）
	 * @param params
	 * @return
	 */
	int totalRows(Map<String, Object> params);

	/**
	 * 根据条件查询用户列表
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> queryList(Map<String, Object> params);


}

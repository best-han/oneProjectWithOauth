package com.windaka.suizhi.zjj.service;

import com.windaka.suizhi.zjj.constants.Page;
import com.windaka.suizhi.zjj.model.AppUser;
import com.windaka.suizhi.zjj.model.LoginAppUser;
import com.windaka.suizhi.zjj.exception.OssRenderException;

import java.util.Map;
import java.util.Set;

public interface AppUserService {

	/**
	 * 新增OSS用户
	 * @param appUser
	 */
	String saveAppUser(AppUser appUser, Set<String> roleIds) throws OssRenderException;

	/**
	 * 修改OSS用户
	 * @param appUser
	 */
	String updateAppUser(AppUser appUser, Set<String> roleIds) throws OssRenderException;
	/**
	* @Author: hjt
	* @Date: 2018/12/18
	* @Description: 删除OSS用户
	*/
	void deleteAppUser(String userId) throws OssRenderException;

	/**
	 * 根据用户名查询用户
	 * @param username
	 * @param password
	 * @return
	 */
	LoginAppUser queryByUsername(String username, String password);

	/**
	 * 根据用户ID查询用户
	 * @param userId
	 * @return java.lang.Map
	 */
	Map<String, Object> queryByUserId(String userId) throws OssRenderException;

	/**
	 * 根据用户ID查询用户-返回实体
	 * @param userId
	 * @return AppUser
	 */
	AppUser queryByUserIdForAppUser(String userId) throws OssRenderException;

	/**
	 * 设置用户角色
	 * @param userId
	 * @param roleIds
	 */
	void saveRoleToUser(String userId, Set<String> roleIds) throws OssRenderException;

	/**
	 * 密码修改
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 */
	void updatePassword(String userId, String oldPassword, String newPassword) throws OssRenderException;

	/**
	 * 查询用户列表，分页
	 * @param params
	 * @return
	 */
	Page<Map<String,Object>> queryList(Map<String, Object> params) throws OssRenderException;


	LoginAppUser updateMe(Map<String, Object> map) throws OssRenderException;




}

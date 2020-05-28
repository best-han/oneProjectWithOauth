package com.windaka.suizhi.zjj.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * 分页参数处理工具
 * @author hjt
 */
@Slf4j
public class PageUtil {

	/**
	 * 分页参数：页码号
	 */
	public static final String PAGE = "page";

	/**
	 * 分页参数：其实行号
	 */
	public static final String START = "start";

	/**
	 * 分页参数，每页数据条数
	 */
	public static final String LENGTH = "limit";


	/**
	 * 转换并校验分页参数<br>
	 * mybatis中limit #{start, JdbcType=INTEGER}, #{length,
	 * JdbcType=INTEGER}里的类型转换貌似失效<br>
	 * 我们这里先把他转成Integer的类型
	 * @param params 分页参数
	 * @param required 分页参数是否是必填
	 */
	public static void pageParamConver(Map<String, Object> params, boolean required) {
		if (required) {// 分页参数必填时，校验参数
			if (params == null || !params.containsKey(PAGE) || !params.containsKey(LENGTH)) {
				throw new IllegalArgumentException("请检查分页参数," + PAGE + "," + LENGTH);
			}
		}
		//设置每页的行数
		if (!CollectionUtils.isEmpty(params)) {
			Integer length = new Integer(10);
			//每页显示的行数应该大于零
			if (params.containsKey(LENGTH)) {
				length = MapUtils.getInteger(params, LENGTH);
				//如果每页显示的行数小于等于0，则默认为10
				if (length <= 0) {
					log.error("length：{}，重置为0", length);
					length = 10;
				}
				params.put(LENGTH, length);
			}

			//计算起始行号
			if (params.containsKey(PAGE)) {
				Integer start = 0;//起始行数
				Integer page = MapUtils.getInteger(params, PAGE);
				if (page < 1) {
					log.error("page：{}，重置为1", page);
					page = 1;
				}
				start = (page-1)*length;	//计算起始行号
				params.put(START, start);
			}

		}
	}
}

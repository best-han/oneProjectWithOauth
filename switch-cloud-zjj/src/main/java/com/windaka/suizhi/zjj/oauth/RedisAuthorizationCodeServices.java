package com.windaka.suizhi.zjj.oauth;

import com.windaka.suizhi.zjj.constants.OssConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands.SetOption;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * redis存储授权码
 * @author hjt
 */
@Service
public class RedisAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	//redis中的client信息，此处主要用于刷新token过期时间的时候用
	@Autowired
	private RedisClientDetailsService redisClientDetailsService;
	/**
	 * 存储code到redis，并设置过期时间，10分钟<br>
	 * value为OAuth2Authentication序列化后的字节<br>
	 * 因为OAuth2Authentication没有无参构造函数<br>
	 * redisTemplate.opsForValue().set(key, value, timeout, unit);
	 * 这种方式直接存储的话，redisTemplate.opsForValue().get(key)的时候有些问题，
	 * 所以这里采用最底层的方式存储，get的时候也用最底层的方式获取
	 */
	@Override
	protected void store(String code, OAuth2Authentication authentication) {

		redisTemplate.execute(new RedisCallback<Long>() {

			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(codeKey(code).getBytes(), SerializationUtils.serialize(authentication),
						Expiration.from(10, TimeUnit.MINUTES), SetOption.UPSERT);
				return 1L;
			}
		});
	}


	/**
	 * 刷新access_token过期时间
	 * 在过身份认证时候进行刷新，这样就避免用户持续操作的情况下token也会过期导致的重新登录
	 * @param token
	 * @param authentication
	 */
	public void refreshStoreAccessToken(String token, OAuth2Authentication authentication){

		RedisTokenStore redisTokenStore = new RedisTokenStore(redisTemplate.getConnectionFactory());
		redisTokenStore.setPrefix(OssConstants.OSS_REDIS_PREFIX);
		//根据token生成OAuth2AccessToken
		//此处参考org.springframework.security.oauth2.provider.token.DefaultTokenServices源码
		DefaultOAuth2AccessToken accessToken = new DefaultOAuth2AccessToken(token);
		int validitySeconds = 0;
		//从redis缓存中获取ClientDetails的token有效期
		if (redisClientDetailsService != null) {
			ClientDetails client = redisClientDetailsService.loadClientByClientId(authentication.getOAuth2Request().getClientId());
			validitySeconds = client.getAccessTokenValiditySeconds();

		}
		if(validitySeconds<=0){
			validitySeconds = 60 * 60 * 12;
		}
		//设置token有效期
		accessToken.setExpiration(new Date(System.currentTimeMillis() + (validitySeconds * 1000L)));
		//设置token有使用范围
		accessToken.setScope(authentication.getOAuth2Request().getScope());
		redisTokenStore.storeAccessToken(accessToken, authentication);

	}

	@Override
	protected OAuth2Authentication remove(final String code) {
		OAuth2Authentication oAuth2Authentication = redisTemplate.execute(new RedisCallback<OAuth2Authentication>() {

			@Override
			public OAuth2Authentication doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyByte = codeKey(code).getBytes();
				byte[] valueByte = connection.get(keyByte);

				if (valueByte != null) {
					connection.del(keyByte);
					return SerializationUtils.deserialize(valueByte);
				}

				return null;
			}
		});

		return oAuth2Authentication;
	}

	/**
	 * 拼装redis中key的前缀
	 * 
	 * @param code
	 */
	private String codeKey(String code) {

		return "oss:oauth2:codes:" + code;
	}
}

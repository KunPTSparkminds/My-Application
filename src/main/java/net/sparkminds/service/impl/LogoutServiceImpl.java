package net.sparkminds.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.service.LogoutService;
import net.sparkminds.service.RedisService;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutService {
	private final RedisService redisService;
	private final RedisTemplate<String, Object> template;
	@Override
	public String logout(String jwt) {
        redisService.cacheJWT(jwt);
        return "Logout Success";
	}

	@Override
	public boolean checkJwtExistedRedis(String jwt) {
		return false;
	}

}

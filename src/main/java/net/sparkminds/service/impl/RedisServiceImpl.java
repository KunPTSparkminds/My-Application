package net.sparkminds.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.service.RedisService;


@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {
	private final RedisTemplate<String, Object> redisTemplate;
	@Override
	public void cacheJWT(String jwt) {
		redisTemplate.opsForValue().set(jwt, jwt);
	}

}

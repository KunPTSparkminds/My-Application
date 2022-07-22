package net.sparkminds.service;

public interface RedisService {
	void cacheJWT(String jwt, Long expiredTime);
}

package net.sparkminds.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.config.JwtTokenUtil;
import net.sparkminds.service.LogoutService;
import net.sparkminds.service.RedisService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutService {
	private final RedisService redisService;
	private final JwtTokenUtil jwtTokenUtil;

	@Override
	public String logout(String jwt) {

		long diffInMillies = jwtTokenUtil.getExpirationDateFromToken(jwt).getTime() - new Date().getTime();
		redisService.cacheJWT(jwt, diffInMillies);
		return "Logout Success";
	}

	@Override
	public boolean checkJwtExistedRedis(String jwt) {
		return false;
	}

}

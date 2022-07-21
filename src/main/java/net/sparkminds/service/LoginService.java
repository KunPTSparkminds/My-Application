package net.sparkminds.service;

import net.sparkminds.service.dto.request.LoginRequestDTO;
import net.sparkminds.service.dto.response.LoginResponse;

public interface LoginService {
	LoginResponse login(LoginRequestDTO loginRequestDTO);
}

package net.sparkminds.controller;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sparkminds.service.LoginService;
import net.sparkminds.service.LogoutService;
import net.sparkminds.service.RegisterService;
import net.sparkminds.service.dto.request.LoginRequestDTO;
import net.sparkminds.service.dto.request.RegisterRequestDTO;
import net.sparkminds.service.dto.response.LoginResponse;
import net.sparkminds.service.dto.response.ReviewerResponseDTO;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AuthController {

	private final RegisterService registerService;
	private final LogoutService logoutService;
	private final LoginService loginService;
	
	@PostMapping("/register")
	public ResponseEntity<ReviewerResponseDTO> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
		return ResponseEntity.ok(registerService.register(registerRequestDTO));
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestHeader HttpHeaders headers) {
		String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
		logoutService.logout(token.split(" ")[1]);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
		return ResponseEntity.ok(loginService.login(loginRequestDTO));
	}
}

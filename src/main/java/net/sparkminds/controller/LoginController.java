package net.sparkminds.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sparkminds.service.LoginService;
import net.sparkminds.service.dto.request.LoginRequestDTO;
import net.sparkminds.service.dto.response.LoginResponse;

@RestController
@RequestMapping("api/login")
@RequiredArgsConstructor
public class LoginController {
	private final LoginService loginService;

	@PostMapping
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
		return ResponseEntity.ok(loginService.login(loginRequestDTO));
	}
}

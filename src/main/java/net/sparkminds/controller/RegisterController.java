package net.sparkminds.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sparkminds.service.RegisterService;
import net.sparkminds.service.dto.request.RegisterRequestDTO;
import net.sparkminds.service.dto.response.ReviewerResponseDTO;

@RestController
@RequestMapping("api/register")
@RequiredArgsConstructor
public class RegisterController {

	private final RegisterService registerService;

	@PostMapping
	public ResponseEntity<ReviewerResponseDTO> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
		return ResponseEntity.ok(registerService.register(registerRequestDTO));
	}
}

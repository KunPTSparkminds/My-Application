package net.sparkminds.service.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.config.JwtTokenUtil;
import net.sparkminds.entity.Reviewer;
import net.sparkminds.repository.ReviewerRepository;
import net.sparkminds.service.JwtUserDetailsService;
import net.sparkminds.service.LoginService;
import net.sparkminds.service.dto.request.LoginRequestDTO;
import net.sparkminds.service.dto.response.LoginResponse;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
	private final ReviewerRepository reviewerRepository;
	
	private final JwtTokenUtil jwtTokenUtil;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private final JwtUserDetailsService jwtUserDetailsService;
	@Override
	public LoginResponse login(LoginRequestDTO loginRequestDTO) {
		Optional<Reviewer> reviewer = reviewerRepository.findByEmail(loginRequestDTO.getEmail());
		boolean isMatchesPassword = bCryptPasswordEncoder.matches(loginRequestDTO.getPassword(), reviewer.get().getPassword());
		if (isMatchesPassword == false) {
			throw new EntityNotFoundException("Password or user name not correct");
		}
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(loginRequestDTO.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		LoginResponse tokenResponse = new LoginResponse();
		tokenResponse.setJwtToken(token);
		return tokenResponse;
	}

}

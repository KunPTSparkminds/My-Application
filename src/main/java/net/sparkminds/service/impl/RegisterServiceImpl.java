package net.sparkminds.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.entity.Reviewer;
import net.sparkminds.repository.ReviewerRepository;
import net.sparkminds.service.RegisterService;
import net.sparkminds.service.dto.request.RegisterRequestDTO;
import net.sparkminds.service.dto.response.ReviewerResponseDTO;
import net.sparkminds.service.mapper.ReviewerMapper;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
	private final ReviewerRepository reviewerRepository;
	private final ReviewerMapper reviewerMapper;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public ReviewerResponseDTO register(RegisterRequestDTO registerRequestDTO) {
		if (reviewerRepository.existsByEmail(registerRequestDTO.getEmail()))
			return null;
		Reviewer reviewer = new Reviewer();
		reviewer.setEmail(registerRequestDTO.getEmail());
		reviewer.setName(registerRequestDTO.getName());
		reviewer.setPicture(registerRequestDTO.getPicture());
		reviewer.setPassword(bCryptPasswordEncoder.encode(registerRequestDTO.getPassword()));
		reviewerRepository.save(reviewer);
		return reviewerMapper.entityToResponse(reviewer);
	}
}

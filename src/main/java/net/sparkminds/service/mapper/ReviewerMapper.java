package net.sparkminds.service.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.entity.Reviewer;
import net.sparkminds.service.dto.request.RegisterRequestDTO;
import net.sparkminds.service.dto.response.ReviewerResponseDTO;

@Service
@RequiredArgsConstructor
public class ReviewerMapper {
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public Reviewer requestToEntity(RegisterRequestDTO dto) {
		if (dto == null)
			return null;

		Reviewer reviewer = new Reviewer();

		reviewer.setName(dto.getEmail());
		reviewer.setEmail(dto.getEmail());
		reviewer.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		reviewer.setPicture(dto.getPicture());
		return reviewer;
	}

	public ReviewerResponseDTO entityToResponse(Reviewer entity) {
		if (entity == null)
			return null;

		ReviewerResponseDTO responseDto = new ReviewerResponseDTO();
		responseDto.setEmail(entity.getEmail());
		responseDto.setName(entity.getName());
		responseDto.setPicture(entity.getPicture());
		return responseDto;
	}
}

package net.sparkminds.service;

import net.sparkminds.service.dto.request.RegisterRequestDTO;
import net.sparkminds.service.dto.response.ReviewerResponseDTO;

public interface RegisterService {
	ReviewerResponseDTO register(RegisterRequestDTO reviewerRequestDTO);
}

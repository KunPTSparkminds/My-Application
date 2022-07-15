package net.sparkminds.service;

import java.util.List;

import net.sparkminds.service.dto.request.ApplicationRequestDTO;
import net.sparkminds.service.dto.response.ApplicationResponseDTO;

public interface ApplicationService {
	List<ApplicationResponseDTO> getAllApplication();
	List<ApplicationResponseDTO> getApplicationById(Long id);
	ApplicationResponseDTO createApplication(ApplicationRequestDTO applicationRequestDTO);
}

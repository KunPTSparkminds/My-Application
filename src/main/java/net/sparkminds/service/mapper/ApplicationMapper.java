package net.sparkminds.service.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.entity.Application;
import net.sparkminds.service.dto.response.ApplicationResponseDTO;

@Service
@RequiredArgsConstructor
public class ApplicationMapper {
	private final ProjectMapper projectMapper;
	public ApplicationResponseDTO entityToResponse(Application entity) {
		if (entity == null) return null;
		
		ApplicationResponseDTO responseDTO = new ApplicationResponseDTO();
		responseDTO.setId(entity.getId());
		responseDTO.setEmailAdress(entity.getEmailAdress());
		responseDTO.setGithubUser(entity.getGithubUser());
		responseDTO.setName(entity.getName());
		responseDTO.setProjects(entity.getProject().stream().map(projectMapper::entityToResponse).collect(Collectors.toList()));
		responseDTO.setCreatedAt(entity.getCreatedAt());
		return responseDTO;
	}
}

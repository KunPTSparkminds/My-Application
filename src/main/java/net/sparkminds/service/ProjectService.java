package net.sparkminds.service;

import java.util.List;

import net.sparkminds.service.dto.request.ProjectRequestDTO;
import net.sparkminds.service.dto.response.ProjectResponseDTO;

public interface ProjectService {
	List<ProjectResponseDTO> getAllProject();
    ProjectResponseDTO updateProject(ProjectRequestDTO projectRequestDTO, long id);
    void deleteProject(Long id);
}

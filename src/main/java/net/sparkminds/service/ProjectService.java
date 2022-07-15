package net.sparkminds.service;

import net.sparkminds.service.dto.request.ProjectRequestDTO;
import net.sparkminds.service.dto.response.ProjectResponseDTO;

public interface ProjectService {
    ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO);
    ProjectResponseDTO updateProject(ProjectRequestDTO projectRequestDTO, long id);
    void deleteProject(Long id);
}

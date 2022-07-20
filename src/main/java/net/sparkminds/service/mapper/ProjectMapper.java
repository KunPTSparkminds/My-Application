package net.sparkminds.service.mapper;

import org.springframework.stereotype.Service;

import net.sparkminds.entity.Project;
import net.sparkminds.service.dto.request.ProjectRequestDTO;
import net.sparkminds.service.dto.response.ProjectResponseDTO;

@Service
public class ProjectMapper {
	public Project requestToEntity(ProjectRequestDTO dto) {
		if (dto == null) return null;
		Project project = new Project();
		project.setNameProject(dto.getNameProject());
		project.setCapacity(dto.getCapacity());
		project.setDuration(dto.getDuration());
		project.setTeamSize(dto.getTeamSize());
		project.setLinkToLiveUrl(dto.getLinkToLiveUrl());
		project.setLinkToRepo(dto.getLinkToRepo());
		project.setRole(dto.getRole());
		project.setStartYear(dto.getStartYear());
		project.setEmploymentMode(dto.getEmploymentMode());
		return project;
	}
	
	public ProjectResponseDTO entityToResponse(Project entity) {
		if (entity == null) return null;
		ProjectResponseDTO project = new ProjectResponseDTO();
		project.setNameProject(entity.getNameProject());
		project.setCapacity(entity.getCapacity());
		project.setDuration(entity.getDuration());
		project.setTeamSize(entity.getTeamSize());
		project.setLinkToLiveUrl(entity.getLinkToLiveUrl());
		project.setLinkToRepo(entity.getLinkToRepo());
		project.setRole(entity.getRole());
		project.setStartYear(entity.getStartYear());
		project.setEmploymentMode(entity.getEmploymentMode());
		return project;
	}
}

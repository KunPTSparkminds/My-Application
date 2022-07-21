package net.sparkminds.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.entity.Project;
import net.sparkminds.repository.ProjectRepository;
import net.sparkminds.service.ProjectService;
import net.sparkminds.service.dto.request.ProjectRequestDTO;
import net.sparkminds.service.dto.response.ProjectResponseDTO;
import net.sparkminds.service.mapper.ProjectMapper;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
	private final ProjectRepository projectRepository;
	
	private final ProjectMapper projectMapper;

	@Override
	@Transactional
	public ProjectResponseDTO updateProject(ProjectRequestDTO projectRequestDTO, long id) {
		Project project = projectRepository.findById(id).orElse(null);

		project.setNameProject(projectRequestDTO.getNameProject());
		project.setEmploymentMode(projectRequestDTO.getEmploymentMode());
		project.setCapacity(projectRequestDTO.getCapacity());
		project.setDuration(projectRequestDTO.getDuration());
		project.setStartYear(projectRequestDTO.getStartYear());
		project.setRole(projectRequestDTO.getRole());
		project.setTeamSize(projectRequestDTO.getTeamSize());
		project.setLinkToRepo(projectRequestDTO.getLinkToRepo());
		project.setLinkToLiveUrl(projectRequestDTO.getLinkToLiveUrl());
		projectRepository.save(project);
		return projectMapper.entityToResponse(project);
	}

	@Override
	@Transactional
	public void deleteProject(Long id) {
		Project project = projectRepository.findById(id).orElse(null);
		if (project != null) {
			projectRepository.deleteProjectById(id);
		}
	}

	@Override
	public List<ProjectResponseDTO> getAllProject() {
		return projectRepository.findAll().
				stream()
				.map(projectMapper::entityToResponse)
				.collect(Collectors.toList());
	}

}

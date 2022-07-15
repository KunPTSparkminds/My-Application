package net.sparkminds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sparkminds.entity.Application;
import net.sparkminds.entity.Project;
import net.sparkminds.repository.ApplicationRepository;
import net.sparkminds.repository.ProjectRepository;
import net.sparkminds.service.ProjectService;
import net.sparkminds.service.dto.request.ProjectRequestDTO;
import net.sparkminds.service.dto.response.ProjectResponseDTO;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    
    //Create project and assign to application
    @Override
    @Transactional
    public ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO) {
        Application application = applicationRepository.findById(projectRequestDTO.getApplicationId()).orElse(null);
        Project project = new Project();
        project.setNameProject(projectRequestDTO.getNameProject());
        project.setEmploymentMode(projectRequestDTO.getEmploymentMode());
        project.setCapacity(projectRequestDTO.getCapacity());
        project.setDuration(projectRequestDTO.getDuration());
        project.setStartYear(projectRequestDTO.getStartYear());
        project.setRole(projectRequestDTO.getRole());
        project.setTeamSize(projectRequestDTO.getTeamSize());
        project.setLinkToRepo(projectRequestDTO.getLinkToRepo());
        project.setLinkToLiveUrl(projectRequestDTO.getLinkToLiveUrl());
        project.setIsDeleted(false);
        project.setApplication(application);
        projectRepository.save(project);
        return ProjectResponseDTO.builder().nameProject(project.getNameProject())
                .employmentMode(project.getEmploymentMode()).capacity(project.getCapacity())
                .startYear(project.getStartYear()).role(project.getRole()).duration(project.getDuration())
                .teamSize(project.getTeamSize()).linkToRepo(project.getLinkToRepo())
                .linkToLiveUrl(project.getLinkToLiveUrl()).build();
    }
    
    // Update project
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
        return ProjectResponseDTO.builder().nameProject(project.getNameProject())
                .employmentMode(project.getEmploymentMode()).capacity(project.getCapacity())
                .startYear(project.getStartYear()).role(project.getRole()).duration(project.getDuration())
                .teamSize(project.getTeamSize()).linkToRepo(project.getLinkToRepo())
                .linkToLiveUrl(project.getLinkToLiveUrl()).build();
    }
    
    //Delete project (Change status "is_delete" to true)
    @Override
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project != null) {
            projectRepository.deleteProjectById(id);
        }
    }

}

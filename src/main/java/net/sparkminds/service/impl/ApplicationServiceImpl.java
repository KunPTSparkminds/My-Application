package net.sparkminds.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sparkminds.entity.Application;
import net.sparkminds.repository.ApplicationRepository;
import net.sparkminds.service.ApplicationService;
import net.sparkminds.service.dto.request.ApplicationRequestDTO;
import net.sparkminds.service.dto.response.ApplicationResponseDTO;
import net.sparkminds.service.dto.response.ProjectResponseDTO;

@Service
@Transactional(readOnly = true)
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
    //Get all application
    @Override
    public List<ApplicationResponseDTO> getAllApplication() {
        return applicationRepository.getApplication().stream().map(entity -> {
            List<ProjectResponseDTO> pasProjects = entity.getProject().stream().map(otp -> {
                return ProjectResponseDTO.builder().nameProject(otp.getNameProject())
                        .employmentMode(otp.getEmploymentMode()).capacity(otp.getCapacity()).duration(otp.getDuration())
                        .startYear(otp.getStartYear()).teamSize(otp.getTeamSize()).linkToRepo(otp.getLinkToRepo())
                        .linkToLiveUrl(otp.getLinkToLiveUrl()).role(otp.getRole()).build();
            }).collect(Collectors.toList());
            return ApplicationResponseDTO.builder().name(entity.getName()).emailAdress(entity.getEmailAdress())
                    .githubUser(entity.getGithubUser()).createdAt(entity.getCreatedAt()).projects(pasProjects).build();
        }).collect(Collectors.toList());
    }
    //Get application by id
    @Override
    public List<ApplicationResponseDTO> getApplicationById(Long id) {
        return applicationRepository.findById(id).stream().map(entity -> {
            List<ProjectResponseDTO> pasProjects = entity.getProject().stream().map(otp -> {

                return ProjectResponseDTO.builder().nameProject(otp.getNameProject()).role(otp.getRole()).build();
            }).collect(Collectors.toList());
            return ApplicationResponseDTO.builder().name(entity.getName()).emailAdress(entity.getEmailAdress())
                    .githubUser(entity.getGithubUser()).projects(pasProjects).build();
        }).collect(Collectors.toList());
    }
    // Create application
    @Override
    @Transactional
    public ApplicationResponseDTO createApplication(ApplicationRequestDTO applicationRequestDTO) {
        Application application = applicationRepository.findByEmailAdressAndIsDeletedFalse(applicationRequestDTO.getEmailAdress())
                .orElse(null);
        if (application != null) {
            application.setIsDeleted(true);
            applicationRepository.save(application);
        }
        Application newApplication = new Application();
        newApplication.setEmailAdress(applicationRequestDTO.getEmailAdress());
        newApplication.setGithubUser(applicationRequestDTO.getGithubUser());
        newApplication.setName(applicationRequestDTO.getName());
        newApplication.setIsDeleted(false);
        applicationRepository.save(newApplication);
        return ApplicationResponseDTO.builder().name(newApplication.getName()).emailAdress(newApplication.getEmailAdress())
                .githubUser(newApplication.getGithubUser()).createdAt(newApplication.getCreatedAt()).build();
    }
}

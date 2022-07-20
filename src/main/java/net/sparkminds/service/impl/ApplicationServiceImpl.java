package net.sparkminds.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.entity.Application;
import net.sparkminds.entity.Project;
import net.sparkminds.repository.ApplicationRepository;
import net.sparkminds.service.ApplicationService;
import net.sparkminds.service.dto.request.ApplicationRequestDTO;
import net.sparkminds.service.dto.response.ApplicationResponseDTO;
import net.sparkminds.service.dto.response.ProjectResponseDTO;
import net.sparkminds.service.mapper.ApplicationMapper;
import net.sparkminds.service.mapper.ProjectMapper;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

	private final ApplicationRepository applicationRepository;
	private final ProjectMapper projectMapper;
	private final ApplicationMapper applicationMapper;

	@Override
	public List<ApplicationResponseDTO> getAllApplication() {
		return applicationRepository.getApplication().stream().map(applicationMapper::entityToResponse).collect(Collectors.toList());
	}

	@Override
	public List<ApplicationResponseDTO> getApplicationById(Long id) {
		return applicationRepository.findById(id).stream().map(applicationMapper::entityToResponse).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public ApplicationResponseDTO createApplication(ApplicationRequestDTO applicationRequestDTO) {
		Application application = applicationRepository
				.findByEmailAdressAndIsDeletedFalse(applicationRequestDTO.getEmailAdress()).orElse(null);
		if (application != null) {
			application.setIsDeleted(true);
			applicationRepository.save(application);
		}
		List<Project> projects = applicationRequestDTO
				.getProjects()
				.stream()
				.map(projectMapper::requestToEntity)
				.collect(Collectors.toList());
		
		Application newApplication = new Application();
		newApplication.setEmailAdress(applicationRequestDTO.getEmailAdress());
		newApplication.setGithubUser(applicationRequestDTO.getGithubUser());
		newApplication.setName(applicationRequestDTO.getName());
		newApplication.setIsDeleted(false);
		newApplication.addProjects(projects);
		applicationRepository.save(newApplication);
		return applicationMapper.entityToResponse(newApplication);
	}

	@Override
	@Transactional
	public ApplicationResponseDTO updateApplication(Long id, ApplicationRequestDTO applicationRequestDTO) {
		Application application = applicationRepository.findById(id).orElse(null);
//		Application application = applicationRepository
//				.findByEmailAdressAndIsDeletedFalse(applicationRequestDTO.getEmailAdress()).orElse(null);
//		if (application != null) {
//			application.setIsDeleted(true);
//			applicationRepository.save(application);
//		}
		application.setEmailAdress(applicationRequestDTO.getEmailAdress());
		application.setGithubUser(applicationRequestDTO.getGithubUser());
		application.setName(applicationRequestDTO.getName());
		applicationRepository.save(application);
		return  applicationMapper.entityToResponse(application);
	}
}

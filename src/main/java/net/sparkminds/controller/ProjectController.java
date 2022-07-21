package net.sparkminds.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sparkminds.service.ProjectService;
import net.sparkminds.service.dto.request.ProjectRequestDTO;
import net.sparkminds.service.dto.response.ProjectResponseDTO;

@RestController
@RequestMapping("api/projects")
public class ProjectController {
	private final ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		super();
		this.projectService = projectService;
	}
	@GetMapping
	public ResponseEntity<List<ProjectResponseDTO>> getAllProject() {
		return ResponseEntity.ok(projectService.getAllProject()); 
	}
	@PutMapping("{id}")
	public ResponseEntity<ProjectResponseDTO> updateProjectById(@PathVariable("id") long id,
			@RequestBody ProjectRequestDTO projectRequestDTO) {
		return ResponseEntity.ok(projectService.updateProject(projectRequestDTO, id));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteProjectById(@PathVariable("id") Long id) {
		projectService.deleteProject(id);
		return ResponseEntity.noContent().build();
	}
}

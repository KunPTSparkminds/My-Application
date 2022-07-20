package net.sparkminds.service.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApplicationRequestDTO {
	@NotBlank(message = "The email is required")
	private String emailAdress;
	@NotBlank(message = "The name is required")
	private String name;
	@NotBlank(message = "The github user is required")
	private String githubUser;
	@NotEmpty(message = "the project is required")
	private List<ProjectRequestDTO> projects;
}

package net.sparkminds.service.dto.response;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponseDTO {
	private String emailAdress;
	private String name;
	private String githubUser;
	private List<ProjectResponseDTO> projects;
	private Date createdAt;
}

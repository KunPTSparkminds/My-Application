package net.sparkminds.service.dto.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import net.sparkminds.entity.enumeration.Capacity;
import net.sparkminds.entity.enumeration.Mode;

@Data
public class ProjectRequestDTO {
	@NotBlank
	private String nameProject;
	
	@NotBlank
	private Mode employmentMode;
	
	@NotBlank
	private Capacity capacity;
	
	@NotBlank
	private String duration;
	
	@NotBlank
	private Date startYear;
	
	@NotBlank
	private String role;
	
	@NotBlank
	private Long teamSize;
	
	private String linkToRepo;
	
	private String linkToLiveUrl;
}

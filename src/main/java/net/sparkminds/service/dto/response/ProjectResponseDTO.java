package net.sparkminds.service.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sparkminds.entity.enumeration.Capacity;
import net.sparkminds.entity.enumeration.Mode;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResponseDTO {
	private long id;
	private String nameProject;
	private Mode employmentMode;
	private Capacity capacity;
	private String duration;
	private Date startYear;
	private String role;
	private Long teamSize;
	private String linkToRepo;
	private String linkToLiveUrl;
}

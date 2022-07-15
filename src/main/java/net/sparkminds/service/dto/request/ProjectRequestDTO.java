package net.sparkminds.service.dto.request;

import java.util.Date;

import lombok.Data;
import net.sparkminds.entity.enumeration.Capacity;
import net.sparkminds.entity.enumeration.Mode;

@Data
public class ProjectRequestDTO {
    private String nameProject;
    private Mode employmentMode;
    private Capacity capacity;
    private String duration;
    private Date startYear;
    private String role;
    private Long teamSize;
    private String linkToRepo;
    private String linkToLiveUrl;
    private long applicationId;
}

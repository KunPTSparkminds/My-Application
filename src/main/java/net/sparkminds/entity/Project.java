package net.sparkminds.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.sparkminds.entity.enumeration.Capacity;
import net.sparkminds.entity.enumeration.Mode;

@Entity
@Data
@Getter
@Setter
@Table(name="project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name_project", nullable = false)
	private String nameProject;
	
	@Column(name="employment_mode", nullable = false)
	@Enumerated(EnumType.STRING)
	private Mode employmentMode;
	
	@Column(name="capacity", nullable = false)
	@Enumerated(EnumType.STRING)
	private Capacity capacity;
	
	@Column(name="duration", nullable = false)
	private String duration;
	
	@Column(name="start_year", nullable = false)
	private Date startYear;
	
	@Column(name="role", nullable = false)
	private String role;
	
	@Column(name="team_size", nullable = false)
	private Long teamSize;
	
	@Column(name="link_to_repo", nullable = true)
	private String linkToRepo;
	
	@Column(name="link_to_live_url", nullable = true)
	private String linkToLiveUrl;
	
	@Column(name="is_deleted", columnDefinition = "boolean default false")
	private Boolean isDeleted = false;
	
	@ManyToOne
	@JoinColumn(name = "application_id", referencedColumnName="id")
    private Application application;
	
	@CreationTimestamp
	@Column(name="created_at", nullable = false, updatable = false)
	private Date createdAt;
	
	@UpdateTimestamp
	@Column(name="updated_at")
	private Date updateAt;
}

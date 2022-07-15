package net.sparkminds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.sparkminds.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

//	long deleteApplicationById(Long id);
//	List<Project> findByApplicationId(Long id);
	
	@Modifying
	@Query(value="UPDATE Project p SET p.isDeleted = true WHERE p.id = :id", nativeQuery = false)
	int deleteProjectById(Long id);
}

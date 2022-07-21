package net.sparkminds.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.sparkminds.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	// Only get CV with project number greater than 1, and deleted status is false
	@Query(value = "SELECT DISTINCT a.id, a.email_address, a.github_user, a.name, a.is_deleted, a.created_at "
			+ "FROM application a JOIN project b ON a.id = b.application_id "
			+ "WHERE a.id = b.application_id and a.is_deleted = 0 and b.is_deleted = 0", nativeQuery = true)
	List<Application> getApplication();

	Optional<Application> findByEmailAdressAndIsDeletedFalse(String email);

	@Modifying
	@Query(value = "UPDATE Application p SET p.isDeleted = true WHERE p.id = :id", nativeQuery = false)
	int deleteApplicationById(Long id);

}

package net.sparkminds.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sparkminds.entity.Reviewer;

public interface ReviewerRepository extends JpaRepository<Reviewer, Long> {

    boolean existsByEmail(String email);
    
    Optional<Reviewer> findOneByEmailIgnoreCaseAndPassword(String email, String password);
    
    Optional<Reviewer> findByEmail(String email);
    
}

package me.stone.rest.play.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.stone.rest.play.users.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	Boolean existsByEmail(String email);
	Boolean existsBySsn(String ssn);
}

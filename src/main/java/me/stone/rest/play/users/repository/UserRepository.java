package me.stone.rest.play.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.stone.rest.play.users.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

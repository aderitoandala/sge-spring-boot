package com.mz.sge.auth.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.mz.sge.auth.user.CustomUser;

public interface UserRepository extends JpaRepository<CustomUser, UUID> {

	Optional<UserDetails> findByUsername(String username);

	boolean existsByUsername(String username);

}

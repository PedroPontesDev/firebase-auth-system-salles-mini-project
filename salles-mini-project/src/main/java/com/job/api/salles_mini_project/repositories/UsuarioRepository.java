package com.job.api.salles_mini_project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.api.salles_mini_project.model.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findUsuarioByfirebaseUid(String firebaseUid);
	
}

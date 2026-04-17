package com.job.api.salles_mini_project.service;

import com.job.api.salles_mini_project.model.entities.Usuario;

public interface UsuarioService {

	Usuario buscarOuCriarUsuario(String firebaseUid, String nome, String email);
	
}

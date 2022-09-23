package com.kynario.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kynario.dev.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByUsername(String username);

}

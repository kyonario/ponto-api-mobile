package com.kynario.dev.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kynario.dev.model.Usuario;
import com.kynario.dev.repository.UsuarioRepository;

@Service
public class UsuarioService{
	
	@Autowired
    private UsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(long id){
        return usuarioRepository.findById(id);
    }

    public Usuario save(Usuario usario){
        return usuarioRepository.save(usario);
    }

    public void deleteById(long id){
    	usuarioRepository.deleteById(id);
    }
    
    public void baterPonto(long id) {
    	Optional<Usuario> usr = usuarioRepository.findById(id);
    	
    }
    public Usuario getByEmail(String email) {
    	return usuarioRepository.findByUsername(email);
    }

}

package com.kynario.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kynario.dev.model.Ponto;
import com.kynario.dev.repository.PontoRepository;

@Service
public class PontoService{
	@Autowired
    private PontoRepository pontoRepository;
	
	public List<Ponto> findAll(){
        return pontoRepository.findAll();
    }

    public Optional<Ponto>  findById(long id){
        return pontoRepository.findById(id);
    }

    public Ponto save(Ponto ponto){
        return pontoRepository.save(ponto);
    }

    public void deleteById(long id){
    	pontoRepository.deleteById(id);
    }

}

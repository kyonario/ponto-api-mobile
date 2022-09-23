package com.kynario.dev.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kynario.dev.model.Ponto;
import com.kynario.dev.model.Usuario;
import com.kynario.dev.service.PontoService;
import com.kynario.dev.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioResource {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	PontoService pontoService;

	/* EndPoint: Cadastrar Vinculo */
	@PostMapping(value = "/v1/register")
	public ResponseEntity<Void> create(@RequestBody Usuario obj) {
		BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder();
		//chamando o método encode para criptografar a senha e armazenar em //uma string
		String senhaCriptografada = criptografar.encode(obj.getPassword());
		// adicionando a criptografia na senha do usuário
		obj.setPassword(senhaCriptografada);
		obj = usuarioService.save(obj);
		return ResponseEntity.status(200).build();

	}

	@PutMapping(value = "/v1/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Usuario usuario) {

            Optional<Usuario> usuarioOptional = usuarioService.findById(id);
            if (!usuarioOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
            }
         
            usuario.setId(usuarioOptional.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
    }

	/* EndPoint: Buscar todos Vinculo */
	@GetMapping
	public ResponseEntity<List<Usuario>> listar() {
		List<Usuario> usuario = usuarioService.findAll();
		if (!usuario.isEmpty()) {
			return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/* EndPoint: Deletar Vinculo */
	@DeleteMapping(value = "/api/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		usuarioService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	@PostMapping(value = "baterponto/in/{id}")
	public ResponseEntity<Object> baterPontoEntrada(@PathVariable Long id) {
		
		//consulta a existencia do usuario
		Optional<Usuario> usuarioOptional = usuarioService.findById(id);
		
		if (!usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        }
		
		String dataHora = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(Calendar.getInstance().getTime());
		String data = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
		Ponto pnt = new Ponto();
		//LocalDateTime hrPnt = dtf.format(LocalDateTime.now())	;
		
		pnt.setHora_entrada(dataHora); //seto a hora de entrada no campo referido
		pnt.setData(data);
		usuarioOptional.get().getPontos().add(pnt); //adicionando na lista um objeto do tipo ponto com a entrada
		
		Usuario user = usuarioOptional.get();
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(user));
		     
		}
	/*	
	@PostMapping(value = "baterponto/out/{id}")
	public ResponseEntity<Object> baterPontoSaida(@PathVariable Long id) {
		
		//consulta a existencia do usuario
		Optional<Usuario> usuarioOptional = usuarioService.findById(id);
		
		if (!usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        }
		
		Set<Ponto> listPonto = usuarioOptional.get().getPontos();
		String data = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
		
		for(Ponto pnt : listPonto) {
			if(pnt.getData().equals(data)) {
				System.out.println("entrou no equals");
				String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(Calendar.getInstance().getTime());
				
				pnt.setHora_saida(timeStamp); //seto a hora de saida no campo referido
				
				Usuario user = usuarioOptional.get().setPontos(pnt);
				return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(user));
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ponto de Entrada Nao Encontrado.");
		
		
		
		//LocalDateTime hrPnt = dtf.format(LocalDateTime.now())	;
		
		
		     
		}*/ 
		

	}
		
		




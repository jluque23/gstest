package com.test.gruposalinas.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.test.gruposalinas.entity.Usuario;
import com.test.gruposalinas.repository.UsuarioRepository;

@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/usuarios")
	public List<Usuario> retrieveAllStudents() {
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/usuarios/{id}")
	public Usuario retrieveUsuario(@PathVariable long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
	
		return usuario.get();
	}
	
	@DeleteMapping("/usuarios/{id}")
	public void deleteUsuario(@PathVariable long id) {
		usuarioRepository.deleteById(id);
	}

	@PostMapping("/usuarios")
	public ResponseEntity<?> create(@RequestBody Usuario usuario) {

		Usuario usuarioNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			usuarioNew = usuarioRepository.save(usuario);
			

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido creado con exito");
		response.put("usuario", usuarioNew);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}


}

package com.test.gruposalinas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.gruposalinas.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}

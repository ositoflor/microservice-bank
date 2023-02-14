package com.br.banco.usuario.infra.repositories.data;

import com.br.banco.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}

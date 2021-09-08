package com.farmacia.LegalFarma.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.farmacia.LegalFarma.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	public Optional <Usuario> findAllByUsuario (String usuario);
}

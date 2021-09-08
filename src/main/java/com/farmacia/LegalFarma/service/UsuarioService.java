package com.farmacia.LegalFarma.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.farmacia.LegalFarma.model.UserLogin;
import com.farmacia.LegalFarma.model.Usuario;
import com.farmacia.LegalFarma.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Usuario CadastraraUsuario (Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder ();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return repository.save(usuario);
	}
	public Optional <UserLogin> Logar (Optional <UserLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder ();
		Optional <Usuario> usuario = repository.findAllByUsuario(user.get().getUsuario());
		
		if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
			String auth = user.get().getUsuario() + ":" + user.get().getSenha();
			byte[] encodeAuth = Base64.encodeBase64 (auth.getBytes(Charset.forName("US-ASCII")));
			String authHeader = "Basic " + new String (encodeAuth);
			user.get().setToken(authHeader);
			user.get().setNome(usuario.get().getNome());
			return user;
		}
	return null;
	}
}
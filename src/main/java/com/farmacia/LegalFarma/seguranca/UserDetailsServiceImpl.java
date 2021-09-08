package com.farmacia.LegalFarma.seguranca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.farmacia.LegalFarma.model.Usuario;
import com.farmacia.LegalFarma.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional <Usuario> user = userRepository.findAllByUsuario(username);
		user.orElseThrow(() -> new UsernameNotFoundException(username + "not found."));
		
		return user.map(UserDetailsImpl :: new).get();
	}
}
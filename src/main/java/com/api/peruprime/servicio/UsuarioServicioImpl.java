package com.api.peruprime.servicio;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.api.peruprime.controlador.dto.UsuarioRegistroDTO;
import com.api.peruprime.modelo.Rol;
import com.api.peruprime.modelo.Usuario;
import com.api.peruprime.repositorio.UsuarioRepositorio;
import com.api.peruprime.util.Constantes;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioServicioImpl.class);
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
		super();
		this.usuarioRepositorio = usuarioRepositorio;
	}

	@Override
	public Usuario guardar(UsuarioRegistroDTO registroDTO) {//ROLE_ADMIN
		Usuario usuario = new Usuario();
		if(registroDTO.getEmail().equals("admin@gmail.com")) {
			usuario = new Usuario(registroDTO.getNombre(), registroDTO.getApellido(), registroDTO.getEmail(),
					passwordEncoder.encode(registroDTO.getPassword()), Arrays.asList(new Rol("ROLE_ADMIN")),
					registroDTO.getSuscrito(), registroDTO.getFechaInscripcion(), registroDTO.getFechaVencimiento());
		}else {
			usuario = new Usuario(registroDTO.getNombre(), registroDTO.getApellido(), registroDTO.getEmail(),
				passwordEncoder.encode(registroDTO.getPassword()), Arrays.asList(new Rol("ROLE_USER")),
				registroDTO.getSuscrito(), registroDTO.getFechaInscripcion(), registroDTO.getFechaVencimiento());
		}
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepositorio.findByEmail(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		logger.info(Constantes.MENSAJE2, "[loadUserByUsername][usuario.getEmail()] ", username);
		return new User(usuario.getEmail(),usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}
	
	@Override
	public List<Usuario> listarUsuarios() {
//		logger.info(Constantes.MENSAJE2, "[loadUserByUsername][emails] ", emails);
		return usuarioRepositorio.findAll();
	}

}

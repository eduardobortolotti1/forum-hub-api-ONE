package com.forumhub.api.forumhub.infra.security;

import com.forumhub.api.forumhub.domain.usuario.Usuario;
import com.forumhub.api.forumhub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    public UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<Usuario> usuario= usuarioRepository.findByEmail(email);
        if (usuario.isPresent()) {
            return new User(
                    usuario.get().getEmail(),
                    new BCryptPasswordEncoder().encode(usuario.get().getSenha()), // TODO REMOVE
                    Collections.emptyList() // No authorities roles are being used.
            );
        }
        throw new UsernameNotFoundException("Email não encontrado.");
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
}

package br.com.rodrigo.api.pedidos.auth;

import br.com.rodrigo.api.pedidos.model.Usuario;
import br.com.rodrigo.api.pedidos.model.form.LoginForm;
import br.com.rodrigo.api.pedidos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;

    private final AuthenticationManager authenticationManager;

    public Usuario authenticate(LoginForm loginUsuario) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUsuario.getEmail(),
                        loginUsuario.getSenha()
                )
        );

        return usuarioRepository.findByEmailIgnoreCase(loginUsuario.getEmail())
                .orElseThrow();
    }
}

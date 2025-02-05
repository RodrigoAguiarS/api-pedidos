package br.com.rodrigo.api.pedidos.repository;


import br.com.rodrigo.api.pedidos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmailIgnoreCase(String email);

    boolean existsByPerfisId(Long idPerfil);
}

package br.com.rodrigo.api.pedidos.repository;


import br.com.rodrigo.api.pedidos.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmailIgnoreCase(String email);

    boolean existsByPerfisId(Long idPerfil);

    @Query("SELECT u FROM Usuario u " +
            "JOIN u.pessoa p " +
            "JOIN u.perfis pf " +
            "WHERE (:nome IS NULL OR LOWER(p.nome) LIKE %:nome%) " +
            "AND (:email IS NULL OR LOWER(u.email) LIKE %:email%) " +
            "AND (:cpf IS NULL OR p.cpf = :cpf) " +
            "AND (:perfilId IS NULL OR pf.id = :perfilId)")
    Page<Usuario> findAll(@Param("email") String email,
                          @Param("nome") String nome,
                          @Param("cpf") String cpf,
                          @Param("perfilId") Long perfilId,
                          Pageable pageable);
}

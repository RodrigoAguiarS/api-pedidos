package br.com.rodrigo.api.pedidos.repository;

import br.com.rodrigo.api.pedidos.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}

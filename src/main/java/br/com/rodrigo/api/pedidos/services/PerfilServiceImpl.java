package br.com.rodrigo.api.pedidos.services;

import br.com.rodrigo.api.pedidos.exception.MensagensError;
import br.com.rodrigo.api.pedidos.exception.ViolacaoIntegridadeDadosException;
import br.com.rodrigo.api.pedidos.model.Perfil;
import br.com.rodrigo.api.pedidos.model.form.PerfilForm;
import br.com.rodrigo.api.pedidos.model.response.PerfilResponse;
import br.com.rodrigo.api.pedidos.repository.PerfilRepository;
import br.com.rodrigo.api.pedidos.repository.UsuarioRepository;
import br.com.rodrigo.api.pedidos.util.ModelMapperUtil;
import org.springframework.stereotype.Service;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Service
public class PerfilServiceImpl extends GenericServiceImpl<Perfil, PerfilForm, PerfilResponse> {

    private final UsuarioRepository usuarioRepository;

    public PerfilServiceImpl(PerfilRepository perfilRepository, UsuarioRepository usuarioRepository) {
        super(perfilRepository);
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected String getEntidadeNome() {
        return "Perfil";
    }

    @Override
    protected Perfil criarEntidade(PerfilForm perfilForm, Long id) {
        Perfil perfil = ModelMapperUtil.map(perfilForm, Perfil.class);
        if(isNotEmpty(id)) {
            perfil.setId(id);
        }
        return perfil;
    }

    @Override
    protected PerfilResponse construirDto(Perfil perfil) {
        return ModelMapperUtil.map(perfil, PerfilResponse.class);
    }

    @Override
    protected void ativar(Perfil perfil) {
        perfil.ativar();
    }

    @Override
    protected void desativar(Perfil perfil) {
        perfil.desativar();
    }

    @Override
    public void apagar(Long id) {
        validarExclusao(id);
        repository.deleteById(id);
    }

    private void validarExclusao(Long id) {
        boolean existeUsuario = usuarioRepository.existsByPerfisId(id);
        if (existeUsuario) {
            throw new ViolacaoIntegridadeDadosException(MensagensError.PERFIL_POSSUI_USUARIO.getMessage());
        }
    }
}

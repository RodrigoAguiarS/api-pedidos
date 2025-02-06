package br.com.rodrigo.api.pedidos.services;

import br.com.rodrigo.api.pedidos.exception.MensagensError;
import br.com.rodrigo.api.pedidos.exception.ObjetoNaoEncontradoException;
import br.com.rodrigo.api.pedidos.model.Endereco;
import br.com.rodrigo.api.pedidos.model.Perfil;
import br.com.rodrigo.api.pedidos.model.Pessoa;
import br.com.rodrigo.api.pedidos.model.Usuario;
import br.com.rodrigo.api.pedidos.model.form.UsuarioForm;
import br.com.rodrigo.api.pedidos.model.response.PerfilResponse;
import br.com.rodrigo.api.pedidos.model.response.UsuarioResponse;
import br.com.rodrigo.api.pedidos.repository.UsuarioRepository;
import br.com.rodrigo.api.pedidos.util.ModelMapperUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, UsuarioForm, UsuarioResponse> {

    private final PasswordEncoder passwordEncoder;
    private final PerfilServiceImpl perfilService;
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(PasswordEncoder passwordEncoder,
                              PerfilServiceImpl perfilService,
                              UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
        this.passwordEncoder = passwordEncoder;
        this.perfilService = perfilService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected String getEntidadeNome() {
        return "Usuário";
    }

    @Override
    protected Usuario criarEntidade(UsuarioForm usuarioForm, Long id) {
        Usuario usuario = buscarOuCriarUsuario(id);
        mapearDadosUsuario(usuarioForm, usuario);
        configurarPerfis(usuario, usuarioForm.getPerfil());
        return usuario;
    }

    private Usuario buscarOuCriarUsuario(Long id) {
        return (id != null) ? repository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException(
                        MensagensError.USUARIO_NAO_ENCONTRADO_POR_ID.getMessage(id)))
                : new Usuario();
    }

    private void mapearDadosUsuario(UsuarioForm usuarioForm, Usuario usuario) {
        ModelMapperUtil.map(usuarioForm, usuario);
        usuario.setSenha(passwordEncoder.encode(usuarioForm.getSenha()));

        if (usuario.getPessoa() == null) {
            usuario.setPessoa(new Pessoa());
        }

        ModelMapperUtil.map(usuarioForm, usuario.getPessoa());

        if (usuario.getPessoa().getEndereco() == null) {
            usuario.getPessoa().setEndereco(new Endereco());
        }

        ModelMapperUtil.map(usuarioForm, usuario.getPessoa().getEndereco());
    }

    private void configurarPerfis(Usuario usuario, Long perfilId) {
        PerfilResponse perfilResponse = perfilService.obterPorId(perfilId);
        Perfil perfil = ModelMapperUtil.map(perfilResponse, Perfil.class);
        usuario.setPerfis(Collections.singleton(perfil));
    }

    @Override
    protected UsuarioResponse construirDto(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getPessoa().getNome(),
                usuario.getPerfis().stream().map(Perfil::getNome).toList()
        );
    }

    @Override
    protected void ativar(Usuario usuario) {
        usuario.ativar();
    }

    @Override
    protected void desativar(Usuario usuario) {
        usuario.desativar();
    }

    public List<String> obterPerfis(String email) {
        Usuario usuario = obterUsuarioPorEmail(email);
        return usuario.getPerfis().stream().map(Perfil::getNome).collect(Collectors.toList());
    }

    public Usuario obterUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ObjetoNaoEncontradoException(
                        MensagensError.USUARIO_NAO_ENCONTRADO_POR_LOGIN.getMessage(email)));
    }

    public Usuario obterUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException(
                        MensagensError.USUARIO_NAO_ENCONTRADO_POR_ID.getMessage(id)));
    }

    public Usuario obterUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return obterUsuarioPorEmail(authentication.getName());
    }
}

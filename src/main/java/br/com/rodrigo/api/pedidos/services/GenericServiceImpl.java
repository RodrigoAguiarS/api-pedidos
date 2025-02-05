package br.com.rodrigo.api.pedidos.services;

import br.com.rodrigo.api.pedidos.exception.MensagensError;
import br.com.rodrigo.api.pedidos.exception.ObjetoNaoEncontradoException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public abstract class GenericServiceImpl<Entity, Form, Response> implements GenericService<Form, Response> {

    protected final JpaRepository<Entity, Long> repository;

    protected GenericServiceImpl(JpaRepository<Entity, Long> repository) {
        this.repository = repository;
    }
    
    protected abstract Entity criarEntidade(Form form, Long id);
    
    protected abstract Response construirDto(Entity entidade);
    
    protected abstract void ativar(Entity entidade);
    
    protected abstract void desativar(Entity entidade);
    
    protected abstract void validarExclusao(Long id);
    
    @Override
    public Response cadastrar(Form form) {
        Entity entidade = criarEntidade(form, null);
        entidade = repository.save(entidade);
        return construirDto(entidade);
    }

    @Override
    public Response atualizar(Long id, Form form) {
        Optional<Entity> optionalEntity = repository.findById(id);
        if (optionalEntity.isPresent()) {
            Entity entidade = criarEntidade(form, id);
            entidade = repository.save(entidade);
            return construirDto(entidade);
        }
        throw new ObjetoNaoEncontradoException(MensagensError.ENTIDADE_NAO_ENCONTRADO.getMessage());
    }

    @Override
    public Response obterPorId(Long id) {
        Optional<Entity> optionalEntity = repository.findById(id);
        if (optionalEntity.isPresent()) {
            return construirDto(optionalEntity.get());
        }
        throw new ObjetoNaoEncontradoException(MensagensError.ENTIDADE_NAO_ENCONTRADO.getMessage());
    }

    @Override
    public List<Response> listarTodos() {
        List<Entity> entities = repository.findAll();
        return entities.stream().map(this::construirDto).toList();
    }

    @Override
    public void apagar(Long id) {
        validarExclusao(id);
        repository.deleteById(id);
    }

    @Override
    public void ativar(Long id) {
        Optional<Entity> optionalEntity = repository.findById(id);
        if (optionalEntity.isPresent()) {
            Entity entidade = optionalEntity.get();
            ativar(entidade);
            repository.save(entidade);
        } else {
            throw new ObjetoNaoEncontradoException(MensagensError.ENTIDADE_NAO_ENCONTRADO.getMessage());
        }
    }

    @Override
    public void desativar(Long id) {
        Optional<Entity> optionalEntity = repository.findById(id);
        if (optionalEntity.isPresent()) {
            Entity entidade = optionalEntity.get();
            desativar(entidade);
            repository.save(entidade);
        } else {
            throw new ObjetoNaoEncontradoException(MensagensError.ENTIDADE_NAO_ENCONTRADO.getMessage());
        }
    }
}

package br.com.rodrigo.api.pedidos.services;

import java.util.List;


public interface GenericService<Form, Response> {

    Response cadastrar(Form form);

    Response atualizar(Long id, Form form);

    Response obterPorId(Long id);

    List<Response> listarTodos();

    void apagar(Long id);

    void ativar(Long id);

    void desativar(Long id);
}

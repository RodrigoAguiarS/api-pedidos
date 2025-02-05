package br.com.rodrigo.api.pedidos.exception;

public class RepositorioNaoInicializadoException extends RuntimeException {
    public RepositorioNaoInicializadoException(String mensagem) {super(mensagem); }
}

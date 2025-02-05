package br.com.rodrigo.api.pedidos.exception;

public class ObjetoNaoEncontradoException extends RuntimeException {
    public ObjetoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}

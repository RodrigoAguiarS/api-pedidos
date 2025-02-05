package br.com.rodrigo.api.pedidos.exception;

public enum MensagensError {

    // Geral
    ENTIDADE_NAO_ENCONTRADO("Entidade não encontrada");

    private final String message;

    MensagensError(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}

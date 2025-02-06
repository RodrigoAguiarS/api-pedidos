package br.com.rodrigo.api.pedidos.exception;

public enum MensagensError {

    // Geral
    ENTIDADE_NAO_ENCONTRADO("Entidade não encontrada"),
    USUARIO_NAO_ENCONTRADO_POR_LOGIN("Usuário não encontrado para o login %s"),
    PERFIL_POSSUI_USUARIO("Perfil não pode ser apagado, está vinculado a um usuário"),
    USUARIO_NAO_ENCONTRADO_POR_ID("Usuário não encontrado para o id %s");

    private final String message;

    MensagensError(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}

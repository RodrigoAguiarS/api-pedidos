package br.com.rodrigo.api.pedidos.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaResponse {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
}
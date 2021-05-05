package com.saulocn.microprofile.openapi;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;


@Schema(name = "SalvarPessoa")
public class SalvarPessoaDTO {

    @Schema(description = "Nome completo da Pessoa", maxLength = 100)
    public String nome;

    @Schema(
            minimum = "18",
            defaultValue = "20"
    )
    public Integer idade;

    @Schema(maxItems = 2)
    public List<EnderecoDTO> enderecos;
}

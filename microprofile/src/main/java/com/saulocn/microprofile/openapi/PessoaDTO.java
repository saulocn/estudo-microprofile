package com.saulocn.microprofile.openapi;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class PessoaDTO {

    @Schema(
            name = "nome",
            description = "Nome da pessoa"
    )
    public String nome;

}
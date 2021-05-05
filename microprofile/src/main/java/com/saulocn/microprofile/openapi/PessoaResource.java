package com.saulocn.microprofile.openapi;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "pessoa")
public class PessoaResource {

    public static final String SALVA_UMA_NOVA_PESSOA = "Salva uma nova pessoa";

    @GET
    public String buscarPessoas() {
        return "hello";
    }

    @POST
    @Operation(deprecated = false,
            description = "Salva uma nova pessoa. É permitido apenas maiores de idade",
            operationId = "meuID",
            summary = SALVA_UMA_NOVA_PESSOA
    )
    public SalvarPessoaDTO salvarPessoa(@RequestBody(description = "Descricao Bonita") SalvarPessoaDTO dto) {
        return null;
    }

    @Path("{id}")
    @DELETE
    public String apagarPessoa(@PathParam("id") Integer id) {
        return "hello";
    }

    @Path("{id}/enderecos")
    @GET
    @Tag(name = "endereco")
    @Tag(name = "pessoa")
    public List<EnderecoDTO> buscarEnderecos(@PathParam("id") Integer id) {
        return null;
    }

}
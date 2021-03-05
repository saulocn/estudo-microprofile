package com.saulocn.microprofile.resource;

import com.saulocn.microprofile.dto.MunicipioDTO;
import com.saulocn.microprofile.service.MunicipioService;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletionStage;

@Path("mp-restclient")
@ApplicationScoped
public class MPRestClientResource {

    @Inject
    @RestClient
    MunicipioService municipioService;

    @GET
    public String adicionarComGet() {
        Integer idUf = 12;
        MunicipioDTO municipio = new MunicipioDTO();
        municipio.setNome("Maceió");
        municipio.setPopulacao(1234);
        return municipioService.adicionar("Valor1", idUf, municipio).toString();
    }

    @GET
    @Path("sem-cdi")
    public String adicionarComGetSemCDI() {
        Integer idUf = 12;
        MunicipioDTO municipio = new MunicipioDTO();
        municipio.setNome("Maceió");
        municipio.setPopulacao(1234);
        MunicipioService service = CDI.current().select(MunicipioService.class, RestClient.LITERAL).get();
        return municipioService.adicionar("Valor2", idUf, municipio).toString();
    }

    @GET
    @Path("builder")
    public String adicionarComGetBuilder() throws URISyntaxException {
        Integer idUf = 12;
        MunicipioDTO municipio = new MunicipioDTO();
        municipio.setNome("Maceió");
        municipio.setPopulacao(1234);
        MunicipioService service = RestClientBuilder.newBuilder()
                .baseUri(new URI("http://localhost:8080")).build(MunicipioService.class);
        return municipioService.adicionar("Valor3", idUf, municipio).toString();
    }

    @GET
    @Path("async")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<MunicipioDTO> adicionarComGetAsync() throws URISyntaxException {
        Integer idUf = 12;
        MunicipioDTO municipio = new MunicipioDTO();
        municipio.setNome("Maceió");
        municipio.setPopulacao(1234);
        return municipioService.adicionarAsync("Valor4", idUf, municipio);
    }

}

package com.saulocn.microprofile.resource;

import com.saulocn.microprofile.dto.MunicipioDTO;
import com.saulocn.microprofile.service.MunicipioService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

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
        return municipioService.adicionar(idUf, municipio).toString();
    }

    @GET
    @Path("sem-cdi")
    public String adicionarComGetSemCDI() {
        Integer idUf = 12;
        MunicipioDTO municipio = new MunicipioDTO();
        municipio.setNome("Maceió");
        municipio.setPopulacao(1234);
        MunicipioService service = CDI.current().select(MunicipioService.class, RestClient.LITERAL).get();
        return municipioService.adicionar(idUf, municipio).toString();
    }

}

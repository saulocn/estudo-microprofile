package com.saulocn.microprofile.resource;


import com.saulocn.microprofile.dto.MunicipioDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Path("municipios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class MunicipioResource {

    private AtomicInteger geradorId = new AtomicInteger();

    @POST
    public MunicipioDTO adicionar(@Context HttpHeaders headers, @QueryParam("idUF") Integer idUF, MunicipioDTO municipioDTO) {
        System.out.println("-----------------------------");
        System.out.println("Adicionando municipio");

        printHeaders(headers);

        municipioDTO.setId(geradorId.incrementAndGet());
        return municipioDTO;
    }

    @GET
    public List<MunicipioDTO> buscar(@QueryParam("idUF") Integer idUF) {
        System.out.println("-----------------------------");
        System.out.println("Adicionando municipio");

        return Collections.emptyList();
    }

    private void printHeaders(HttpHeaders headers) {
        MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();

        for (Map.Entry<String, List<String>> entry : requestHeaders.entrySet()) {
            System.out.println("HEADER: " + entry.getKey());
            System.out.println("VALORES: " + entry.getValue());
        }
        System.out.println("-----------------------------");
    }

}

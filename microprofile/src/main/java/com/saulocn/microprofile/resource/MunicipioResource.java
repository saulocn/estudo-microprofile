package com.saulocn.microprofile.resource;


import com.saulocn.microprofile.dto.MunicipioDTO;
import io.opentracing.Tracer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
//@Traced(value = false)
public class MunicipioResource {

    private final AtomicInteger geradorId = new AtomicInteger();

    @Inject
    Tracer tracer;


    @POST
    public MunicipioDTO adicionar(@Context HttpHeaders headers, @QueryParam("idUF") Integer idUF, MunicipioDTO municipioDTO) {
        tracer.activeSpan().log("Post chamado");
        System.out.println("-----------------------------");
        System.out.println("Adicionando municipio");

        System.out.println("Baggage Items do municipio resource");
        tracer.activeSpan().context().baggageItems().forEach(System.out::println);
        tracer.activeSpan().setTag("MinhaTAG", "VALOR");

        printHeaders(headers);

        if (idUF.equals(0)) {
            throw new NotFoundException("UF não encontrada");
        }

        if (idUF.equals(1)) {
            throw new RuntimeException("Ocorreu um erro ao salvar o municipios");
        }
        tracer.activeSpan().log("Validações realizadas");


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        municipioDTO.setId(geradorId.incrementAndGet());
        tracer.activeSpan().log("Salvo no banco ");
        return municipioDTO;
    }

    @GET
    public List<MunicipioDTO> buscar(@QueryParam("idUF") Integer idUF) {
        System.out.println("-----------------------------");
        System.out.println("Buscando municipio");


        if (idUF.equals(0)) {
            throw new NotFoundException("UF não encontrada");
        }

        if (idUF.equals(1)) {
            throw new RuntimeException("Ocorreu um erro ao salvar o municipios");
        }

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

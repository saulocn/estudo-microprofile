package com.saulocn.microprofile.resource;

import com.saulocn.microprofile.dto.MunicipioDTO;
import com.saulocn.microprofile.service.MunicipioService;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Traced
public class ServicoIntermediario {

    @Inject
    @RestClient
    MunicipioService municipioService;


    @Inject
    Tracer tracer;

    public String adicionarMunicipio(Integer idUF) {
        System.out.println("Baggage Items do service intermediario");
        tracer.activeSpan().context().baggageItems().forEach(System.out::println);

        tracer.activeSpan().setBaggageItem("meu-baggage-item-intermediario", "valor-do-baggage-item-intermediario");

        Span span = tracer.buildSpan("Adicionando municipioREST").asChildOf(tracer.activeSpan()).withTag("tag-customizada", "valor-da-tag").start();

        MunicipioDTO municipio = new MunicipioDTO();
        municipio.setNome("Maceió");
        municipio.setPopulacao(1234);

        tracer.activeSpan().log("DTO criado...");
        String retorno = municipioService.adicionar("Valor1", idUF, municipio).toString();

        span.finish();
        return retorno;
    }
}

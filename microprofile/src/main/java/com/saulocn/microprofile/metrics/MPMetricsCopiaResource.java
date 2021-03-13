package com.saulocn.microprofile.metrics;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/mp-metrics-copia")
@ApplicationScoped
public class MPMetricsCopiaResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /*
    @Counted(
            name = "meuContador",
            // No quarkus lança um erro por ter os metadados diferentes entre métricas reutilizáveis
            //unit = MetricUnits.BYTES,
            absolute = true,
            reusable = true
    )*/
    public Integer methodName() {
        return "Hello".length();
    }

    @GET
    @Path("tambem")
    @Produces(MediaType.APPLICATION_JSON)
    /*@Counted(
            name = "meuContador",
            absolute = true,
            reusable = true
    )*/
    public Integer methodName2() {
        return "Hello".length();
    }
}

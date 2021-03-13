package com.saulocn.microprofile.metrics;

import org.eclipse.microprofile.metrics.annotation.ConcurrentGauge;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/mp-metrics")
@ApplicationScoped
public class MPMetricsResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@Counted(
    //        name = "meuContador",
    //        absolute = true
    //)
    @ConcurrentGauge
    public String methodName() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello";
    }
}

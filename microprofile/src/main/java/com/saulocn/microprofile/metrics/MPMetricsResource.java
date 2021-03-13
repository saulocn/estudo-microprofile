package com.saulocn.microprofile.metrics;

import org.eclipse.microprofile.metrics.annotation.Counted;

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
    @Counted(
            name = "meuContador",
            absolute = true,
            reusable = true
    )
    //@ConcurrentGauge
    //@Gauge(unit = MetricUnits.NONE)
    //@Metered
    //@Timed
    //@SimplyTimed
    public Integer methodName() {
        return "Hello".length();
    }

    @GET
    @Path("tambem")
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(
            name = "meuContador",
            absolute = true,
            reusable = true
    )
    public Integer methodName2() {
        return "Hello".length();
    }
}

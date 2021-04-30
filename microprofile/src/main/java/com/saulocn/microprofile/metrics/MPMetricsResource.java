package com.saulocn.microprofile.metrics;

import com.saulocn.microprofile.metrics.service.MPService;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.Histogram;
import org.eclipse.microprofile.metrics.Meter;
import org.eclipse.microprofile.metrics.Timer;
import org.eclipse.microprofile.metrics.annotation.Metric;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/mp-metrics")
@ApplicationScoped
public class MPMetricsResource {

    @Inject
    MPService mpService;

    @Inject
    ClasseCustomizada classeCustomizada;

    @Inject
    @Metric(name = "contador")
    Counter counter;

    @Inject
    @Metric(name = "meter")
    Meter meter;

    @Inject
    @Metric(name = "timer")
    Timer timer;

    @Inject
    @Metric(name = "histogram")
    Histogram histogram;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /*@Counted(
            name = "meuContador",
            absolute = true,
            reusable = true
    )*/
    //@ConcurrentGauge
    //@Gauge(unit = MetricUnits.NONE)
    //@Metered
    //@Timed
    //@SimplyTimed
    public String methodName() {
        mpService.metodo1();
        mpService.metodo1();
        counter.inc(2);
        meter.mark(10);
        timer.time();
        histogram.update(123);
        return "Hello " + classeCustomizada.fazAlgo();
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

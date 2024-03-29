package com.saulocn.microprofile.metrics;

import com.saulocn.microprofile.metrics.service.MPService;
import org.eclipse.microprofile.metrics.*;
import org.eclipse.microprofile.metrics.annotation.Metric;
import org.eclipse.microprofile.metrics.annotation.RegistryType;

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


    @Inject
    MetricRegistry metricRegistry;

    @Inject
    @RegistryType(type = MetricRegistry.Type.BASE)
    MetricRegistry baseMetricRegistry;

    @Inject
    @RegistryType(type = MetricRegistry.Type.VENDOR)
    MetricRegistry vendorMetricRegistry;

    //Não rolou no Liberty
    //@Inject
    //@Metric(name = "simpleTimer")
    //SimpleTimer simpleTimer;

    @Inject
    @Metric(name = "concurrentGauge")
    ConcurrentGauge concurrentGauge;

    // Não funcionou no OpenLiberty
    //@Inject
    //public MPMetricsResource(@Metric(name = "instances") Counter instances) {
    //    instances.inc(3);
    //}

    /* Não funcionou no Quarkus
    @javax.enterprise.inject.Produces
    @Metric(name = "counter2")
    @ApplicationScoped
    Counter counter2 = new Counter() {
        int i = 0;

        @Override
        public void inc() {
            i++;
        }

        @Override
        public void inc(long l) {
            i += l;
        }

        @Override
        public long getCount() {
            return i;
        }
    };*/


    @GET
    @Produces(MediaType.APPLICATION_JSON)

    /*@Counted(
            name = "meuContador",
            absolute = true,
            reusable = true
    )*/
    //@ConcurrentGauge
    //@org.eclipse.microprofile.metrics.annotation.Gauge(name = "hitPercentage", unit = MetricUnits.NONE, tags = "tag=tag")
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
        //simpleTimer.time();
        concurrentGauge.inc();
        //counter2.inc(20);
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


    @GET
    @Path("criar-metricas")
    public String criarMetricas() {
        Counter counter = baseMetricRegistry.counter("contador-2");
        Metadata metadata = Metadata.builder().withName("contador-2").withUnit("Kg").build();

        // Dá erro no quarkus
        //Counter counter2 = metricRegistry.counter(metadata);
        counter.inc();
        return "OK " + counter.getCount();
    }

    @GET
    @Path("apagar-metricas")
    public String apagarMetricas() {
        baseMetricRegistry.remove("contador-2");
        return "OK ";
    }
}

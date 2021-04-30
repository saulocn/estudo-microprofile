package com.saulocn.microprofile.metrics.service;

import com.saulocn.microprofile.metrics.ClasseCustomizada;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

//@ApplicationScoped
@RequestScoped
@Counted(name = "counted")
public class MPService {
    public void metodo1() {

    }

    public void metodo2() {

    }

    @Gauge(unit = MetricUnits.NONE, name = "gauge")
    public void metodo3() {

    }

    @Produces
    @ApplicationScoped
    public ClasseCustomizada criar() {
        return new ClasseCustomizada();
    }
}

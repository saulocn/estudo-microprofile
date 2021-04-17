package com.saulocn.microprofile.metrics.service;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;

import javax.enterprise.context.RequestScoped;

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

}

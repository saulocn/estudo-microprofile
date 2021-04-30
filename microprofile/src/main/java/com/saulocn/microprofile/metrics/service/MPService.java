package com.saulocn.microprofile.metrics.service;

import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.enterprise.context.RequestScoped;

//@ApplicationScoped
@RequestScoped
@Counted(name = "counted")
public class MPService {
    public void metodo1() {

    }

    public void metodo2() {

    }

    public void metodo3() {

    }

}

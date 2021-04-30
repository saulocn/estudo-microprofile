package com.saulocn.microprofile.metrics;

import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClasseCustomizada {

    @Counted
    public int fazAlgo() {
        return 123;
    }
}

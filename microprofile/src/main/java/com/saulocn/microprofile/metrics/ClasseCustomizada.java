package com.saulocn.microprofile.metrics;

import org.eclipse.microprofile.metrics.annotation.Counted;

public class ClasseCustomizada {

    @Counted
    public int fazAlgo() {
        return 123;
    }
}

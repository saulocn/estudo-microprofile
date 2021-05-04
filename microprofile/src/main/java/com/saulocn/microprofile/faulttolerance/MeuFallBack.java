package com.saulocn.microprofile.faulttolerance;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

public class MeuFallBack implements FallbackHandler<String> {

    @Override
    public String handle(ExecutionContext executionContext) {
        return "Minha classe de fallback";
    }
}

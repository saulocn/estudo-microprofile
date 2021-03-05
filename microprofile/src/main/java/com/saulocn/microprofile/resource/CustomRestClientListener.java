package com.saulocn.microprofile.resource;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.spi.RestClientListener;

public class CustomRestClientListener implements RestClientListener {
    @Override
    public void onNewClient(Class<?> aClass, RestClientBuilder restClientBuilder) {
        restClientBuilder.register(CustomRequestFilter.class);
    }
}

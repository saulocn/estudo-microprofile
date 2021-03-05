package com.saulocn.microprofile.resource;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.IOException;
import java.util.Collections;

public class CustomRequestFilter implements ClientRequestFilter {
    @Override
    public void filter(ClientRequestContext clientRequestContext) throws IOException {
        clientRequestContext.getHeaders().put("CustomizerHeadFIlter", Collections.singletonList("Lista customizada da parada"));
    }
}

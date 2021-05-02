package com.saulocn.microprofile.faulttolerance;

import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("mp-fault")
@RequestScoped
public class MPFault {

    @GET
    @Path("timeout")
    @Produces(MediaType.APPLICATION_JSON)
    @Timeout(200)
    public String timeout(@QueryParam("sleep") Integer sleep) throws InterruptedException {
        Thread.sleep(sleep);
        return "Hello com timeout!";
    }
}

package com.saulocn.microprofile.faulttolerance;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicInteger;

@Path("mp-fault")
@RequestScoped
public class MPFault {

    AtomicInteger atomicInteger = new AtomicInteger();

    @GET
    @Path("timeout")
    @Produces(MediaType.APPLICATION_JSON)
    @Timeout(200)

    public String timeout(@QueryParam("sleep") Integer sleep) throws InterruptedException {
        Thread.sleep(sleep);
        return "Hello com timeout!";
    }

    @GET
    @Path("retry")
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 20,
            //abortOn = IllegalArgumentException.class
            //retryOn = MyThrowable.class,
            maxDuration = 1000
    )
    public String retry(@QueryParam("erros") Integer erros) throws Exception {
        if (atomicInteger.get() == 2) {
            Thread.sleep(1000);
        }

        if (atomicInteger.incrementAndGet() <= erros) {
            throw new Exception("Erro: " + atomicInteger.get());
            // Só funcionou no Quarkus
            //throw new MyThrowable();
        }
        return "Hello com retry!";
    }
}

package com.saulocn.microprofile.faulttolerance;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
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

    @GET
    @Path("fallback")
    @Produces(MediaType.APPLICATION_JSON)
    @Fallback(
            //fallbackMethod = "meuFallback",
            // pode ser a classe como abaixo, ou um método como acima,
            value = MeuFallBack.class,
            // O Quarkus considera quando se é um Throwable, mas o OpenLiberty não
            // skipOn = MyThrowable.class,
            applyOn = MyThrowable.class
    )
    public String fallback(@QueryParam("erro") Boolean erro) throws Throwable {
        if (erro) {
            //throw new Exception("Erro: " + atomicInteger.get());
            throw new MyThrowable();
        }
        return "Hello com fallback!";
    }

    // a assinatura deve ser igual
    public String meuFallback(@QueryParam("erro") Boolean erro) {
        return "Hello do método de fallback!";
    }


    @GET
    @Path("circuit-breaker")
    @Produces(MediaType.APPLICATION_JSON)
    // Não funcionou no Liberty
    @CircuitBreaker(
            delay = 10000,
            successThreshold = 5,
            failureRatio = 0.2,
            requestVolumeThreshold = 4
    )
    public String circuitBreaker(@QueryParam("erro") Boolean erro) throws Throwable {
        if (erro) {
            throw new Exception("Erro!");
        }
        return "Hello com circuit breaker!";
    }

}

package com.saulocn.microprofile.faulttolerance;

import com.saulocn.microprofile.dto.MunicipioDTO;
import com.saulocn.microprofile.service.MunicipioService;
import org.eclipse.microprofile.faulttolerance.*;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

@Path("mp-fault")
//@RequestScoped
// Para funcionar o circuit breaker e o bulkhead no Open Liberty, o Resource precisa ser Application Scoped,
// apesar de não fazer parte da especificação
@ApplicationScoped
public class MPFault {

    static AtomicInteger atomicInteger = new AtomicInteger();

    @Inject
    @RestClient
    MunicipioService municipioService;

    @GET
    @Path("timeout")
    @Produces(MediaType.APPLICATION_JSON)
    @Timeout(200)
    public String timeout(@QueryParam("sleep") Integer sleep) throws InterruptedException {
        Thread.sleep(sleep);
        return "Hello com timeout!";
    }


    @GET
    @Path("rest-client")
    @Produces(MediaType.APPLICATION_JSON)
    public MunicipioDTO restClient() {
        return municipioService.buscarMunicipio();
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
            value = MeuFallBack.class
            // O Quarkus considera quando se é um Throwable, mas o OpenLiberty não
            // skipOn = MyThrowable.class,
            //applyOn = MyThrowable.class
    )
    public String fallback(@QueryParam("erro") Boolean erro) throws Throwable {
        if (erro) {
            throw new Exception("Erro: " + atomicInteger.get());
            //throw new MyThrowable();
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


    @GET
    @Path("bulkhead")
    @Produces(MediaType.APPLICATION_JSON)
    @Bulkhead(
            value = 3,
            // O waitingTaskQueue funcionaria em um método assíncrono
            waitingTaskQueue = 10
    )
    public String bulkhead(@QueryParam("erro") Boolean erro) throws Throwable {
        Thread.sleep(10000);
        return "Hello com circuit breaker!";
    }


    @GET
    @Path("future")
    @Produces(MediaType.APPLICATION_JSON)
    @Asynchronous
    @Bulkhead(
            value = 3,
            waitingTaskQueue = 1
    )
    public Future<String> future(@QueryParam("erro") Boolean erro) throws Throwable {
        Thread.sleep(6000);
        CompletableFuture<String> retorno = new CompletableFuture<>();
        retorno.completeAsync(MPFault::successo);
        return retorno;
    }


    @GET
    @Path("future-retry")
    @Produces(MediaType.APPLICATION_JSON)
    @Asynchronous
    @Retry(maxRetries = 3)
    public Future<String> futureRetry(@QueryParam("erro") Boolean erro) throws Throwable {
        atomicInteger.incrementAndGet();
        CompletableFuture<String> retorno = new CompletableFuture<>();
        retorno.completeAsync(MPFault::falha);
        return retorno;
    }


    @GET
    @Path("future-stage")
    @Produces(MediaType.APPLICATION_JSON)
    @Asynchronous
    @Retry(maxRetries = 3)
    public CompletionStage<String> futureStage(@QueryParam("erro") Boolean erro) throws Throwable {
        atomicInteger.incrementAndGet();
        CompletableFuture<String> retorno = new CompletableFuture<>();
        retorno.completeAsync(MPFault::falha);
        return retorno;
    }

    private static String successo() {
        return "sucesso";
    }

    private static String falha() {
        throw new NullPointerException("Null: " + atomicInteger.get());
    }
}

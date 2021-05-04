package com.saulocn.microprofile.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class AnyManagedBean {

    @Produces
    @Readiness
    public HealthCheck verifyDatabase() {
        return () -> HealthCheckResponse.named("O outro banco de dados Readiness").up().build();
    }

    @Produces
    @Liveness
    public HealthCheck verifyDatabaseLiveness() {
        return () -> HealthCheckResponse.named("O outro banco de dados Liveness").up().build();
    }
}

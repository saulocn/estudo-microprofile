package com.saulocn.microprofile.jwt;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("mp-jwt")
public class MPJWT {

    @Inject
    JsonWebToken token;

    @Inject
    @Claim(value = "sub", standard = Claims.preferred_username)
    String nome;

    @Inject
    @Claim(value = "sub", standard = Claims.preferred_username)
    Instance<String> nomeInst;

    //@Inject
    //@Claim(value = "sub", standard = Claims.preferred_username)
    //Optional<String> nomeOpt;

    @GET
    @Path("permit-all")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)

    public String permitAll() {
        return "hello";
    }

    @GET
    @Path("deny-all")
    @DenyAll
    @Produces(MediaType.TEXT_PLAIN)
    public String denyAll() {
        return "hello";
    }

    @GET
    @Path("proprietario")
    @RolesAllowed("proprietario")
    @Produces(MediaType.TEXT_PLAIN)
    public String rolesProprietario() {
        return "hello, " + token.getName() + " - " + nome + " - " + nomeInst.get();// + " - " + nomeOpt.get();
    }

    @GET
    @Path("ajudante")
    @RolesAllowed("ajudante")
    @Produces(MediaType.TEXT_PLAIN)
    public String rolesAjudante() {
        return "hello";
    }
}

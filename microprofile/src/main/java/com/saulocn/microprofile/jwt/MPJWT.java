package com.saulocn.microprofile.jwt;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("mp-jwt")
public class MPJWT {

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
        return "hello";
    }

    @GET
    @Path("ajudante")
    @RolesAllowed("ajudante")
    @Produces(MediaType.TEXT_PLAIN)
    public String rolesAjudante() {
        return "hello";
    }
}

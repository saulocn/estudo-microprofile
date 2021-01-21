package com.saulocn.microprofile.service;

import com.saulocn.microprofile.dto.MunicipioDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("municipios")
@RegisterRestClient(configKey = "correios")
public interface MunicipioService {

    @POST
    public MunicipioDTO adicionar(@HeaderParam("UsingHeaderParam") String header, @QueryParam("idUF") Integer idUF, MunicipioDTO municipioDTO);

}

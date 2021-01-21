package com.saulocn.microprofile.service;

import com.saulocn.microprofile.dto.MunicipioDTO;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.ext.DefaultClientHeadersFactoryImpl;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("municipios")
@RegisterRestClient(configKey = "correios")
@ClientHeaderParam(name = "UsingInterfaceHeaderParam", value = "InterfaceValue1")
@RegisterClientHeaders(DefaultClientHeadersFactoryImpl.class)
public interface MunicipioService {

    @POST
    @ClientHeaderParam(name = "UsingMethodHeaderParam", value = "MethodValue2")
    @ClientHeaderParam(name = "UsingMethodCalledHeaderParam", value = "{gerarToken}")
    @ClientHeaderParam(name = "UsingClassMethodCalledHeaderParam", value = "{com.saulocn.microprofile.config.TokenGenerator.novoGerarToken}")
    public MunicipioDTO adicionar(@HeaderParam("UsingHeaderParam") String header, @QueryParam("idUF") Integer idUF, MunicipioDTO municipioDTO);

    default String gerarToken(String header) {
        return header + "bla123";
    }
}

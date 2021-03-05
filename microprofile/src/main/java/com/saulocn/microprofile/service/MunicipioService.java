package com.saulocn.microprofile.service;

import com.saulocn.microprofile.dto.MunicipioDTO;
import com.saulocn.microprofile.params.BeanParamSample;
import com.saulocn.microprofile.resource.CustomRequestFilter;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.ext.DefaultClientHeadersFactoryImpl;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import java.util.concurrent.CompletionStage;

@Path("municipios")
@RegisterRestClient(configKey = "correios")
@ClientHeaderParam(name = "UsingInterfaceHeaderParam", value = "InterfaceValue1")
@RegisterClientHeaders(DefaultClientHeadersFactoryImpl.class)
@RegisterProvider(CustomRequestFilter.class)
public interface MunicipioService {

    @POST
    @ClientHeaderParam(name = "UsingMethodHeaderParam", value = "MethodValue2")
    @ClientHeaderParam(name = "UsingMethodCalledHeaderParam", value = "{gerarToken}")
    @ClientHeaderParam(name = "UsingClassMethodCalledHeaderParam", value = "{com.saulocn.microprofile.config.TokenGenerator.novoGerarToken}")
    public MunicipioDTO adicionar(@HeaderParam("UsingHeaderParam") String header, @QueryParam("idUF") Integer idUF, MunicipioDTO municipioDTO);


    @POST
    public MunicipioDTO adicionar2(@BeanParam BeanParamSample beanParam);

    @POST
    public CompletionStage<MunicipioDTO> adicionarAsync(@HeaderParam("UsingHeaderParam") String header, @QueryParam("idUF") Integer idUF, MunicipioDTO municipioDTO);

    default String gerarToken(String header) {
        return header + "bla123";
    }
}

package com.saulocn.microprofile.params;

import com.saulocn.microprofile.dto.MunicipioDTO;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;

public class BeanParamSample {
    @HeaderParam("UsingHeaderParam")
    String header;
    @QueryParam("idUF")
    Integer idUF;
    MunicipioDTO municipioDTO;
}

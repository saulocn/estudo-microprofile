package com.saulocn.microprofile.openapi;

import org.eclipse.microprofile.openapi.OASFactory;
import org.eclipse.microprofile.openapi.OASModelReader;
import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.eclipse.microprofile.openapi.models.info.Info;

public class MyOASReader implements OASModelReader {
    @Override
    public OpenAPI buildModel() {
        OpenAPI openAPI = OASFactory.createOpenAPI();
        Info info = OASFactory.createInfo()
                .title("Titulo do reader criado programaticamente");
        info.setDescription("Descrição programaticamente");
        openAPI.setInfo(info);
        return openAPI;
    }
}

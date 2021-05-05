package com.saulocn.microprofile;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(info = @Info(
        title = "Super Ultra Hiper Mega App Bla Bla Bla",
        version = "0.0.1",
        //description = "Propriedades de descrição da app",
        contact = @Contact(
                email = "xpto@mail.com",
                name = "Saulo"
        ),
        license = @License(
                name = "LicencaTeste",
                url = "http://www.google.com"
        )
))
@ApplicationPath("/")
public class MinhaAplicacao extends Application {

}

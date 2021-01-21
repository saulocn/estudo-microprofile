package com.saulocn.microprofile;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.config.spi.ConfigSource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("mp-config")
@ApplicationScoped
//@RequestScoped
public class MPConfigResource {

    @Inject
    Config config;

    //@Inject
    //@ConfigProperty(name = "quantidade.elementos.pagina", defaultValue = "44")
    //Integer quantidadeElementosPagina;

    @Inject
    @ConfigProperty(name = "quantidade.elementos.pagina")
    Optional<Integer> quantidadeElementosPaginaOp;

    @Inject
    @ConfigProperty(name = "quantidade.elementos.pagina")
    Provider<Integer> quantidadeElementosPaginaP;

    @Inject
    @ConfigProperty(name = "email.corporativo")
    Email email;

    //@Inject
    //@ConfigProperty(name = "custom.config.source")
    //String customConfigSource;

    @Inject
    @ConfigProperty(name = "custom.config.source")
    Provider<String> customConfigSourceProvider;


    @GET
    @Path("quantidade")
    @Produces(MediaType.TEXT_PLAIN)
    public String getQuantidade() {
        return quantidadeElementosPaginaOp.toString();
    }

    @GET
    @Path("config-sources")
    @Produces(MediaType.TEXT_PLAIN)
    public String getConfigSources() {
        customConfigSourceProvider.get();
        config = ConfigProvider.getConfig();
        final Iterable<ConfigSource> configSources = config.getConfigSources();
        final StringBuilder sb = new StringBuilder();
        for (final ConfigSource configSource : configSources) {
            sb.append("NOME: ").append(configSource.getName())
                    .append("\nORDINAL: ").append(configSource.getOrdinal())
                    .append("\nPropertyNames: ").append(configSource.getPropertyNames())
                    .append("\n\n");
        }

        return sb.toString();
    }

}

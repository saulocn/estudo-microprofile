package com.saulocn.microprofile;

import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.config.spi.ConfigSource;

@Path("mp-config")
@ApplicationScoped
public class MPConfigResource {

    @Inject
    Config config;

    @Inject
    @ConfigProperty(name = "quantidade.elementos.pagina", defaultValue = "44")
    Integer quantidadeElementosPagina;

    @Inject
    @ConfigProperty(name = "quantidade.elementos.pagina")
    Optional<Integer> quantidadeElementosPaginaOp;

    @Inject
    @ConfigProperty(name = "quantidade.elementos.pagina")
    Provider<Integer> quantidadeElementosPaginaP;

    @GET
    @Path("quantidade")
    @Produces(MediaType.TEXT_PLAIN)
    public String getQuantidade() {
        return quantidadeElementosPaginaP.toString();
    }

    @GET
    @Path("config-sources")
    @Produces(MediaType.TEXT_PLAIN)
    public String getConfigSources() {
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

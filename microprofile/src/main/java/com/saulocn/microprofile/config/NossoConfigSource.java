package com.saulocn.microprofile.config;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Collections;
import java.util.Map;

public class NossoConfigSource implements ConfigSource {
    Map<String, String> propriedades = Collections.singletonMap("custom.config.source", "microprofile10");

    @Override
    public int getOrdinal() {
        return 1;
    }

    @Override
    public Map<String, String> getProperties() {
        return propriedades;
    }

    @Override
    public String getValue(String s) {
        System.out.println("-----" + s);
        return propriedades.get(s);
    }

    @Override
    public String getName() {
        return "Meu config source customizado";
    }
}

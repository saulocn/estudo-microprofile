package com.saulocn.microprofile.config;

public class TokenGenerator {

    public static String novoGerarToken(String header) {
        return header + "_novo_bla123";
    }
}

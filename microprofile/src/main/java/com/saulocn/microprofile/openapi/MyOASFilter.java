package com.saulocn.microprofile.openapi;

import org.eclipse.microprofile.openapi.OASFilter;
import org.eclipse.microprofile.openapi.models.Operation;

import static com.saulocn.microprofile.openapi.PessoaResource.SALVA_UMA_NOVA_PESSOA;

public class MyOASFilter implements OASFilter {

    @Override
    public Operation filterOperation(Operation operation) {
        //operation.setSummary("Sumário fixo de todas operações");
        if (SALVA_UMA_NOVA_PESSOA.equals(operation.getSummary())) {
            return null;
        }
        return OASFilter.super.filterOperation(operation);
    }
}

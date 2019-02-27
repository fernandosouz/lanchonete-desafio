package com.example.dextrastartup.demo.wrappers;

import com.example.dextrastartup.demo.model.Ingrediente;

import java.util.List;

/**
 * Criado por  Fernando Henrique de Souza em 26/02/19.
 */

/**
 * Classe que empacota as propriedades do objeto recebido no endpoint @see LancheEndPoint#detalhesDoPedido()
 */
public class LancheWrapperRequest {

    private List<Ingrediente> ingredientes;
    private Integer codigoDoLanche;

    public Integer getCodigoDoLanche() {
        return codigoDoLanche;
    }

    public void setCodigoDoLanche(Integer codigoDoLanche) {
        this.codigoDoLanche = codigoDoLanche;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
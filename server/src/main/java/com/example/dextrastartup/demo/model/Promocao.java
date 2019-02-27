package com.example.dextrastartup.demo.model;

import com.example.dextrastartup.demo.utils.AbstractModel;


/**
 * Criado por  Fernando Henrique de Souza on 23/02/19.
 */

/**
 * Classe que define a entidade "Promoção"
 */
public class Promocao extends AbstractModel {
    private String nome;
    private int quantidade;

    public Promocao(Integer codigo, String nome, String descricao, int quantidade){
        this.setCodigo(codigo);
        this.setDescricao(descricao);
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

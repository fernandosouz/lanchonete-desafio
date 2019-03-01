package com.example.dextrastartup.demo.model;


/**
 * Criado por  Fernando Henrique de Souza on 23/02/19.
 */

import com.example.dextrastartup.demo.model.CalculadorasDePromocao.CalculadoraDePromocao;

/**
 * Classe que define a entidade "Promoção"
 */
public class PromocaoModel extends AbstractModel {
    private String nome;
    private int quantidade;
    public CalculadoraDePromocao calculadoraDePromocao;

    public PromocaoModel(Integer codigo, String nome, String descricao, int quantidade, CalculadoraDePromocao calculadoraDePromocao){
        this.setCodigo(codigo);
        this.setDescricao(descricao);
        this.nome = nome;
        this.quantidade = quantidade;
        this.calculadoraDePromocao = calculadoraDePromocao;
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

    public CalculadoraDePromocao getCalculadoraDePromocao() {
        return calculadoraDePromocao;
    }

    public void setCalculadoraDePromocao(CalculadoraDePromocao calculadoraDePromocao) {
        this.calculadoraDePromocao = calculadoraDePromocao;
    }
}

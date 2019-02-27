package com.example.dextrastartup.demo.model;

import com.example.dextrastartup.demo.repositories.IngredienteRepositorio;
import com.example.dextrastartup.demo.utils.AbstractModel;

import java.util.Objects;


/**
 * Criado por  Fernando Henrique de Souza on 23/02/19.
 */

/**
 * Classe que define a entidade "Ingrediente"
 */

public class  Ingrediente extends AbstractModel {
    private int quantidade;
    private double valor;

    public Ingrediente() {
    }

    /**
     * Construtor da classe que especifíca código, descrição, valor e quantidade.
     */
    public Ingrediente(Integer codigo, String descricao, double valor, int quantidade){
        this.setCodigo(codigo);
        this.setDescricao(descricao);
        this.valor = valor;
        this.quantidade = quantidade;
    }

    /**
     * Construtor da classe que especifíca o código.
     */
    public Ingrediente(Integer codigo){
        this.setCodigo(codigo);
        this.setDescricao(IngredienteRepositorio.retornaIngredientePorCodigo(codigo).getDescricao());
        this.valor = IngredienteRepositorio.retornaIngredientePorCodigo(codigo).getValor();
        this.quantidade = IngredienteRepositorio.retornaIngredientePorCodigo(codigo).getQuantidade();
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingrediente ingrediente = (Ingrediente) o;
        return Objects.equals(this.getCodigo(), ingrediente.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCodigo());
    }
}

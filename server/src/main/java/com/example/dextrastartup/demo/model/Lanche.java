package com.example.dextrastartup.demo.model;

import com.example.dextrastartup.demo.repositories.IngredienteRepositorio;
import com.example.dextrastartup.demo.repositories.LancheRepositorio;
import com.example.dextrastartup.demo.utils.AbstractModel;

import java.util.*;

/**
 * Criado por  Fernando Henrique de Souza on 23/02/19.
 */

/**
 * Classe que define a entidade "Lanche"
 */

public class Lanche extends AbstractModel {
    private double valorTotal;
    private List<Ingrediente> listaDeIngredientes;
    private List<Promocao> listaDePromocoes;
    private double valorComDesconto;

    /**
     * Construtor da classe que especifíca o código.
     */
    public Lanche(Integer codigo) {
        this.setCodigo(codigo);
        this.setDescricao(LancheRepositorio.retornaLanchePorCodigo(codigo).getDescricao());
        this.listaDeIngredientes = LancheRepositorio.retornaLanchePorCodigo(codigo).getListaDeIngredientes();
        this.valorTotal = LancheRepositorio.retornaLanchePorCodigo(codigo).retornaValorTotal();
        this.valorComDesconto = this.valorTotal;
        this.listaDePromocoes = new ArrayList<Promocao>();
    }

    /**
     * Construtor da classe que especifíca código, descrição e a lista de ingredientes.
     */
    public Lanche(Integer codigo, String descricao, List<Ingrediente> listaDeIngredientes) {
        this.setCodigo(codigo);
        this.setDescricao(descricao);
        this.listaDeIngredientes = listaDeIngredientes;
        this.valorTotal = this.retornaValorTotal();
        this.valorComDesconto = this.retornaValorTotal();
        this.listaDePromocoes = new ArrayList<Promocao>();
    }

    public List<Ingrediente> getListaDeIngredientes() {
        return listaDeIngredientes;
    }

    public void setListaDeIngredientes(List<Ingrediente> listaDeIngredientes) {
        this.listaDeIngredientes = listaDeIngredientes;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorComDesconto() {
        return valorComDesconto;
    }

    public void setValorComDesconto(double valorComDesconto) {
        this.valorComDesconto = valorComDesconto;
    }

    public List<Promocao> getListaDePromocoes() {
        return listaDePromocoes;
    }

    public void setListaDePromocoes(List<Promocao> listaDePromocoes) {
        this.listaDePromocoes = listaDePromocoes;
    }

    /**Método que retorna o valor total do lanche
     * O valor total é a somatória do valor de todos os ingredientes
     * @author Fernando Henrique de Souza
     * @return double valor total do lanche.
     */
    public double retornaValorTotal(){
        double valorTotal = 0;
        for(Ingrediente ingrediente: this.getListaDeIngredientes()){
            valorTotal += ingrediente.getValor() * ingrediente.getQuantidade();
        }
        return valorTotal;
    }

    /**Método que verifica se o lanche foi personalizado.
     * Verifica comparando a quantidade de ingredientes de um lanche padrão(de mesmo código)
     * com o lanche atual
     * @author Fernando Henrique de Souza
     * @return boolean que indica se ele foi personalizado
     */
    public Boolean foiPersonalizado(){
        Lanche lancheOriginal = new Lanche(this.getCodigo());
        int quantidadeTotal = 0;
        int quantidadeTotalOriginal = 0;

        for(Ingrediente ingrediente: this.getListaDeIngredientes()){
            quantidadeTotal += ingrediente.getQuantidade();
        }

        for(Ingrediente ingrediente: lancheOriginal.getListaDeIngredientes()){
            quantidadeTotalOriginal += ingrediente.getQuantidade();
        }
        //TODO Aguardando definição - É possível remover um ingrediente default?
        if(quantidadeTotalOriginal < quantidadeTotal){
            return true;
        }else {
            return false;
        }
    }

    /**Método que verifica qual é a quantidade de porções de um determinado ingrediente no lanche.
     * @author Fernando Henrique de Souza
     * @param  codigo  código do Ingrediente
     * @return quantidade de porções que existem
     */
    public int retornaQuantidadeDePorcoesPorCodigo(Integer codigo){
        int index = this.getListaDeIngredientes().indexOf(IngredienteRepositorio.retornaIngredientePorCodigo(codigo));
        return this.getListaDeIngredientes().get(index).getQuantidade();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lanche lanche = (Lanche) o;
        return Objects.equals(this.getCodigo(), lanche.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCodigo());
    }
}

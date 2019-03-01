package com.example.dextrastartup.demo.model;

import com.example.dextrastartup.demo.repositories.IngredienteRepository;
import com.example.dextrastartup.demo.repositories.LancheRepository;
import org.decimal4j.util.DoubleRounder;

import java.util.*;

/**
 * Criado por  Fernando Henrique de Souza on 23/02/19.
 */

/**
 * Classe que define a entidade "Lanche"
 */

public class LancheModel extends AbstractModel {
    private double valorTotal;
    private List<IngredienteModel> listaDeIngredientes;
    private List<PromocaoModel> listaDePromocoes;
    private double valorComDesconto;

    /**
     * Construtor da classe que especifíca o código.
     */
    public LancheModel(Integer codigo) {
        this.setCodigo(codigo);
        this.setDescricao(LancheRepository.retornaLanchePorCodigo(codigo).getDescricao());
        this.listaDeIngredientes = LancheRepository.retornaLanchePorCodigo(codigo).getListaDeIngredientes();
        this.valorTotal = LancheRepository.retornaLanchePorCodigo(codigo).retornaValorTotal();
        this.valorComDesconto = this.valorTotal;
        this.listaDePromocoes = new ArrayList<PromocaoModel>();
    }

    /**
     * Construtor da classe que especifíca código, descrição e a lista de ingredientes.
     */
    public LancheModel(Integer codigo, String descricao, List<IngredienteModel> listaDeIngredientes) {
        this.setCodigo(codigo);
        this.setDescricao(descricao);
        this.listaDeIngredientes = listaDeIngredientes;
        this.valorTotal = this.retornaValorTotal();
        this.valorComDesconto = this.retornaValorTotal();
        this.listaDePromocoes = new ArrayList<PromocaoModel>();
    }

    public List<IngredienteModel> getListaDeIngredientes() {
        return listaDeIngredientes;
    }

    public void setListaDeIngredientes(List<IngredienteModel> listaDeIngredientes) {
        this.listaDeIngredientes = listaDeIngredientes;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = DoubleRounder.round(valorTotal, 2);
    }

    public double getValorComDesconto() {
        return valorComDesconto;
    }

    public void setValorComDesconto(double valorComDesconto) {
        this.valorComDesconto = DoubleRounder.round(valorComDesconto, 2);
    }

    public List<PromocaoModel> getListaDePromocoes() {
        return listaDePromocoes;
    }

    public void setListaDePromocoes(List<PromocaoModel> listaDePromocoes) {
        this.listaDePromocoes = listaDePromocoes;
    }

    /**Método que retorna o valor total do lanche
     * O valor total é a somatória do valor de todos os ingredientes
     * @author Fernando Henrique de Souza
     * @return double valor total do lanche.
     */
    public double retornaValorTotal(){
        double valorTotal = 0;
        for(IngredienteModel ingrediente: this.getListaDeIngredientes()){
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
        LancheModel lancheOriginal = new LancheModel(this.getCodigo());
        int quantidadeTotal = 0;
        int quantidadeTotalOriginal = 0;

        for(IngredienteModel ingrediente: this.getListaDeIngredientes()){
            quantidadeTotal += ingrediente.getQuantidade();
        }

        for(IngredienteModel ingrediente: lancheOriginal.getListaDeIngredientes()){
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
        int index = this.getListaDeIngredientes().indexOf(IngredienteRepository.retornaIngredientePorCodigo(codigo));
        if(index >= 0) {
            return this.getListaDeIngredientes().get(index).getQuantidade();
        }else {
            return 0;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LancheModel lanche = (LancheModel) o;
        return Objects.equals(this.getCodigo(), lanche.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCodigo());
    }
}

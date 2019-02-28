package com.example.dextrastartup.demo.controllers;

import com.example.dextrastartup.demo.model.IngredienteModel;
import com.example.dextrastartup.demo.model.LancheModel;
import com.example.dextrastartup.demo.model.PromocaoModel;
import com.example.dextrastartup.demo.repositories.IngredienteRepository;
import com.example.dextrastartup.demo.repositories.PromocaoRepository;

import java.util.List;

/**
 * Criado por  Fernando Henrique de Souza em 26/02/19.
 */

/**
 * Classe que simula o repositório de informações da entidade "Promoção".
 */
public class LancheController {

    LancheModel lanche;

    /**
     *
     *  Construtor que define lanche que será manipulado.
     **/
    public LancheController(LancheModel lanche) {
        this.lanche = lanche;
    }

    /**
     *
     * Construtor default
     **/
    public LancheController() {

    }

    public LancheModel getLanche() {
        return lanche;
    }

    public void setLanche(LancheModel lanche) {
        this.lanche = lanche;
    }

    /**Método que aplica uma determina promoção no valor com desconto do lanche
     *
     * Esse método define o cálculo que será aplicado em cada promoção, definindo o valor com desconto do lanche.
     *
     * A quantidade de vezes que a promoção "Light" será aplicada é sempre 1, pois não depende da quantidade de porções
     * de um ingrediente específico
     *
     * @author Fernando Henrique de Souza
     * @param codigoDaPromocao Código da promoção que será aplicada
     * @param quantidadeDeAplicacoes Quantidade de vezes que a promoção será aplicada
     */
    public void aplicaPromocao(Integer codigoDaPromocao, Integer quantidadeDeAplicacoes){
        if(codigoDaPromocao == 1){
            quantidadeDeAplicacoes = 1;
        }
        PromocaoModel promocao = PromocaoRepository.retornaPromocaoPorCodigo(codigoDaPromocao);
        lanche.getListaDePromocoes().add(promocao);
        promocao.setQuantidade(quantidadeDeAplicacoes);

        switch (codigoDaPromocao){
            case 1:
                /*Promoção 1 - Desconto de 10%*/
                lanche.setValorComDesconto(lanche.getValorTotal() - lanche.getValorTotal()/10);
                break;
            case 2:
                /*Promoção 3 - Desconta 1 porção a cada 3 porções de hambúrguer de carne.*/
                lanche.setValorComDesconto(lanche.getValorComDesconto() - (quantidadeDeAplicacoes * IngredienteRepository.retornaIngredientePorCodigo(3).getValor()));
                break;
            case 3:
                /*Promoção 2 - Desconta 1 porção a cada 3 porções de queijo.*/
                lanche.setValorComDesconto(lanche.getValorComDesconto() - (quantidadeDeAplicacoes * IngredienteRepository.retornaIngredientePorCodigo(5).getValor()));
                break;
            default:
                break;
        }
    }

    /**Esse método contém a regra de negócio que informa se a promoção deve ou não ser aplicada.
     *
     *
     * @author Fernando Henrique de Souza
     * @param codigoDaPromocao Código da promoção que será aplicada
     * @return boolean que representa se o lanche participa da promoção
     */
    public Boolean participaDaPromocao(Integer codigoDaPromocao) {
        switch (codigoDaPromocao) {
            case 1:
                IngredienteModel ingredienteAlface = new IngredienteModel(1);
                IngredienteModel ingredienteBacon = new IngredienteModel(2);
                /*Se tem alface e não tem bacon*/
                return lanche.getListaDeIngredientes().contains(ingredienteAlface)
                        && !lanche.getListaDeIngredientes().contains(ingredienteBacon);
            case 2:
                /*Se tem 3 ou mais porções de hambúrguer de carne*/
                return 0 < (int) retornaQuantidadeDeAplicacoesPorCodigoDeProduto(3);
            case 3:
                /*Se tem 3 ou mais porções de queijo*/
                int a = (int) retornaQuantidadeDeAplicacoesPorCodigoDeProduto(5);
                return 0 < a;
            default: return false;
        }
    }

    /**Método que verifica se o lanche participa da promoção e chama o método que aplica as alterações.
     *
     * @author Fernando Henrique de Souza
     */
    public void aplicaPromocoes(){
        if(participaDaPromocao(1)){
            aplicaPromocao(1, null);
        }
        if(participaDaPromocao(2)){
            aplicaPromocao(2, retornaQuantidadeDeAplicacoesPorCodigoDeProduto(3));
        }
        if(participaDaPromocao(3)){
            aplicaPromocao(3, retornaQuantidadeDeAplicacoesPorCodigoDeProduto(5));
        }
    }

    /**Esse método retorna a quantidade de vezes que a promoção será aplicada baseado no código do produto.
     *
     *
     * @author Fernando Henrique de Souza
     * @param codigo Código do ingrediente que será verificado
     * @return quantidade de vezes que a promoção será aplicada ao lanche.
     */
    public Integer retornaQuantidadeDeAplicacoesPorCodigoDeProduto(Integer codigo){
        return (int)this.lanche.retornaQuantidadeDePorcoesPorCodigo(codigo) / 3;
    }

    /**Esse método que adiciona o ingrediente ao lanche pela quantidade.
     *
     *
     * @author Fernando Henrique de Souza
     * @param codigoDoIngrediente Código da promoção que será aplicada
     * @param quantidade Código da promoção que será aplicada
     */
    public void adicionarIngredientePorCodigoEQuantidade(Integer codigoDoIngrediente, Integer quantidade){
        IngredienteModel ingrediente = IngredienteRepository.retornaIngredientePorCodigo(codigoDoIngrediente);
        int index = lanche.getListaDeIngredientes().indexOf(ingrediente);
        if (lanche.getListaDeIngredientes().contains(ingrediente)) {
            lanche.getListaDeIngredientes().get(index).setQuantidade(lanche.getListaDeIngredientes().get(index).getQuantidade() + quantidade);
        }else{
            ingrediente.setQuantidade(quantidade);
            lanche.getListaDeIngredientes().add(ingrediente);
        }

        lanche.setValorComDesconto(lanche.retornaValorTotal());
        lanche.setValorTotal(lanche.retornaValorTotal());
    }


    /**Esse método chama o método que adiciona os ingredientes no lanche e o método que aplica as promoções.
     *
     * @author Fernando Henrique de Souza
     * @param ingredienteList lista de ingredientes que será adicionada ao lanche
     * @return Lanche com as alterações
     */
    public LancheModel aplicarAlteracoes(List<IngredienteModel> ingredienteList){
        for(IngredienteModel ingrediente: ingredienteList){
            if(ingrediente.getQuantidade() > 0){
                adicionarIngredientePorCodigoEQuantidade(ingrediente.getCodigo(), ingrediente.getQuantidade());
            }
        }

        aplicaPromocoes();

        return lanche;
    }
}

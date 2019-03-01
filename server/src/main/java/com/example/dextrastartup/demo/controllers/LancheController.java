package com.example.dextrastartup.demo.controllers;

import com.example.dextrastartup.demo.model.IngredienteModel;
import com.example.dextrastartup.demo.model.LancheModel;
import com.example.dextrastartup.demo.model.PromocaoModel;
import com.example.dextrastartup.demo.repositories.IngredienteRepository;
import com.example.dextrastartup.demo.repositories.PromocaoRepository;
import com.example.dextrastartup.demo.utils.Constantes;

import java.text.DecimalFormat;
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
    private void aplicaPromocao(Integer codigoDaPromocao, Integer quantidadeDeAplicacoes){
        if(codigoDaPromocao == 1){
            quantidadeDeAplicacoes = 1;
        }

        PromocaoModel promocao = PromocaoRepository.retornaPromocaoPorCodigo(codigoDaPromocao);
        lanche.getListaDePromocoes().add(promocao);
        promocao.setQuantidade(quantidadeDeAplicacoes);
        double valorASerDescontado = 0.0;
        valorASerDescontado = promocao.calculadoraDePromocao.calcula(this.lanche);
        promocao.setCalculadoraDePromocao(null);

        lanche.setValorComDesconto(lanche.getValorComDesconto() - valorASerDescontado);
    }

    /**Esse método contém a regra de negócio que informa se a promoção deve ou não ser aplicada.
     *
     *
     * @author Fernando Henrique de Souza
     * @param codigoDaPromocao Código da promoção que será aplicada
     * @return boolean que representa se o lanche participa da promoção
     */
    private Boolean participaDaPromocao(Integer codigoDaPromocao) {
        switch (codigoDaPromocao) {
            case 1:
                IngredienteModel ingredienteAlface = new IngredienteModel(Constantes.CODIGO_ALFACE);
                IngredienteModel ingredienteBacon = new IngredienteModel(Constantes.CODIGO_BACON);
                /*Se tem alface e não tem bacon*/
                return lanche.getListaDeIngredientes().contains(ingredienteAlface)
                        && !lanche.getListaDeIngredientes().contains(ingredienteBacon);
            case 2:
                /*Se tem 3 ou mais porções de hambúrguer de carne*/
                return 0 < (int) retornaQuantidadeDeAplicacoesPorCodigoDeProduto(Constantes.CODIGO_HAMBURGUER_CARNE);
            case 3:
                /*Se tem 3 ou mais porções de queijo*/
                int a = (int) retornaQuantidadeDeAplicacoesPorCodigoDeProduto(Constantes.CODIGO_QUEIJO);
                return 0 < a;
            default: return false;
        }
    }

    /**Método que verifica se o lanche participa da promoção e chama o método que aplica as alterações.
     *
     * @author Fernando Henrique de Souza
     */
    public void aplicaPromocoes(){
        if(participaDaPromocao(Constantes.CODIGO_PROMO_LIGHT)){
            aplicaPromocao(Constantes.CODIGO_PROMO_LIGHT, null);
        }
        if(participaDaPromocao(Constantes.CODIGO_PROMO_MUITA_CARNE)){
            aplicaPromocao(Constantes.CODIGO_PROMO_MUITA_CARNE, retornaQuantidadeDeAplicacoesPorCodigoDeProduto(Constantes.CODIGO_HAMBURGUER_CARNE));
        }
        if(participaDaPromocao(Constantes.CODIGO_PROMO_MUITO_QUEIJO)){
            aplicaPromocao(Constantes.CODIGO_PROMO_MUITO_QUEIJO, retornaQuantidadeDeAplicacoesPorCodigoDeProduto(Constantes.CODIGO_QUEIJO));
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

        ingredienteList.forEach(i -> {
                if(i.getQuantidade() == 0)  return;
                adicionarIngredientePorCodigoEQuantidade(i.getCodigo(), i.getQuantidade());
        });

        aplicaPromocoes();

        return lanche;
    }
}

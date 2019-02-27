package com.example.dextrastartup.demo.repositories;

import com.example.dextrastartup.demo.model.PromocaoModel;

/**
 * Criado por  Fernando Henrique de Souza em 24/02/19.
 */

/**
 * Classe que simula o repositório de informações da entidade "Promoção".
 */
public class PromocaoRepository {

    /**Método que retorna uma promoção específica de acordo com o código passado
     * @author Fernando Henrique de Souza
     * @param codigoDaPromocao Código da promoção que será retornada
     * @return Promoção retornada definida por código
     */
    public static PromocaoModel retornaPromocaoPorCodigo(Integer codigoDaPromocao){
        switch (codigoDaPromocao) {
            case 1:
                return new PromocaoModel(1, "Light", "Você ganho 10% de desconto!", 1);
            case 2:
                return new PromocaoModel(2, "Muita carne", "A cada três porções de carne, você só pagou duas!", 0);
            case 3:
                return new PromocaoModel(3, "Muito queijo", "A cada três porções de queijo, você só pagou duas!", 0);
            default: return new PromocaoModel(4, "Inválido", "Inválido", 0);
        }

    }
}

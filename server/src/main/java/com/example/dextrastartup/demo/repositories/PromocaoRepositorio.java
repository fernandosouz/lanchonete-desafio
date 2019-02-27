package com.example.dextrastartup.demo.repositories;

import com.example.dextrastartup.demo.model.Promocao;

import java.util.ArrayList;
import java.util.List;

/**
 * Criado por  Fernando Henrique de Souza em 24/02/19.
 */

/**
 * Classe que simula o repositório de informações da entidade "Promoção".
 */
public class PromocaoRepositorio {

    /**Método que retorna uma promoção específica de acordo com o código passado
     * @author Fernando Henrique de Souza
     * @param codigoDaPromocao Código da promoção que será retornada
     * @return Promoção retornada definida por código
     */
    public static Promocao retornaPromocaoPorCodigo(Integer codigoDaPromocao){
        switch (codigoDaPromocao) {
            case 1:
                return new Promocao(1, "Light", "Você ganho 10% de desconto!", 1);
            case 2:
                return new Promocao(2, "Muita carne", "A cada três porções de carne, você só pagou duas!", 0);
            case 3:
                return new Promocao(3, "Muito queijo", "A cada três porções de queijo, você só pagou duas!", 0);
            default: return new Promocao(4, "Inválido", "Inválido", 0);
        }

    }
}

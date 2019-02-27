package com.example.dextrastartup.demo.repositories;

import com.example.dextrastartup.demo.exceptions.LancheNotFoundException;
import com.example.dextrastartup.demo.model.LancheModel;

import java.util.Arrays;

/**
 * Criado por  Fernando Henrique de Souza em 24/02/19.
 */

/**
 * Classe que simula o repositório de informações da entidade "Lanche".
 */

public class LancheRepository {

    /**  Método que retorna um lanche específico de acordo com o código passado.
     * @author Fernando Henrique de Souza
     * @param codigoDoLanche Código do lanche que será retornado.
     * @return Lanche retornado definido por código.
     */
    public static LancheModel retornaLanchePorCodigo(Integer codigoDoLanche){
        switch (codigoDoLanche){
            case 1:
                return new LancheModel(1, "X-Bacon", IngredienteRepository.retornaListadeIngredientesPorCodigo(
                        Arrays.asList(2,3,5)
                ));
            case 2:
                return new LancheModel(2, "X-Burguer", IngredienteRepository.retornaListadeIngredientesPorCodigo(
                        Arrays.asList(3,5)
                ));
            case 3:
                return new LancheModel(3, "X-Egg", IngredienteRepository.retornaListadeIngredientesPorCodigo(
                        Arrays.asList(3,4,5)
                ));
            case 4:
                return new LancheModel(4, "X-Egg Bacon", IngredienteRepository.retornaListadeIngredientesPorCodigo(
                        Arrays.asList(2,3,4,5)
                ));
            case 5:
                return new LancheModel(5, "Personalizado", IngredienteRepository.retornaListadeIngredientesPorCodigo(
                        Arrays.asList()
                ));
            default:
                throw new LancheNotFoundException(codigoDoLanche, LancheRepository.class);
        }
    }
}

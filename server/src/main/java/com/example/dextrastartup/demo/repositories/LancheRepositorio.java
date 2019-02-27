package com.example.dextrastartup.demo.repositories;

import com.example.dextrastartup.demo.model.Lanche;

import java.util.Arrays;

/**
 * Criado por  Fernando Henrique de Souza em 24/02/19.
 */

/**
 * Classe que simula o repositório de informações da entidade "Lanche".
 */

public class LancheRepositorio {

    /**  Método que retorna um lanche específico de acordo com o código passado.
     * @author Fernando Henrique de Souza
     * @param codigoDoLanche Código do lanche que será retornado.
     * @return Lanche retornado definido por código.
     */
    public static Lanche retornaLanchePorCodigo(Integer codigoDoLanche){
        switch (codigoDoLanche){
            case 1:
                return new Lanche(1, "X-Bacon", IngredienteRepositorio.retornaListadeIngredientesPorCodigo(
                        Arrays.asList(2,3,5)
                ));
            case 2:
                return new Lanche(2, "X-Burguer", IngredienteRepositorio.retornaListadeIngredientesPorCodigo(
                        Arrays.asList(3,5)
                ));
            case 3:
                return new Lanche(3, "X-Egg", IngredienteRepositorio.retornaListadeIngredientesPorCodigo(
                        Arrays.asList(3,4,5)
                ));
            case 4:
                return new Lanche(4, "X-Egg Bacon", IngredienteRepositorio.retornaListadeIngredientesPorCodigo(
                        Arrays.asList(2,3,4,5)
                ));
            case 5:
                return new Lanche(5, "Personalizado", IngredienteRepositorio.retornaListadeIngredientesPorCodigo(
                        Arrays.asList()
                ));
            default:
                return new Lanche(5, "Personalizado", IngredienteRepositorio.retornaListadeIngredientesPorCodigo(
                        Arrays.asList()));
        }
    }
}

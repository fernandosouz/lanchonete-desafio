package com.example.dextrastartup.demo.repositories;

import com.example.dextrastartup.demo.exceptions.IngredienteNotFoundException;
import com.example.dextrastartup.demo.model.IngredienteModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Criado por  Fernando Henrique de Souza em 24/02/19.
 */

/**
 * Classe que simula o repositório de informações da entidade "Ingrediente".
 */
public class IngredienteRepository {
    /**Método que retorna um ingrediente específico de acordo com o código passado.
     * @author Fernando Henrique de Souza
     * @param codigoDoIngrediente Código do ingrediente que será retornado.
     * @return Ingrediente retornado definido por código.
     */
    public static IngredienteModel retornaIngredientePorCodigo(Integer codigoDoIngrediente){
        switch (codigoDoIngrediente){
            case 1: return new IngredienteModel(1, "Alface", 0.4, 1);
            case 2: return new IngredienteModel(2, "Bacon", 2.00, 1);
            case 3: return new IngredienteModel(3, "Hambúrguer de carne", 3.00, 1);
            case 4: return new IngredienteModel(4, "Ovo", 0.80, 1);
            case 5: return new IngredienteModel(5, "Queijo", 1.5, 1);
            default: throw new IngredienteNotFoundException(codigoDoIngrediente, IngredienteRepository.class);
        }

    }

    /**Método que retorna uma lista de ingredientes baseado em uma lista de códigos
     * @author Fernando Henrique de Souza
     * @param listaDeCodigos Lista de códigos de ingredientes a serem retornados
     * @return Lista de ingredientes
     */
    static public List<IngredienteModel> retornaListadeIngredientesPorCodigo(List<Integer> listaDeCodigos){
        List<IngredienteModel> listaDeIngredientes = new ArrayList<>();

        for(Integer codigo: listaDeCodigos){
            listaDeIngredientes.add(IngredienteRepository.retornaIngredientePorCodigo(codigo));
        }

        return listaDeIngredientes;
    }
}

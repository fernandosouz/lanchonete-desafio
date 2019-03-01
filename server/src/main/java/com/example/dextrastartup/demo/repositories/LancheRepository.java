package com.example.dextrastartup.demo.repositories;

import com.example.dextrastartup.demo.exceptions.LancheNotFoundException;
import com.example.dextrastartup.demo.model.LancheModel;
import com.example.dextrastartup.demo.utils.Constantes;

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
                return new LancheModel(Constantes.CODIGO_XBACON, "X-Baconzaçoo", IngredienteRepository.retornaListadeIngredientesPorCodigo(
                        Arrays.asList(  Constantes.CODIGO_BACON,
                                        Constantes.CODIGO_HAMBURGUER_CARNE,
                                        Constantes.CODIGO_QUEIJO)
                ));
            case 2:
                return new LancheModel(Constantes.CODIGO_XBURGUER, "X-Burguer", IngredienteRepository.retornaListadeIngredientesPorCodigo(
                        Arrays.asList(  Constantes.CODIGO_HAMBURGUER_CARNE,
                                        Constantes.CODIGO_QUEIJO)
                ));
            case 3:
                return new LancheModel(Constantes.CODIGO_XEGG, "X-Egg", IngredienteRepository.retornaListadeIngredientesPorCodigo(
                        Arrays.asList(  Constantes.CODIGO_HAMBURGUER_CARNE,
                                        Constantes.CODIGO_OVO,
                                        Constantes.CODIGO_QUEIJO)
                ));
            case 4:
                return new LancheModel(Constantes.CODIGO_XEGG_BACON, "X-Egg Bacon", IngredienteRepository.retornaListadeIngredientesPorCodigo(
                        Arrays.asList(  Constantes.CODIGO_OVO,
                                        Constantes.CODIGO_BACON,
                                        Constantes.CODIGO_HAMBURGUER_CARNE,
                                        Constantes.CODIGO_QUEIJO)
                ));
            case 5:
                return new LancheModel(Constantes.CODIGO_PERSONALIZADO, "Personalizado", IngredienteRepository.retornaListadeIngredientesPorCodigo(
                        Arrays.asList()
                ));
            default:
                throw new LancheNotFoundException(codigoDoLanche, LancheRepository.class);
        }
    }
}

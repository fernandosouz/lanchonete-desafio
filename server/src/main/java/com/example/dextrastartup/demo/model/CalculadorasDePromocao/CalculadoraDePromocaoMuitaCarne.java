package com.example.dextrastartup.demo.model.CalculadorasDePromocao;

import com.example.dextrastartup.demo.model.LancheModel;
import com.example.dextrastartup.demo.utils.Constantes;

public class CalculadoraDePromocaoMuitaCarne implements CalculadoraDePromocao {

    public double calcula(LancheModel lanche){
        int quantidade = (int)lanche.retornaQuantidadeDePorcoesPorCodigo(Constantes.CODIGO_HAMBURGUER_CARNE) / 3;
        return (quantidade * Constantes.VALOR_HAMBURGUER_CARNE);
    }

}

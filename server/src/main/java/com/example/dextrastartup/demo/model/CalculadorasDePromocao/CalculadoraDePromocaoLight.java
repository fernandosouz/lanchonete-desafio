package com.example.dextrastartup.demo.model.CalculadorasDePromocao;

import com.example.dextrastartup.demo.model.LancheModel;

public class CalculadoraDePromocaoLight implements CalculadoraDePromocao {

    public double calcula(LancheModel lanche){
        return lanche.getValorTotal() * 0.1;
    }
}

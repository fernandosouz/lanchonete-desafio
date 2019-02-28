package com.example.dextrastartup.demo;

import com.example.dextrastartup.demo.model.IngredienteModel;
import com.example.dextrastartup.demo.model.LancheModel;
import com.example.dextrastartup.demo.controllers.LancheController;
import com.example.dextrastartup.demo.repositories.IngredienteRepository;
import com.example.dextrastartup.demo.repositories.LancheRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    LancheController lancheController = new LancheController();

	@Test
	public void testaValorDeUmLanchePadrao() throws Exception {
		double valorLanche = LancheRepository.retornaLanchePorCodigo(1).retornaValorTotal();

		double valorLanchePorIngrediente = 0;
		valorLanchePorIngrediente += IngredienteRepository.retornaIngredientePorCodigo(2).getValor();
		valorLanchePorIngrediente += IngredienteRepository.retornaIngredientePorCodigo(3).getValor();
		valorLanchePorIngrediente += IngredienteRepository.retornaIngredientePorCodigo(5).getValor();

		assertEquals(valorLanche, valorLanchePorIngrediente, 0.001);
	}

    @Test
    public void testaValorDeUmLanchePersonalizado() throws Exception {
        LancheModel lanche = LancheRepository.retornaLanchePorCodigo(1);
        lancheController.setLanche(lanche);
        lancheController.adicionarIngredientePorCodigoEQuantidade(5, 1);
        lancheController.adicionarIngredientePorCodigoEQuantidade(3, 1);
        lancheController.adicionarIngredientePorCodigoEQuantidade(1, 1);

        double valorLanche = lanche.getValorTotal();

        double valorLanchePorIngrediente = 0;
        valorLanchePorIngrediente += IngredienteRepository.retornaIngredientePorCodigo(2).getValor();
        valorLanchePorIngrediente += IngredienteRepository.retornaIngredientePorCodigo(3).getValor();
        valorLanchePorIngrediente += IngredienteRepository.retornaIngredientePorCodigo(5).getValor();
        valorLanchePorIngrediente += IngredienteRepository.retornaIngredientePorCodigo(5).getValor();
        valorLanchePorIngrediente += IngredienteRepository.retornaIngredientePorCodigo(3).getValor();
        valorLanchePorIngrediente += IngredienteRepository.retornaIngredientePorCodigo(1).getValor();

        assertEquals(valorLanche, valorLanchePorIngrediente, 0.001);
    }


    @Test
    public void testaSeUmLancheFoiPersonalizado() throws Exception {
        LancheModel lanche = LancheRepository.retornaLanchePorCodigo(1);
        lancheController.setLanche(lanche);
        lancheController.adicionarIngredientePorCodigoEQuantidade( 5, 1);
        lancheController.adicionarIngredientePorCodigoEQuantidade( 3, 1);
        lancheController.adicionarIngredientePorCodigoEQuantidade( 3, 1);

        assertEquals(lanche.foiPersonalizado(), true);
    }

    @Test
    public void testaSeUmLancheFoiNaoFoiPersonalizado() throws Exception {
        LancheModel lanche = LancheRepository.retornaLanchePorCodigo(5);
        assertEquals(lanche.foiPersonalizado(), false);
    }

    @Test
    public void testaQuantidadeDePorcoesASerDescontadaMuitoQueijoEmXEggQuandoHouverTresPorcoes() throws Exception {
        LancheModel lanche = LancheRepository.retornaLanchePorCodigo(3);
        lancheController.setLanche(lanche);
        lancheController.adicionarIngredientePorCodigoEQuantidade( 5, 2);
        assertEquals((int)lancheController.retornaQuantidadeDeAplicacoesPorCodigoDeProduto(5), 1);
    }

    @Test
    public void testaQuantidadeDePorcoesASerDescontadaMuitoQueijoEmXBaconQuandoHouverSetePorcoes() throws Exception {
        LancheModel lanche = LancheRepository.retornaLanchePorCodigo(1);
        lancheController.setLanche(lanche);
        lancheController.adicionarIngredientePorCodigoEQuantidade(5, 6);

        assertEquals((int)lancheController.retornaQuantidadeDeAplicacoesPorCodigoDeProduto(5), 2);
    }

    @Test
    public void testaQuantidadeDePorcoesASerDescontadaMuitoQueijoEmXEggQuandoHouverDuasPorcoes() throws Exception {
        LancheModel lanche = LancheRepository.retornaLanchePorCodigo(3);
        lancheController.setLanche(lanche);
        lancheController.adicionarIngredientePorCodigoEQuantidade(5, 1);

        assertEquals((int)lancheController.retornaQuantidadeDeAplicacoesPorCodigoDeProduto(5), 0);
    }

    @Test
    public void testaSePromocaoLightFoiAplicadaEmXBurguerQuandoApto() throws Exception {
	    LancheModel lanche = LancheRepository.retornaLanchePorCodigo(2);
        lancheController.setLanche(lanche);
        lancheController.adicionarIngredientePorCodigoEQuantidade( 1, 1);
	    lancheController.aplicaPromocoes();

	    assertEquals(lanche.getValorComDesconto(), lanche.getValorTotal() * 0.9, 0.001);
    }

    @Test
    public void testaSePromocaoLightNaoFoiAplicadaEmXBurguerQuandoNaoApto() throws Exception {
        LancheModel lanche = LancheRepository.retornaLanchePorCodigo(2);
        lanche.setListaDePromocoes(new ArrayList<>());
        lancheController.setLanche(lanche);
        lancheController.aplicaPromocoes();

        assertEquals(lanche.getValorComDesconto(), lanche.getValorTotal(), 0.001);
    }

    @Test
    public void testaSeQuantidadeTotalAdicionadaEstaCorreta() throws Exception {
        LancheModel lanche = LancheRepository.retornaLanchePorCodigo(2);
        lancheController.setLanche(lanche);
        lancheController.adicionarIngredientePorCodigoEQuantidade(5, 3);
        lancheController.adicionarIngredientePorCodigoEQuantidade(1, 2);
        int quantidadeTotalDeIngredientes = 0;
        for(IngredienteModel ingrediente: lanche.getListaDeIngredientes()){
            quantidadeTotalDeIngredientes += ingrediente.getQuantidade();
        }

        assertEquals(quantidadeTotalDeIngredientes, 7);
    }
}

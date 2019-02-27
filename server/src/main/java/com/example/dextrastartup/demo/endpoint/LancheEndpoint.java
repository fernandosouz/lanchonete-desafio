package com.example.dextrastartup.demo.endpoint;

import com.example.dextrastartup.demo.model.*;
import com.example.dextrastartup.demo.controllers.LancheController;
import com.example.dextrastartup.demo.repositories.LancheRepositorio;
import com.example.dextrastartup.demo.wrappers.LancheWrapperRequest;
import org.springframework.web.bind.annotation.*;

/**
 * Criado por  Fernando Henrique de Souza on 23/02/19.
 */

/**
 * Classe que define os endpoints da entidade "Lanche"
 */
@CrossOrigin
@RestController
@RequestMapping("lanche")


/** Método que escuta requisições no path lanche/detalhesDoPedido e chama o método que aplica as alterações
 *
 * @author Fernando Henrique de Souza
 * @param wrapper Objeto enviado pelo client
 * @return Response com as alterações
 */
//TODO - Usar SWAGGER
public class LancheEndpoint {
    @RequestMapping(value="/detalhesDoPedido", method=RequestMethod.POST,consumes="application/json",produces="application/json")
    @ResponseBody
    public Lanche detalhesDoPedido(@RequestBody LancheWrapperRequest wrapper) {

        Lanche lanche = LancheRepositorio.retornaLanchePorCodigo(wrapper.getCodigoDoLanche());

        LancheController lancheController = new LancheController(lanche);
        return lancheController.aplicarAlteracoes(wrapper.getIngredientes());
    }
}

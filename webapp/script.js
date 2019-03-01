
  var lancheEscolhido = 0;

  function chamaServicoCalculoDoPreco(){
      var listaDeIngredientes = [];
      listaDeIngredientes.push({codigo: 1, quantidade: 2, descricao:"", valor: 1.0});
      var ingrediente = {};
      ingrediente.codigo = 1;
      ingrediente.descricao = "asda";
      var ingredientes = [];

      ingredientes.push({"codigo":1, "quantidade": parseInt(document.getElementById("quantidadeAlface").innerText)});
      ingredientes.push({"codigo":2, "quantidade": parseInt(document.getElementById("quantidadeBacon").innerText)});
      ingredientes.push({"codigo":3, "quantidade": parseInt(document.getElementById("quantidadeHamburguer").innerText)});
      ingredientes.push({"codigo":4, "quantidade": parseInt(document.getElementById("quantidadeOvo").innerText)});
      ingredientes.push({"codigo":5, "quantidade": parseInt(document.getElementById("quantidadeQueijo").innerText)});

      var a = { "ingredientes":   ingredientes,
                "codigoDoLanche": lancheEscolhido}

      $.ajax({
        url: "http://localhost:8089/lanche/detalhesDoPedido",
        type: "POST",
        contentType : "application/json",
        data: JSON.stringify(a),
        dataType: "json",
        success: function (result) {
            mostraResultados(result);
        },
        error: function (xhr, ajaxOptions, thrownError) {
          console.log(xhr);
          console.log(ajaxOptions);
          console.log(thrownError);
        }
    });
  }

  function reiniciaPagina(){
      document.getElementById("quantidadeAlface").innerText = 0;
      document.getElementById("quantidadeBacon").innerText = 0;
      document.getElementById("quantidadeHamburguer").innerText = 0;
      document.getElementById("quantidadeOvo").innerText = 0;
      document.getElementById("quantidadeQueijo").innerText = 0;

      document.getElementById('toggleOption').innerText = "Clique aqui para escolher seu lanche";

      document.getElementById("promocoes").style.visibility = 'hidden';
      document.getElementById("resultados").style.visibility = 'hidden';
  }

  function mostraResultados(resultados){
      document.getElementById("promocoes").style.visibility = 'hidden';
      document.getElementById("resultados").style.visibility = 'hidden';
      document.getElementById("lista-ingredientes").style.display = 'none';

      var aplicouPromocao = resultados.listaDePromocoes.length > 0;

      var valorTotal = resultados.valorTotal;
      document.getElementById('resultados').style.visibility = 'visible';
      document.getElementById('mostraValortotal').innerText = "O valor total do seu pedido é: R$" + resultados.valorTotal;

      if(aplicouPromocao){
          document.getElementById("promocoes").style.visibility = 'visible';
          document.getElementById("valor-desconto").innerText = "Valor com desconto: R$" + resultados.valorComDesconto;
          document.getElementById('texto-promocoes').innerText = "";
          for(var n in resultados.listaDePromocoes){
            var promocao = resultados.listaDePromocoes[n];
            document.getElementById('texto-promocoes').innerText += promocao.nome +": "+ promocao.descricao + "\n";
          }

      }
  }

  function percorrerIngredientesDoLanche(lanche){
    for(var ingrediente in lanche) {
      colocaQuantidadePadraoEmIngrediente(ingrediente, lanche[ingrediente])
    }
  }

  function colocaQuantidadePadraoEmIngrediente(id, quantidade){
    document.getElementById(id).innerText = quantidade
  }

  function adicionarIngrediente(id) {
    if(parseInt(document.getElementById(id).innerText) >= 0){
        document.getElementById(id).innerText = parseInt(document.getElementById(id).innerText) + 1;
    }
  }

  function removerIngrediente(id) {
    if(parseInt(document.getElementById(id).innerText) > 0){
        document.getElementById(id).innerText = parseInt(document.getElementById(id).innerText) - 1;
    }
  }

  function mostrarIngredientesDoLanche(codigo, nome){
    document.getElementById('toggleOption').innerText = nome;
    document.getElementById('lista-ingredientes').style.display = "grid";

    lancheEscolhido = codigo;
    document.getElementById("lista-ingredientes").style.visibility = "visible";
  }

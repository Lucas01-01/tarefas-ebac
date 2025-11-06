package com.ebac.vendas;

import com.ebac.vendas.dao.impl.ClienteDAO;
import com.ebac.vendas.dao.impl.NotaFiscalDAO;
import com.ebac.vendas.dao.impl.ProdutoDAO;
import com.ebac.vendas.domain.Cliente;
import com.ebac.vendas.domain.ItemNota;
import com.ebac.vendas.domain.NotaFiscal;
import com.ebac.vendas.domain.Produto;
import com.ebac.vendas.service.VendaService;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Criando DAOs
        ClienteDAO clienteDAO = new ClienteDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        NotaFiscalDAO notaFiscalDAO = new NotaFiscalDAO();
        VendaService vendaService = new VendaService(notaFiscalDAO);

        // Criando um cliente
        Cliente cliente = new Cliente("João da Silva", "123.456.789-00", "joao@email.com");
        clienteDAO.salvar(cliente);

        // Criando produtos
        Produto p1 = new Produto("Notebook", 3500.0, "NOT001");
        Produto p2 = new Produto("Mouse", 50.0, "MOU001");
        produtoDAO.salvar(p1);
        produtoDAO.salvar(p2);

        // Criando itens da nota
        ItemNota item1 = new ItemNota(p1, 1);
        ItemNota item2 = new ItemNota(p2, 2);

        // Criando uma venda
        NotaFiscal nota = vendaService.criarVenda(cliente, Arrays.asList(item1, item2));

        // Imprimindo a nota fiscal
        System.out.println("\n*** NOTA FISCAL ***");
        System.out.println("ID: " + nota.getId());
        System.out.println("Data: " + nota.getData());
        System.out.println("Cliente: " + nota.getCliente().getNome());
        System.out.println("\nItens:");
        for (ItemNota item : nota.getItens()) {
            System.out.printf("%s - Qtd: %d - Subtotal: R$ %.2f%n",
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    item.getSubtotal());
        }
        System.out.printf("\nValor Total: R$ %.2f%n", nota.getValorTotal());
    }
}
package com.ebac.vendas.service;

import com.ebac.vendas.dao.INotaFiscalDAO;
import com.ebac.vendas.dao.impl.NotaFiscalDAO;
import com.ebac.vendas.domain.Cliente;
import com.ebac.vendas.domain.ItemNota;
import com.ebac.vendas.domain.NotaFiscal;
import com.ebac.vendas.domain.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendaServiceTest {
    
    private VendaService vendaService;
    private INotaFiscalDAO notaFiscalDAO;
    private Cliente cliente;
    private Produto produto1;
    private Produto produto2;

    @BeforeEach
    void setUp() {
        notaFiscalDAO = new NotaFiscalDAO();
        vendaService = new VendaService(notaFiscalDAO);
        
        cliente = new Cliente("João", "123.456.789-00", "joao@email.com");
        produto1 = new Produto("Mouse", 50.0, "MOU001");
        produto2 = new Produto("Teclado", 100.0, "TEC001");
    }

    @Test
    void deveCriarVendaComSucesso() {
        // Given
        List<ItemNota> itens = Arrays.asList(
            new ItemNota(produto1, 2),
            new ItemNota(produto2, 1)
        );

        // When
        NotaFiscal notaFiscal = vendaService.criarVenda(cliente, itens);

        // Then
        assertNotNull(notaFiscal);
        assertEquals(cliente, notaFiscal.getCliente());
        assertEquals(2, notaFiscal.getItens().size());
        assertEquals(200.0, notaFiscal.getValorTotal()); // (50 * 2) + (100 * 1)
    }

    @Test
    void deveBuscarVendasPorData() {
        // Given
        List<ItemNota> itens = Arrays.asList(new ItemNota(produto1, 1));
        NotaFiscal nota = vendaService.criarVenda(cliente, itens);

        // When
        List<NotaFiscal> vendas = vendaService.buscarVendasPorData(LocalDate.now());

        // Then
        assertEquals(1, vendas.size());
        assertEquals(nota.getId(), vendas.get(0).getId());
    }

    @Test
    void deveBuscarVendasPorCliente() {
        // Given
        List<ItemNota> itens = Arrays.asList(new ItemNota(produto1, 1));
        NotaFiscal nota = vendaService.criarVenda(cliente, itens);

        // When
        List<NotaFiscal> vendas = vendaService.buscarVendasPorCliente("123.456.789-00");

        // Then
        assertEquals(1, vendas.size());
        assertEquals(nota.getId(), vendas.get(0).getId());
    }

    @Test
    void deveGerarIdUnicoParaCadaVenda() {
        // Given
        List<ItemNota> itens = Arrays.asList(new ItemNota(produto1, 1));

        // When
        NotaFiscal nota1 = vendaService.criarVenda(cliente, itens);
        NotaFiscal nota2 = vendaService.criarVenda(cliente, itens);

        // Then
        assertNotEquals(nota1.getId(), nota2.getId());
    }
}
package com.ebac.vendas.domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class NotaFiscalTest {
    
    @Test
    void deveCriarNotaFiscalComSucesso() {
        // Given
        Long id = 1L;
        Cliente cliente = new Cliente("João", "123.456.789-00", "joao@email.com");

        // When
        NotaFiscal notaFiscal = new NotaFiscal(id, cliente);

        // Then
        assertNotNull(notaFiscal);
        assertEquals(id, notaFiscal.getId());
        assertEquals(cliente, notaFiscal.getCliente());
        assertEquals(LocalDate.now(), notaFiscal.getData());
        assertTrue(notaFiscal.getItens().isEmpty());
        assertEquals(0.0, notaFiscal.getValorTotal());
    }

    @Test
    void deveAdicionarItensECalcularValorTotal() {
        // Given
        NotaFiscal notaFiscal = new NotaFiscal(1L, 
            new Cliente("João", "123.456.789-00", "joao@email.com"));
        
        Produto p1 = new Produto("Mouse", 50.0, "MOU001");
        Produto p2 = new Produto("Teclado", 100.0, "TEC001");
        
        ItemNota item1 = new ItemNota(p1, 2); // 100.0
        ItemNota item2 = new ItemNota(p2, 1); // 100.0

        // When
        notaFiscal.adicionarItem(item1);
        notaFiscal.adicionarItem(item2);

        // Then
        assertEquals(2, notaFiscal.getItens().size());
        assertEquals(200.0, notaFiscal.getValorTotal());
    }

    @Test
    void deveManterListaDeItensImutavel() {
        // Given
        NotaFiscal notaFiscal = new NotaFiscal(1L, 
            new Cliente("João", "123.456.789-00", "joao@email.com"));
        
        Produto produto = new Produto("Mouse", 50.0, "MOU001");
        ItemNota item = new ItemNota(produto, 2);
        
        // When
        notaFiscal.adicionarItem(item);
        List<ItemNota> itens = notaFiscal.getItens();

        // Then
        assertThrows(UnsupportedOperationException.class, () -> {
            itens.add(new ItemNota(produto, 1));
        });
    }

    @Test
    void deveRetornarToStringFormatadoCorretamente() {
        // Given
        Cliente cliente = new Cliente("João", "123.456.789-00", "joao@email.com");
        NotaFiscal notaFiscal = new NotaFiscal(1L, cliente);
        
        Produto produto = new Produto("Mouse", 50.0, "MOU001");
        ItemNota item = new ItemNota(produto, 2);
        
        notaFiscal.adicionarItem(item);

        // When
        String toString = notaFiscal.toString();

        // Then
        assertTrue(toString.contains("1")); // id
        assertTrue(toString.contains(LocalDate.now().toString()));
        assertTrue(toString.contains("João")); // cliente
        assertTrue(toString.contains("Mouse")); // produto
        assertTrue(toString.contains("100.0")); // valor total
    }
}
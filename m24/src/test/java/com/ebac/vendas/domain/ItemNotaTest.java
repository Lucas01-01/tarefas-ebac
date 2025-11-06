package com.ebac.vendas.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemNotaTest {
    
    @Test
    void deveCriarItemNotaComSucesso() {
        // Given
        Produto produto = new Produto("Mouse", 50.0, "MOU001");
        Integer quantidade = 2;

        // When
        ItemNota itemNota = new ItemNota(produto, quantidade);

        // Then
        assertNotNull(itemNota);
        assertEquals(produto, itemNota.getProduto());
        assertEquals(quantidade, itemNota.getQuantidade());
        assertEquals(100.0, itemNota.getSubtotal()); // 50.0 * 2
    }

    @Test
    void deveCalcularSubtotalAoAlterarQuantidade() {
        // Given
        Produto produto = new Produto("Mouse", 50.0, "MOU001");
        ItemNota itemNota = new ItemNota(produto, 2);

        // When
        itemNota.setQuantidade(3);

        // Then
        assertEquals(150.0, itemNota.getSubtotal()); // 50.0 * 3
    }

    @Test
    void deveCalcularSubtotalAoAlterarProduto() {
        // Given
        Produto produtoOriginal = new Produto("Mouse", 50.0, "MOU001");
        Produto novoProduto = new Produto("Teclado", 100.0, "TEC001");
        ItemNota itemNota = new ItemNota(produtoOriginal, 2);

        // When
        itemNota.setProduto(novoProduto);

        // Then
        assertEquals(200.0, itemNota.getSubtotal()); // 100.0 * 2
    }

    @Test
    void deveRetornarToStringFormatadoCorretamente() {
        // Given
        Produto produto = new Produto("Mouse", 50.0, "MOU001");
        ItemNota itemNota = new ItemNota(produto, 2);

        // When
        String toString = itemNota.toString();

        // Then
        assertTrue(toString.contains("Mouse"));
        assertTrue(toString.contains("2"));
        assertTrue(toString.contains("100.0")); // subtotal
    }
}
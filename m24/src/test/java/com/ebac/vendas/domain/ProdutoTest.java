package com.ebac.vendas.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {
    
    @Test
    void deveCriarProdutoComSucesso() {
        // Given
        String nome = "Notebook";
        Double preco = 3500.0;
        String codigo = "NOT001";

        // When
        Produto produto = new Produto(nome, preco, codigo);

        // Then
        assertNotNull(produto);
        assertEquals(nome, produto.getNome());
        assertEquals(preco, produto.getPreco());
        assertEquals(codigo, produto.getCodigo());
    }

    @Test
    void deveAlterarDadosDoProdutoComSucesso() {
        // Given
        Produto produto = new Produto("Notebook", 3500.0, "NOT001");

        // When
        produto.setNome("Notebook Pro");
        produto.setPreco(4500.0);

        // Then
        assertEquals("Notebook Pro", produto.getNome());
        assertEquals(4500.0, produto.getPreco());
        assertEquals("NOT001", produto.getCodigo()); // Código não deve mudar
    }

    @Test
    void deveRetornarToStringFormatadoCorretamente() {
        // Given
        Produto produto = new Produto("Notebook", 3500.0, "NOT001");

        // When
        String toString = produto.toString();

        // Then
        assertTrue(toString.contains("Notebook"));
        assertTrue(toString.contains("3500.0"));
        assertTrue(toString.contains("NOT001"));
    }
}
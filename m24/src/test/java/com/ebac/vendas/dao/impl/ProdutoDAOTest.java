package com.ebac.vendas.dao.impl;

import com.ebac.vendas.domain.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoDAOTest {
    
    private ProdutoDAO produtoDAO;
    private Produto produto;

    @BeforeEach
    void setUp() {
        produtoDAO = new ProdutoDAO();
        produto = new Produto("Mouse", 50.0, "MOU001");
    }

    @Test
    void deveSalvarProdutoComSucesso() {
        // When
        produtoDAO.salvar(produto);

        // Then
        Produto produtoSalvo = produtoDAO.buscarPorCodigo("MOU001");
        assertNotNull(produtoSalvo);
        assertEquals(produto.getNome(), produtoSalvo.getNome());
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        // Given
        produtoDAO.salvar(produto);

        // When
        produto.setNome("Mouse Gamer");
        produto.setPreco(100.0);
        produtoDAO.atualizar(produto);

        // Then
        Produto produtoAtualizado = produtoDAO.buscarPorCodigo("MOU001");
        assertEquals("Mouse Gamer", produtoAtualizado.getNome());
        assertEquals(100.0, produtoAtualizado.getPreco());
    }

    @Test
    void deveExcluirProdutoComSucesso() {
        // Given
        produtoDAO.salvar(produto);

        // When
        produtoDAO.excluir(produto);

        // Then
        Produto produtoExcluido = produtoDAO.buscarPorCodigo("MOU001");
        assertNull(produtoExcluido);
    }

    @Test
    void deveBuscarTodosProdutos() {
        // Given
        produtoDAO.salvar(produto);
        produtoDAO.salvar(new Produto("Teclado", 100.0, "TEC001"));

        // When
        var produtos = produtoDAO.buscarTodos();

        // Then
        assertEquals(2, produtos.size());
    }

    @Test
    void deveRetornarNullQuandoBuscarProdutoInexistente() {
        // When
        Produto produtoInexistente = produtoDAO.buscarPorCodigo("XXX000");

        // Then
        assertNull(produtoInexistente);
    }
}
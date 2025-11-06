package com.ebac.vendas.dao.impl;

import com.ebac.vendas.domain.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteDAOTest {
    
    private ClienteDAO clienteDAO;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        clienteDAO = new ClienteDAO();
        cliente = new Cliente("João", "123.456.789-00", "joao@email.com");
    }

    @Test
    void deveSalvarClienteComSucesso() {
        // When
        clienteDAO.salvar(cliente);

        // Then
        Cliente clienteSalvo = clienteDAO.buscarPorCPF("123.456.789-00");
        assertNotNull(clienteSalvo);
        assertEquals(cliente.getNome(), clienteSalvo.getNome());
    }

    @Test
    void deveAtualizarClienteComSucesso() {
        // Given
        clienteDAO.salvar(cliente);

        // When
        cliente.setNome("João da Silva");
        cliente.setEmail("joao.silva@email.com");
        clienteDAO.atualizar(cliente);

        // Then
        Cliente clienteAtualizado = clienteDAO.buscarPorCPF("123.456.789-00");
        assertEquals("João da Silva", clienteAtualizado.getNome());
        assertEquals("joao.silva@email.com", clienteAtualizado.getEmail());
    }

    @Test
    void deveExcluirClienteComSucesso() {
        // Given
        clienteDAO.salvar(cliente);

        // When
        clienteDAO.excluir(cliente);

        // Then
        Cliente clienteExcluido = clienteDAO.buscarPorCPF("123.456.789-00");
        assertNull(clienteExcluido);
    }

    @Test
    void deveBuscarTodosClientes() {
        // Given
        clienteDAO.salvar(cliente);
        clienteDAO.salvar(new Cliente("Maria", "987.654.321-00", "maria@email.com"));

        // When
        var clientes = clienteDAO.buscarTodos();

        // Then
        assertEquals(2, clientes.size());
    }

    @Test
    void deveRetornarNullQuandoBuscarClienteInexistente() {
        // When
        Cliente clienteInexistente = clienteDAO.buscarPorCPF("000.000.000-00");

        // Then
        assertNull(clienteInexistente);
    }
}
package br.com.rpires.testes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.rpires.dao.ClienteDAOImpl;
import br.com.rpires.dao.IClienteDAO;
import br.com.rpires.service.ClienteService;
import br.com.rpires.service.IClienteService;

public class ClienteServiceTest {
    
    private IClienteService clienteService;
    private IClienteDAO clienteDAO;
    
    @BeforeEach
    public void setup() {
        clienteDAO = new ClienteDAOImpl();
        clienteService = new ClienteService(clienteDAO);
    }
    
    @Test
    public void testSalvarCliente() {
        // Cenário
        String cliente = "João";
        
        // Execução
        Boolean resultado = clienteService.salvar(cliente);
        
        // Verificação
        assertTrue(resultado);
        assertEquals(cliente, clienteService.buscar(cliente));
    }
    
    @Test
    public void testSalvarClienteJaExistente() {
        // Cenário
        String cliente = "João";
        clienteService.salvar(cliente);
        
        // Execução
        Boolean resultado = clienteService.salvar(cliente);
        
        // Verificação
        assertFalse(resultado);
    }
    
    @Test
    public void testSalvarClienteInvalido() {
        // Cenário
        String cliente = null;
        
        // Execução
        Boolean resultado = clienteService.salvar(cliente);
        
        // Verificação
        assertFalse(resultado);
    }
    
    @Test
    public void testBuscarCliente() {
        // Cenário
        String cliente = "Maria";
        clienteService.salvar(cliente);
        
        // Execução
        String resultado = clienteService.buscar(cliente);
        
        // Verificação
        assertEquals(cliente, resultado);
    }
    
    @Test
    public void testBuscarClienteInexistente() {
        // Cenário
        String cliente = "José";
        
        // Execução
        String resultado = clienteService.buscar(cliente);
        
        // Verificação
        assertNull(resultado);
    }
    
    @Test
    public void testAtualizarCliente() {
        // Cenário
        String cliente = "Ana";
        String novosDados = "Ana Silva";
        clienteService.salvar(cliente);
        
        // Execução
        Boolean resultado = clienteService.atualizar(cliente, novosDados);
        
        // Verificação
        assertTrue(resultado);
        assertEquals(novosDados, clienteService.buscar(cliente));
    }
    
    @Test
    public void testAtualizarClienteInexistente() {
        // Cenário
        String cliente = "Pedro";
        String novosDados = "Pedro Silva";
        
        // Execução
        Boolean resultado = clienteService.atualizar(cliente, novosDados);
        
        // Verificação
        assertFalse(resultado);
    }
    
    @Test
    public void testExcluirCliente() {
        // Cenário
        String cliente = "Carlos";
        clienteService.salvar(cliente);
        
        // Execução
        Boolean resultado = clienteService.excluir(cliente);
        
        // Verificação
        assertTrue(resultado);
        assertNull(clienteService.buscar(cliente));
    }
    
    @Test
    public void testExcluirClienteInexistente() {
        // Cenário
        String cliente = "Lucas";
        
        // Execução
        Boolean resultado = clienteService.excluir(cliente);
        
        // Verificação
        assertFalse(resultado);
    }
}
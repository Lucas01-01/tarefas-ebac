package br.com.rpires.service;

import br.com.rpires.dao.IClienteDAO;

/**
 * Implementação do Service de Cliente
 */
public class ClienteService implements IClienteService {
    
    private IClienteDAO clienteDAO;
    
    public ClienteService(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }
    
    @Override
    public Boolean salvar(String cliente) {
        return clienteDAO.cadastrar(cliente);
    }
    
    @Override
    public String buscar(String cliente) {
        return clienteDAO.buscar(cliente);
    }
    
    @Override
    public Boolean atualizar(String cliente, String novosDados) {
        return clienteDAO.atualizar(cliente, novosDados);
    }
    
    @Override
    public Boolean excluir(String cliente) {
        return clienteDAO.excluir(cliente);
    }
}
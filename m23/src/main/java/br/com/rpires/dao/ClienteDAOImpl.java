package br.com.rpires.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementação do DAO de Cliente usando um Map como "banco de dados" em memória
 */
public class ClienteDAOImpl implements IClienteDAO {
    
    private Map<String, String> clientesDB;
    
    public ClienteDAOImpl() {
        this.clientesDB = new HashMap<>();
    }
    
    @Override
    public Boolean cadastrar(String cliente) {
        if (cliente == null || cliente.trim().isEmpty()) {
            return false;
        }
        if (clientesDB.containsKey(cliente.trim())) {
            return false;
        }
        clientesDB.put(cliente.trim(), cliente.trim());
        return true;
    }
    
    @Override
    public String buscar(String cliente) {
        if (cliente == null || cliente.trim().isEmpty()) {
            return null;
        }
        return clientesDB.get(cliente.trim());
    }
    
    @Override
    public Boolean atualizar(String cliente, String novosDados) {
        if (cliente == null || novosDados == null || 
            cliente.trim().isEmpty() || novosDados.trim().isEmpty()) {
            return false;
        }
        if (!clientesDB.containsKey(cliente.trim())) {
            return false;
        }
        clientesDB.put(cliente.trim(), novosDados.trim());
        return true;
    }
    
    @Override
    public Boolean excluir(String cliente) {
        if (cliente == null || cliente.trim().isEmpty()) {
            return false;
        }
        if (!clientesDB.containsKey(cliente.trim())) {
            return false;
        }
        clientesDB.remove(cliente.trim());
        return true;
    }
}
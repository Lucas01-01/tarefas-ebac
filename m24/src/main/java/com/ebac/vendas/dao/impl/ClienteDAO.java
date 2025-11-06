package com.ebac.vendas.dao.impl;

import com.ebac.vendas.dao.IClienteDAO;
import com.ebac.vendas.domain.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAO implements IClienteDAO {
    private List<Cliente> clientes;

    public ClienteDAO() {
        this.clientes = new ArrayList<>();
    }

    @Override
    public void salvar(Cliente cliente) {
        this.clientes.add(cliente);
    }

    @Override
    public void atualizar(Cliente cliente) {
        Cliente clienteEncontrado = buscarPorCPF(cliente.getCpf());
        if (clienteEncontrado != null) {
            clienteEncontrado.setNome(cliente.getNome());
            clienteEncontrado.setEmail(cliente.getEmail());
        }
    }

    @Override
    public void excluir(Cliente cliente) {
        this.clientes.removeIf(c -> c.getCpf().equals(cliente.getCpf()));
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        // Como não temos um ID para Cliente, vamos retornar vazio
        return Optional.empty();
    }

    @Override
    public List<Cliente> buscarTodos() {
        return new ArrayList<>(this.clientes);
    }

    @Override
    public Cliente buscarPorCPF(String cpf) {
        return this.clientes.stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }
}
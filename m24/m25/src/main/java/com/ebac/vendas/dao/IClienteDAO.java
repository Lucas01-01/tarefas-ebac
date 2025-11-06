package com.ebac.vendas.dao;

import com.ebac.vendas.domain.Cliente;

public interface IClienteDAO extends IGenericDAO<Cliente> {
    Cliente buscarPorCPF(String cpf);
}
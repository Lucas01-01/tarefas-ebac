package com.ebac.vendas.dao;

import com.ebac.vendas.domain.Produto;

public interface IProdutoDAO extends IGenericDAO<Produto> {
    Produto buscarPorCodigo(String codigo);
}
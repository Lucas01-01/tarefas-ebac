package com.ebac.vendas.dao;

import java.util.List;
import java.util.Optional;

public interface IGenericDAO<T> {
    void salvar(T entidade);
    void atualizar(T entidade);
    void excluir(T entidade);
    Optional<T> buscarPorId(Long id);
    List<T> buscarTodos();
}
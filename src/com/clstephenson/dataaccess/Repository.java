package com.clstephenson.dataaccess;

import com.clstephenson.DataRepositoryException;
import com.clstephenson.LoginSession;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Repository<T> {
    int add(T entity, LoginSession session) throws DataRepositoryException;

    boolean update(T entity, LoginSession session) throws DataRepositoryException;

    boolean removeById(int id) throws DataRepositoryException;

    boolean remove(T entity) throws DataRepositoryException;

    T findById(int id) throws DataRepositoryException;

    default T findSingle(Predicate<T> condition) throws DataRepositoryException {
        List<T> list = this.find(condition);
        return list.isEmpty() ? null : list.get(0);
    }

    default List<T> find(Predicate<T> condition) throws DataRepositoryException {
        List<T> list = this.findAll()
                .stream()
                .filter(condition)
                .collect(Collectors.toList());
        return list.isEmpty() ? new ArrayList<>() : list;
    }

    List<T> findAll() throws DataRepositoryException;

}

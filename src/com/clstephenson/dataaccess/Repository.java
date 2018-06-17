package com.clstephenson.dataaccess;

import com.clstephenson.LoginSession;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Repository<T> {
    int add(T entity, LoginSession session);

    boolean update(T entity, LoginSession session);

    boolean removeById(int id);

    boolean remove(T entity);

    T findById(int id);

    default T findSingle(Predicate<T> condition) {
        List<T> list = this.find(condition);
        return list.isEmpty() ? null : list.get(0);
    }

    default List<T> find(Predicate<T> condition) {
        List<T> list = this.findAll()
                .stream()
                .filter(condition)
                .collect(Collectors.toList());
        return list.isEmpty() ? new ArrayList<>() : list;
    }

    List<T> findAll();

}

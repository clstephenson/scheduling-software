package com.clstephenson.dataaccess;

import com.clstephenson.LoginSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Repository<T> {
    int add(T entity, LoginSession session) throws SQLException, IOException;
    boolean remove(T entity) throws SQLException;
    List<T> findAll() throws SQLException;

    default List<T> find(Predicate<T> condition) throws SQLException {
        List<T> list = this.findAll()
                .stream()
                .filter(condition)
                .collect(Collectors.toList());
        return list.isEmpty() ? new ArrayList<>() : list;
    }

    default T findSingle(Predicate<T> condition) throws SQLException {
        List<T> list = this.find(condition);
        return list.isEmpty() ? null : list.get(0);
    }
}

package com.monopoly.database.dao;

import java.util.List;

public interface GenericDao<T> {

    public T insert(T object);

    public T getByKey(int key);

    public void update(T object);

    public void delete(T object);

    public List<T> getAll();
}
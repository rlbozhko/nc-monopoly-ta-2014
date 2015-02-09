package com.monopoly.database.dao;

import java.util.List;

public interface Dao<T> {    

    public T getByKey(int key);

    public T insert(T object);
    
    public void update(T object);
    
    public void merge(T object);

    public void delete(T object);

    public List<T> getAllByParentKey(int key);
}
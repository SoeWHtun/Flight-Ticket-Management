package com.example.dao;

import java.util.List;

public interface AbstractDao<T> {
    public void create(T object);
    public T findById(int id);
    public void update(int id, T object);
    public void delete(int id);
    public List<T> getAll();

}

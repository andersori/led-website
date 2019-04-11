package com.github.andersori.led.dao;

import java.util.List;

public interface DAO<T>{

    public T get(Class<T> type, Long id);
    
    public void save(T obj);

    public void update(T obj);

    public void remove(T obj);

    public List<T> list(T obj);
    
}

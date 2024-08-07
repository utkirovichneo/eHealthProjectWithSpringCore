package uz.pdp.dao;

import java.util.List;

public interface BaseDao<T>{
    void create(T t);
    void update(T t);
    void delete(int id);
    T get(int id);
    List<T> getAll();
}
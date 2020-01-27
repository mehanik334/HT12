package ua.epam.repositories;

import java.util.ArrayList;

public interface CrudRepository<T,ID> {

    T getById(ID id);
    ArrayList<T> getAll();
    T save(T obj);
    T deleteById(ID id);
    boolean update(T obj);

}

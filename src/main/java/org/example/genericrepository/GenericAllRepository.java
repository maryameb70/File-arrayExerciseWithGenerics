package org.example.genericrepository;

import org.example.base.Base;

import java.io.IOException;

public interface GenericAllRepository<T> {

    T get(int index);
    void add(T element);
    int find(T element);
    void remove(T element);
    void remove(int index);
    void shift(int index);
    Boolean contain(T element);
    void print();
    void deleteContent();

}

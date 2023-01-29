package org.example.genericrepository;

public interface GenericAllRepository<T> {

    T get(int index) throws InvalidIndexException;
    void add(T element);
    int find(T element);
    void remove(T element) throws InvalidIndexException;
    void remove(int index) throws InvalidIndexException;
    void shift(int index);
    Boolean contain(T element);
    void print();
    void deleteContent();

}

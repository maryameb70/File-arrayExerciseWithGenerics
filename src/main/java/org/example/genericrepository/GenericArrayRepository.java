package org.example.genericrepository;

import org.example.base.Base;

public class GenericArrayRepository<T extends Base> implements GenericAllRepository<T>, ArrayRepository<T> {

    private Base[] elements;
    private int emptyIndex = 0;

    public GenericArrayRepository() {
        elements = new Base[3];
    }

    public GenericArrayRepository(int size) {
        elements = new Base[size];
    }

    public GenericArrayRepository(GenericArrayRepository<T> repo) {
        this.elements = repo.elements;
    }

    @Override
    public T get(int index) throws InvalidIndexException {
        if (isIndexInvalid(index)) {
            throw new InvalidIndexException(String.format("Invalid index%d", index));
        }
        return (T) elements[index];
    }

    public Integer getById(T id) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null && elements[i] == id)
                return elements[i].getId();
        }
        return null;
    }

    @Override
    public void add(T element) {
        if (element != null) {
            if (emptyIndex >= elements.length) {
                extendArray();
            }
            elements[emptyIndex++] = element;
        }
    }

    @Override
    public void add(T[] arr) {
        if (arr != null) {
            for (T value : arr) {
                add(value);
            }
        }
    }

    private void extendArray() {
        Base[] extend = new Base[elements.length + 3];
        for (int i = 0; i < elements.length; i++) {
            extend[i] = elements[i];
        }
        this.elements = extend;
    }

    @Override
    public void remove(int index) throws InvalidIndexException {
        if (isIndexInvalid(index)) {
            throw new InvalidIndexException(String.format("Invalid index%d", index));
        }
        elements[index] = null;
        shift(index);
    }

    @Override
    public void remove(T element) throws InvalidIndexException {
        int index = find(element);
        if (index == -1) {
            throw new InvalidIndexException("The element to be searched for does not exist in the array");
        }
        remove(index);
    }

    public void removeAllElement(T element) throws InvalidIndexException {
        if (element != null) {
            for (int i = 0; i < elements.length; i++) {
                remove(element);
            }
        }
    }

    @Override
    public void shift(int index) {
        for (int i = index; i < elements.length - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--emptyIndex] = null;
    }

    @Override
    public void deleteContent() {
        emptyIndex = 0;
        elements = new Base[emptyIndex];
    }

    @Override
    public int find(T element) {
        if (element == null) {
            return -1;
        }
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null && elements[i].getId().equals(element.getId())) {
                return i;
            }
        }
        return -1;
    }

    public int findFirstById(T id) {
        if (id == null) {
            return -1;
        }
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null && elements[i].equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void update(int index, T element) throws InvalidIndexException {
        if (isIndexInvalid(index)) {
            throw new InvalidIndexException(String.format("Invalid index%d", index));
        }
        elements[index] = element;
    }

    @Override
    public Boolean contain(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null && elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public T[] subElements(int from, int to) {
        if (from < 0 || from > to || to >= elements.length) {
            return null;
        }
        Base[] temp = new Base[to - from + 1];
        int index = 0;
        for (int i = from; i <= to; i++) {
            temp[index++] = elements[i];
        }
        return (T[]) temp;
    }

    public GenericArrayRepository<T> subElementsGeneric(int from, int to) {
        if (from < 0 || from > to || to >= elements.length) {
            return null;
        }
        GenericArrayRepository<T> gr = new GenericArrayRepository<>();
        Base[] temp = new Base[to - from + 1];
        int index = 0;
        for (int i = from; i <= to; i++) {
            temp[index++] = elements[i];
        }
        gr.elements = temp;
        return gr;
    }

    private boolean isIndexInvalid(int index) {
        if (index < 0 || index >= emptyIndex) {
            return true;
        }
        return false;
    }

    @Override
    public void print() {
        for (Base element : elements) {
            if (element != null) {
                System.out.println(element.getId());
            }
        }
    }
}

package org.example.genericrepository;

import org.example.base.Base;

import java.util.Arrays;

public class GenericArrayRepository<T extends Base> implements GenericAllRepository<T> {

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
    public T get(int index) {
        if (isIndexInvalid(index)) {
            return null;
        }
        return (T) elements[index];
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
    public void remove(int index) {
        if (isIndexInvalid(index)) return;
        elements[index] = null;
        shift(index);
    }

    @Override
    public void remove(T element) {
        int index = find(element);
        if (index == -1) {
            return;
        }
        remove(index);
    }

    public void removeAllElement(T element) {

        if (element != null) {
            for (int i = 0; i < elements.length; i++) {
                System.out.println(Arrays.toString(elements));
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
    public void clear() {
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

    public void update(int index, T element) {
        if (isIndexInvalid(index)) {
            return;
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

    public Integer getById(T id) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null && elements[i].getId().equals(id))
                return (Integer) elements[i].getId();
        }
        return null;
    }

    public T[] subElements(int from, int to) {
        if (from < 0 || from > to || to >= elements.length) {
            return null;
        }
        Object[] temp = new Object[to - from + 1];
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
        for (Base str : elements) {
            try {
                System.out.println(str.getId());
            } catch (Exception e) {
                return;
            }

        }
    }
}

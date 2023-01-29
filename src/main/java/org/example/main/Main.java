package org.example.main;

import org.example.base.Base;
import org.example.genericrepository.GenericArrayRepository;
import org.example.genericrepository.GenericFileRepository;
import org.example.genericrepository.InvalidIndexException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InvalidIndexException {
        //Array practice

        GenericArrayRepository<Base> genericRepository = new GenericArrayRepository<>();
        Base num1 = new Base();
        num1.setId(1);
        Base num2 = new Base();
        num2.setId(2);
        Base num3 = new Base();
        num3.setId(3);
        Base num4 = new Base();
        num4.setId(2);
        Base num5 = new Base();
        num5.setId(2);
        Base num6 = new Base();
        num6.setId(5);
        genericRepository.add(num1);
        genericRepository.add(num2);
        genericRepository.add(num3);
        genericRepository.add(num4);
        genericRepository.add(num5);
        genericRepository.add(num6);
        System.out.println("Array elements:");
        genericRepository.print();
        try {
            System.out.println("Print the value of the selected element with index:" + genericRepository.get(1).getId());
        } catch (InvalidIndexException e) {
            throw new RuntimeException(e);
        }
        System.out.println(("Print the value of the selected element with id:" + genericRepository.getById(num4)));
        try {
            genericRepository.remove(4);
        } catch (InvalidIndexException e) {
            throw new RuntimeException(e);
        }
        try {
            genericRepository.remove(num1);
        } catch (InvalidIndexException e) {
            throw new RuntimeException(e);
        }
        try {
            genericRepository.removeAllElement(num2);
        } catch (InvalidIndexException e) {
            System.out.println(e);
        }
        System.out.println("The array after remove:");
        genericRepository.print();
        try {
            genericRepository.update(0, new Base(8));
        } catch (InvalidIndexException e) {
            throw new RuntimeException(e);
        }

        System.out.println("The array after update:");
        genericRepository.print();
        System.out.println("The index of the First Element in the Array:" + genericRepository.find(num6));
        System.out.println("print index of find Element in the Array:" + genericRepository.findFirstById(num6));
        System.out.println("Print the existence of an element in the array:" + genericRepository.contain(num6));
        System.out.println("sub-element:");
        //System.out.println(Arrays.toString(genericRepository.subElements(0, 2)));
        genericRepository.subElementsGeneric(0, 2).print();
        System.out.println("before clear:");
        genericRepository.print();
        genericRepository.deleteContent();
        System.out.println("after clear:");
        genericRepository.print();
        System.out.println("------------------");
        //File practice
        GenericFileRepository<String> genericFileRepository = new GenericFileRepository("myFile");
        genericFileRepository.add("hello");
        genericFileRepository.add("good");
        genericFileRepository.add("by");
        genericFileRepository.add("hello");
        genericFileRepository.add("good");
        genericFileRepository.add("by");
        System.out.println("File content:");
        genericFileRepository.print();
        System.out.println("The content of the desired line number in the file:" + genericFileRepository.get(1));
        System.out.println("The line number of the desired content in the file:" + genericFileRepository.find("hello"));
        genericFileRepository.remove("good");
        genericFileRepository.remove(3);
        System.out.println("File content after deletion:");
        genericFileRepository.print();
        genericFileRepository.deleteContent();
        genericFileRepository.removeFile();
    }
}
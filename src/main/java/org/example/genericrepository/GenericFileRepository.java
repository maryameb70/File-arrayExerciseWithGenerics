package org.example.genericrepository;

import java.io.*;
import java.util.Scanner;

public class GenericFileRepository<T> implements GenericAllRepository<T> {

    private String fileName;
    private File file;
    private FileWriter fileWriter = null;
    private BufferedWriter bufferedWriter = null;
    private FileReader fileReader = null;
    private BufferedReader bufferedReader = null;
    private Integer indexLine = 0;

    public GenericFileRepository(String fileName) {
        this.fileName = fileName;
        file = new File(fileName);
    }

    @Override
    public T get(int index) {
        int line = 0;
        String currentLine;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while ((currentLine = bufferedReader.readLine()) != null) {
                line++;
                if (index == line) {
                    fileReader.close();
                    bufferedReader.close();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception in file writing", e);
        }
        return (T) currentLine;
    }

    @Override
    public void add(T element) {
        try {
            fileWriter = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.append(element + "\n");
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception in file writing", e);

        }
    }

    @Override
    public int find(T element) {
        String[] words = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            int index = 1;
            while ((line = bufferedReader.readLine()) != null) {
                words = line.split(" ");
                for (String word : words) {
                    if (word.equals(element)) {
                        fileReader.close();
                        bufferedReader.close();
                        return indexLine = index;
                    }
                }
                index++;
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception in file writing", e);

        }
        return 0;
    }

    @Override
    public void remove(T element) {
        int line = find(element);
        if (line == 0) {
            return;
        }
        remove(line);
    }

    @Override
    public void remove(int index) {
        if (index <= 0) {
            return;
        }
        shift(index);
    }

    @Override
    public void shift(int index) {
        File oldFile = new File("myFile");
        File newFile = new File("newFile");
        int line = 0;
        String currentLine;
        try {
            fileReader.close();
            bufferedReader.close();
            fileWriter = new FileWriter(newFile);
            bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter pw = new PrintWriter(bufferedWriter);
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while ((currentLine = bufferedReader.readLine()) != null) {
                line++;
                if (index != line) {
                    pw.println(currentLine);
                }
            }
            pw.flush();
            pw.close();
            bufferedWriter.close();
            fileWriter.close();
            fileReader.close();
            bufferedReader.close();
            file.delete();
            newFile.renameTo(oldFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception in file writing", e);
        }
    }

    @Override
    public void print() {
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            String s = "";
            while ((line = bufferedReader.readLine()) != null) {
                s += line + "\n";
            }
            System.out.println(s);
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception in file writing", e);
        }
    }

    public void clear() {
        if (file.delete()) {
            System.out.println(file.getName() + " is successfully deleted");
        } else {
            System.out.println("Failed to delete " + file.getName() + " file");
        }
    }

    @Override
    public Boolean contain(T element) {
        return null;
    }
}

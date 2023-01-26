package org.example.genericrepository;

import java.io.*;

public class GenericFileRepository<T> implements GenericAllRepository<T> {
    private String fileName;
    private File file;
    private Integer indexLine = 0;

    public GenericFileRepository(String fileName) {
        this.fileName = fileName;
        file = new File(fileName);
    }

    @Override
    public T get(int index) {
        int line = 0;
        String currentLine;
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((currentLine = bufferedReader.readLine()) != null) {
                line++;
                if (index == line) {
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
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
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
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            int index = 1;
            while ((line = bufferedReader.readLine()) != null) {
                words = line.split(" ");
                for (String word : words) {
                    if (word.equals(element)) {
                        return indexLine = index;
                    }
                }
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception in file writing", e);

        }
        return -1;
    }

    @Override
    public void remove(T element) {
        int line = find(element);
        if (line == -1) {
            return;
        }
        remove(line);
    }

    @Override
    public void remove(int index) {
        if (index < 1) {
            return;
        }
        shift(index);
    }

    @Override
    public void shift(int index) {
        int line = 0;
        String currentLine;
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String content = "";
            while ((currentLine = bufferedReader.readLine()) != null) {
                line++;
                if (index != line) {
                    content += currentLine + "\n";
                }
            }
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception in file writing", e);
        }
    }

    @Override
    public void print() {
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            String s = "";
            while ((line = bufferedReader.readLine()) != null) {
                s += line + "\n";
            }
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception in file writing", e);
        }
    }

    @Override
    public void deleteContent() {
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception in file writing", e);
        }
    }

    public void removeFile() {
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

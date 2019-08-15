package ict.booth.generator.classes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

final public class MyBuffer<T extends Number> {

    private static final String PATH = "ict_numbers.txt";


    void write(String line) {
        write(line, PATH);
    }


    void write(List<T> series) {
        write(series, PATH);
    }

    void write(List<T> series, String path) {
        String line = "";
        for (T num : new ArrayList<>(series)) {
            line += (num + " ");
        }

        write(line, path);
    }

    void append(String line, String path) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path, true));
            bw.write(line + " ");
            bw.flush();
        } catch (IOException e) {
            System.out.println("Error!");
        } finally {
            try {
                assert bw != null;
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void write(String line, String path) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path));
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error!");
        } finally {
            try {
                assert bw != null;
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    String read(String path) {
        System.out.println("sup sup!");
        FileReader fr;
        BufferedReader br = null;
        String output = "";
        try {

            if (path != null) fr = new FileReader(path);
            else fr = new FileReader(PATH);

            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                output += (line + "\n");
            }
        } catch (NoSuchFileException e) {
            System.out.println("No such file.");
        } catch (IOException e) {
            System.out.println("Error!");
        } finally {
            try {
                assert br != null;
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return output;
    }

    public void createFiles(String path) {
        File myfile;
        if (path != null) myfile = new File(path);
        else myfile = new File(PATH);

        try {
            if (myfile.createNewFile()) {
                System.out.println("File created!");
            } else {
                System.out.println("File was created!");
            }
        } catch (IOException e) {
            System.out.println("Mapping faliure!");
        }

    }

    public void delete(String path) {
        try {
            Path file_path = Paths.get(path);
            Files.delete(file_path);
            System.out.println("File successfully deleted!");
        } catch (NoSuchFileException e) {
            System.out.println("No such file.");
        } catch (SecurityException e) {
            System.out.println("No permission to delete.");
        } catch (IOException e) {
            System.out.println("IOException.");
        }
    }
}

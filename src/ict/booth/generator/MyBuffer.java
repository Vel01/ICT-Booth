package ict.booth.generator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

final class MyBuffer {

    private final static String PATH_ELIMINATED_NUMBERS = "ict_eliminated_numbers.txt";

    static List<Integer> load(String filePath) throws IOException {

        Path path = Paths.get(filePath);
        BufferedReader bufferedReader = Files.newBufferedReader(path);

        String input;
        List<Integer> list = new ArrayList<>();
        String[] itemPieces = new String[0];
        try {

            while ((input = bufferedReader.readLine()) != null) {
                itemPieces = input.split("\\W+");
            }
            for (String num : itemPieces) {
                list.add(Integer.valueOf(String.valueOf(num)));
            }

            System.out.println("Loaded Successfully!");
        } finally {
            bufferedReader.close();
        }
        return list;
    }

    static void write(String file) throws IOException {
        Path path = Paths.get(file);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            List<Integer> list = MainWindow.getKeys();
            for (Integer integer : list) {
                bufferedWriter.write(integer + " ");
            }
            System.out.println("Saved Successfully!");
        }
    }

    static boolean clear() throws IOException {
        Path path = Paths.get(PATH_ELIMINATED_NUMBERS);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            bufferedWriter.write("");
            System.out.println("Successfully Reset!");
            return true;
        }
    }
}

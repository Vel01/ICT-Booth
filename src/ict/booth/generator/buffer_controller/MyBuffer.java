package ict.booth.generator.buffer_controller;

import ict.booth.generator.main_controller.MainController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class MyBuffer {

    /**
     * load method will read the data in a txt file.
     *
     * @param filePath the location of a txt file.
     * @return will return the loaded data in a txt file.
     * @throws IOException will throws if file doesn't exist.
     */
    public static List<Integer> load(String filePath) throws IOException {

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

            System.out.println("MyBuffer.called: Loaded Successfully!");
        } finally {
            bufferedReader.close();
        }
        return list;
    }

    /**
     * write method will saved the data given by the keys reference in a txt file.
     *
     * @param file where the data will be saved.
     * @throws IOException will throws if file doesn't exist.
     */
    public static void write(String file) throws IOException {
        Path path = Paths.get(file);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            List<Integer> list = MainController.getKeys();
            for (Integer integer : list) {
                bufferedWriter.write(integer + " ");
            }
            System.out.println("MyBuffer.called: Saved Successfully!");
        }
    }

    /**
     * clear are overloaded methods that will erase the data in a txt file.
     * @return if successful will returning true.
     * @throws IOException will throws if files doesn't exist.
     */
    public static boolean clear() throws IOException {
        Path path = Paths.get(PathFiles.PATH_ELIMINATED_NUMBERS);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            bufferedWriter.write("");
            System.out.println("MyBuffer.called: Successfully Reset!");
            return true;
        }
    }

    public static boolean clear(String filePath) throws IOException {
        clear();
        Path path = Paths.get(filePath);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            bufferedWriter.write("");
            System.out.println("MyBuffer.called: Successfully Reset!");
            return true;
        }
    }
}

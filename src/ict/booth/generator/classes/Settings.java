package ict.booth.generator.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final public class Settings {

    private MyBuffer<Number> buffer = new MyBuffer<>();
    private static List<Integer> eliminated_series = new ArrayList<>();
    private static int maxNumber = 600;

    public void generate() {
        String line = "";
        for (int i = 1; i <= maxNumber; i++) {
            line += (i + " ");
        }
        buffer.write(line);
    }

    public void save(String line, String path) {
        buffer.append(line, path);
    }

    public void showEliminatedNumbers(String path) {
        String line = buffer.read(path);
        if (!line.isEmpty()) {
            String[] numbers = line.split("\\W+");
            for (String num : numbers) eliminated_series.add(Integer.valueOf(num));
            Collections.sort(eliminated_series);
            String show = "";
            for (int num : eliminated_series) show += (num + " ");
            System.out.println("Current Eliminated Numbers: " + show);
            eliminated_series.clear();
        }
    }

    public void generatedWithoutEliminatedNumbers(String path) {
        String line = "";
        String file = buffer.read(path);
        if (!file.isEmpty()) {
            String[] numbers = file.split("\\W+");
            for (String num : numbers) eliminated_series.add(Integer.valueOf(num));

            for (int i = 1; i <= maxNumber; i++) {
                if (find(String.valueOf(i)) > -1) continue;
                line += (i + " ");
            }

            buffer.write(line);
            eliminated_series.clear();
        }
    }

    private int find(String number) {
        for (int num : eliminated_series) {
            if (String.valueOf(num).equals(number)) {
                return num;
            }
        }
        return -1;
    }
}
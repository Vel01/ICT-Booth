package ict.booth.generator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainWindow {

    @FXML
    private Label lbl_number;
    @FXML
    private BorderPane id_main_window;

    private final static String PATH_BACKUP_NUMBERS = "ict_backup_numbers.txt";
    private static List<Integer> keys = new ArrayList<>();

    @FXML
    public void onButtonClicked() {

        int value = rand(600);
        if (value != -1) {
            lbl_number.setText(String.valueOf(value));
            System.out.println(value);
            showDialog();
            return;
        }
        lbl_number.setText("0");
    }

    @FXML
    public void reset() throws IOException {
        if (MyBuffer.clear()) keys.clear();
    }

    @FXML
    public void backUp() throws IOException {
        MyBuffer.write(PATH_BACKUP_NUMBERS);
    }

    @FXML
    public void retrieve() throws IOException {
        keys.addAll(MyBuffer.load(PATH_BACKUP_NUMBERS));
    }

    @FXML
    private void showDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(id_main_window.getScene().getWindow());
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main_dialog.fxml"));
            dialog.getDialogPane().setContent(root);

        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Ok pressed");
        }
    }

    private static int rand(int max) {
        if (keys.size() == max) return -1;
        int n = (int) (Math.random() * max) + 1;
        if (!keys.contains(n)) {
            keys.add(n);
            return n;
        }
        return rand(max);
    }

    static void receiver(List<Integer> list) {
        keys.addAll(list);
    }

    static List<Integer> getKeys() {
        return new ArrayList<>(keys);
    }

}
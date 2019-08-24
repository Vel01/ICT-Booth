package ict.booth.generator.main_controller;

import ict.booth.generator.buffer_controller.MyBuffer;
import ict.booth.generator.buffer_controller.PathFiles;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainController implements Savable {

    @FXML
    private Label lbl_number;
    @FXML
    private BorderPane id_main_window;
    /**
     * All eliminated numbers are stored in an object keys reference as list of integer.
     */
    private static List<Integer> keys = new ArrayList<>();

    /**
     * Generate numbers from 1 to 600 and display each generated number to "lbl_number" Label
     * If keys reference is full "lbl_number" label will set to 0.
     */
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

    /**
     * coldReset method will clear the data in txt file and keys reference.
     *
     * @throws IOException will throws if file is not exist.
     */
    @FXML
    public void coldReset() throws IOException {
        if (MyBuffer.clear()) keys.clear();
    }

    /**
     * hardReset method will clear the data in txt file and keys reference and set
     * the label to 0 as default value.
     *
     * @throws IOException will throws if file is not exist.
     */
    @FXML
    public void hardReset() throws IOException {
        if (MyBuffer.clear(PathFiles.PATH_BACKUP_NUMBERS)) keys.clear();
        lbl_number.setText("0");
    }

    /**
     * exit method will fully exited the application and saved all the data before it
     * close.
     *
     * @throws IOException will throws if files does'nt exist.
     */
    @FXML
    public void exit() throws IOException {
        save();
        Platform.exit();
        System.exit(0);
    }

    /**
     * retrieve method will clear keys reference data and set label to 0 then
     * will load the current data in a txt file.
     *
     * @throws IOException will throws if file is not exist.
     */
    @FXML
    public void retrieve() throws IOException {
        keys.clear();
        lbl_number.setText("0");
        keys.addAll(MyBuffer.load(PathFiles.PATH_BACKUP_NUMBERS));
    }

    /**
     * showDialog method will be called if label was clicked.
     */
    @FXML
    private void showDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(id_main_window.getScene().getWindow());
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ict/booth/generator/main_fxml/main_dialog.fxml"));
            dialog.getDialogPane().setContent(root);
            Rectangle2D bounds = Screen.getPrimary().getBounds();
            dialog.setX(bounds.getMaxX() - 750);
            dialog.setY(bounds.getMaxY() - 750);
        } catch (IOException e) {
            System.out.println("MainWindow.called: Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("MainWindow.called: Ok pressed");
        }
    }

    /**
     * rand is the operation that generate a single value.
     *
     * @param max hold the given maximum value.
     * @return will hold the value to be passed if this was called.
     */
    private static int rand(int max) {
        if (keys.size() == max) return -1;
        int n = (int) (Math.random() * max) + 1;
        if (!keys.contains(n)) {
            keys.add(n);
            return n;
        }
        return rand(max);
    }

    /**
     * This will be use to get and set the loaded value in a txt file and give it
     * to keys reference to be hold.
     * @param list will be the reference of the loaded txt file that was retrieved.
     */
    static void receiver(List<Integer> list) {
        keys.addAll(list);
    }

    /**
     * this will be used to get all values in keys reference.
     * @return will return the list of data.
     */
    public static List<Integer> getKeys() {
        return new ArrayList<>(keys);
    }

}
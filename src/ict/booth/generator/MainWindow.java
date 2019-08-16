package ict.booth.generator;

import ict.booth.generator.classes.Generator;
import ict.booth.generator.classes.MyBuffer;
import ict.booth.generator.classes.Settings;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainWindow {
    @FXML
    private Label lbl_number;
    private final static String PATH_ELIMINATED_NUMBERS = "ict_eliminated_numbers.txt";

    private static Settings settings = new Settings();
    private Generator generator = new Generator();

    public void onButtonClicked() {

        try {
            String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
            System.out.println("I'm going to sleep on the: " + s);
            Thread.sleep(1000);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
                    System.out.println("I'm updating the label on the: " + s);
                    System.out.println("We did something!");
                    System.out.println("Button was clicked: " + lbl_number.getText());
                    String value = generator.picked();
                    if (value != null) {
                        settings.save(value, "ict_eliminated_numbers.txt");
                        lbl_number.setText(value);
                    } else lbl_number.setText("0");

                }
            });
        } catch (InterruptedException event) {
            // I don't care about this...
        }

    }

    public void onMenuFullGenerate() {
        settings.generate();
    }

    public void onMenuHalfGenerate() {
        settings.generatedWithoutEliminatedNumbers(PATH_ELIMINATED_NUMBERS);
    }

    public void onMenuReset(){
        MyBuffer myBuffer = new MyBuffer();
        myBuffer.delete(PATH_ELIMINATED_NUMBERS);
        myBuffer.createFiles(PATH_ELIMINATED_NUMBERS);
    }

}

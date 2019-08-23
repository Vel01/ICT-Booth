package ict.booth.generator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private final static String PATH_ELIMINATED_NUMBERS = "ict_eliminated_numbers.txt";
    private final static String PATH_BACKUP_NUMBERS = "ict_backup_numbers.txt";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main_window.fxml"));
        primaryStage.setTitle("Generator");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.setResizable(false);
        Image image = new Image(getClass().getResourceAsStream("Home24.gif"));
        primaryStage.getIcons().add(image);
        primaryStage.show();

        System.out.println("nag start");
        MainWindow.receiver(MyBuffer.load(PATH_ELIMINATED_NUMBERS));
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        System.out.println("tumigil");
        MyBuffer.write(PATH_ELIMINATED_NUMBERS);
        MyBuffer.write(PATH_BACKUP_NUMBERS);
    }
}

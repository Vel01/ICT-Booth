package ict.booth.generator.main_controller;

import ict.booth.generator.buffer_controller.MyBuffer;
import ict.booth.generator.buffer_controller.PathFiles;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application implements Savable {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ict/booth/generator/main_fxml/main_window.fxml"));
        primaryStage.setTitle("Generator");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.setResizable(false);
        Image image = new Image(getClass().getResourceAsStream("/ict/booth/generator/Home24.gif"));
        primaryStage.getIcons().add(image);
        primaryStage.show();

        System.out.println("Main.called: nag start");
        MainController.receiver(MyBuffer.load(PathFiles.PATH_ELIMINATED_NUMBERS));
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * before the application stopped the class will saved the current data making
     * the application as auto-savable.
     *
     * @throws Exception will throws if files doesn't exist.
     */
    @Override
    public void stop() throws Exception {
        save();
    }
}

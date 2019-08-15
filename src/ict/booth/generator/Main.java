package ict.booth.generator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main_window.fxml"));
        primaryStage.setTitle("Generator");
        primaryStage.setScene(new Scene(root, 350, 300));
        primaryStage.setResizable(false);
        Image image = new Image(getClass().getResourceAsStream("Home24.gif"));
        primaryStage.getIcons().add(image);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

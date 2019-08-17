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
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.setResizable(false);
        Image image = new Image(getClass().getResourceAsStream("Home24.gif"));
        primaryStage.getIcons().add(image);
        primaryStage.show();

        System.out.println("nag start");
        // TODO: 8/17/2019 retrieved the eliminated numbers here....
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        // TODO: 8/17/2019 save the numbers that already eliminated here...
        System.out.println("tumigil");
    }
}

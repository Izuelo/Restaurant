package pw.macdollanapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;




public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Image image = new Image("pw/macdollanapp/MCbackground.jpg");
        ImageView mv = new ImageView(image);

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Mac Dolan");
        primaryStage.setScene(new Scene(root, 810, 675));
        primaryStage.show();

}


    public static void main(String[] args) {
        launch(args);
    }
}

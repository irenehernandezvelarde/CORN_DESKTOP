package ch.makery.corn;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		 this.primaryStage.setTitle("CORN");

		 FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(MainApp.class.getResource("view/userPane.fxml"));
		 try {
			AnchorPane userOverview = (AnchorPane) loader.load();
			Scene scene = new Scene(userOverview);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public static void main(String[] args) {
		launch(args);
	}

}

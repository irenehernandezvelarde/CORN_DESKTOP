module CORN {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;

	opens ch.makery.corn to javafx.graphics, javafx.fxml;
}

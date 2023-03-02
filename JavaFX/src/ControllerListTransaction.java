import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Polygon;

public class ControllerListTransaction {
    @FXML
    private Label origin, quantity, acceptDate;

    @FXML
    private Polygon coloredShape;

    public void setOrigin(String origin) {
        this.origin.setText(origin);
    }

    public void setQuantity(String quantity) {
        this.quantity.setText(quantity);
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate.setText(acceptDate);
    }

    public void setColor(String color) {
        coloredShape.setStyle("-fx-fill: " + color);
    }
    
}


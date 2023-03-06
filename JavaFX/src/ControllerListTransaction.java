import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Polygon;

public class ControllerListTransaction {
    @FXML
    private Label id, origin, destiny, quantity, accepted, acceptDate;

    @FXML
    private void handleMenuAction() {
        ControllerTransactions c3 = (ControllerTransactions) UtilsViews.getController("transactionList");

    }

    public void setId(String id) {
        this.id.setText(id);
    }

    public void setOrigin(String origin) {
        this.origin.setText(origin);
    }

    public void setDestiny(String destiny) {
        this.destiny.setText(destiny);
    }

    public void setQuantity(String quantity) {
        this.quantity.setText(quantity);
    }

    public void setAccepted(String accepted){
        this.accepted.setText(accepted);
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate.setText(acceptDate);
    }

}


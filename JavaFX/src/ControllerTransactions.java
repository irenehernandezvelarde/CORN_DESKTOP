import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ControllerTransactions implements Initializable{

    private String sendPhone = null;

    @FXML
    private VBox vBoxList = new VBox();

    @FXML
    private Button go_back;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void loadTransactionList() {
        JSONObject obj = new JSONObject("{}");
        obj.put("type", "get_transactions");
        obj.put("phone", sendPhone);

        UtilsHTTP.sendPOST(Main.protocol + "://" + Main.host + ":" + Main.port + "/dades", obj.toString(),
                (response) -> {
                    loadTransactions(response);
        });
    }

    private void loadTransactions(String response) {
        JSONObject objResponse = new JSONObject(response);
        if (objResponse.getString("status").equals("OK")) {
            JSONArray JSONlist = objResponse.getJSONArray("transactions");
            URL resource = this.getClass().getResource("./assets/listItem.fxml");
            vBoxList.getChildren().clear();
            for (int i = 0; i < JSONlist.length(); i++) {
                JSONObject transaction = JSONlist.getJSONObject(i);
                System.out.println(transaction);
                try {
                    FXMLLoader loader = new FXMLLoader(resource);
                    Parent itemTemplate = loader.load();
                    ControllerListTransaction itemController = loader.getController();
                    itemController.setId(String.valueOf(transaction.getInt("id_transaction")));
                    if (!transaction.getString("TimeSetup").equals(null)){
                        itemController.setCreateData((String.valueOf(transaction.getString("TimeSetup"))));
                    }
                    else if (!transaction.get("origin").equals(null) && !transaction.getString("TimeAccept").equals(null)){
                        itemController.setOrigin(transaction.getString("origin"));
                        itemController.setAcceptDate((String.valueOf(transaction.getString("TimeAccept"))));
                    }
                    itemController.setDestiny(transaction.getString("destiny"));
                    itemController.setQuantity((String.valueOf(transaction.getDouble("quantity"))));
                    itemController.setAccepted((String.valueOf(transaction.getInt("accepted"))));
                    vBoxList.getChildren().add(itemTemplate);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @FXML
    public void setUserDetailList(){
        UtilsViews.setViewAnimating("user");
    }

    public void mostrarVista(String sendPhone){
        this.sendPhone = sendPhone;
        loadTransactionList();
    }
}

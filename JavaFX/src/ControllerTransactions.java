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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ControllerTransactions implements Initializable{
    
    @FXML
    private VBox vBoxList = new VBox();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        vBoxList.getChildren().clear();
    }

    private void getUsers(){

        vBoxList.getChildren().clear();
    
        JSONObject obj = new JSONObject("{}");
        UtilsHTTP.sendPOST(Main.protocol + "://" + Main.host + ":" + Main.port + "/api/get_profiles", obj.toString(), (response) -> {

            JSONObject objResponse = new JSONObject(response);
            if (objResponse.getString("status").equals("OK")) {

                JSONArray JSONlist = objResponse.getJSONArray("profiles"); //Este devuelve un array de objetos json
                URL resource = this.getClass().getResource("./src/userItemView.fxml");

                for(int i = 0; i < JSONlist.length(); i++){ 
                    
                    // Get console information
                    JSONObject user = JSONlist.getJSONObject(i);
                    
                    System.out.println(user.getString("userName"));

                    try{
                        // Load template and set controller
                        FXMLLoader loader = new FXMLLoader(resource);
                        Parent itemTemplate = loader.load();
                        ControllerListTransaction userItemController = loader.getController();

                        System.out.println(user.getString("userName"));
                        System.out.println(user.getString("userLastName"));
                        System.out.println(user.getString("userPhoneNumber"));
                        System.out.println(user.getString("userStatus"));
                        System.out.println(user.getString("userStatusModifyTime"));


                        // Fill user item 
                        userItemController.setOrigin(Transaction.getString("origin"));
                        userItemController.setLastName(user.getString("userLastName"));
                        userItemController.setPhoneNumber(user.getString("userPhoneNumber"));
                        userItemController.setEmail(user.getString("userEmail"));
                        userItemController.setBalance(user.getString("userBalance"));
                        userItemController.setStatus(user.getString("userStatus"));
                        userItemController.setLastStatusModified(dateFormat(user.getString("userStatusModifyTime")));
                        vBoxList.getChildren().add(itemTemplate);

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }

        });
    }

    
}

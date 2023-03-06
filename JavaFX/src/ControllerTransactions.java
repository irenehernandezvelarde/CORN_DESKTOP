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
        obj.put("type", "get_profiles");
        UtilsHTTP.sendPOST(Main.protocol + "://" + Main.host + ":" + Main.port + "/dades", obj.toString(), (response) -> {
            JSONObject objResponse = new JSONObject(response);
            if (objResponse.getString("status").equals("OK")) {

                JSONArray JSONlist = objResponse.getJSONArray("transactions"); //Este devuelve un array de objetos json
                URL resource = this.getClass().getResource("./src/listItem.fxml");

                for(int i = 0; i < JSONlist.length(); i++){ 
                    
                    // Get console information
                    JSONObject user = JSONlist.getJSONObject(i);
                    
                    System.out.println(user.getString("userName"));

                    try{
                       
                       
                        

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }

        });
    }

    
}

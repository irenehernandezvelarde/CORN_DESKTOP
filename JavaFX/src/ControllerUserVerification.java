import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class ControllerUserVerification implements Initializable{

    private String sendPhone = null;

    @FXML
    private Button go_back;

    @FXML
    private ImageView front_DNI, back_DNI;

    @FXML
    private ComboBox<String> verification_state;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        verification_state.getItems().addAll("NO_VERIFICAT", "PER_VERIFICAR", "ACCEPTAT", "REFUSAT");
        verification_state.getSelectionModel().select("NO_VERIFICAT");
    }

    @FXML
    public void setUserView(){
        UtilsViews.setViewAnimating("user");
    }

    @FXML
    public void setImages(String response){
        // Rebre l’objecte del servidor
        JSONObject objResponse = new JSONObject(response);

        if (objResponse.getString("status").equals("OK")) {

            String foto1 = objResponse.getString("foto1");
            String foto2 = objResponse.getString("foto2");

            // Transformar la cadena de text amb dades binàries en un byte[]
            byte[] decodedBytes1 = Base64.getDecoder().decode(foto1);
            byte[] decodedBytes2 = Base64.getDecoder().decode(foto2);

            // Crear un objecte ‘Image’ amb les dades rebudes i posar-les en una imatge
            Image img1 = new Image(new java.io.ByteArrayInputStream(decodedBytes1));
            Image img2 = new Image(new java.io.ByteArrayInputStream(decodedBytes2));

            front_DNI.setImage(img1);
            back_DNI.setImage(img2);
        }

    }
    public void loadVerificationList() {
        JSONObject obj = new JSONObject("{}");
        obj.put("type", "getImage");
        obj.put("phone", sendPhone);

        UtilsHTTP.sendPOST(Main.protocol + "://" + Main.host + ":" + Main.port + "/dades", obj.toString(),
                (response) -> {
                setImages(response);
        });
    }

    public void mostrarVista(String sendPhone){
        this.sendPhone = sendPhone;
        loadVerificationList();
    }
}

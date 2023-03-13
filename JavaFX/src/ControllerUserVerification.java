import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class ControllerUserVerification implements Initializable{

    private String sendPhone = null;
    private String initial, current;
    private Image defaultImage = new Image("./assets/noImage.png");

    private Alert alertBack = new Alert(AlertType.CONFIRMATION);

    @FXML
    private Button go_back, saveState;

    @FXML
    private ImageView front_DNI, back_DNI;

    @FXML
    private ComboBox<String> verification_state;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        verification_state.getItems().addAll("NO_VERIFICAT", "PER_VERIFICAR", "ACCEPTAT", "REFUSAT");

    }

    @FXML
    public void setUserView(){
        if (!initial.equals(current)){
            alertBack.setHeaderText("Esats segur de tornar sense guardar?");
            alertBack.setContentText("No has guardat els canvis fets!");

            Optional<ButtonType> result = alertBack.showAndWait();
            if (result.get() == ButtonType.OK){
                System.out.println("ENTRO");
                saveVerificate();
                front_DNI.setImage(defaultImage);
                back_DNI.setImage(defaultImage);
                UtilsViews.setViewAnimating("user");
            }
        }
    }

    @FXML
    public void saveStateDrop(){
        current = verification_state.getSelectionModel().getSelectedItem();
        System.out.println("CURRENT = "+current);
        System.out.println("INITIAL = "+initial);

    }

    @FXML
    public void saveVerificate(){
        front_DNI.setImage(defaultImage);
        back_DNI.setImage(defaultImage);
        UtilsViews.setViewAnimating("user");
    }

    @FXML
    public void setImages(String response){
        // Rebre l’objecte del servidor
        JSONObject objResponse = new JSONObject(response);

        if (objResponse.getString("status").equals("OK")) {
            String foto1 = objResponse.getString("foto1");
            String foto2 = objResponse.getString("foto2");

            if (!objResponse.getString("foto1").equalsIgnoreCase("null") && !objResponse.getString("foto2").equalsIgnoreCase("null")){
                // Transformar la cadena de text amb dades binàries en un byte[]
                byte[] decodedBytes1 = Base64.getDecoder().decode(foto1);
                byte[] decodedBytes2 = Base64.getDecoder().decode(foto2);

                // Crear un objecte ‘Image’ amb les dades rebudes i posar-les en una imatge
                Image img1 = new Image(new java.io.ByteArrayInputStream(decodedBytes1));
                Image img2 = new Image(new java.io.ByteArrayInputStream(decodedBytes2));

                front_DNI.setImage(img1);
                back_DNI.setImage(img2);

            }else if (!objResponse.getString("foto1").equalsIgnoreCase("null") && objResponse.getString("foto2").equalsIgnoreCase("null")){
                // Transformar la cadena de text amb dades binàries en un byte[]
                byte[] decodedBytes1 = Base64.getDecoder().decode(foto1);

                // Crear un objecte ‘Image’ amb les dades rebudes i posar-les en una imatge
                Image img1 = new Image(new java.io.ByteArrayInputStream(decodedBytes1));

                front_DNI.setImage(img1);
                back_DNI.setImage(defaultImage);

            }else if (objResponse.getString("foto1").equalsIgnoreCase("null") && !objResponse.getString("foto2").equalsIgnoreCase("null")){
                // Transformar la cadena de text amb dades binàries en un byte[]
                byte[] decodedBytes2 = Base64.getDecoder().decode(foto2);

                // Crear un objecte ‘Image’ amb les dades rebudes i posar-les en una imatge
                Image img2 = new Image(new java.io.ByteArrayInputStream(decodedBytes2));

                front_DNI.setImage(defaultImage);
                back_DNI.setImage(img2);
            }else{
                front_DNI.setImage(defaultImage);
                back_DNI.setImage(defaultImage);
            }

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

    public void loadStatesList() {
        JSONObject obj = new JSONObject("{}");
        obj.put("type", "get_profile_desktop");
        obj.put("phone", sendPhone);

        UtilsHTTP.sendPOST(Main.protocol + "://" + Main.host + ":" + Main.port + "/dades", obj.toString(),
                (response) -> {
                    loadStates(response);
        });
    }

    private void loadStates(String response) {
        JSONObject objResponse = new JSONObject(response);
        if (objResponse.getString("status").equals("OK")) {
            JSONArray JSONlist = objResponse.getJSONArray("result");
            for (int i = 0; i < JSONlist.length(); i++) {
                JSONObject user = JSONlist.getJSONObject(i);
                System.out.println(user.getString("state"));
                if (user.getString("state").equalsIgnoreCase("NO_VERIFICAT")){
                    verification_state.getSelectionModel().select("NO_VERIFICAT");
                }else{
                    verification_state.getSelectionModel().select(user.getString("state"));
                }
            }

        }
        initial = verification_state.getSelectionModel().getSelectedItem();
    }

    public void mostrarVerification(String sendPhone){
        this.sendPhone = sendPhone;
        loadVerificationList();
        loadStatesList();
    }
}

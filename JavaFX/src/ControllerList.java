import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class ControllerList implements Initializable{
    @FXML
	private TableView<User> userTable;
	@FXML
	private TableColumn<User,String> firstNameColumn;
	@FXML
	private TableColumn<User,String> lastNameColumn;
    @FXML
	private TableColumn<User,Number> phoneColumn;
    private ObservableList<User> userData = FXCollections.observableArrayList();

    @FXML
    private Label firstNameLabel, lastNameLabel, phoneLabel, emailLabel, balanceLabel;

    @FXML
    private Button transactionButton;

    Alert alert = new Alert(AlertType.ERROR);

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        // Initialize the person table with the two columns.
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(
        cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(
        cellData -> cellData.getValue().lastNameProperty());
        phoneColumn.setCellValueFactory(
        cellData -> cellData.getValue().phoneProperty());
        JSONObject obj = new JSONObject("{}");
        obj.put("type", "profiles");

        UtilsHTTP.sendPOST(Main.protocol + "://" + Main.host + ":" + Main.port + "/dades", obj.toString(), (response) -> {
            loadListCallback(response);
        });
        userTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showUserDetails(newValue));

    }

    @FXML
    private void setTransactionListView(ActionEvent event){
        if (phoneLabel.getText().equals("nul") || phoneLabel.getText().equals("")){
            alert.setHeaderText("Telefon de l'usuari no existeix");
            alert.setContentText("El telefon de l'usuari seleccionat no existeix.");

            alert.showAndWait();
        }
        else{
            String sendPhone = phoneLabel.getText();
            ControllerTransactions cl = (ControllerTransactions) UtilsViews.getController("transactionList");
            cl.mostrarVista(sendPhone);

            UtilsViews.setViewAnimating("transactionList");
        }

    }

    private void loadListCallback (String response) {

        JSONObject objResponse = new JSONObject(response);
        if (objResponse.getString("status").equals("OK")) {
            JSONArray JSONlist = objResponse.getJSONArray("result");


            // Add received consoles from the JSON to the yPane (VBox) list
            for (int i = 0; i < JSONlist.length(); i++) {

                // Get console information
                JSONObject user = JSONlist.getJSONObject(i);

                // Fill template with console information
                User newUser = new User(user.getString("name"),user.getString("surname"),user.getInt("phone"), user.getString("email"),user.getDouble("balance"));
                userData.add(newUser);
                userTable.setItems(userData);
            }

        }
    }

    private void showUserDetails(User user){
        if (user != null){
            firstNameLabel.setText(user.getFirstName());
            lastNameLabel.setText(user.getLastName());
            phoneLabel.setText(Integer.toString(user.getPhone()));
            emailLabel.setText(user.getEmail());
            balanceLabel.setText(Double.toString(user.getBalance()));
            System.out.println(user);
        }else{
            firstNameLabel.setText("Undefined");
            lastNameLabel.setText("Undefined");
            phoneLabel.setText("Undefined");
            emailLabel.setText("Undefined");
            balanceLabel.setText("Undefined");
        }
    }
}

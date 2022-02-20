package Controllers;


import com.example.employees_management.DatabaseConnection;
import com.example.employees_management.HelloApplication;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private MFXButton btnLogin;

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private MFXTextField usernameTextField;
    @FXML
    private MFXPasswordField passwordTextField;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    private Stage mStage;

    public void setStage(Stage mStage) {
        this.mStage = mStage;
    }

    public void closeLoginStage(ActionEvent actionEvent) {
        mStage.close();
    }

    public void loginButtonOnAction(ActionEvent event) throws IOException {
        validateLogin();
    }

    public void cancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM users WHERE username='" + usernameTextField.getText() + "' AND password='" + passwordTextField.getPassword() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("Congratulations!");
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("dashboard.fxml"));
                    Parent root = loader.load();
                    DashboardController dashboard = loader.getController();
                    dashboard.setStage(mStage);
                    dashboard.setLoginLabel(usernameTextField.getText());

                    mStage.setTitle("second scene");
                    mStage.setScene(new Scene(root));
                    mStage.show();
                } else {
                    loginMessageLabel.setText("Invalid login. Please try again");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

}
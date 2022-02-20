package Controllers;

import com.example.employees_management.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private BorderPane mainPane;


    @FXML
    private Label loginUsername;

    public void setLoginLabel(String username) {
        this.loginUsername.setText(username);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        handleActionOverview();
    }

    private Stage mStage;

    public void setStage(Stage mStage) {
        this.mStage = mStage;
    }

    public void CloseDashboardStage(ActionEvent actionEvent) {
        mStage.close();
    }

    public void loginoutButtonOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Parent root = loader.load();
        LoginController login = loader.getController();
        login.setStage(mStage);

        mStage.setTitle("first scene");
        mStage.setScene(new Scene(root));
        mStage.show();
    }

    public void handleActionOverview() {
        System.out.println("you clicked me");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("dashboard_ov.fxml");
        mainPane.setCenter(view);
    }

    public void handleActionDepartments(ActionEvent actionEvent) {
        System.out.println("you clicked me");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("departments.fxml");
        mainPane.setCenter(view);
    }

    public void handleActionEquipe(ActionEvent actionEvent) {
        System.out.println("you clicked me");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("equipe.fxml");
        mainPane.setCenter(view);
    }

    public void handleActionProjets(ActionEvent actionEvent) {
        System.out.println("you clicked me");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("projets.fxml");
        mainPane.setCenter(view);
    }

    public void handleActionEmploye(ActionEvent actionEvent) {
        System.out.println("you clicked me");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("employe.fxml");
        mainPane.setCenter(view);
    }

    public void handleActionDirecteur(ActionEvent actionEvent) {
        System.out.println("you clicked me");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("directeur.fxml");
        mainPane.setCenter(view);
    }
}

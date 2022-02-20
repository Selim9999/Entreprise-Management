package Controllers;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EditDirecteurCrudController implements Initializable {

    @FXML
    private MFXTextField idTextField;

    @FXML
    private MFXTextField nomTextField;

    @FXML
    private MFXTextField prenomTextField;

    @FXML
    private MFXTextField salaireTextField;

    @FXML
    private MFXTextField primeTextField;


    private Stage mStage;

    public void setStage(Stage mStage) {
        this.mStage = mStage;
        mStage.initStyle(StageStyle.UNDECORATED);
        mStage.setX(1150);
        mStage.setY(350);

    }


    public MFXTextField getIdTextField() {
        return idTextField;
    }

    public MFXTextField getNomTextField() {
        return nomTextField;
    }

    public MFXTextField getPrenomTextField() {
        return prenomTextField;
    }

    public MFXTextField getSalaireTextField() {
        return salaireTextField;
    }

    public MFXTextField getPrimeTextField() {
        return primeTextField;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void editDirecteur(ActionEvent event) {
        DirecteurController ConnectNow = new DirecteurController();
        Connection connectDB = ConnectNow.getConnetion();

        String value1 = idTextField.getText();
        String value2 = nomTextField.getText();
        String value3 = prenomTextField.getText();
        String value4 = salaireTextField.getText();
        String value5 = primeTextField.getText();

        String query = "UPDATE directeur set id = '" + value1 + "', nom = '" + value2 + "', prenom = '" + value3 + "', salaire = '" + value4 + "', prime = '" + value5 + "' where id ='" + value1 + "' ";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);
            mStage.close();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();

        }
    }

    public void hideEditDirecteur() {
        mStage.close();
    }
}

package Controllers;

import io.github.palexdev.materialfx.controls.MFXDatePicker;
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

public class AddProjetsCrudController implements Initializable {

    @FXML
    private MFXTextField idTextField;

    @FXML
    private MFXTextField descriptionTextField;

    @FXML
    private MFXTextField idEquipeTextField;

    @FXML
    private MFXDatePicker dateDebutDatePicker;

    @FXML
    private MFXDatePicker dateFinDatePicker;


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

    public MFXTextField getDescriptionTextField() {
        return descriptionTextField;
    }

    public MFXTextField getIdEquipeTextField() {
        return idEquipeTextField;
    }

    public MFXDatePicker getDateDebutDatePicker() {
        return dateDebutDatePicker;
    }

    public MFXDatePicker getDateFinDatePicker() {
        return dateFinDatePicker;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    void addProjets(ActionEvent event) {
        ProjetsController ConnectNow = new ProjetsController();
        Connection connectDB = ConnectNow.getConnetion();

        String query = "INSERT INTO projets VALUES (" + idTextField.getText() + ",'" + descriptionTextField.getText() + "','" + dateDebutDatePicker.getDate() + "','" + dateFinDatePicker.getDate() + "'," + idEquipeTextField.getText() + ")";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);
            mStage.close();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void hideAddProjets() {
        mStage.close();
    }
}

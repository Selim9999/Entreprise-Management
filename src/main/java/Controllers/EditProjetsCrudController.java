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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditProjetsCrudController implements Initializable {
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
    void editProjets(ActionEvent event) {
        ProjetsController ConnectNow = new ProjetsController();
        Connection connectDB = ConnectNow.getConnetion();


        String value1 = idTextField.getText();
        String value2 = descriptionTextField.getText();
        LocalDate value3 = dateDebutDatePicker.getDate();
        System.out.println(value3);
        LocalDate value4 = dateFinDatePicker.getDate();
        System.out.println(value4);
        String value5 = idEquipeTextField.getText();


        String query = "UPDATE projets set id = '" + value1 + "', description = '" + value2 + "', dateDebut = '" + value3 + "', dateFin = '" + value4 + "', idEquipe = '" + value5 + "' where id ='" + value1 + "' ";

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

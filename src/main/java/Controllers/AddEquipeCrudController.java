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

public class AddEquipeCrudController implements Initializable {

    @FXML
    private MFXTextField idTextField;

    @FXML
    private MFXTextField nomTextField;

    @FXML
    private MFXTextField nbrEmpTextField;

    @FXML
    private MFXTextField sommeSalaireTextField;

    @FXML
    private MFXTextField idDepartementTextField;


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

    public MFXTextField getNbrEmpTextField() {
        return nbrEmpTextField;
    }

    public MFXTextField getSommeSalaireTextField() {
        return sommeSalaireTextField;
    }

    public MFXTextField getIdDepartementTextField() {return idDepartementTextField; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void addEquipe(ActionEvent event) {
        EquipeController ConnectNow = new EquipeController();
        Connection connectDB = ConnectNow.getConnetion();

        String query = "INSERT INTO equipe VALUES (" + idTextField.getText() + ",'" + nomTextField.getText() + "','" + nbrEmpTextField.getText() + "'," + sommeSalaireTextField.getText() + "," + idDepartementTextField.getText() + ")";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);
            mStage.close();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void hideAddEquipe() {
        mStage.close();
    }



}

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

public class AddEmployeCrudController implements Initializable {

    @FXML
    private MFXTextField idTextField;

    @FXML
    private MFXTextField nomTextField;

    @FXML
    private MFXTextField prenomTextField;

    @FXML
    private MFXTextField nbrHeureTextField;

    @FXML
    private MFXTextField prixHeureTextField;

    @FXML
    private MFXTextField idEquipeTextField;


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

    public MFXTextField getNbrHeureTextField() {
        return nbrHeureTextField;
    }

    public MFXTextField getPrixHeureTextField() {
        return prixHeureTextField;
    }

    public MFXTextField getIdEquipeTextField() {
        return idEquipeTextField;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void addEmploye(ActionEvent event) {
        EmployeController ConnectNow = new EmployeController();
        Connection connectDB = ConnectNow.getConnetion();

        String query = "INSERT INTO employe VALUES (" + idTextField.getText() + ",'" + nomTextField.getText() + "','" + prenomTextField.getText() + "','" + nbrHeureTextField.getText() + "'," + prixHeureTextField.getText() + "," + idEquipeTextField.getText() + ")";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);
            mStage.close();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void hideAddEmploye() {
        mStage.close();
    }
}

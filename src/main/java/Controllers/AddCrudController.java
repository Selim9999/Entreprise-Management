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

public class AddCrudController implements Initializable {



    @FXML
    private MFXTextField idTextField;

    @FXML
    private MFXTextField nameTextField;

    @FXML
    private MFXTextField directorTextField;

    @FXML
    private MFXTextField budgetTextField;

    @FXML
    private MFXTextField membersTextField;


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

    public MFXTextField getNameTextField() {
        return nameTextField;
    }

    public MFXTextField getDirectorTextField() {
        return directorTextField;
    }

    public MFXTextField getBudgetTextField() {
        return budgetTextField;
    }

    public MFXTextField getMembersTextField() {
        return membersTextField;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    void addDepartment(ActionEvent event) {
        DepartmentsController ConnectNow = new DepartmentsController();
        Connection connectDB = ConnectNow.getConnetion();

        String query = "INSERT INTO departments VALUES (" + idTextField.getText() + ",'" + nameTextField.getText() + "','" + directorTextField.getText() + "','" + budgetTextField.getText() + "'," + membersTextField.getText() + ")";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);
            mStage.close();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void hideAddDepartment() {
        mStage.close();
    }


}

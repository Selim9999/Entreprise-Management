package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EditCrudController implements Initializable {
    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField directorTextField;

    @FXML
    private TextField budgetTextField;

    @FXML
    private TextField membersTextField;

    private Stage mStage;

    public void setStage(Stage mStage) {
        this.mStage = mStage;
        mStage.initStyle(StageStyle.UNDECORATED);
        mStage.setX(1150);
        mStage.setY(350);
    }

    public TextField getIdTextField() {
        return idTextField;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public TextField getDirectorTextField() {
        return directorTextField;
    }

    public TextField getBudgetTextField() {
        return budgetTextField;
    }

    public TextField getMembersTextField() {
        return membersTextField;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }




    @FXML
    void editDepartment(ActionEvent event) {
        DepartmentsController ConnectNow = new DepartmentsController();
        Connection connectDB = ConnectNow.getConnetion();

        String value1 = idTextField.getText();
        String value2 = nameTextField.getText();
        String value3 = directorTextField.getText();
        String value4 = budgetTextField.getText();
        String value5 = membersTextField.getText();

        String query = "UPDATE departments set id = '" + value1 + "', name = '" + value2 + "', director = '" + value3 + "', budget = '" + value4 + "', members = '" + value5 + "' where id ='" + value1 + "' ";

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

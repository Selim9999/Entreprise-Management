package Controllers;


import Models.Departments;
import com.example.employees_management.HelloApplication;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DepartmentsController implements Initializable {

    @FXML
    private TableView<Departments> tableView;

    @FXML
    private TableColumn<Departments, Integer> idTab;

    @FXML
    private TableColumn<Departments, String> nameTab;

    @FXML
    private TableColumn<Departments, String> directorTab;

    @FXML
    private TableColumn<Departments, Integer> budgetTab;

    @FXML
    private TableColumn<Departments, Integer> membersTab;

    javafx.scene.input.MouseEvent mouseEvent;

    int index = -1;






    @FXML
    private MFXTextField searchTextField;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showDepartments();
        //tableView.setStyle("-fx-selection-bar: black; -fx-selection-bar-non-focused: salmon; -fx-selection-text: blue");
    }


    public Connection getConnetion() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/projectapp", "root", "");
            return conn;

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }


    public ObservableList<Departments> getDepartmentsList() {
        ObservableList<Departments> departmentsList = FXCollections.observableArrayList();
        Connection conn = getConnetion();
        String query = "SELECT * FROM departments";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Departments departments;

            while(rs.next()) {
                departments = new Departments(rs.getInt("id"), rs.getString("name"), rs.getString("director"), rs.getInt("budget"), rs.getInt("members"));
                departmentsList.add(departments);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return departmentsList;
    }


    public void showDepartments() {
        ObservableList<Departments> list = getDepartmentsList();
        System.out.println(" got list ");

        try {
            idTab.setCellValueFactory(new PropertyValueFactory<Departments, Integer>("id"));
            nameTab.setCellValueFactory(new PropertyValueFactory<Departments, String>("name"));
            directorTab.setCellValueFactory(new PropertyValueFactory<Departments, String>("director"));
            budgetTab.setCellValueFactory(new PropertyValueFactory<Departments, Integer>("budget"));
            membersTab.setCellValueFactory(new PropertyValueFactory<Departments, Integer>("members"));

            tableView.setItems(list);

        } catch (NullPointerException e) {
            System.out.println(" null pointer exception thrown ");
        }
    }


    public void showAddDepartment() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addCrud.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        AddCrudController addcrudController = fxmlLoader.getController();
        addcrudController.setStage(stage);
        stage.setTitle("Add crud");
        stage.setScene(new Scene(root1));
        stage.show();
    }


    public void refreshDepartment() {
        showDepartments();
    }

    public void handleEdit(ActionEvent actionEvent) throws IOException {
            Stage stage = new Stage();
            getSelected(mouseEvent);
            //tableView.getItems().addAll(tableView.getSelectionModel().getSelectedItems());
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editCrud.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            EditCrudController editcrudController = fxmlLoader.getController();
            editcrudController.setStage(stage);
            editcrudController.getIdTextField().setText(idTab.getCellData(index).toString());
            editcrudController.getNameTextField().setText(nameTab.getCellData(index).toString());
            editcrudController.getDirectorTextField().setText(directorTab.getCellData(index).toString());
            editcrudController.getBudgetTextField().setText(budgetTab.getCellData(index).toString());
            editcrudController.getMembersTextField().setText(membersTab.getCellData(index).toString());
            stage.setTitle("Add crud");
            stage.setScene(new Scene(root1));
            stage.show();
    }


    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        index = tableView.getSelectionModel().getSelectedIndex();
        /*if (index <= 1) {
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addCrud.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        AddCrudController addcrudController = fxmlLoader.getController();
        addcrudController.getIdTextField().setText(idTab.getCellData(index).toString());
        addcrudController.getNameTextField().setText(nameTab.getCellData(index).toString());
        addcrudController.getDirectorTextField().setText(directorTab.getCellData(index).toString());
        addcrudController.getBudgetTextField().setText(budgetTab.getCellData(index).toString());
        addcrudController.getMembersTextField().setText(membersTab.getCellData(index).toString());*/
    }


    public void handleDelete(ActionEvent actionEvent) {
        //tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItems());
        Departments departments = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().removeAll(departments);
        System.out.println(departments.getId());

        Connection conn = getConnetion();
        String query = "DELETE FROM departments where id = '" + departments.getId() + "' "  ;
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }


    public void searchTable() {
        try {
            ObservableList<Departments> list1 = FXCollections.observableArrayList();
            ObservableList<Departments> list = getDepartmentsList();
            Departments departments;
            for (int i=0; i< list.size(); i++) {
                if (list.get(i).getName().equals(searchTextField.getText())) {
                    departments = new Departments(list.get(i).getId(), list.get(i).getName(), list.get(i).getDirector(), list.get(i).getBudget(), list.get(i).getMembers());
                    list1.add(departments);
                }
                else {
                    tableView.setItems(list);
                }
                tableView.setItems(list1);
            }
        } catch (Exception e) {
            System.out.println("null");
        }
    }


    public void saveDepartmentFile() throws IOException {
        ObservableList<Departments> list = getDepartmentsList();
        try {
            FileWriter myWriter = new FileWriter("department.txt");
            for (int i=0;i<list.size(); i++) {
                myWriter.write(String.valueOf(list.get(i).getId()) + "    |    " + list.get(i).getName() + "    |    " + list.get(i).getDirector() + "    |    " + String.valueOf(list.get(i).getBudget()) + "    |    " + String.valueOf(list.get(i).getMembers()));
                myWriter.append('\n');
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }
}

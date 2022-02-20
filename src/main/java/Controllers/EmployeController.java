package Controllers;

import Models.Employe;
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

public class EmployeController implements Initializable {


    @FXML
    private TableView<Employe> tableView;

    @FXML
    private TableColumn<Employe, Integer> idTab;

    @FXML
    private TableColumn<Employe, String> nomTab;

    @FXML
    private TableColumn<Employe, String> prenomTab;

    @FXML
    private TableColumn<Employe, Integer> nbrHeureTab;

    @FXML
    private TableColumn<Employe, Integer> prixHeureTab;

    @FXML
    private TableColumn<Employe, String> idEquipeTab;

    @FXML
    private TableColumn<Employe, Integer> idEquipeTab1;


    javafx.scene.input.MouseEvent mouseEvent;

    int index = -1;




    @FXML
    private MFXTextField searchTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showEmploye();
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


    public ObservableList<Employe> getEmployeList() {
        ObservableList<Employe> employeList = FXCollections.observableArrayList();
        Connection conn = getConnetion();
        String query = "SELECT e.*, eq.nom FROM employe AS e INNER JOIN equipe AS eq ON e.idEquipe = eq.id";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Employe employe;

            while(rs.next()) {
                employe = new Employe(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getInt("nbrHeure"), rs.getInt("prixHeure"), rs.getInt("idEquipe"), rs.getString("eq.nom"));
                employeList.add(employe);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return employeList;
    }


    public void showEmploye() {
        ObservableList<Employe> list = getEmployeList();
        System.out.println(" got list ");

        try {
            idTab.setCellValueFactory(new PropertyValueFactory<Employe, Integer>("id"));
            nomTab.setCellValueFactory(new PropertyValueFactory<Employe, String>("nom"));
            prenomTab.setCellValueFactory(new PropertyValueFactory<Employe, String>("prenom"));
            nbrHeureTab.setCellValueFactory(new PropertyValueFactory<Employe, Integer>("nbrHeure"));
            prixHeureTab.setCellValueFactory(new PropertyValueFactory<Employe, Integer>("prixHeure"));
            idEquipeTab.setCellValueFactory(new PropertyValueFactory<Employe, String>("nom1"));
            idEquipeTab1.setCellValueFactory(new PropertyValueFactory<Employe, Integer>("idEquipe"));

            tableView.setItems(list);
        } catch (NullPointerException e) {
            System.out.println(" null pointer exception thrown ");
        }
    }


    public void showAddEmploye() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addEmployeCrud.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        AddEmployeCrudController addemployecrudController = fxmlLoader.getController();
        addemployecrudController.setStage(stage);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Add crud");
        stage.setScene(new Scene(root1));
        stage.show();
    }


    public void refreshEmploye() {
        showEmploye();
    }


    public void handleEdit(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        getSelected(mouseEvent);
        //tableView.getItems().addAll(tableView.getSelectionModel().getSelectedItems());
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editEmployeCrud.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        EditEmployeCrudController editemployecrudController = fxmlLoader.getController();
        editemployecrudController.setStage(stage);
        editemployecrudController.getIdTextField().setText(idTab.getCellData(index).toString());
        editemployecrudController.getNomTextField().setText(nomTab.getCellData(index).toString());
        editemployecrudController.getPrenomTextField().setText(prenomTab.getCellData(index).toString());
        editemployecrudController.getNbrHeureTextField().setText(nbrHeureTab.getCellData(index).toString());
        editemployecrudController.getPrixHeureTextField().setText(prixHeureTab.getCellData(index).toString());
        editemployecrudController.getIdEquipeTextField().setText(idEquipeTab1.getCellData(index).toString());
        stage.setTitle("Add crud");
        stage.setScene(new Scene(root1));
        stage.show();
    }


    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        index = tableView.getSelectionModel().getSelectedIndex();
    }


    public void handleDelete(ActionEvent actionEvent) {
        //tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItems());
        Employe employe = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().removeAll(employe);
        System.out.println(employe.getId());

        Connection conn = getConnetion();
        String query = "DELETE FROM employe where id = '" + employe.getId() + "' "  ;
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
            ObservableList<Employe> list1 = FXCollections.observableArrayList();
            ObservableList<Employe> list = getEmployeList();
            Employe employe;
            for (int i=0; i< list.size(); i++) {
                if (list.get(i).getNom().equals(searchTextField.getText())) {
                    employe = new Employe(list.get(i).getId(), list.get(i).getNom(), list.get(i).getPrenom(), list.get(i).getNbrHeure(), list.get(i).getPrixHeure(), list.get(i).getIdEquipe(), list.get(i).getNom1());
                    list1.add(employe);
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


    public void saveEmployeFile() throws IOException {
        ObservableList<Employe> list = getEmployeList();
        try {
            FileWriter myWriter = new FileWriter("employe.txt");
            for (int i=0;i<list.size(); i++) {
                myWriter.write(String.valueOf(list.get(i).getId()) + "    |    " + list.get(i).getNom() + "    |    " + list.get(i).getPrenom() + "    |    " + String.valueOf(list.get(i).getNbrHeure()) + "    |    " + String.valueOf(list.get(i).getPrixHeure()) + "    |    " + list.get(i).getNom1());
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

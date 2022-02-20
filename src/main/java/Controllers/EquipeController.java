package Controllers;

import Models.Equipe;
import com.example.employees_management.HelloApplication;
import io.github.palexdev.materialfx.controls.MFXButton;
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

public class EquipeController implements Initializable {

    @FXML
    private MFXTextField searchTextField;

    @FXML
    private MFXButton btnAddDepartmeent;

    public TableView<Equipe> getTableView() {
        return tableView;
    }

    @FXML
    private TableView<Equipe> tableView;

    @FXML
    private TableColumn<Equipe, Integer> idTab;

    @FXML
    private TableColumn<Equipe, String> nomTab;

    @FXML
    private TableColumn<Equipe, Integer> nbrEmpTab;

    @FXML
    private TableColumn<Equipe, Integer> sommeSalaireTab;

    public TableColumn<Equipe, Integer> getIdTab() {
        return idTab;
    }

    public TableColumn<Equipe, String> getNomTab() {
        return nomTab;
    }

    public TableColumn<Equipe, Integer> getNbrEmpTab() {
        return nbrEmpTab;
    }

    public TableColumn<Equipe, Integer> getSommeSalaireTab() {
        return sommeSalaireTab;
    }

    public TableColumn<Equipe, String> getIdDepartementTab() {
        return idDepartementTab;
    }

    public TableColumn<Equipe, Integer> getIdDepartementTab1() {
        return idDepartementTab1;
    }

    @FXML
    private TableColumn<Equipe, String> idDepartementTab;

    @FXML
    private TableColumn<Equipe, Integer> idDepartementTab1;

    javafx.scene.input.MouseEvent mouseEvent;

    int index = -1;



    @FXML
    private MFXButton btnDelete;

    @FXML
    private MFXButton btnEdit;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showEquipe();
        //idDepartementTab1.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("idDepartement"));
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


    public ObservableList<Equipe> getEquipeList() {
        ObservableList<Equipe> equipeList = FXCollections.observableArrayList();
        Connection conn = getConnetion();
        String query = "SELECT e.*, d.name FROM equipe AS e INNER JOIN departments AS d ON e.idDepartement = d.id";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Equipe equipe;

            while(rs.next()) {
                equipe = new Equipe(rs.getInt("id"), rs.getString("nom"), rs.getInt("nbrEmp"), rs.getInt("sumSalaire"),rs.getInt("idDepartement"), rs.getString("name"));
                equipeList.add(equipe);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return equipeList;
    }


    public void showEquipe() {
        ObservableList<Equipe> list = getEquipeList();
        for (int i =0; i<list.size(); i++) {
            System.out.println(list.get(i).toString());
        }

        try {
            idTab.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("id"));
            nomTab.setCellValueFactory(new PropertyValueFactory<Equipe, String>("nom"));
            nbrEmpTab.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("nbrEmp"));
            sommeSalaireTab.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("sumSalaire"));
            idDepartementTab.setCellValueFactory(new PropertyValueFactory<Equipe, String>("name"));
            idDepartementTab1.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("idDepartement"));


            tableView.setItems(list);
        } catch (NullPointerException e) {
            System.out.println(" null pointer exception thrown ");
        }
    }



    public void showAddEquipe() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addEquipeCrud.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        AddEquipeCrudController addequipecrudController = fxmlLoader.getController();
        addequipecrudController.setStage(stage);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Add crud");
        stage.setScene(new Scene(root1));
        stage.show();
    }


    public void refreshEquipe() {
        showEquipe();
    }


    public void handleEdit(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        getSelected(mouseEvent);
        //tableView.getItems().addAll(tableView.getSelectionModel().getSelectedItems());
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editEquipeCrud.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        EditEquipeCrudController editequipecrudController = fxmlLoader.getController();
        editequipecrudController.setStage(stage);
        editequipecrudController.getIdTextField().setText(idTab.getCellData(index).toString());
        editequipecrudController.getNomTextField().setText(nomTab.getCellData(index).toString());
        editequipecrudController.getNbrEmpTextField().setText(nbrEmpTab.getCellData(index).toString());
        editequipecrudController.getSommeSalaireTextField().setText(sommeSalaireTab.getCellData(index).toString());
        editequipecrudController.getIdDepartementTextField().setText(idDepartementTab1.getCellData(index).toString());

        stage.setTitle("Add crud");
        stage.setScene(new Scene(root1));
        stage.show();
    }


    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        index = tableView.getSelectionModel().getSelectedIndex();
       /* if (index <= 1) {
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addEquipeCrud.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        AddEquipeCrudController addequipecrudController = fxmlLoader.getController();
        addequipecrudController.getIdTextField().setText(idTab.getCellData(index).toString());
        addequipecrudController.getNomTextField().setText(nomTab.getCellData(index).toString());
        addequipecrudController.getNbrEmpTextField().setText(nbrEmpTab.getCellData(index).toString());
        addequipecrudController.getSommeSalaireTextField().setText(sommeSalaireTab.getCellData(index).toString());
        addequipecrudController.getIdDepartementTextField().setText(idDepartementTab.getCellData(index).toString());*/
    }


    public void handleDelete(ActionEvent actionEvent) {
        //tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItems());
        Equipe equipe = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().removeAll(equipe);
        System.out.println(equipe.getId());

        Connection conn = getConnetion();
        String query = "DELETE FROM equipe where id = '" + equipe.getId() + "' "  ;
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
            ObservableList<Equipe> list1 = FXCollections.observableArrayList();
            ObservableList<Equipe> list = getEquipeList();
            Equipe equipe;
            for (int i=0; i< list.size(); i++) {
                if (list.get(i).getNom().equals(searchTextField.getText())) {
                    equipe = new Equipe(list.get(i).getId(), list.get(i).getNom(), list.get(i).getNbrEmp(), list.get(i).getSumSalaire(), list.get(i).getIdDepartement(), list.get(i).getName());
                    list1.add(equipe);
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


    public void saveEquipeFile() throws IOException {
        ObservableList<Equipe> list = getEquipeList();
        try {
            FileWriter myWriter = new FileWriter("equipe.txt");
            for (int i=0;i<list.size(); i++) {
                myWriter.write(String.valueOf(list.get(i).getId()) + "    |    " + list.get(i).getNom() + "    |    " + String.valueOf(list.get(i).getNbrEmp()) + "    |    " + String.valueOf(list.get(i).getSumSalaire()) + "    |    " + list.get(i).getName());
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




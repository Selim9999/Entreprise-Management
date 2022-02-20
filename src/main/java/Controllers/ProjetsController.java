package Controllers;

import Models.Projets;
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
import java.util.Date;
import java.util.ResourceBundle;

public class ProjetsController implements Initializable {
    @FXML
    private MFXTextField searchTextField;

    @FXML
    private MFXButton btnAddDepartmeent;

    @FXML
    private TableView<Projets> tableView;

    @FXML
    private TableColumn<Projets, Integer> idTab;

    @FXML
    private TableColumn<Projets, String> descriptionTab;

    @FXML
    private TableColumn<Projets, Date> dateDebutTab;

    @FXML
    private TableColumn<Projets, Date> dateFinTab;

    @FXML
    private TableColumn<Projets, String> idEquipeTab;

    @FXML
    private TableColumn<Projets, Integer> idEquipeTab1;

    javafx.scene.input.MouseEvent mouseEvent;

    int index = -1;



    @FXML
    private MFXButton btnDelete;

    @FXML
    private MFXButton btnEdit;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showProjets();
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


    public ObservableList<Projets> getProjetsList() {
        ObservableList<Projets> projetsList = FXCollections.observableArrayList();
        Connection conn = getConnetion();
        String query = "SELECT p.*, e.nom FROM projets as p INNER JOIN equipe AS e ON p.idEquipe = e.id";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Projets projets;

            while(rs.next()) {
                projets = new Projets(rs.getInt("id"), rs.getString("description"), rs.getDate("dateDebut"), rs.getDate("dateFin"), rs.getInt("idEquipe"), rs.getString("nom"));
                projetsList.add(projets);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return projetsList;
    }


    public void showProjets() {
        ObservableList<Projets> list = getProjetsList();
        System.out.println(" got list ");

        try {
            idTab.setCellValueFactory(new PropertyValueFactory<Projets, Integer>("id"));
            descriptionTab.setCellValueFactory(new PropertyValueFactory<Projets, String>("description"));
            dateDebutTab.setCellValueFactory(new PropertyValueFactory<Projets, Date>("dateDebut"));
            dateFinTab.setCellValueFactory(new PropertyValueFactory<Projets, Date>("dateFin"));
            idEquipeTab.setCellValueFactory(new PropertyValueFactory<Projets, String>("nom"));
            idEquipeTab1.setCellValueFactory(new PropertyValueFactory<Projets, Integer>("idEquipe"));


            tableView.setItems(list);
        } catch (NullPointerException e) {
            System.out.println(" null pointer exception thrown ");
        }
    }


    public void showAddProjets() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addProjetsCrud.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        AddProjetsCrudController addprojetscrudController = fxmlLoader.getController();
        addprojetscrudController.setStage(stage);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Add crud");
        stage.setScene(new Scene(root1));
        stage.show();
    }


    public void refreshProjets() {
        showProjets();
    }


    public void handleEdit(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        getSelected(mouseEvent);
        //tableView.getItems().addAll(tableView.getSelectionModel().getSelectedItems());
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editProjetsCrud.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        EditProjetsCrudController editprojetscrudController = fxmlLoader.getController();
        editprojetscrudController.setStage(stage);
        editprojetscrudController.getIdTextField().setText(idTab.getCellData(index).toString());
        editprojetscrudController.getDescriptionTextField().setText(descriptionTab.getCellData(index).toString());
        //editprojetscrudController.getDateDebutDatePicker().setDateFormatter(DateTimeFormatter.ofPattern(dateDebutTab.getCellData(index).toString()));
        //editprojetscrudController.getDateDebutDatePicker().setDateFormatter(DateTimeFormatter.ofPattern(dateFinTab.getCellData(index).toString()));
        editprojetscrudController.getIdEquipeTextField().setText(idEquipeTab1.getCellData(index).toString());
        stage.setTitle("Add crud");
        stage.setScene(new Scene(root1));
        stage.show();
    }


    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        index = tableView.getSelectionModel().getSelectedIndex();
       /* if (index <= 1) {
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addProjetsCrud.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        AddProjetsCrudController addprojetscrudController = fxmlLoader.getController();
        addprojetscrudController.getIdTextField().setText(idTab.getCellData(index).toString());
        addprojetscrudController.getDescriptionTextField().setText(descriptionTab.getCellData(index).toString());
        //addprojetscrudController.getDateDebutDatePicker().setDateFormatter(DateTimeFormatter.ofPattern(dateDebutTab.getCellData(index).toString()));
        //addprojetscrudController.getDateFinDatePicker().setDateFormatter(DateTimeFormatter.ofPattern(dateFinTab.getCellData(index).toString()));
        addprojetscrudController.getIdEquipeTextField().setText(idEquipeTab.getCellData(index).toString());*/
    }

    public void handleDelete(ActionEvent actionEvent) {
        //tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItems());
        Projets projets = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().removeAll(projets);
        System.out.println(projets.getId());

        Connection conn = getConnetion();
        String query = "DELETE FROM projets where id = '" + projets.getId() + "' "  ;
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
            ObservableList<Projets> list1 = FXCollections.observableArrayList();
            ObservableList<Projets> list = getProjetsList();
            Projets projets;
            for (int i=0; i< list.size(); i++) {
                if (String.valueOf(list.get(i).getId()).equals(searchTextField.getText())) {
                    projets = new Projets(list.get(i).getId(), list.get(i).getDescription(), list.get(i).getDateDebut(), list.get(i).getDateFin(), list.get(i).getIdEquipe(), list.get(i).getNom());
                    list1.add(projets);
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


    public void saveProjetsFile() throws IOException {
        ObservableList<Projets> list = getProjetsList();
        try {
            FileWriter myWriter = new FileWriter("projets.txt");
            for (int i=0;i<list.size(); i++) {
                myWriter.write(String.valueOf(list.get(i).getId()) + "    |    " + list.get(i).getDescription() + "    |    " + String.valueOf(list.get(i).getDateDebut()) + "    |    " + String.valueOf(list.get(i).getDateFin()) + "    |    " + list.get(i).getNom());
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

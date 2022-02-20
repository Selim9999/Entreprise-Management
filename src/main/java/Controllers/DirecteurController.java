package Controllers;

import Models.Directeur;
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

public class DirecteurController implements Initializable {

    @FXML
    private TableView<Directeur> tableView;

    @FXML
    private TableColumn<Directeur, Integer> idTab;

    @FXML
    private TableColumn<Directeur, String> nomTab;

    @FXML
    private TableColumn<Directeur, String> prenomTab;

    @FXML
    private TableColumn<Directeur, Float> salaireTab;

    @FXML
    private TableColumn<Directeur, Float> primeTab;


    javafx.scene.input.MouseEvent mouseEvent;

    int index = -1;


    @FXML
    private MFXTextField searchTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showDirecteur();

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


    public ObservableList<Directeur> getDirecteurList() {
        ObservableList<Directeur> directeurList = FXCollections.observableArrayList();
        Connection conn = getConnetion();
        String query = "SELECT * FROM directeur";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Directeur directeur;

            while(rs.next()) {
                directeur = new Directeur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getFloat("salaire"), rs.getFloat("prime"));
                directeurList.add(directeur);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return directeurList;
    }


    public void showDirecteur() {
        ObservableList<Directeur> list = getDirecteurList();
        System.out.println(" got list ");

        try {
            idTab.setCellValueFactory(new PropertyValueFactory<Directeur, Integer>("id"));
            nomTab.setCellValueFactory(new PropertyValueFactory<Directeur, String>("nom"));
            prenomTab.setCellValueFactory(new PropertyValueFactory<Directeur, String>("prenom"));
            salaireTab.setCellValueFactory(new PropertyValueFactory<Directeur, Float>("salaire"));
            primeTab.setCellValueFactory(new PropertyValueFactory<Directeur, Float>("prime"));

            tableView.setItems(list);
        } catch (NullPointerException e) {
            System.out.println(" null pointer exception thrown ");
        }
    }


    public void showAddDirecteur() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addDirecteurCrud.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        AddDirecteurCrudController adddirecteurcrudController = fxmlLoader.getController();
        adddirecteurcrudController.setStage(stage);
        stage.setTitle("Add crud");
        stage.setScene(new Scene(root1));
        stage.show();
    }


    public void refreshDirecteur() {
        showDirecteur();
    }


    public void handleEdit(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        getSelected(mouseEvent);
        //tableView.getItems().addAll(tableView.getSelectionModel().getSelectedItems());
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editDirecteurCrud.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        EditDirecteurCrudController editdirecteurcrudController = fxmlLoader.getController();
        editdirecteurcrudController.setStage(stage);
        editdirecteurcrudController.getIdTextField().setText(idTab.getCellData(index).toString());
        editdirecteurcrudController.getNomTextField().setText(nomTab.getCellData(index).toString());
        editdirecteurcrudController.getPrenomTextField().setText(prenomTab.getCellData(index).toString());
        editdirecteurcrudController.getSalaireTextField().setText(salaireTab.getCellData(index).toString());
        editdirecteurcrudController.getPrimeTextField().setText(primeTab.getCellData(index).toString());
        stage.setTitle("Add crud");
        stage.setScene(new Scene(root1));
        stage.show();
    }


    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        index = tableView.getSelectionModel().getSelectedIndex();
    }


    public void handleDelete(ActionEvent actionEvent) {
        //tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItems());
        Directeur directeur = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().removeAll(directeur);
        System.out.println(directeur.getId());

        Connection conn = getConnetion();
        String query = "DELETE FROM directeur where id = '" + directeur.getId() + "' "  ;
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
            ObservableList<Directeur> list1 = FXCollections.observableArrayList();
            ObservableList<Directeur> list = getDirecteurList();
            Directeur directeur;
            for (int i=0; i< list.size(); i++) {
                if (list.get(i).getNom().indexOf(searchTextField.getText())>= 0) {
                    directeur = new Directeur(list.get(i).getId(), list.get(i).getNom(), list.get(i).getPrenom(), list.get(i).getSalaire(), list.get(i).getPrime());
                    list1.add(directeur);
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


    public void saveDirecteurFile() throws IOException {
        ObservableList<Directeur> list = getDirecteurList();
        try {
            FileWriter myWriter = new FileWriter("directeur.txt");
            for (int i=0;i<list.size(); i++) {
                myWriter.write(String.valueOf(list.get(i).getId()) + "    |    " + list.get(i).getNom() + "    |    " + list.get(i).getPrenom() + "    |    " + String.valueOf(list.get(i).getPrime()) + "    |    " + String.valueOf(list.get(i).getSalaire()));
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

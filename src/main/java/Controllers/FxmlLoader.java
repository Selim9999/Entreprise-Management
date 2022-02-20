package Controllers;

import com.example.employees_management.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;

public class FxmlLoader {
    public AnchorPane view;



    public Pane getPage(String filename) {
        try {
            URL fileURL = HelloApplication.class.getResource(filename);
            if (fileURL == null) {
                throw new java.io.FileNotFoundException("FXML file can't be found");
            }

            view = new FXMLLoader().load(fileURL);
        } catch (Exception e) {
            System.out.println("No page" + filename + "Please check FxmlLoader");
        }
        return view;
    }
}

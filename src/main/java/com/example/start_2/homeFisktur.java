package com.example.start_2;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class homeFisktur implements Initializable {
    @FXML private Button geriButton;
    @FXML private TableView<fisktür> tableview;
    @FXML private TableColumn<fisktür,String> idCol;
    @FXML private TableColumn<fisktür,String>evCol;
    @FXML private TableColumn<fisktür,String>konukCol;
    @FXML private TableColumn<fisktür,String>yerCol;
    ObservableList<fisktür> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     geriButton.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
             try {
                 Parent root=loader.load();
                 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 Scene scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
             } catch (IOException e) {
                 e.printStackTrace();
             }


         }
     });

        DBConnection conn=new DBConnection();

        try
        {      Connection con=conn.connDB();
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM stadyum.fisktür");
            while (rs.next()){
                oblist.add(new fisktür(rs.getString("idfisktür"), rs.getString("evsahibi"), rs.getString("konuk"), rs.getString("tarih")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        idCol.setCellValueFactory(new PropertyValueFactory<>("idfisktür"));
        evCol.setCellValueFactory(new PropertyValueFactory<>("evsahibi"));
        konukCol.setCellValueFactory(new PropertyValueFactory<>("konuk"));
        yerCol.setCellValueFactory(new PropertyValueFactory<>("tarih"));
        tableview.setItems(oblist);

    }

}

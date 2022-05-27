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

public class puanController implements Initializable {

    @FXML
    private Button geriButton;

    @FXML
    private TableColumn<puanTablo,String> beraberlikCol;

    @FXML
    private TableColumn<puanTablo,String> yenilenCol;

    @FXML
    private TableColumn<puanTablo,String> idCol;

    @FXML
    private TableColumn<puanTablo,String> puanCol;

    @FXML
    private TableColumn<puanTablo,String> atilanCol;
    @FXML
    private TableColumn<puanTablo,String> averajCol;
    @FXML
    private TableColumn<puanTablo,String> galibiyetCol;

    @FXML
    private TableView<puanTablo> tableview;

    @FXML
    private TableColumn<puanTablo,String> takimCol;

    @FXML
    private TableColumn<puanTablo,String> maglubiyetCol;

    @FXML
    private TableColumn<puanTablo,String>oynananCol;
    ObservableList<puanTablo> oblist = FXCollections.observableArrayList();
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
            ResultSet rs= st.executeQuery("SELECT * FROM stadyum.puantablosu");
            while (rs.next()){
                oblist.add(new puanTablo(rs.getString("idp"), rs.getString("takim"), rs.getString("oynanan"), rs.getString("galibiyet"), rs.getString("beraber"), rs.getString("maglup"), rs.getString("a"), rs.getString("y"), rs.getString("averaj"), rs.getString("puan")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        idCol.setCellValueFactory(new PropertyValueFactory<>("idp"));
        takimCol.setCellValueFactory(new PropertyValueFactory<>("takim"));
        oynananCol.setCellValueFactory(new PropertyValueFactory<>("oynanan"));
        galibiyetCol.setCellValueFactory(new PropertyValueFactory<>("galibiyet"));
        beraberlikCol.setCellValueFactory(new PropertyValueFactory<>("beraber"));
        maglubiyetCol.setCellValueFactory(new PropertyValueFactory<>("maglup"));
        atilanCol.setCellValueFactory(new PropertyValueFactory<>("a"));
        yenilenCol.setCellValueFactory(new PropertyValueFactory<>("y"));
        averajCol.setCellValueFactory(new PropertyValueFactory<>("averaj"));
        puanCol.setCellValueFactory(new PropertyValueFactory<>("puan"));
        tableview.setItems(oblist);

    }
}

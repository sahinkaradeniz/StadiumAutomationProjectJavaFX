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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class adminFiskturController implements Initializable {
    @FXML private Button geriButton,ekle,sil,yenile;
    @FXML private TextField id,evsahibi,konuk,tarih;
    @FXML private TableView<fisktür> tableview;
    @FXML private TableColumn<fisktür,String> idCol;
    @FXML private TableColumn<fisktür,String>evCol;
    @FXML private TableColumn<fisktür,String>konukCol;
    @FXML private TableColumn<fisktür,String>yerCol;
    ObservableList<fisktür> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        sil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Connection connect =null;
                DBConnection baglanti=new DBConnection();
                PreparedStatement statement=null;

                try {
                    connect=baglanti.connDB();
                    String sql="delete from stadyum.fisktür where idfisktür=?";
                    statement=connect.prepareStatement(sql);
                    statement.setInt(1, Integer.parseInt(id.getText()));
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"'"+id.getText()+"' no lu ID ye sahip müsabaka silinmiştir.");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("adminFisktur.fxml"));
                    try {
                        Parent root = loader.load();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    baglanti.Showeror(e);
                }

            }
        });


        ekle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Connection connect =null;
                DBConnection baglanti=new DBConnection();
                PreparedStatement statement=null;

                try {
                    connect=baglanti.connDB();
                    String sql="insert into stadyum.fisktür(evsahibi,konuk,tarih)"
                            +"VALUES(?,?,?)";
                    statement=connect.prepareStatement(sql);
                    statement.setString(1,evsahibi.getText());
                    statement.setString(2,konuk.getText());
                    statement.setString(3,tarih.getText());
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Müsabaka Eklendi");


                    FXMLLoader loader = new FXMLLoader(getClass().getResource("adminFisktur.fxml"));
                    try {
                        Parent root = loader.load();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (SQLException e) {
                    baglanti.Showeror(e);
                }


            }
        });





        geriButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("homeAdmin.fxml"));
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

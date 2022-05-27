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

public class adminTalepController implements Initializable {
    @FXML
    private TableView<talep> tableview;
    @FXML
    private Button geriButton;

    @FXML
    private TableColumn<talep,String> adiCol;

    @FXML
    private TableColumn<talep,String>idCol;

    @FXML
    private Button sil;

    @FXML
    private TableColumn<talep,String>talebiCol;

    @FXML
    private TextField id;

    @FXML
    private TableColumn<talep,String>SoyadiCol;
    ObservableList<talep> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        sil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Connection connect =null;
                DBConnection baglanti=new DBConnection();
                PreparedStatement statement=null;

                try {
                    connect=baglanti.connDB();
                    String sql="delete from stadyum.talepler where idtalepler=?";
                    statement=connect.prepareStatement(sql);
                    statement.setInt(1, Integer.parseInt(id.getText()));
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"'"+id.getText()+"' no lu ID ye ait talep silinmiştir.");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("talepGoruntule.fxml"));
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
        DBConnection conn=new DBConnection();

        try
        {      Connection con=conn.connDB();
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM stadyum.talepler");
            while (rs.next()){
                oblist.add(new talep(rs.getString("idtalepler"), rs.getString("ad"), rs.getString("soyad"), rs.getString("talep")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        idCol.setCellValueFactory(new PropertyValueFactory<>("idtalepler"));
        adiCol.setCellValueFactory(new PropertyValueFactory<>("ad"));
        SoyadiCol.setCellValueFactory(new PropertyValueFactory<>("soyad"));
        talebiCol.setCellValueFactory(new PropertyValueFactory<>("talep"));
        tableview.setItems(oblist);


    }
}

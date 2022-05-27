package com.example.start_2;

import Database.DBConnection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class talepControllerHome implements Initializable {
    @FXML
    private javafx.scene.control.Button gonder,geriButton;
    @FXML
    TextArea talepText;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        geriButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                try {
                    Parent root = loader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        gonder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Connection connect =null;
                DBConnection baglanti=new DBConnection();
                PreparedStatement statement=null;

                try {
                    connect=baglanti.connDB();
                    String sql="insert into stadyum.talepler(ad,soyad,talep)"
                            +"VALUES(?,?,?)";
                    statement=connect.prepareStatement(sql);
                    statement.setString(1,homePageController.kullanici.getAd());
                    statement.setString(2,homePageController.kullanici.getSoyad());
                    statement.setString(3,talepText.getText());
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Talebiniz iletildi ekibimiz en kısa süre içerisinde dönecektir.\n iyi günler dileriz. ");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("talepYaz.fxml"));
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

    }
}

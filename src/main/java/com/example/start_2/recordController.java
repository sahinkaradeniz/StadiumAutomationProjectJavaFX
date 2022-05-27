package com.example.start_2;

import Database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class recordController {

    Stage stage;
    Scene scene;
    Parent root;

    @FXML
    private TextField txttakim;

    @FXML
    private TextField txttc;

    @FXML
    private Label labeluye;

    @FXML
    private TextField txttel;

    @FXML
    private TextField txtad;

    @FXML
    private TextField txtmail;

    @FXML
    private Button kayitgeri;

    @FXML
    private TextField txtsoyad;

    @FXML
    private PasswordField txtsifre;

    @FXML
    private Button kayittamamlama,closeRecord;

    public  void Insert(ActionEvent event) throws SQLException, IOException {
        Connection connect =null;
        DBConnection baglanti=new DBConnection();
        PreparedStatement statement=null;
        try {
            connect=baglanti.connDB();
            String sql="insert into stadyum.kullanicibilgileri(tcno,ad,soyad,tel,emal,sifre)"
                    +"VALUES(?,?,?,?,?,?)";
            statement=connect.prepareStatement(sql);
            statement.setString(1,txttc.getText());
            statement.setString(2,txtad.getText());
            statement.setString(3,txtsoyad.getText());
            statement.setString(4,txttel.getText());
            statement.setString(5,txtmail.getText());
            statement.setString(6,txtsifre.getText());
            statement.executeUpdate();
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            JOptionPane.showMessageDialog(null,"Tebrikler kayıt işlemleriniz gerçekleşti. " +
                    "TC no ve şifreniz ile giriş yapabilirsiniz.");

        }catch (SQLException exception){
            baglanti.Showeror(exception);
        }
        finally {
            statement.close();
            connect.close();
        }
    }


    public void switchToScene1(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("giriş sayfası açılırken bir sorun oluştu '"+e+"'");

        }
    }


}

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.start_2.homePageController.kullanici;

public class bController implements Initializable {
    @FXML
    TextField adBilgilerim;
    @FXML
    private Button geriButtonBilgilerim;
    @FXML
    private TextField telBilgilerim;
    @FXML
    private Button güncelleButonBilgilerim;
    @FXML
    private TextField soyadBilgilerim;
    @FXML
    private TextField takimBilgilerim;
    @FXML
    private TextField emailBilgilerim,tcBilgilerim;
    @FXML
    private TableView<bController> homeMaclarTable;
    @FXML
    private TableColumn<bController,String>evCol;
    @FXML
    private TableColumn<bController,String>konukCol;
    @FXML
    private TableColumn<bController,String>yerCol;



    public TextField getAdBilgilerim() {
        return adBilgilerim;
    }

    public TextField getTcBilgilerim() {
        return tcBilgilerim;
    }

    public void setTcBilgilerim(TextField tcBilgilerim) {
        this.tcBilgilerim = tcBilgilerim;
    }

    public void setAdBilgilerim(TextField adBilgilerim) {
        this.adBilgilerim = adBilgilerim;
    }

    public Button getGerButtonBilgilerim() {
        return geriButtonBilgilerim;
    }

    public void setGerButtonBilgilerim(Button gerButtonBilgilerim) {
        this.geriButtonBilgilerim = gerButtonBilgilerim;
    }

    public TextField getTelBilgilerim() {
        return telBilgilerim;
    }

    public void setTelBilgilerim(TextField telBilgilerim) {
        this.telBilgilerim = telBilgilerim;
    }

    public Button getGüncelleButonBilgilerim() {
        return güncelleButonBilgilerim;
    }

    public void setGüncelleButonBilgilerim(Button güncelleButonBilgilerim) {
        this.güncelleButonBilgilerim = güncelleButonBilgilerim;
    }

    public TextField getSoyadBilgilerim() {
        return soyadBilgilerim;
    }

    public void setSoyadBilgilerim(TextField soyadBilgilerim) {
        this.soyadBilgilerim = soyadBilgilerim;
    }

    public TextField getTakimBilgilerim() {
        return takimBilgilerim;
    }

    public void setTakimBilgilerim(TextField takimBilgilerim) {
        this.takimBilgilerim = takimBilgilerim;
    }

    public TextField getEmailBilgilerim() {
        return emailBilgilerim;
    }

    public void setEmailBilgilerim(TextField emailBilgilerim) {
        this.emailBilgilerim = emailBilgilerim;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        geriButtonBilgilerim.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                try {
                    Parent root = loader.load();
                    homePageController homepagecontroller = loader.getController();
                    homepagecontroller.setDeneme("Hoşgeldiniz "+kullanici.getAd()+" "+kullanici.getSoyad());
                    homepagecontroller.homeTabloYazdir();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    System.out.println("Ana sayfaya dönüldü");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        güncelleButonBilgilerim.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Connection connect =null;
                DBConnection baglanti=new DBConnection();
                PreparedStatement statement=null;


                try {
                    connect=baglanti.connDB();
                    String sql="UPDATE stadyum.kullanicibilgileri SET tcno=?,ad=?,soyad=?,tel=?,emal=?,bakiye=? where id=?";
                    statement=connect.prepareStatement(sql);
                    statement.setString(1,tcBilgilerim.getText());
                    statement.setString(2,adBilgilerim.getText());
                    statement.setString(3,soyadBilgilerim.getText());
                    statement.setString(4,telBilgilerim.getText());
                    statement.setString(5,emailBilgilerim.getText());
                    statement.setInt(6, Integer.parseInt(takimBilgilerim.getText()));
                    statement.setInt(7,kullanici.getId());
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Bilgileriniz başarı ile güncellendi.");

                } catch (SQLException exception) {
                    baglanti.Showeror(exception);
                }


            }
        });




    }


    public void setAdyazdir(String text){
        adBilgilerim.setText(text);
    }
    public void setSoyadyazdir(String text){
        soyadBilgilerim.setText(text);
    }
    public void setTcyazdir(String text){
        tcBilgilerim.setText(text);
    }
    public void setTelyazdir(String text){
       telBilgilerim.setText(text);
    }
    public void setTakimyazdir(int bakiye){
        takimBilgilerim.setText(String.valueOf(bakiye));
    }
    public void setMailyazdir(String text){
        emailBilgilerim.setText(text);
    }


    Stage stage;
    Scene scene;
    Parent root;

    public void switchToSceneHome(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Kayit sayfası açılırken bir sorun oluştu '"+e+"'");

        }
    }












}

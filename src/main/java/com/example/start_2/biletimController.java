package com.example.start_2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.start_2.homePageController.kullanici;

public class biletimController implements Initializable {
    @FXML
    private Label yerBilet;

    @FXML
    private Label müsabakaBilet,konukBilet;

    @FXML
    private Label tarihBilet;

    @FXML
    private Button geriButtonBilet;

    @FXML
    private Label soyadBilet;

    @FXML
    private Label adiBilet;

    public biletimController(Label yerBilet, Label müsabakaBilet, Label tarihBilet, Button geriButtonBilet, Label soyadBilet, Label adiBilet) {
        this.yerBilet = yerBilet;
        this.müsabakaBilet = müsabakaBilet;
        this.tarihBilet = tarihBilet;
        this.geriButtonBilet = geriButtonBilet;
        this.soyadBilet = soyadBilet;
        this.adiBilet = adiBilet;
    }
    public biletimController(){}
    public void adYazdir(String text){
        adiBilet.setText(text);
    }
    public void soyadYazdir(String text){
        soyadBilet.setText(text);
    }
    public void yerYazdir(String text){
        yerBilet.setText(text);
    }
    public void evYazdir(String text){
        müsabakaBilet.setText(text);
    }
    public void konukYazdir(String text){
        konukBilet.setText(text);
    }
    public void tarihYazdir(String text){
        tarihBilet.setText(text);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        geriButtonBilet.setOnAction(new EventHandler<ActionEvent>() {
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

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}

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
import javafx.scene.control.Label;
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
public class bakiyeController implements Initializable {
    @FXML
    private Button bakiyeYukle;
    @FXML
    private TextField adBilgilerim111;
    @FXML
    private Label bakiyeLabel;
    @FXML
    private TextField yuklemeTutar;
    @FXML
    private Button back;
    @FXML
    private TextField tcBilgilerim;
    @FXML
    private TextField adBilgilerim11;
    @FXML
    private TextField adBilgilerim1;
    @FXML
    private Label bakiye;
  //  static  User kullanici = new User();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bakiyeYukle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBConnection baglanti = new DBConnection();
                Connection connect =null;
                PreparedStatement statement=null;
                if (tcBilgilerim.getText().length() == 0 || yuklemeTutar.getText().length() == 0) {
                    bakiyeLabel.setText("Lütfen tüm alanları doldurunuz.");
                    JOptionPane.showMessageDialog(null,"Lütfen tüm alanları doldurunuz");

                }else{
                    connect=baglanti.connDB();
                    try { String sql="UPDATE stadyum.kullanicibilgileri SET tcno=?,bakiye=? where id=?";
                        int a=Integer.valueOf(yuklemeTutar.getText());
                        // int ifade1 = Integer.valueOf(ifade
                        a=a+Integer.valueOf(kullanici.getBakiye());
                        kullanici.setBakiye(a);
                        statement=connect.prepareStatement(sql);
                        statement.setString(1,tcBilgilerim.getText());
                        statement.setString(2, String.valueOf(kullanici.getBakiye()));
                        statement.setInt(3,kullanici.getId());
                        statement.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Bakiye yükleme işleminiz başarı ile gerçekleştirilmiştir. " +
                                "Yüklenen Tutar " +
                                ""+yuklemeTutar.getText()+" TL");
                        homePageController.kullanici.setBakiye(a);
                     /*   String guncelBakiye=homePageController.kullanici.getBakiye();
                        guncelBakiye=guncelBakiye+yuklemeTutar.getText();
                        homePageController.kullanici.setBakiye(guncelBakiye);
                        kullanici.setBakiye(guncelBakiye);
                        biletController.kullanici.setBakiye(guncelBakiye);
                        homePageController.kullanici.setBakiye(a);
                        kullanici.setBakiye(a);
                        biletController.kullanici.setBakiye(a);*/
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
                  back.setOnAction(new EventHandler<ActionEvent>() {
                      @Override
                      public void handle(ActionEvent event) {
                          FXMLLoader loader= new FXMLLoader(getClass().getResource("home.fxml"));
                          try {
                              Parent root =loader.load();
                              homePageController homepagecontroller = loader.getController();
                              homepagecontroller.setDeneme("Hoşgeldiniz "+kullanici.getAd()+" "+kullanici.getSoyad());
                              homepagecontroller.homeTabloYazdir();
                              Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
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

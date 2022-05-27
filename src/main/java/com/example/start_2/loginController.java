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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class loginController implements Initializable {
    Stage stage;
    Scene scene;
    Parent root;
    @FXML
    private PasswordField loginPassword;

    @FXML
    private TextField loginTcno;

    @FXML
    private Button loginGiris;

    @FXML
    private Button loginUyeol;
    @FXML
    private Label loginLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginGiris.setOnAction(new EventHandler<ActionEvent>() {
            private DBConnection conn = new DBConnection();
            @Override
            public void handle(ActionEvent event) {
                if (loginTcno.getText().length() == 0 || loginPassword.getText().length() == 0) {
                    loginLabel.setText("Lütfen tüm alanları doldurunuz.");
                    JOptionPane.showMessageDialog(null,"Lütfen tüm alanları doldurunuz");

                } else {
                    try {
                        Connection con = conn.connDB();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM stadyum.kullanicibilgileri");
                        while (rs.next()) {
                            if (loginTcno.getText().equals(rs.getString("tcno")) && loginPassword.getText().equals(rs.getString("sifre"))) {
                                User kullanici = new User();
                                kullanici.setId(rs.getInt("id"));
                                kullanici.setTcno(rs.getString("tcno"));
                                kullanici.setAd(rs.getString("ad"));
                                kullanici.setSoyad(rs.getString("soyad"));
                                kullanici.setTel(rs.getString("tel"));
                                kullanici.setEmal(rs.getString("emal"));
                                kullanici.setSifre(rs.getString("sifre"));
                                kullanici.setBakiye(rs.getInt("bakiye"));
                                kullanici.setBilet(rs.getString("bilet"));
                                homePageController.kullanici.setId(rs.getInt("id"));
                                homePageController.kullanici.setTcno(rs.getString("tcno"));
                                homePageController.kullanici.setAd(rs.getString("ad"));
                                homePageController.kullanici.setSoyad(rs.getString("soyad"));
                                homePageController.kullanici.setTel(rs.getString("tel"));
                                homePageController.kullanici.setEmal(rs.getString("emal"));
                                homePageController.kullanici.setSifre(rs.getString("sifre"));
                                homePageController.kullanici.setBakiye(rs.getInt("bakiye"));
                                homePageController.kullanici.setBilet(rs.getString("bilet"));
                                //bakiyeController.kullanici.setId(rs.getInt("id"));
                                System.out.println("kullanıcı giriş yaptı "+kullanici.getAd());

                                switch (kullanici.getTcno()) {
                                    case "admin" -> {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("homeAdmin.fxml"));
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
                                    case "muhase" -> {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("muhasebe.fxml"));
                                        try {
                                            Parent root = loader.load();
                                            homePageController homepagecontroller = loader.getController();
                                            homepagecontroller.setDeneme(kullanici.getAd());
                                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                            Scene scene = new Scene(root);
                                            stage.setScene(scene);
                                            stage.show();

                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                    default -> {
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
                                }

                            }else {
                                loginLabel.setText("Kullanici Adı veya şifre hatalı");

                            }

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                }

            }
        });


    }

    public void switchToScene2(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("record.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Kayit sayfası açılırken bir sorun oluştu '"+e+"'");

        }
    }
}
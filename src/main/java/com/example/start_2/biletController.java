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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.start_2.homePageController.kullanici;

public class biletController implements Initializable {
    @FXML
    private TableView<maclar> homeMaclarTable;
    @FXML private TableColumn<maclar,String> idCol;
    @FXML private TableColumn<maclar,String>evCol;
    @FXML private TableColumn<maclar,String>konukCol;
    @FXML private TableColumn<maclar,String>tarihCol;
    @FXML private TableColumn<maclar,String>yerCol;
    ObservableList<maclar> oblist = FXCollections.observableArrayList();

    @FXML
    private Button k1;
    @FXML
    private Button k2;
    @FXML
    private Button k3;
    @FXML
    private Button k4;
    @FXML
    private Button k5;
    @FXML
    private Button g1;
    @FXML
    private Button k6;
    @FXML
    private Button g2;
    @FXML
    private TextField macno,koltukno,ucret;
    @FXML
    private Button g3;
    @FXML
    private Button g4;
    @FXML
    private Button g5;
    @FXML
    private Button g6;
    @FXML
    private Button d1;
    @FXML
    private Button d2;
    @FXML
    private Button b1;
    @FXML
    private Button d3;
    @FXML
    private Button b2;
    @FXML
    private Button b3,biletal,geriButton;
    @FXML
    private Label uyari;

    static maclar mac=new maclar();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idCol.setCellValueFactory(new PropertyValueFactory<>("idmaclar"));
        evCol.setCellValueFactory(new PropertyValueFactory<>("evsahibi"));
        konukCol.setCellValueFactory(new PropertyValueFactory<>("konuk"));
        tarihCol.setCellValueFactory(new PropertyValueFactory<>("tarih"));
        yerCol.setCellValueFactory(new PropertyValueFactory<>("yer"));
        homeMaclarTable.setItems(oblist);

        int kenarbilet=75;
        int ortabilet=100;
        k1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                koltukno.setText("K1");
                ucret.setText(String.valueOf(kenarbilet));
                System.out.println("koltuk seçildi");
            }
        });


        k2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                koltukno.setText("K2");
                ucret.setText(String.valueOf(kenarbilet));
                System.out.println("koltuk seçildi");
            }
        });
        k3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                koltukno.setText("K3");
                ucret.setText(String.valueOf(kenarbilet));
                System.out.println("koltuk seçildi");
            }
        });
        k4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                koltukno.setText("K4");
                ucret.setText(String.valueOf(kenarbilet));
                System.out.println("koltuk seçildi");
            }
        });
        k5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                koltukno.setText("K5");
                ucret.setText(String.valueOf(ortabilet));
                System.out.println("koltuk seçildi");
            }
        });
        k6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                koltukno.setText("K6");
                ucret.setText(String.valueOf(ortabilet));
                System.out.println("koltuk seçildi");
            }
        });

        g1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                koltukno.setText("G1");
                ucret.setText(String.valueOf(kenarbilet));
                System.out.println("koltuk seçildi");
            }
        });


        g2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                koltukno.setText("G2");
                ucret.setText(String.valueOf(kenarbilet));
                System.out.println("koltuk seçildi");
            }
        });
        g3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                koltukno.setText("G3");
                ucret.setText(String.valueOf(kenarbilet));
                System.out.println("koltuk seçildi");
            }
        });
        g4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                koltukno.setText("G4");
                ucret.setText(String.valueOf(kenarbilet));
                System.out.println("koltuk seçildi");
            }
        });
        g5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                koltukno.setText("G5");
                ucret.setText(String.valueOf(ortabilet));
                System.out.println("koltuk seçildi");
            }
        });
        g6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                koltukno.setText("G6");
                ucret.setText(String.valueOf(ortabilet));
                System.out.println("koltuk seçildi");
            }
        });
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                koltukno.setText("B1");
                ucret.setText(String.valueOf(kenarbilet));
                System.out.println("koltuk seçildi");
            }
        });


        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                koltukno.setText("B2");
                ucret.setText(String.valueOf(ortabilet));
                System.out.println("koltuk seçildi");
            }
        });
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                koltukno.setText("B3");
                ucret.setText(String.valueOf(kenarbilet));
                System.out.println("koltuk seçildi");
            }
        });
        d1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                koltukno.setText("D1");
                ucret.setText(String.valueOf(kenarbilet));
                System.out.println("koltuk seçildi");
            }
        });


        d2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                koltukno.setText("D2");
                ucret.setText(String.valueOf(ortabilet));
                System.out.println("koltuk seçildi");
            }
        });
        d3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                koltukno.setText("D3");
                ucret.setText(String.valueOf(kenarbilet));
                System.out.println("koltuk seçildi");
            }
        });
        geriButton.setOnAction(new EventHandler<ActionEvent>() {
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

        biletal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Connection connect =null;
                DBConnection baglanti=new DBConnection();
                PreparedStatement statement=null;
                int k=kullanici.getBakiye();
                if (k<Integer.valueOf(ucret.getText())){
                    System.out.println("Bakiye Yetersiz");
                    uyari.setText("Bakiyeniz Yetersiz. Lüten yükleme yapınız");
                }else{
                    try {
                        connect=baglanti.connDB();
                        String sql="UPDATE stadyum.kullanicibilgileri SET bilet=?,koltuk=?,bakiye=? WHERE id=?";

                        // int ifade1 = Integer.valueOf(ifade
                        k=k-Integer.parseInt(ucret.getText());
                        statement=connect.prepareStatement(sql);
                        statement.setString(1,macno.getText());
                        statement.setString(2,koltukno.getText());
                        statement.setInt(3,k);
                        statement.setInt(4,kullanici.getId());
                        statement.executeUpdate();
                        kullanici.setBakiye(k);
                        kullanici.setBilet(macno.getText());
                        JOptionPane.showMessageDialog(null,"Biletiniz Tanımlandı. Biletim bölümünden kontrol edebilirsiniz." +
                                " " +macno.getText()+ "no lu maç "+
                                " "+koltukno.getText()+" nolu koltuk ");

                    } catch (SQLException exception) {
                        baglanti.Showeror(exception);
                    }

                }

            }
        });



    }
    public void biletTabloYazdir(){

        DBConnection conn=new DBConnection();

        try
        {       Connection con=conn.connDB();
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM stadyum.maclar");
            while (rs.next()){
                oblist.add(new maclar(rs.getString("idmaclar"), rs.getString("evsahibi"), rs.getString("konuk"), rs.getString("tarih"), rs.getString("yer")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}

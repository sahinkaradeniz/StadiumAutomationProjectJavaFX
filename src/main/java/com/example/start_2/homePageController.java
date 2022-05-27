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
import javafx.scene.control.Label;
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

public class homePageController  implements Initializable {

    @FXML
    private Button homeKartButton;

    @FXML
    private Button homeFisktür;

    @FXML
    private Button homeBilet;
    @FXML private Button homeBiletim;

    @FXML
    private Button homeBakiye;

    @FXML
    private Button bilgilerim;

    @FXML
    private Label homeGirisLabel;

    @FXML
    private Button homePuantablo,talep;

    @FXML
    private TableView<?> homeMaclar;

    @FXML private TableView<maclar> homeMaclarTable;
    @FXML private TableColumn<maclar,String> idCol;
    @FXML private TableColumn<maclar,String>evCol;
    @FXML private TableColumn<maclar,String>konukCol;
    @FXML private TableColumn<maclar,String>tarihCol;
    @FXML private TableColumn<maclar,String>yerCol;
    ObservableList<maclar> oblist = FXCollections.observableArrayList();

//---------------------------------------------

    static User kullanici = new User();

    String ad,soyad,tcno,sifre,telno,emal,takim;
    int id;

    public static User getKullanici() {
        return kullanici;
    }

    public static void setKullanici(User kullanici) {
        homePageController.kullanici = kullanici;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {

        this.ad = kullanici.getAd();
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getTcno() {
        return tcno;
    }

    public void setTcno(String tcno) {
        this.tcno = tcno;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getEmal() {
        return emal;
    }

    public void setEmal(String emal) {
        this.emal = emal;
    }

    public String getTakim() {
        return takim;
    }

    public void setTakim(String takim) {
        this.takim = takim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    static maclar macbilgi=new maclar();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        talep.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("talepYaz.fxml"));
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
          homePuantablo.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("puanTablosu.fxml"));
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

            homeBiletim.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("biletim.fxml"));
                    try {
                        Parent root = loader.load();
                        biletimController biletimController=loader.getController();
                        biletimController.adYazdir(kullanici.getAd());
                        biletimController.soyadYazdir(kullanici.getSoyad());
                        biletimController.adYazdir(kullanici.getAd());
                        DBConnection conn=new DBConnection();
                        try
                        {
                            Connection con=conn.connDB();
                            Statement st=con.createStatement();
                            // ResultSet rs= st.executeQuery("SELECT * FROM ");
                            // kullanici.setId(rs.getInt("id"));
                            ResultSet rs=st.executeQuery("SELECT * FROM stadyum.maclar WHERE idmaclar="+kullanici.getBilet());
                            while (rs.next()){

                                macbilgi.setId(rs.getString("idmaclar"));
                                macbilgi.setEvsahibi(rs.getString("evsahibi"));
                                macbilgi.setKonuk(rs.getString("konuk"));
                                macbilgi.setTarih(rs.getString("tarih"));
                                macbilgi.setYer(rs.getString("yer"));

                                //oblist.add(new maclar(rs.getString("idmaclar"), rs.getString("evsahibi"), rs.getString("konuk"), rs.getString("tarih"), rs.getString("yer")));
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        biletimController.yerYazdir(macbilgi.getYer());
                        biletimController.tarihYazdir(macbilgi.getTarih());
                        biletimController.konukYazdir(macbilgi.getKonuk());
                        biletimController.evYazdir(macbilgi.getEvsahibi());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

           bilgilerim.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("bilgilerim.fxml"));
                   try {
                       Parent root = loader.load();
                       bController bController=loader.getController();
                       bController.setAdyazdir(kullanici.getAd());
                       bController.setSoyadyazdir(kullanici.getSoyad());
                       bController.setMailyazdir(kullanici.getEmal());
                       bController.setTakimyazdir(kullanici.getBakiye());
                       bController.setTcyazdir(kullanici.getTcno());
                       bController.setTelyazdir(kullanici.getTel());


                       // homePageController homepagecontroller = loader.getController();
                      // homepagecontroller.setDeneme(kullanici.getAd());
                       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                       Scene scene = new Scene(root);
                       stage.setScene(scene);
                       stage.show();


                   } catch (IOException e) {
                       e.printStackTrace();
                   }

               }
           });


           homeBilet.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("biletislem.fxml"));
                   try {
                       Parent root=loader.load();
                       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                       Scene scene = new Scene(root);
                       stage.setScene(scene);
                       stage.show();
                       biletController biletController=loader.getController();
                       biletController.biletTabloYazdir();

                   } catch (IOException e) {
                       e.printStackTrace();
                   }

               }
           });
           homeFisktür.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("fisktür.fxml"));
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

         homeBakiye.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 FXMLLoader loader= new FXMLLoader(getClass().getResource("bakiyeislem.fxml"));
                 try {
                     Parent root =loader.load();
                     Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
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
        {     Connection con=conn.connDB();
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM stadyum.maclar");
            while (rs.next()){
                oblist.add(new maclar(rs.getString("idmaclar"), rs.getString("evsahibi"), rs.getString("konuk"), rs.getString("tarih"), rs.getString("yer")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("idmaclar"));
        evCol.setCellValueFactory(new PropertyValueFactory<>("evsahibi"));
        konukCol.setCellValueFactory(new PropertyValueFactory<>("konuk"));
        tarihCol.setCellValueFactory(new PropertyValueFactory<>("tarih"));
        yerCol.setCellValueFactory(new PropertyValueFactory<>("yer"));
        homeMaclarTable.setItems(oblist);



    }
    public void setDeneme(String text){
        homeGirisLabel.setText(text);
    }

    public void homeTabloYazdir(){

        DBConnection conn=new DBConnection();

        try
        {     Connection con=conn.connDB();
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
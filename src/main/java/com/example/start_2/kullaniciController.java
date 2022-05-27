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

public class kullaniciController implements Initializable {

    @FXML
    private TableColumn<User,String> tcCol;
    @FXML
    private TableView<User> kullaniciTable;
    @FXML
    private TableColumn<User,Integer> idCol;
    @FXML
    private TableColumn<User,Integer> bakiyeCol;
    @FXML
    private TableColumn<User,String> koltukCol;
    @FXML
    private TableColumn<User,String>  mailCol;
    @FXML
    private TableColumn<User,String>  telCol;
    @FXML
    private TableColumn<User,String>  adCol;
    @FXML
    private TableColumn<User,String>  soyadCol;
    @FXML
    private TableColumn<User,String> biletCol;

    @FXML
    private Button silKullanici;
    @FXML
    private TextField mailText;
    @FXML
    private Button ekleKullanici;
    @FXML
    private TextField soyadText;
    @FXML
    private TextField telText;
    @FXML
    private TextField koltukText;
    @FXML
    private TextField tcText;
    @FXML
    private TextField biletText;
    @FXML
    private Button geriButtonKullanici;
    @FXML
    private Button duzenleKulllanici;
    @FXML
    private TextField idText;
    @FXML
    private Button yenile,getir;
    @FXML
    private TextField adText;
    ObservableList<User> oblist = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcCol.setCellValueFactory(new PropertyValueFactory<>("tcno"));
        adCol.setCellValueFactory(new PropertyValueFactory<>("ad"));
        soyadCol.setCellValueFactory(new PropertyValueFactory<>("soyad"));
        telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
        mailCol.setCellValueFactory(new PropertyValueFactory<>("emal"));
        biletCol.setCellValueFactory(new PropertyValueFactory<>("bilet"));
        bakiyeCol.setCellValueFactory(new PropertyValueFactory<>("bakiye"));
        koltukCol.setCellValueFactory(new PropertyValueFactory<>("koltuk"));
        kullaniciTable.setItems(oblist);
        User kullanici1 = new User();
        geriButtonKullanici.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
        });

        getir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                        DBConnection conn=new DBConnection();
                         if(idText.getText().length()==0){
                             JOptionPane.showMessageDialog(null,"Lütfen bir ID değeri giriniz");
                         }else{Connection con = conn.connDB();

                             try {
                                 Statement st=con.createStatement();
                                 // ResultSet rs= st.executeQuery("SELECT * FROM ");
                                 // kullanici.setId(rs.getInt("id"));
                                 ResultSet rs=st.executeQuery("SELECT * FROM stadyum.kullanicibilgileri WHERE id="+idText.getText());
                                 while (rs.next()){


                                     kullanici1.setId(rs.getInt("id"));
                                     kullanici1.setTcno(rs.getString("tcno"));
                                     kullanici1.setAd(rs.getString("ad"));
                                     kullanici1.setSoyad(rs.getString("soyad"));
                                     kullanici1.setTel(rs.getString("tel"));
                                     kullanici1.setEmal(rs.getString("emal"));
                                     kullanici1.setSifre(rs.getString("sifre"));
                                     kullanici1.setBakiye(rs.getInt("bakiye"));
                                     kullanici1.setBilet(rs.getString("bilet"));
                                     kullanici1.setKoltuk(rs.getString("koltuk"));
                                     System.out.println(kullanici1.getSoyad());
                                     adText.setText(kullanici1.getAd());
                                     soyadText.setText(kullanici1.getSoyad());
                                     tcText.setText(kullanici1.getTcno());
                                     telText.setText(kullanici1.getTel());
                                     mailText.setText(kullanici1.getEmal());
                                     biletText.setText(kullanici1.getBilet());
                                     koltukText.setText(kullanici1.getKoltuk());

                                 }

                                     } catch (SQLException e) {
                                 e.printStackTrace();
                             }



                         }
            }
        });


        ekleKullanici.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Connection connect =null;
                DBConnection baglanti=new DBConnection();
                PreparedStatement statement=null;
                try {
                    connect=baglanti.connDB();
                    String sql="insert into stadyum.kullanicibilgileri(tcno,ad,soyad,tel,emal,sifre,bakiye)"
                            +"VALUES(?,?,?,?,?,?,?)";
                    statement=connect.prepareStatement(sql);
                    statement.setString(1,tcText.getText());
                    statement.setString(2,adText.getText());
                    statement.setString(3,soyadText.getText());
                    statement.setString(4,telText.getText());
                    statement.setString(5,mailText.getText());
                    statement.setString(6,"default");
                    statement.setString(7, String.valueOf(0));
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Kullanıcı eklendi");

                    temizle();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("kullanicilar.fxml"));
                    try {
                        Parent root = loader.load();
                        kullaniciController kullaniciController=loader.getController();
                        kullaniciController.kullaniciTabloYazdir();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }catch (SQLException exception){
                    baglanti.Showeror(exception);
                }

            }
        });


        yenile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("kullanicilar.fxml"));
                try {
                    Parent root = loader.load();
                    kullaniciController kullaniciController=loader.getController();
                    kullaniciController.kullaniciTabloYazdir();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



        silKullanici.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Connection connect =null;
                DBConnection baglanti=new DBConnection();
                PreparedStatement statement=null;
                try {
                    connect=baglanti.connDB();
                    String sql="delete from stadyum.kullanicibilgileri where id=?";;
                    statement=connect.prepareStatement(sql);
                    statement.setInt(1, Integer.parseInt(idText.getText()));
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"'"+idText.getText()+"' no lu ID ye sahip kullanici silinmiştir.");
                    temizle();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("kullanicilar.fxml"));
                    try {
                        Parent root = loader.load();
                        kullaniciController kullaniciController=loader.getController();
                        kullaniciController.kullaniciTabloYazdir();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }catch (SQLException exception){
                    baglanti.Showeror(exception);
                }


            }
        });

               duzenleKulllanici.setOnAction(new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent event) {
                       String a=kullaniciTable.getColumns().get(3).getCellObservableValue(2).getValue().toString();
                       System.out.println("value"+a);
                       Connection connect =null;
                       DBConnection baglanti=new DBConnection();
                       PreparedStatement statement=null;
                       try {
                           connect=baglanti.connDB();
                           String sql="UPDATE stadyum.kullanicibilgileri SET tcno=?,ad=?,soyad=?,tel=?,emal=?,sifre=?,bilet=?,bakiye=?,koltuk=? where id="+idText.getText();
                           statement=connect.prepareStatement(sql);
                           statement.setString(1,tcText.getText());
                           statement.setString(2,adText.getText());
                           statement.setString(3,soyadText.getText());
                           statement.setString(4,telText.getText());
                           statement.setString(5,mailText.getText());
                           statement.setString(6,"default");
                           statement.setString(7,biletText.getText());
                           statement.setString(8, String.valueOf(0));
                           statement.setString(9,koltukText.getText());
                           statement.executeUpdate();
                           FXMLLoader loader = new FXMLLoader(getClass().getResource("kullanicilar.fxml"));
                           try {
                               Parent root = loader.load();
                               kullaniciController kullaniciController=loader.getController();
                               kullaniciController.kullaniciTabloYazdir();
                               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                               Scene scene = new Scene(root);
                               stage.setScene(scene);
                               stage.show();

                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                           temizle();
                           JOptionPane.showMessageDialog(null,"Düzenleme İşlemi başarı ile gerçekleşti.");

                       }catch (SQLException exception){
                           baglanti.Showeror(exception);
                       }

                   }
               });

    }
    public void temizle(){
        idText.setText("");
        tcText.setText("");
        adCol.setText("");
        soyadCol.setText("");
        telCol.setText("");
        mailText.setText("");
        biletText.setText("");
        koltukText.setText("");
    }


    public void kullaniciTabloYazdir(){

        DBConnection conn=new DBConnection();

        try
        {      Connection con=conn.connDB();
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM stadyum.kullanicibilgileri");
            while (rs.next()){
                oblist.add(new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("ad"), rs.getString("soyad"), rs.getString("tel"), rs.getString("emal"), rs.getString("sifre"), rs.getString("bilet"), rs.getInt("bakiye"), rs.getString("koltuk")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }






}

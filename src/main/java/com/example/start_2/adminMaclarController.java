package com.example.start_2;



/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.sql.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

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


public class adminMaclarController implements Initializable {

    @FXML
    private TextField evsahibi;
    @FXML
    private TextField konuk;
    @FXML
    private TextField yer,id;
    @FXML
    private TextField tarih;
    @FXML
    private Button geriButton;
    @FXML
    private Button ekleMac,silMac;
    @FXML
    private Button yenile;

    @FXML private TableView<maclar> tableview;
    @FXML private TableColumn<maclar,String>idCol;
    @FXML private TableColumn<maclar,String>evCol;
    @FXML private TableColumn<maclar,String>konukCol;
    @FXML private TableColumn<maclar,String>tarihCol;
    @FXML private TableColumn<maclar,String>yerCol;
    ObservableList<maclar>  oblist = FXCollections.observableArrayList();
    static maclar mac=new maclar();
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       ekleMac.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               Connection connect =null;
               DBConnection baglanti=new DBConnection();
               PreparedStatement statement=null;

               try {
                   connect=baglanti.connDB();
                   String sql="insert into stadyum.maclar(evsahibi,konuk,tarih,yer)"
                           +"VALUES(?,?,?,?)";
                   statement=connect.prepareStatement(sql);
                   statement.setString(1,evsahibi.getText());
                   statement.setString(2,konuk.getText());
                   statement.setString(3,tarih.getText());
                   statement.setString(4,yer.getText());
                   statement.executeUpdate();
                   JOptionPane.showMessageDialog(null,"Müsabaka Eklendi");


                   FXMLLoader loader = new FXMLLoader(getClass().getResource("macEkle.fxml"));
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

       silMac.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               Connection connect =null;
               DBConnection baglanti=new DBConnection();
               PreparedStatement statement=null;

               try {
                   connect=baglanti.connDB();
                   String sql="delete from stadyum.maclar where idmaclar=?";
                   statement=connect.prepareStatement(sql);
                   statement.setInt(1, Integer.parseInt(id.getText()));
                   statement.executeUpdate();
                   JOptionPane.showMessageDialog(null,"'"+id.getText()+"' no lu ID ye sahip müsabaka silinmiştir.");

                   FXMLLoader loader = new FXMLLoader(getClass().getResource("macEkle.fxml"));
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

   yenile.setOnAction(new EventHandler<ActionEvent>() {
       @Override
       public void handle(ActionEvent event) {


       }
   });
   geriButton.setOnAction(new EventHandler<ActionEvent>() {
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
        idCol.setCellValueFactory(new PropertyValueFactory<>("idmaclar"));
        evCol.setCellValueFactory(new PropertyValueFactory<>("evsahibi"));
        konukCol.setCellValueFactory(new PropertyValueFactory<>("konuk"));
        tarihCol.setCellValueFactory(new PropertyValueFactory<>("tarih"));
        yerCol.setCellValueFactory(new PropertyValueFactory<>("yer"));
        tableview.setItems(oblist);

        // TODO
    }




}
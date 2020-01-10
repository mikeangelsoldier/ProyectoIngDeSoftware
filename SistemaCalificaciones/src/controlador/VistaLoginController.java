/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import AccesoBD.ConexionJavaSQLServer;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import vista.loginMeta;
import controlador.MenuPrincipalController;

public class VistaLoginController implements Initializable {

    String contenidoTxtUsuario, contenidoTxtpassword, usuarioActual, tipoLogin;
    boolean isAdmin, isProfesor, isAlumno;
    //   MenuPrincipalController accesoControles= new MenuPrincipalController();

    @FXML
    private JFXTextField txtUser;

    @FXML
    private JFXPasswordField txtPass;

    @FXML
    private Label logo, logo2;

    @FXML
    private void ingresar(ActionEvent e) {
        acceso();
    }

    public void cambiarVista(ActionEvent e) throws Exception {
        validarLogin();
        //Para administrador mostrar todo
        if (isAdmin == true && isProfesor == false && isAlumno == false) {
            System.out.println("Es administrador");
            // Parent panelTabla = FXMLLoader.load(getClass().getResource("/javafxapplication1/VistaAlumno.fxml"));
            Parent panelTabla = FXMLLoader.load(getClass().getResource("/javafxapplication1/MenuPrincipal.fxml"));
            Scene panelTablaScene = new Scene(panelTabla);
            loginMeta.idUsuario = usuarioActual;
            //  loginMeta.tipoUsuario = tipoLogin;
            System.out.println("En el sistema yo soy " + loginMeta.idUsuario);
            System.out.println("Soy");
            //Esta linea obtiene la informacion de los componentes del scene
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(panelTablaScene);
            window.show();
            // accesoControles.getMenuVer().setVisible(false);

            //Para profesor mostrar algunas cosas
        } else if (isAdmin == false && isProfesor == true && isAlumno == false) {
            System.out.println("Es Profesor");
            Parent panelTabla = FXMLLoader.load(getClass().getResource("/javafxapplication1/MenuPrincipal.fxml"));
            Scene panelTablaScene = new Scene(panelTabla);
            loginMeta.idUsuario = usuarioActual;
            //   loginMeta.tipoUsuario = tipoLogin;
            System.out.println("En el sistema yo soy " + loginMeta.idUsuario);
            //     System.out.println("Soy "+ loginMeta.tipoUsuario);
            //Esta linea obtiene la informacion de los componentes del scene 
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(panelTablaScene);
            window.show();

            //Para alumno solo ver graficas y calificaciones
        } else if (isAdmin == false && isProfesor == false && isAlumno == true) {
            System.out.println("Es Alumno");
            Parent panelTabla = FXMLLoader.load(getClass().getResource("/javafxapplication1/VistaGraficosYEstadisticasAlumno.fxml"));
            Scene panelTablaScene = new Scene(panelTabla);
            loginMeta.idUsuario = usuarioActual;
            //  loginMeta.tipoUsuario = tipoLogin;
            System.out.println("En el sistema yo soy " + loginMeta.idUsuario);
            //Esta linea obtiene la informacion de los componentes del scene
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(panelTablaScene);
            window.show();
        } else {
            System.out.println("El usuario no existe");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image("/images/iconUser.png");
        logo.setGraphic(new ImageView(image));
        Image image2 = new Image("/images/iconPassw.png");
        logo2.setGraphic(new ImageView(image2));

    }

    public void validarLogin() {

        ConexionJavaSQLServer.conectarBD();
        contenidoTxtUsuario = txtUser.getText();
        contenidoTxtpassword = txtPass.getText();
        isAdmin = false;
        isProfesor = false;
        isAlumno = false;

        try {
            PreparedStatement psAdmin = ConexionJavaSQLServer.connect.prepareCall("CALL buscarLoginAdministrador(?, ?)");
            PreparedStatement psProfesor = ConexionJavaSQLServer.connect.prepareCall("CALL buscarLoginProfesor(?, ?)");
            PreparedStatement psAlumno = ConexionJavaSQLServer.connect.prepareCall("CALL buscarLoginAlumno(?, ?)");

            psAdmin.setString(1, contenidoTxtUsuario);
            psAdmin.setString(2, contenidoTxtpassword);

            psProfesor.setString(1, contenidoTxtUsuario);
            psProfesor.setString(2, contenidoTxtpassword);

            psAlumno.setString(1, contenidoTxtUsuario);
            psAlumno.setString(2, contenidoTxtpassword);

            ResultSet rsAdmin = psAdmin.executeQuery();
            ResultSet rsProfesor = psProfesor.executeQuery();
            ResultSet rsAlumno = psAlumno.executeQuery();

            if (rsAdmin.next()) {
                usuarioActual = rsAdmin.getString("login");
//                 tipoLogin=rsAdmin.getString("");
                loginMeta.tipoUsuario = "administrador";
                isAdmin = true;
            } else if (rsProfesor.next()) {
                usuarioActual = rsProfesor.getString("login");
                isProfesor = true;
            } else if (rsAlumno.next()) {
                usuarioActual = rsAlumno.getString("login");
                isAlumno = true;
            }
            psAdmin.close();
        } catch (Exception e) {
            System.out.println("Error ");
            e.printStackTrace();

        }

    }

    public void acceso() {
        validarLogin();
        if (isAdmin == true && isProfesor == false && isAlumno == false) {
            System.out.println("Es administrador");
            //   loginMeta.tipoUsuario ="administrador" ;}
            System.out.println("entro admin");
            tipoLogin = "administrador";

        } else if (isAdmin == false && isProfesor == true && isAlumno == false) {
            System.out.println("Es Profesor");
            System.out.println("entro profe");
            //   loginMeta.tipoUsuario ="profesor" ;
            tipoLogin = "profesor";
        } else if (isAdmin == false && isProfesor == false && isAlumno == true) {
            System.out.println("Es Alumno");
            // loginMeta.tipoUsuario ="alumno" ;
            tipoLogin = "alumno";
        } else {
            System.out.println("El usuario no existe");
        }
        System.out.println("El login del usuario actual es " + usuarioActual);
    }

}

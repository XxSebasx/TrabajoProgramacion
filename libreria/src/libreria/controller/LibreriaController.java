package libreria.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import libreria.dao.LibreriaDAO;
import libreria.model.Libro;

public class LibreriaController implements Initializable {
    private LibreriaDAO libreriaDAO;

    @FXML
    private ComboBox<String> OpcionesBusqueda;

    @FXML
    private TextField autor;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnaniadir;

    @FXML
    private Button btneliminar;

    @FXML
    private TextField buscador;

    @FXML
    private TextField cantidad;

    @FXML
    private TextField id;

    @FXML
    private TextField libreSeleccionado;

    @FXML
    private ListView<Libro> lista;

    @FXML
    private TextField nuevoPrecio;

    @FXML
    private TextField precio;

    @FXML
    private TextField titulo;

    @FXML
    void aniadirLIbro(MouseEvent event) {
        int idNuevo = Integer.parseInt(id.getText());
        String tituloNuevo = titulo.getText();
        String autorNuevo = autor.getText();
        double precioNuevo = Double.parseDouble(precio.getText());
        int cantidadNuevo = Integer.parseInt(cantidad.getText());
        Libro libroNuevo = new Libro(idNuevo, tituloNuevo, autorNuevo, precioNuevo, cantidadNuevo);
        libreriaDAO.insertar(libroNuevo);
    }

    @FXML
    void buscar(ActionEvent event) {
        String textoBuscador = buscador.getText();
        lista.getItems().addAll(libreriaDAO.buscar(textoBuscador));
    }

    @FXML
    void eliminar(MouseEvent event) {
        Libro libro = lista.getSelectionModel().getSelectedItem();
        libreriaDAO.borrar(libro);
    }

    @FXML
    void limpiar(MouseEvent event) {
        id.setText("");
        titulo.setText("");
        autor.setText("");
        precio.setText("");
        cantidad.setText("");
    }

    @FXML
    void modificar(MouseEvent event) {
        Libro libro = lista.getSelectionModel().getSelectedItem();
        double precioNuevo = Double.parseDouble(nuevoPrecio.getText());
        libreriaDAO.modificiar(libro, precioNuevo);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        libreriaDAO = new LibreriaDAO();
        lista.getItems().addAll(libreriaDAO.getLibros());
    }

}

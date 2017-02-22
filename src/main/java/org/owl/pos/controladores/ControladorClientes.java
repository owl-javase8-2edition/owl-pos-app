/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.owl.pos.controladores;

import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.owl.pos.modelos.Cliente;
import org.owl.pos.servicios.ServicioClientes;
import org.owl.pos.vistas.animations.FadeInUpTransition;
import static org.owl.pos.vistas.helpers.ModelColumnsHelper.setModelColumn;
import static org.owl.pos.vistas.helpers.StageManagementHelper.dialog;
import static org.owl.pos.vistas.helpers.StringHelper.getNullIfEmpty;

/**
 * FXML Controller class
 *
 * @author raphapy
 */
public class ControladorClientes implements Initializable {

    //Tabla
    @FXML
    private TableView<Cliente> tableData;
    @FXML
    private TableColumn colAccion;
    @FXML
    private TableColumn<Cliente, Long> colId;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colApellido;
    @FXML
    private TableColumn<Cliente, String> colRazonSocial;
    @FXML
    private TableColumn<Cliente, String> colRuc;
    @FXML
    private TableColumn<Cliente, String> colNumeroDocumento;
    @FXML
    private TableColumn<Cliente, String> colDireccion;
    @FXML
    private TableColumn<Cliente, String> colTelefono;
    @FXML
    private TableColumn<Cliente, Character> colSexo;
    @FXML
    private TableColumn<Cliente, Integer> colEdad;
    @FXML
    private TableColumn<Cliente, Date> colFechaAlta;
    @FXML
    private TableColumn<Cliente, Date> colFechaBaja;
    @FXML
    private TableColumn<Cliente, String> colEmail;

    //Form
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtRazonSocial;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtEdad;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtRuc;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtNumeroDocumento;
    @FXML
    private TextArea txtDireccion;
    @FXML
    private ComboBox<String> cbxSexo;
    @FXML
    private ComboBox<String> cbxEstado;

    @FXML
    private Button btnNew;

    //Estrutura
    @FXML
    private AnchorPane paneTabel;
    @FXML
    private AnchorPane paneCrud;
    @FXML
    private Button btnBack;

    @FXML
    private ImageView imgLoad;
    @FXML
    private ProgressBar bar;

    private ObservableList<Cliente> listData;

    //Servicio
    private ServicioClientes clientesService;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        clientesService = new ServicioClientes();

        Platform.runLater(() -> {

            listData = FXCollections.observableArrayList();
            //status = 0;
            
            tableData.setPlaceholder(new Label("No hay datos que mostrar"));

            setModelColumn(colId, "id");
            setModelColumn(colNombre, "nombre");
            setModelColumn(colApellido, "apellido");
            setModelColumn(colRazonSocial, "razonSocial");
            setModelColumn(colRuc, "ruc");
            setModelColumn(colNumeroDocumento, "numeroDocumento");
            setModelColumn(colDireccion, "direccion");
            setModelColumn(colTelefono, "telefono");
            setModelColumn(colSexo, "sexo");
            setModelColumn(colEdad, "edad");
            setModelColumn(colFechaAlta, "fechaAlta");
            setModelColumn(colFechaBaja, "fechaBaja");
            setModelColumn(colEmail, "email");

            colAccion.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Object, Boolean> p) {
                    return new SimpleBooleanProperty(p.getValue() != null);
                }
            });
            colAccion.setCellFactory(new Callback<TableColumn<Object, Boolean>, TableCell<Object, Boolean>>() {
                @Override
                public TableCell<Object, Boolean> call(TableColumn<Object, Boolean> p) {
                    return new ButtonCell(tableData);
                }
            });
            selectWithService();
        });
    }

    private void clear() {
        txtId.clear();
        txtEmail.clear();
        txtRazonSocial.clear();
        txtTelefono.clear();
        txtEdad.clear();
        txtRuc.clear();
        txtNombre.clear();
        txtApellido.clear();
        txtNumeroDocumento.clear();
        txtDireccion.clear();
        cbxSexo.setValue("");
        cbxEstado.setValue("");
    }

    private void selectData() {
        if (listData == null) {
            listData = FXCollections.observableArrayList(clientesService.listarTodos());
        } else {
            listData.clear();
            listData.addAll(clientesService.listarTodos());
        }
        tableData.setItems(listData);
    }

    private void selectWithService() {
        Service<Integer> service = new Service<Integer>() {
            @Override
            protected Task<Integer> createTask() {
                selectData();
                return new Task<Integer>() {
                    @Override
                    protected Integer call() throws Exception {
                        Integer max = clientesService.listarTodos().size();
                        if (max > 35) {
                            max = 30;
                        }
                        updateProgress(0, max);
                        for (int k = 0; k < max; k++) {
                            Thread.sleep(40);
                            updateProgress(k + 1, max);
                        }
                        return max;
                    }
                };
            }
        };
        service.start();
        bar.progressProperty().bind(service.progressProperty());
        service.setOnRunning((WorkerStateEvent event) -> {
            imgLoad.setVisible(true);
        });
        service.setOnSucceeded((WorkerStateEvent event) -> {
            imgLoad.setVisible(false);
            new FadeInUpTransition(paneTabel).play();
        });
    }

    @FXML
    private void keyState(KeyEvent e) {
        if (cbxEstado.getValue().length() > 2) {
            dialog(Alert.AlertType.INFORMATION, "State Must 2 Char");
            cbxEstado.setValue("");
        }
    }

    @FXML
    private void clickOnTableData(MouseEvent event) {
        //if (status == 1) {
            try {
                Cliente cliente = tableData.getSelectionModel().getSelectedItem();
                txtId.setText(getNullIfEmpty(cliente.getId().toString()));
                txtApellido.setText(getNullIfEmpty(cliente.getApellido()));
                txtDireccion.setText(getNullIfEmpty(cliente.getDireccion()));
                txtEdad.setText(cliente.getEdad()!=null?cliente.getEdad().toString():null);
                txtEmail.setText(getNullIfEmpty(cliente.getEmail()));
                
                String estado = null;
                if(cliente.getEstado()!=null) {
                    switch(cliente.getEstado()) {
                        case 'A': 
                            estado = "Activo";
                            break;
                        case 'I':
                            estado = "Inactivo";
                            break;
                    } 
                } else {
                    estado = "";
                }
                
                cbxEstado.setValue(estado);
                txtNombre.setText(getNullIfEmpty(cliente.getNombre()));
                
                txtNumeroDocumento.setText(getNullIfEmpty(cliente.getNumeroDocumento()));
                txtRazonSocial.setText(getNullIfEmpty(cliente.getRazonSocial()));
                txtRuc.setText(getNullIfEmpty(cliente.getRuc()));

                String sexo=null;
                if(cliente.getSexo()!=null) {
                    switch(cliente.getSexo()) {
                        case 'F': 
                            sexo="Femenino";
                            break;
                        case 'M':
                            sexo="Masculino";
                            break;
                        case 'O':
                            sexo="Otro";
                            break;
                    }
                }

                cbxSexo.setValue(sexo);
                txtTelefono.setText(getNullIfEmpty(cliente.getTelefono()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        //}
    }

    @FXML
    private void clickOnBtnNew(ActionEvent event) {
        paneTabel.setOpacity(0);
        new FadeInUpTransition(paneCrud).play();
        Platform.runLater(() -> {
            clear();
            //enfoca primer campo del form
            txtNombre.requestFocus();
        });
    }

    @FXML
    public void clickOnBtnGuardar(ActionEvent event) {
        crearOActualizarCliente();
    }

    @FXML
    public void clickOnBtnBack(ActionEvent event) {
        paneCrud.setOpacity(0);
        new FadeInUpTransition(paneTabel).play();
    }

    public void saveOnEnter(KeyEvent e) {
        if(KeyCode.ENTER.equals(e.getCode())) {
            crearOActualizarCliente();
        }
    }

    private void crearOActualizarCliente() {
        Cliente cliente = new Cliente();
        
        Long id = txtId.getText()!=null && !"".equals(txtId.getText().trim()) ? Long.parseLong(txtId.getText()) : null;
        
        cliente.setId(id);
        cliente.setApellido(getNullIfEmpty(txtApellido.getText()));
        cliente.setDireccion(getNullIfEmpty(txtDireccion.getText()));
        
        String edadStr = getNullIfEmpty(txtEdad.getText());
        cliente.setEdad(edadStr!=null?Integer.parseInt(edadStr):null);
        
        cliente.setEmail(getNullIfEmpty(txtEmail.getText()));
        
        Character estado = null;
        if(cbxEstado.getValue()!=null) {
            switch(cbxEstado.getValue()) {
                case "Activo": 
                    estado='A';
                    break;
                case "Inactivo":
                    estado='I';
                    break;
            }
        }
        
        cliente.setEstado(estado);
        
        cliente.setNombre(getNullIfEmpty(txtNombre.getText()));
        cliente.setNumeroDocumento(getNullIfEmpty(txtNumeroDocumento.getText()));
        cliente.setRazonSocial(getNullIfEmpty(txtRazonSocial.getText()));
        cliente.setRuc(getNullIfEmpty(txtRuc.getText()));
        
        Character sexo=null;
        if(cbxSexo.getValue()!=null) {
            switch(cbxSexo.getValue()) {
                case "Femenino": 
                    sexo='F';
                    break;
                case "Masculino":
                    sexo='M';
                    break;
                case "Otro":
                    sexo='O';
                    break;
            }
        }
        
        cliente.setSexo(sexo);
        
        cliente.setTelefono(getNullIfEmpty(txtTelefono.getText()));
        cliente.setfechaAlta(new Date());
        
        //invocación al servicio
        clientesService.crearOActualizar(cliente);
        
        clear();
        //enfoca primer campo del form
        txtNombre.requestFocus();
        selectData();
        dialog(Alert.AlertType.INFORMATION, "Datos guardados correctamente.");
    }
    
    private class ButtonCell extends TableCell<Object, Boolean> {

        final Hyperlink cellButtonDelete = new Hyperlink("Eliminar");
        final Hyperlink cellButtonEdit = new Hyperlink("Editar");
        final HBox hb = new HBox(cellButtonDelete, cellButtonEdit);

        ButtonCell(final TableView tblView) {
            hb.setSpacing(4);
            cellButtonDelete.setOnAction((ActionEvent t) -> {
                //status = 1;
                int row = getTableRow().getIndex();
                tableData.getSelectionModel().select(row);
                clickOnTableData(null);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Está seguro que desea eliminar al cliente " + txtNombre.getText() + " ?");
                alert.initStyle(StageStyle.UTILITY);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    Cliente p = new Cliente();
                    p.setId(Long.valueOf(txtId.getText()));
                    clientesService.eliminar(p);
                    clear();
                    selectData();
                } else {
                    clear();
                    selectData();
                }
                
            });
            cellButtonEdit.setOnAction((ActionEvent event) -> {
                int row = getTableRow().getIndex();
                tableData.getSelectionModel().select(row);
                clickOnTableData(null);
                paneTabel.setOpacity(0);
                new FadeInUpTransition(paneCrud).play();
                
                Platform.runLater(()->txtNombre.requestFocus());
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(hb);
            } else {
                setGraphic(null);
            }
        }
    }
}

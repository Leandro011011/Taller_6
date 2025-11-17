import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {

    private JTabbedPane tabbedPane1;
    private JTextField txtID;
    private JComboBox cmbProducto;
    private JComboBox cmbMes;
    private JButton btnRegistrar;
    private JButton btnMostrarProducts;
    private JTextField txtPrecioEdit;
    private JTextField txtIdBuscar;
    private JButton btnBuscarId;
    private JTextArea txtInfoId;
    private JComboBox cmbProductosBuscar;
    private JButton buscarButton;
    private JTextArea txtInfoProductosNombre;
    private JButton btnProductosTotal;
    private JList listProductos;
    private JPanel ventana;
    private JList listEdit;
    private JButton btnCambiar;
    private JTextArea txtInfoProducto;
    private JButton btnCalcular;
    private JTextArea txtInfoPrecio;
    private JButton btnLimpiarID;
    private JButton btnLimpiarProdc;
    Tienda tienda = new Tienda();
    int codigo = 0;
    int indice;
    public Ventana(){
        txtInfoPrecio.setEditable(false);
        txtInfoProducto.setEditable(false);
        txtInfoProducto.setEditable(false);
        txtInfoId.setEditable(false);


        //BOTON DE AGREGAR UNA VENTA
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String textId = txtID.getText();
                if (textId.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error, ingrese el ID de la venta por favor", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    int id = Integer.parseInt(txtID.getText());
                    String producto = cmbProducto.getSelectedItem().toString();
                    String mes = cmbMes.getSelectedItem().toString();
                    if (id <= codigo) {
                        JOptionPane.showMessageDialog(null, "Error en ingresar el ID, debe ser mayor");
                    } else {
                        if (producto.isEmpty() || mes.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Error, datos erroneos", "ERROR", JOptionPane.ERROR_MESSAGE);
                        } else {
                            double precio = 0;
                            if (cmbProducto.getSelectedIndex() == 1) {
                                precio = 1200;
                            } else if (cmbProducto.getSelectedIndex() == 2) {
                                precio = 1300;
                            } else if (cmbProducto.getSelectedIndex() == 3) {
                                precio = 2100;
                            }

                            Venta v = new Venta(id, producto, precio, mes);
                            tienda.agregarVenta(v);
                            codigo = id;
                            JOptionPane.showMessageDialog(null, "Venta agregada con exito  :D");

                            txtID.setText("");
                            cmbProducto.setSelectedIndex(0);
                            cmbMes.setSelectedIndex(0);
                            txtInfoPrecio.setText("");
                        }
                    }
                }
            }
        });

        //BOTON DE MOSTRAR LISTA DE PRODUCTOS
        btnMostrarProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DefaultListModel lsm = new DefaultListModel();

                for (Producto p: tienda.getProductos()){
                    lsm.addElement(p.toString());
                }

                JOptionPane.showMessageDialog(null, "Cargando datos...");
                listEdit.setModel(lsm);

            }
        });

        listEdit.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                if (listEdit.getSelectedIndex()!= -1){
                    indice = listEdit.getSelectedIndex();
                    Producto p = tienda.getProductos().get(indice);

                    JOptionPane.showMessageDialog(null, "Exito, mira la parte de editar precio");
                    txtInfoProducto.setText(p.toString());
                    txtPrecioEdit.setText(""+p.getPrecio());


                }
            }
        });

        //BOTON DE CAMBIAR PRECIO DE UN PRODUCTO
        btnCambiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String txtPrecio = txtPrecioEdit.getText();
                if (txtPrecio.isEmpty()){

                }else {
                    double precio = Double.parseDouble(txtPrecioEdit.getText());
                    tienda.getProductos().get(indice).setPrecio(precio);

                    JOptionPane.showMessageDialog(null, "Se cambio el precio con exito");
                }
            }
        });

        //BOTON DE BUSCAR UNA VENTA POR ID
        btnBuscarId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String idtxt = txtIdBuscar.getText();
                if (idtxt.isEmpty() || tienda.getVentas().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Error, no se ingreso el ID", "ERROR", JOptionPane.ERROR_MESSAGE);
                    txtInfoId.setText("");
                }else{
                    int id = Integer.parseInt(txtIdBuscar.getText());
                    if (tienda.buscarPorId(id) == null){
                        JOptionPane.showMessageDialog(null, "Error, no se encontro esa Venta");
                        txtInfoId.setText("");
                    }else {
                        JOptionPane.showMessageDialog(null, "Cargando datos...");
                        txtInfoId.setText(tienda.buscarPorId(id).toString());
                    }
                }
            }
        });

        //BOTON DE BUSCAR VENTAS POR PRODUCTO
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String producto = cmbProductosBuscar.getSelectedItem().toString();
                if (producto.isEmpty() || tienda.getVentas().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Error, datos faltantes", "ERROR", JOptionPane.ERROR_MESSAGE);
                    txtInfoProductosNombre.setText("");
                }else{

                    JOptionPane.showMessageDialog(null, "Cargando datos...");
                    txtInfoProductosNombre.setText(tienda.ventasPorProducto(producto));
                }
            }
        });

        //BOTON DE MOSTRAR TODAS LAS VENTAS REALIZADAS
        btnProductosTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DefaultListModel lsm = new DefaultListModel();
                if (tienda.listarTodos().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Error, no hay ventas registradas", "ERROR", JOptionPane.ERROR_MESSAGE);

                }else{
                    for (Venta v: tienda.listarTodos()){
                        lsm.addElement(v.toString());
                    }
                    JOptionPane.showMessageDialog(null, "Cargando datos....");
                    listProductos.setModel(lsm);
                }
            }
        });

        //BOTON DE CALCULAR PRECIO DE EL PRODUCTO SELECCIONADO
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String txtId = txtID.getText();
                txtInfoPrecio.setText("");
                if (txtId.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error, datos faltantes (ID)", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    String producto = cmbProducto.getSelectedItem().toString();
                    String mes = cmbMes.getSelectedItem().toString();
                    int id = Integer.parseInt(txtID.getText());
                    if (producto.isEmpty() || mes.isEmpty() || id < 0) {
                        JOptionPane.showMessageDialog(null, "Error, datos faltantes", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Calculando....");
                        txtInfoPrecio.append("=======Informacion de la Venta=======\n");
                        txtInfoPrecio.append("ID de la Venta: "+id+"\n");
                        txtInfoPrecio.append("Producto Seleccionado: "+producto+"\n");
                        txtInfoPrecio.append("Mes que se Vendio: "+mes+"\n");
                        txtInfoPrecio.append("=====================================\n");
                        if (cmbProducto.getSelectedIndex() == 1) {
                            txtInfoPrecio.append("El precio a total a pagas es de: "+tienda.getProductos().get(cmbProducto.getSelectedIndex()-1).getPrecio()+"\n");
                        } else if (cmbProducto.getSelectedIndex() == 2) {
                            txtInfoPrecio.append("El precio a total a pagas es de: "+tienda.getProductos().get(cmbProducto.getSelectedIndex()-1).getPrecio()+"\n");
                        } else {
                            txtInfoPrecio.append("El precio a total a pagas es de: "+tienda.getProductos().get(cmbProducto.getSelectedIndex()-1).getPrecio()+"\n");
                        }
                        txtInfoPrecio.append("=====================================\n");

                    }

                }
            }
        });
        //BOTON DE LIMPIAR LOS CAMPOS CUANDO SE DESEA BUSCAR POR ID UNA VENTA
        btnLimpiarID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Limpiando campos....");
                txtInfoId.setText("");
                txtIdBuscar.setText("");
            }
        });
        //BOTON DE LIMPIAR LOS CAMPOS CUANDO SE DESEA BUSCAR VENTAS POR PRODUCTO
        btnLimpiarProdc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Limpiando campos....");
                cmbProductosBuscar.setSelectedIndex(0);
                txtInfoProductosNombre.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestion de Ventas");
        frame.setContentPane(new Ventana().ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(700, 500);
        frame.setLocation(650, 300);
        frame.setVisible(true);
    }
}

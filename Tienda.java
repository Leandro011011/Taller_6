import java.util.ArrayList;
import java.util.List;

public class Tienda {
    //Atributos
    private List<Venta> ventas;
    private List<Producto> productos;

    //Metodos
    public Tienda() {
        this.ventas = new ArrayList<Venta>();
        this.productos = new ArrayList<Producto>();
        agregarProductos();
    }
    public List<Venta> getVentas() {
        return ventas;
    }

    public void agregarVenta(Venta p){
        ventas.add(p);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void agregarProductos(){
        Producto producto1 = new Producto(1, "Cocina", 1200);
        Producto producto2 = new Producto(2, "Refrigeradora", 1300 );
        Producto producto3 = new Producto(3, "Televisor", 2100);

        productos.add(producto1);
        productos.add(producto2);
        productos.add(producto3);
    }
    //BUSQUEDA BINARIA
    public Venta buscarPorId(int id) {
        int i = 0;
        int f = ventas.size() - 1;
        int m;
        while (i <= f){
            m = (i + f)/2;
            if (id == ventas.get(m).getId()){
                return ventas.get(m);
            }else if(id <= ventas.get(m).getId()){
                f = m - 1;
            }else{
                i = m + 1;
            }
        }

        return null;
    }
    //BUSQUEDA LINEAL
    public String ventasPorProducto(String producto){
        StringBuilder sc = new StringBuilder();
        for (Venta v: listarTodos()){
            if (producto.equals(v.getNombre())){
                sc.append(v.toString());
            }
        }

        return sc.toString();
    }

    public List<Venta> listarTodos(){
        return ventas;
    }
}

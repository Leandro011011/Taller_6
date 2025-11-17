public class Venta {
    //Atributos
    private int id;
    private String producto;
    private double total;
    private String mesVendido;
    //Metodos
    public Venta(int id, String nombre, double precio, String mesVendido) {
        this.id = id;
        this.producto = nombre;
        this.total = precio;
        this.mesVendido = mesVendido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return producto;
    }

    public void setNombre(String nombre) {
        this.producto = nombre;
    }

    public double getPrecio() {
        return total;
    }

    public void setPrecio(double precio) {
        this.total = precio;
    }

    public String getMesVendido() {
        return mesVendido;
    }

    public void setMesVendido(String mesVendido) {
        this.mesVendido = mesVendido;
    }

    @Override
    public String toString() {
        return "\nVenta: " +
                "| ID: " + id +
                "| Producto: " + producto+
                "| total: " + total +
                "| Mes Vendido: " + mesVendido;
    }
}

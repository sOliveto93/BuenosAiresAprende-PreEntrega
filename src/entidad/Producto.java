package entidad;

public class Producto {
    private static long ultimoID=0;
    private final long id;
    private final String nombre;
    private final int codigo;
    private final String categoria;
    private final double precio;
    private int stock;



    public Producto(String nombre, int codigo, String categoria, double precio,int stock) {
        ultimoID++;
        this.id = ultimoID;
        this.nombre = nombre;
        this.codigo = codigo;
        this.categoria = categoria;
        this.precio = precio;
        this.stock=stock;

    }

    public long getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }

    public long getCodigo() { return codigo;}

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", codigo=" + codigo +
                ", categoria='" + categoria + '\'' +
                ", precio=" + precio +
                ", stock=" + stock;
    }
}

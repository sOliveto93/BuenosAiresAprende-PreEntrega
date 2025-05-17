package repository;

import entidad.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoRepository {

    private static final ProductoRepository instance=new ProductoRepository();
    private final List<Producto> productos=new ArrayList<>();

    private ProductoRepository(){
        inicializarStock();
    }
    public static ProductoRepository getInstance(){
        return instance;
    }
    private void inicializarStock(){
        productos.add( new Producto( "leche",321,"lacteos",1500.00,50));
        productos.add( new Producto( "cereal",78,"cereales",1200.00,30));
        productos.add( new Producto( "carne",4,"carnes",5200.00,20));
        productos.add( new Producto( "fideos",89,"fideos",900.00,15));
        productos.add( new Producto( "cerveza 1lt",965,"bebidas con alcohol",4000.00,32));
    }
    public List<Producto> getAll(){
        return productos;
    }
//    public int countAllProducts(){
//        return productos.size();
//    }
    public void add(Producto producto){
        productos.add(producto);
    }
    public Optional<Producto> getById(long id){
        return productos.stream()
                .filter(e->e.getId()==id)
                .findFirst();
    }

//    public Optional<Producto> getByName(String nombre) {
//        return productos.stream()
//                .filter(e->e.getNombre().equals(nombre))
//                .findFirst();
//    }


//    public boolean deleteProducto(Producto producto) {
//        return productos.remove(producto);
//    }
//    public Producto deleteProductoByIndex(int index) {
//        return productos.remove(index);
//    }
}

package logica;

import entidad.Producto;
import repository.ProductoRepository;

import java.util.Optional;


public class ProductoLogica {

    ProductoRepository productoRepo;

    public ProductoLogica(ProductoRepository productoRepo){
        this.productoRepo=productoRepo;
    }

    public void add(String nombre,int codigo,String categoria,double precio,int stock){
        Producto p=new Producto(nombre,codigo,categoria,precio,stock);
        productoRepo.add(p);
    }
    public void listarTodos(){
        for(Producto p : productoRepo.getAll()){
            System.out.println(p.toString());
        }
    }
    public Optional<Producto> findById(long id){
        return productoRepo.getById(id);
    }

//    public Optional<Producto> findByName(String nombre){
//        return productoRepo.getByName(nombre);
//    }
//    public void listarProductoById(long id){
//       Optional<Producto> p= findById(id);
//
//        if(p.isPresent()){
//            System.out.println(p.get());
//        }else{
//            System.out.println("producto no encontrado");
//        }
//    }
//    public void listarProductoByName(String nombre){
//        Optional<Producto> p= findByName(nombre);
//
//        if(p.isPresent()){
//            System.out.println(p.get().toString());
//        }else{
//            System.out.println("producto no encontrado");
//        }
//    }
//    public void modificarProductoById(long id,String nombre,double precio,int stock){
//        Optional<Producto>producto=findById(id);
//        if(producto.isPresent()){
//            Producto current=producto.get();
//            current.setNombre(nombre);
//            current.setPrecio(precio);
//            current.setStock(stock);
//            System.out.println("producto modificado con exito\n"+current);
//        }else{
//            System.out.println("producto no encontrado");
//        }
//    }
//    public void modificarProductoByName(String nombre,String nuevoNombre,double precio,int stock){
//        Optional<Producto>producto=findByName(nombre);
//        if(producto.isPresent()){
//            Producto current=producto.get();
//            current.setNombre(nuevoNombre);
//            current.setPrecio(precio);
//            current.setStock(stock);
//            System.out.println("producto modificado con exito\n"+current);
//        }else{
//            System.out.println("producto no encontrado");
//        }
//    }
//    public void eliminarProductoById(long id){
//        Optional<Producto>producto=findById(id);
//        if(producto.isPresent()){
//           boolean resultado=productoRepo.deleteProducto(producto.get());
//            if(resultado){
//                System.out.println("eliminado con exito");
//            }else{
//                System.out.println("problemas");
//            }
//        }
//    }
//    public void eliminarProductoByName(String nombre){
//        Optional<Producto>producto=findByName(nombre);
//        if(producto.isPresent()){
//            boolean resultado= productoRepo.deleteProducto(producto.get());
//            if(resultado){
//                System.out.println("eliminado con exito");
//            }else{
//                System.out.println("problemas");
//            }
//        }
//    }
//    public void eliminarProductoByIndex(int index){
//        if(productoRepo.countAllProducts()>=index){
//            Producto p=productoRepo.deleteProductoByIndex(index);
//            System.out.println("producto eliminado con exito");
//            System.out.println(p);
//        }else{
//            System.out.println("indice fuera de rango");
//        }
//    }
    public boolean verificarStock(int cantidad,Long id){
        Optional<Producto> p=findById(id);
        if(p.isPresent()){
            int stock=p.get().getStock();
            return cantidad <= stock;
        }
        else {
            System.out.println("⚠️ Producto con ID " + id + " no encontrado.");
        }
        return false;
    }

    public boolean existeCodigo(int codigo) {
        return productoRepo.getAll().stream().anyMatch(c->c.getCodigo()==codigo);
    }
}

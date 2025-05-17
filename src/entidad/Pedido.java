package entidad;

import repository.ProductoRepository;

import java.util.*;

public class Pedido {
    private static long ultimoID=0;
    private final long id;
    private final Map<Long, Integer> detalle;
    private double subtotal;
    private final ProductoRepository productoRepo;

    public Pedido(ProductoRepository productoRepo){
        ultimoID++;
        this.id = ultimoID;
        this.detalle = new HashMap<>();
        this.subtotal =0.0;
        this.productoRepo=productoRepo;
    }
    public void agregarCantidadProducto(long idProducto, int cantidad) {
        Optional<Producto> productoOptional=productoRepo.getById(idProducto);

        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            int stockDisponible = producto.getStock();

            if (stockDisponible >= cantidad) {
                detalle.compute(idProducto, (k, v) -> (v == null) ? cantidad : v + cantidad);

                producto.setStock(stockDisponible - cantidad);
            } else {
                System.out.println("Stock insuficiente para el producto: " + producto.getNombre());
            }

            recalcularSubtotal();
        }

    }
    public boolean quitarProducto(long idProducto) {
        Optional<Producto> productoOptional=productoRepo.getById(idProducto);
        if(productoOptional.isPresent()){
            Producto producto=productoOptional.get();
            int devolucionStock = detalle.remove(idProducto);

            producto.setStock(producto.getStock() + devolucionStock);
            recalcularSubtotal();
            return true;
        }
        return false;
    }
    public boolean descontarCantidadProducto(long idProducto, int cantidad) {
        Optional<Producto> productoOptional=productoRepo.getById(idProducto);
        if(productoOptional.isPresent()){
            Producto producto=productoOptional.get();
            if (detalle.containsKey(idProducto)) {
                int cantidadActual=detalle.get(idProducto);
                if (cantidadActual > cantidad) {
                    detalle.put(idProducto,cantidadActual - cantidad);
                    //esto devuelve la cantidad elegida por el usuario al stock
                    producto.setStock(producto.getStock() + cantidad);
                }else{
                    detalle.remove(idProducto);
                    //esto devuelve la totalidad del detalle al stock
                    producto.setStock(producto.getStock() + cantidadActual);
                }
                recalcularSubtotal();
                return true;
            }

        }
        return false;
    }

    private void recalcularSubtotal() {
        subtotal = 0.0;
        for (Map.Entry<Long, Integer> entry : detalle.entrySet()) {
            long id = entry.getKey();
            int cantidad = entry.getValue();

            productoRepo.getById(id).ifPresent(p -> subtotal += p.getPrecio() * cantidad);
        }
    }




    public long getId() {
        return id;
    }


    public double getSubtotal() {
        return subtotal;
    }



    public Map<Long, Integer> getDetalle() {
        return detalle;
    }

    public String imprimirDetalle(){
        StringBuilder sb=new StringBuilder();
        for(Long index: getDetalle().keySet()){
           Optional<Producto> productoOptional=productoRepo.getById(index);
           if(productoOptional.isPresent()){
               Producto p=productoOptional.get();
               sb.append("\t ID:").append(p.getId()).append("  ").append(p.getNombre());
               sb.append(", p/u $").append(p.getPrecio());
               sb.append(", cantidad ").append(getDetalle().get(index));
               sb.append("\n");
           }
        }
        return sb.toString();
    }
    @Override
    public String toString() {
        return "Pedido{\n" +
                "\tID-Pedido =" + getId() +
                "\n\tDetalle\n" + imprimirDetalle() +
                "\n\tsubtotal=" + getSubtotal()+"\n" +
                '}';
    }
    public boolean noContieneProducto(long idProducto) {
        return !detalle.containsKey(idProducto);
    }

}

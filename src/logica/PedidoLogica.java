package logica;

import entidad.Pedido;
import entidad.Producto;
import repository.ProductoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidoLogica {

    List<Pedido> pedidos=new ArrayList<>();
    ProductoRepository productoRepo;

    public PedidoLogica(ProductoRepository productoRepo){
        this.productoRepo=productoRepo;
    }

    public Pedido crearPedido(){
       Pedido pedido= new Pedido(productoRepo);
       pedidos.add(pedido);
       return pedido;
    }
    public List<Pedido> getAllPedidos(){
        return pedidos;
    }
    public Optional<Pedido> buscarPedidoPorId(Long id){
        return pedidos.stream()
                .filter(p->p.getId()==id)
                .findFirst();
    }

    public Pedido agrandarPedido(Long idProducto,int cantidad,Pedido pedido){
        for (Producto p : productoRepo.getAll()) {
            if (p.getId() == idProducto) {
                if (p.getStock() >= cantidad) {
                    pedido.agregarCantidadProducto(idProducto, cantidad);
                } else {
                    System.out.printf("Stock insuficiente del producto con ID: " +p.getId()+" -- nombre: "+ p.getNombre()+" -- codigo Interno: "+p.getCodigo());
                }
                break;
            }
        }
        return pedido;
    }
    public boolean descontarProductoPedido(Long idProducto,int cantidad,Pedido pedido){
        return pedido.descontarCantidadProducto(idProducto,cantidad);

    }
    public boolean quitarProductoPedido(long id,Pedido pedido){
        return pedido.quitarProducto(id);
    }
    public void imprimirPedido(Pedido pedido){
        System.out.println(pedido.toString());
    }

}

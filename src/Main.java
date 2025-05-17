import entidad.Pedido;
import errores.PedidoNoEncontrado;
import logica.PedidoLogica;
import logica.ProductoLogica;
import repository.ProductoRepository;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static void mostrarMenuPrincipal(ProductoLogica productoLogica, PedidoLogica pedidoLogica) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Listar productos");
            System.out.println("2. Agregar producto al stock");
            System.out.println("3. Crear pedido");
            System.out.println("4. Listar pedidos");
            System.out.println("5. Modificar Pedido");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1" -> productoLogica.listarTodos();
                case "2" -> agregarProductoAlStock(productoLogica);
                case "3" -> crearPedido(pedidoLogica, productoLogica);
                case "4" -> listarPedidos(pedidoLogica);
                case "5" -> modificarPedido(pedidoLogica,productoLogica);
                case "0" -> {
                    salir = true;
                    System.out.println("👋 ¡Hasta luego!");
                }
                default -> System.out.println("❌ Opción no válida.");
            }
        }
    }

    private static void agregarProductoAlStock(ProductoLogica productoLogica) {
        try {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine().trim();
            System.out.print("Código: ");
            int codigo = Integer.parseInt(scanner.nextLine());
            System.out.print("Categoría: ");
            String categoria = scanner.nextLine().trim();
            System.out.print("Precio: ");
            double precio = Double.parseDouble(scanner.nextLine());
            System.out.print("Stock: ");
            int stock = Integer.parseInt(scanner.nextLine());


            boolean entradasValidas=validarEntradas(nombre,categoria,precio,stock,codigo);

            if(!entradasValidas){
                return;
            }
            if(productoLogica.existeCodigo(codigo)){
                System.out.println("❌ Ya existe un producto con ese código.");
                return;
            }
            productoLogica.add(nombre, codigo, categoria, precio, stock);
            System.out.println("✅ Producto agregado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Entrada inválida. Verifica los datos numéricos.");
        }
    }

    private static boolean validarCantidad(int cantidad){
        if (cantidad <= 0) {
            System.out.println("❌ La cantidad debe ser mayor que cero.");
            return false;
        }
        return true;
    }
    private static boolean validarID(Long id){
        if(id<=0){
            System.out.println("El Id del producto no puedo ser negativo ni cero");
            return false;
        }
        return true;
    }
    private static boolean validarEntradas(String nombre, String categoria, double precio, int stock,int codigo) {
        if (nombre.isEmpty() || categoria.isEmpty()) {
            System.out.println("❌ El nombre y la categoría no pueden estar vacíos.");
            return false;
        }
        if (precio < 0 || stock < 0) {
            System.out.println("❌ El precio y el stock no pueden ser negativos.");
            return false;
        }
        if(codigo <= 0){
            System.out.println("❌ El codigo no puede ser negativo ni cero.");
            return false;
        }
        return true;
    }

    private static void crearPedido(PedidoLogica pedidoLogica, ProductoLogica productoLogica) {
        Pedido pedido = pedidoLogica.crearPedido();
        System.out.println("✅ Pedido creado con ID: " + pedido.getId());

        System.out.print("Desea agregar productos al pedido? (Y/N): ");
        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            agregarProductosAlPedido(pedido, pedidoLogica, productoLogica);
        }
    }

    private static void agregarProductosAlPedido(Pedido pedido, PedidoLogica pedidoLogica, ProductoLogica productoLogica) {
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.print("ID del producto: ");
                long idProducto = Long.parseLong(scanner.nextLine());
                boolean idValido=validarID(idProducto);
                if(!idValido){
                    continue;
                }
                if(productoLogica.findById(idProducto).isEmpty()){
                    System.out.println("❌ El producto con ese ID no existe.");
                    continue;
                }
                System.out.print("Cantidad: ");
                int cantidad = Integer.parseInt(scanner.nextLine());
                boolean cantidadValida=validarCantidad(cantidad);
                if(!cantidadValida){continue;}
                if (!productoLogica.verificarStock(cantidad, idProducto)) {
                    System.out.println("❌ No hay suficiente stock disponible.");
                } else {
                    pedido = pedidoLogica.agrandarPedido(idProducto, cantidad, pedido);
                    System.out.println("✅ Producto agregado. Pedido actualizado:");
                    System.out.println(pedido.imprimirDetalle()+"\n\t\t\tsubTotal = "+pedido.getSubtotal());
                }

                System.out.print("Desea seguir agregando productos? (Y/N): ");
                continuar = scanner.nextLine().equalsIgnoreCase("Y");

            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida. Use solo números para ID y cantidad.");
            }
        }
    }
    private static void listarPedidos(PedidoLogica pedidoLogica){
        List<Pedido> pedidos=pedidoLogica.getAllPedidos();
        System.out.println("Pedidos totales --> "+pedidos.size());
        if(!pedidos.isEmpty()){
            for(Pedido p:pedidos){
                pedidoLogica.imprimirPedido(p);
            }
        }

    }
    private static void modificarPedido(PedidoLogica pedidoLogica,ProductoLogica productoLogica){
        System.out.println("ingrese el id del pedido");
        try{
            Long idPedido=Long.parseLong(scanner.nextLine());
            Pedido pedido = pedidoLogica.buscarPedidoPorId(idPedido)
                    .orElseThrow(()->new PedidoNoEncontrado("❌ Pedido no encontrado"));

                pedidoLogica.imprimirPedido(pedido);
                System.out.println("agregar --> 1 \nquitar --> 2\ndescontar -->3");
                int opcion=Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1 -> agregarProductosAlPedido(pedido, pedidoLogica, productoLogica);
                    case 2 -> quitarProductosDelPedido(pedido, pedidoLogica);
                    case 3 -> descontarCantidadProductoAlPedido(pedido, pedidoLogica);
                    default -> System.out.println("opcion invalida");
                }
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Entrada inválida. El ID debe ser un numero valido");
        }catch (PedidoNoEncontrado e){
            System.out.println( e.getMessage());
        }
    }

    private static void descontarCantidadProductoAlPedido(Pedido pedido, PedidoLogica pedidoLogica) {
        System.out.println("ingresa el ID del producto a quitar");
        try{
            long idProducto=Long.parseLong(scanner.nextLine());
            if (pedido.noContieneProducto(idProducto)) {
                System.out.println("❌ El producto no está en este pedido.");
                return;
            }
            System.out.println("Ingresar cantidad a quitar");
            int cantidad=Integer.parseInt(scanner.nextLine());
            boolean cantidadValida=validarCantidad(cantidad);
            if(!cantidadValida){
                return;
            }
            boolean quitado=pedidoLogica.descontarProductoPedido(idProducto,cantidad,pedido);

            if (quitado) {
                System.out.println("✅ Cantidad descontada del pedido.");
                pedidoLogica.imprimirPedido(pedido);
            } else {
                System.out.println("❌ No se pudo descontar la cantidad. Verifica el ID del producto o la cantidad.");
            }
        }catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválda. El ID debe ser un numero valido");
        }
    }

    private static void quitarProductosDelPedido(Pedido pedido, PedidoLogica pedidoLogica) {
        System.out.println("ingresa el ID del producto a quitar");
        try{
            long idProducto=Long.parseLong(scanner.nextLine());
            if (pedido.noContieneProducto(idProducto)) {
                System.out.println("❌ El producto no está en este pedido.");
                return;
            }
            boolean quitado=pedidoLogica.quitarProductoPedido(idProducto,pedido);
            if(!quitado){
                System.out.println("error al quitar el producto con id "+ idProducto+ " del pedido ");
                pedidoLogica.imprimirPedido(pedido);
            }
            else{
                System.out.println("Producto quitado con exito");
                pedidoLogica.imprimirPedido(pedido);
            }
        }catch (NumberFormatException e) {
            System.out.println("⚠️ Entrada inválida. El ID debe ser un numero valido");
        }

    }

    public static void main(String[] args) {
        ProductoRepository repo = ProductoRepository.getInstance();
        ProductoLogica productoLogica = new ProductoLogica(repo);
        PedidoLogica pedidoLogica = new PedidoLogica(repo);
        mostrarMenuPrincipal(productoLogica, pedidoLogica);
        scanner.close();
    }

}

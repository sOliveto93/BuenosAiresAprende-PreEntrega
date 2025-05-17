Consignas de la Pre-Entrega (Proyecto 
Integrador) 
Introducción a la Pre-Entrega del Proyecto 
¡Hola, desarrollador/a de TechLab! Recordá que es fundamental cumplir con la 
pre-entrega de tu proyecto integrador para evidenciar el avance en cada uno de los temas 
aprendidos: desde lo más básico en Java hasta la organización modular y el manejo de 
excepciones. Esta instancia te va a permitir: 
1. Validar que hayas comprendido y aplicado los conceptos clave (POO, colecciones, 
excepciones, etc.). 
2. Recibir feedback oportuno antes de la entrega final. 
3. Asegurar que tu aplicación esté en un estado funcional y coherente con los 
requerimientos de un sistema de e-commerce inicial. 
A continuación, se detallan las consignas que debés cumplir en tu pre-entrega. ¡Revisalas 
atentamente y asegurate de cubrir cada una para demostrar tu dominio de los contenidos! 
1. Ingreso de Productos 
○ Implementar una funcionalidad para agregar productos con: 
■ Nombre (String). 
■ Precio (double). 
■ Cantidad en Stock (int). 
○ Almacenarlos en una colección dinámica (ej. ArrayList<Producto>). 
2. Visualización de Productos 
○ Listar todos los productos, mostrando: 
■ ID (autogenerado o posición en la lista). 
■ Nombre, Precio, Stock. 
○ El sistema debe mostrar un menú con la opción de “Listar productos”. 
3. Búsqueda y Actualización 
○ Permitir buscar un producto por nombre o ID. 
○ Si se encuentra, mostrar su información (mínimo: nombre, precio, stock). 
○ (Opcional) Poder actualizar algunos datos (precio o stock), validando que los 
valores sean coherentes (ej., stock no negativo).
4. Eliminación de Productos 
○ Habilitar la eliminación de un producto por ID o por posición. 
○ (Opcional) Pedir confirmación antes de borrarlo. 
5. Creación de Pedidos 
○ Implementar la clase Pedido (o Orden) que incluya: 
■ Lista de productos (o estructura LineaPedido con producto y 
cantidad). 
○ Solicitar al usuario qué productos desea y en qué cantidad. 
○ Verificar stock; si no alcanza, lanzar una excepción (por ejemplo 
StockInsuficienteException) o mostrar un mensaje de error. 
○ Calcular el costo total (suma de precio * cantidad de cada producto). 
○ Disminuir el stock de cada producto si el pedido se confirma. 
○ (Opcional) Mostrar una lista de pedidos realizados. 
6. Menú Principal Interactivo 
○ Presentar un menú con opciones como: 
■ Agregar producto 
■ Listar productos 
■ Buscar/Actualizar producto 
■ Eliminar producto 
■ Crear un pedido 
■ (Opcional) Listar pedidos 
■ Salir 
○ El programa se repite hasta que el usuario elija “Salir”. 
7. POO y Principios de Diseño 
○ Dividir la lógica en clases y métodos: 
■ Producto, Pedido, Main (para el menú), (opcional) LineaPedido, 
etc. 
○ Emplear encapsulamiento (atributos privados, getters/setters). 
○ (Opcional) Añadir herencia/polimorfismo si tu proyecto lo requiere (ej. 
Bebida, Comida que heredan de Producto).
8. Excepciones 
○ Manejar con try/catch los errores de conversión de tipo, al ingresar valores 
no válidos, etc. 
○ Crear al menos una excepción personalizada (por ej. 
StockInsuficienteException o ProductoNoEncontradoException) 
y lanzarla en el escenario adecuado. 
9. Organización en Paquetes y Módulos 
○ Separar las clases en paquetes lógicos (ej. com.techlab.productos, 
com.techlab.excepciones, etc.). 
10. Estructura del Código y Legibilidad 
○ Mantener un código limpio, con nombres descriptivos y funciones cortas. 
○ Evitar métodos excesivamente largos o mezclar muchas responsabilidades 
en una sola clase.
# Proyecto Integrador - Pre-Entrega  
**TechLab - Curso de Programación en Java**

## 🎯 Objetivo de la Pre-Entrega

La pre-entrega del proyecto integrador tiene como objetivo evidenciar tu avance en los temas clave del curso:

- Programación Orientada a Objetos (POO)
- Colecciones dinámicas
- Manejo de excepciones
- Organización modular

Esta instancia te permitirá:

1. Validar la comprensión de los conceptos fundamentales.
2. Recibir feedback antes de la entrega final.
3. Asegurar una base funcional del sistema de e-commerce.

---

## 📋 Requisitos Funcionales

### 1. Ingreso de Productos
- Permitir agregar productos con:
  - `Nombre` (String)
  - `Precio` (double)
  - `Cantidad en Stock` (int)
- Almacenarlos en una colección dinámica, como `ArrayList<Producto>`.

### 2. Visualización de Productos
- Listar todos los productos, mostrando:
  - ID (autogenerado o basado en la posición)
  - Nombre
  - Precio
  - Stock
- Incluir en el menú la opción **"Listar productos"**.

### 3. Búsqueda y Actualización
- Permitir buscar un producto por **nombre o ID**.
- Si se encuentra:
  - Mostrar información relevante (mínimo: nombre, precio, stock)
  - *(Opcional)* Permitir actualizar precio o stock, validando datos coherentes.

### 4. Eliminación de Productos
- Permitir eliminar productos por **ID o posición en la lista**.
- *(Opcional)* Confirmar la eliminación antes de efectuarla.

### 5. Creación de Pedidos
- Implementar una clase `Pedido` (o `Orden`) que incluya:
  - Lista de productos (o estructura `LineaPedido` con producto y cantidad)
- Solicitar al usuario:
  - Qué productos desea
  - En qué cantidad
- Verificar stock disponible:
  - Si no alcanza, lanzar excepción (`StockInsuficienteException`) o mostrar error.
- Calcular costo total del pedido.
- Disminuir stock de productos al confirmar el pedido.
- *(Opcional)* Mostrar lista de pedidos realizados.

### 6. Menú Principal Interactivo
El menú debe ofrecer las siguientes opciones:
- Agregar producto
- Listar productos
- Buscar / Actualizar producto
- Eliminar producto
- Crear un pedido
- *(Opcional)* Listar pedidos
- Salir

> El menú debe repetirse hasta que el usuario elija "Salir".

---

## 🧠 Buenas Prácticas de Diseño

### 7. POO y Principios de Diseño
- Dividir la lógica en clases y métodos bien definidos:
  - `Producto`, `Pedido`, `Main`, *(opcional)* `LineaPedido`, etc.
- Aplicar encapsulamiento: atributos privados + getters/setters.
- *(Opcional)* Aplicar herencia o polimorfismo si se justifica (ej: `Bebida` y `Comida` heredan de `Producto`).

### 8. Manejo de Excepciones
- Usar `try/catch` para:
  - Errores de conversión (input inválido, etc.)
- Crear al menos una **excepción personalizada**:
  - Ej: `StockInsuficienteException`, `ProductoNoEncontradoException`
  - Lanzarla en el contexto adecuado.

### 9. Organización en Paquetes y Módulos
- Separar las clases en paquetes lógicos, como:
  - `com.techlab.productos`
  - `com.techlab.excepciones`
  - `com.techlab.pedidos`

### 10. Estructura del Código y Legibilidad
- Código limpio y organizado.
- Nombres descriptivos para clases, métodos y variables.
- Métodos cortos y con responsabilidad única.
- Evitar clases/métodos que mezclen demasiadas responsabilidades.

---

## ✅ Recomendaciones Finales
- Verificá que todas las funcionalidades estén integradas al menú.
- Probá múltiples escenarios de ingreso y validación.
- Documentá tu código con comentarios claros si es necesario.
- Usá control de versiones (Git) para registrar tu progreso.

---


# Análisis del Flujo de Trabajo del Proyecto NovaGear

Este documento describe la arquitectura y el flujo de datos de la aplicación **NovaGear**, construida sobre **Java y Spring Boot**. El proyecto sigue un diseño arquitectónico multicapa (N-Tier Architecture), un estándar muy utilizado en aplicaciones empresariales y microservicios.

## 1. Arquitectura en Capas

La aplicación está dividida en capas principales, cada una con una responsabilidad única, garantizando y promoviendo el principio de separación de responsabilidades (Separation of Concerns).

### Las Capas Actuales
1. **Capa de Control/API (Controladores):** *(Nota: Aún no implementada gráficamente en los archivos, pero es el punto de entrada lógico para aplicaciones web).* Se encarga de recibir las peticiones HTTP del usuario, invocar a los servicios y devolver las respuestas correspondientes.
2. **Capa de Transferencia de Datos (DTOs y Mappers):** Se encarga de pasar la información de un lado a otro (ej: `ProductRequestDTO`, `ProductResponseDTO`) limitando la exposición de entidades de Base de Datos a capas superiores usando Mappers (`ProductMapper`).
3. **Capa de Servicio (Business Logic):** Aquí residen las reglas de negocio (`ProductService`, `ProductServiceImpl`). Orquesta las llamadas entre la validación, repositorios y otras utilidades.
4. **Capa de Acceso a Datos (Repositorios):** Proporciona la interfaz para interactuar con la Base de Datos (`ProductRepository`, `CategoryRepository` que extienden de `JpaRepository`).
5. **Capa de Entidades (Persistencia):** Definición de las tablas y relaciones de la Base de Datos mediante JPA/Hibernate (`Product`, `Category`).

---

## 2. Flujo desde el Usuario hasta la Base de Datos

Tomando como ejemplo la **creación de un producto**, el ciclo de vida de una petición (Request -> Response) es el siguiente:

### Paso 1: Interacción del Usuario (Cliente -> API)
El usuario (o cliente frontend, postman, etc.) hace una petición HTTP (por ejemplo, `POST /api/products`) enviando un cuerpo en formato JSON que representa los datos necesarios para crear un producto.

### Paso 2: Recepción del DTO (Data Transfer Object)
La solicitud es capturada; la información en JSON se deserializa en un objeto de Java de tipo `ProductRequestDTO`. Los DTOs aseguran que la interfaz externa no esté atada directamente al diseño de la base de datos (Entidades).

### Paso 3: Capa de Servicio (Lógica de Negocio)
El controlador envía el `ProductRequestDTO` a `ProductServiceImpl`. 
En el método `createProduct`:
1. **Mapeo Inicial:** Usa `ProductMapper.toEntity(request)` para convertir el `ProductRequestDTO` en un objeto `Product` (Entidad lista para la DB).
2. **Validación y Relaciones:** 
   - Utiliza `CategoryRepository.findById(request.categoryId())` para buscar si la categoría proporcionada por el usuario realmente existe.
   - Si no existe, lanza un `RuntimeException`.
   - Si existe, se le asigna esta categoría al producto con `product.setCategories(category)`.
3. **Persistencia:** Llama a `ProductRepository.save(product)`. 

### Paso 4: Capa de Repositorio a la Base de Datos
`ProductRepository` actúa como un puente (gracias a Spring Data JPA). Traduce el comando `.save()` de Java a una consulta SQL `INSERT INTO products...` (o equivalente) y establece la transacción contra la Base de Datos relacional, guardando el registro.

### Paso 5: Retorno al Usuario
1. Una vez guardado, el repositorio devuelve la entidad construida (ahora con un ID generado por la base de datos).
2. La capa de servicio utiliza `ProductMapper.toResponse(savedProduct)` para transformar el `Product` recién guardado en un `ProductResponseDTO`, ocultando información sensible u organizando los datos de forma legible (ej: asigna el nombre de la categoría o "Uncategorized" si viene vacía).
3. Este DTO se retorna finalmente al usuario con un código HTTP exitoso (201 Created).

---

## 3. Puntos a Destacar de la Estructura Actual

- **@Transactional:** El uso de `@Transactional` en el `ProductServiceImpl` asegura que si ocurre algún fallo durante la creación del producto o vinculación de la categoría, cualquier cambio en la base de datos se deshace (Rollback), manteniendo la integridad de los datos.
- **DTO Record Pattern:** Si los DTOs están configurados como records de Java (`ProductRequestDTO`), promueven la inmutabilidad y reducen el boilerplate.
- **Acoplamiento Débil (Inyección de Dependencias):** Interfaces como `ProductService`, `ProductRepository`, etc., se inyectan mediante constructores, facilitando los tests unitarios.

## 4. Próximos pasos recomendados
Para completar el flujo del proyecto tal y como se observa en su estructura base:
1. **Agregar Controladores (`@RestController`):** Para exponer las operaciones de `ProductService` sobre protocolo HTTP.
2. **Manejo de Excepciones Globales (`@ControllerAdvice`):** Capturar el `RuntimeException` del servicio para devolver respuestas estructuradas HTTP como `404 Not Found`.
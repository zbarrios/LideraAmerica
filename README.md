# Lidera America

El proyecto está configurado para funcionar con una base de datos H2 volátil. Si se desea cambiarla, 
se puede hacer a través de la configuración en el archivo application.properties.

## H2 Console

Para interactuar con la base de datos, se puede acceder a la consola de H2 a través de un navegador web. Una vez que la aplicación esté en funcionamiento, acceda a:

http://localhost:8080/h2-console

Ingrese la información de la URL, usuario y contraseña (que también se encuentra en el archivo application.properties):

```yml
url: jdbc:h2:mem:localdb
username: sa 
password: pass
```

Desde la consola podrá visualizar la base de datos y revisar los datos que se han almacenado.

## Supuestos
Para este proyecto, se han asumido los siguientes puntos:

* La relación entre autores y libros es de uno a muchos,
  lo que significa que un libro puede tener un único autor, pero un autor puede estar asociado con varios libros.
* Cuando se realiza una actualización de varios libros para un autor,
  se asume que si estos libros estaban previamente relacionados con otro autor, se desvinculan automáticamente de este último.

## Documentación de la API - Authors

A continuación, se presenta la documentación para los endpoints de la API relacionados con autores.

### Obtener todos los autores

- Método: GET
- Ruta: `/author/all`
- Descripción: Este endpoint permite obtener todos los autores registrados en el sistema.
- Respuesta exitosa: Código 200 OK y lista de objetos `Author` en formato JSON.
- Respuesta de error: Código 404 Not Found si no se encuentran autores registrados.

### Obtener autor por ID

- Método: GET
- Ruta: `/author/{id}`
- Descripción: Este endpoint permite obtener un autor específico por su ID.
- Parámetros de ruta:
  - `id` (Integer, obligatorio): El ID único del autor que se desea obtener.
- Respuesta exitosa: Código 200 OK y objeto `Author` en formato JSON.
- Respuesta de error: Código 404 Not Found si no se encuentra el autor con el ID especificado.

### Obtener autor por nombre

- Método: GET
- Ruta: `/author/by-name/{name}`
- Descripción: Este endpoint permite obtener un autor específico por su nombre.
- Parámetros de ruta:
  - `name` (String, obligatorio): El nombre del autor que se desea obtener.
- Respuesta exitosa: Código 200 OK y objeto `Author` en formato JSON.
- Respuesta de error: Código 404 Not Found si no se encuentra el autor con el nombre especificado.

### Agregar un nuevo autor

- Método: POST
- Ruta: `/author`
- Descripción: Este endpoint permite agregar un nuevo autor al sistema.
- Cuerpo de la solicitud (RequestBody): Objeto `AuthorUpdateCreateRequest` en formato JSON con los datos del nuevo autor.
- Respuesta exitosa: Código 200 OK y objeto `Author` en formato JSON con los datos del autor recién creado.

### Actualizar un autor existente

- Método: PUT
- Ruta: `/author/{id}`
- Descripción: Este endpoint permite actualizar los datos de un autor existente.
- Parámetros de ruta:
  - `id` (Integer, obligatorio): El ID único del autor que se desea actualizar.
- Cuerpo de la solicitud (RequestBody): Objeto `AuthorUpdateCreateRequest` en formato JSON con los nuevos datos del autor.
- Respuesta exitosa: Código 200 OK y objeto `Author` en formato JSON con los datos actualizados del autor.
- Respuesta de error: Código 404 Not Found si no se encuentra el autor con el ID especificado.

### Eliminar un autor por ID

- Método: DELETE
- Ruta: `/author/{id}`
- Descripción: Este endpoint permite eliminar un autor específico por su ID.
- Parámetros de ruta:
  - `id` (Integer, obligatorio): El ID único del autor que se desea eliminar.
- Respuesta exitosa: Código 200 OK y mensaje de éxito en formato JSON.
- Respuesta de error: Código 404 Not Found si no se encuentra el autor con el ID especificado.

## Documentación de la API - Books

A continuación, se presenta la documentación para los endpoints de la API relacionados con libros.

### Obtener todos los libros

- Método: GET
- Ruta: `/book/all`
- Descripción: Este endpoint permite obtener todos los libros registrados en el sistema.
- Respuesta exitosa: Código 200 OK y lista de objetos `Book` en formato JSON.
- Respuesta de error: Código 404 Not Found si no se encuentran libros registrados.

### Obtener libro por título

- Método: GET
- Ruta: `/book/by-name/{name}`
- Descripción: Este endpoint permite obtener un libro específico por su título.
- Parámetros de ruta:
  - `name` (String, obligatorio): El título del libro que se desea obtener.
- Respuesta exitosa: Código 200 OK y objeto `Book` en formato JSON.
- Respuesta de error: Código 404 Not Found si no se encuentra el libro con el título especificado.

### Agregar un nuevo libro

- Método: POST
- Ruta: `/book`
- Descripción: Este endpoint permite agregar un nuevo libro al sistema.
- Cuerpo de la solicitud (RequestBody): Objeto `BookUpdateCreateRequest` en formato JSON con los datos del nuevo libro.
- Respuesta exitosa: Código 200 OK y objeto `Book` en formato JSON con los datos del libro recién creado.

### Actualizar un libro existente

- Método: PUT
- Ruta: `/book/{id}`
- Descripción: Este endpoint permite actualizar los datos de un libro existente.
- Parámetros de ruta:
  - `id` (Integer, obligatorio): El ID único del libro que se desea actualizar.
- Cuerpo de la solicitud (RequestBody): Objeto `BookUpdateCreateRequest` en formato JSON con los nuevos datos del libro.
- Respuesta exitosa: Código 200 OK y objeto `Book` en formato JSON con los datos actualizados del libro.
- Respuesta de error: Código 404 Not Found si no se encuentra el libro con el ID especificado.

### Eliminar un libro por ID

- Método: DELETE
- Ruta: `/book/{id}`
- Descripción: Este endpoint permite eliminar un libro específico por su ID.
- Parámetros de ruta:
  - `id` (Integer, obligatorio): El ID único del libro que se desea eliminar.
- Respuesta exitosa: Código 200 OK y mensaje de éxito en formato JSON.
- Respuesta de error: Código 404 Not Found si no se encuentra el libro con el ID especificado.

# Practica 8
## Se utilizao PAGE-OBJECT-MODEL para realizar esta practica.

Documentacion sobre [Page-Object-Model](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)

### Visión general
Page Object es un patrón de diseño que se ha hecho popular en la automatización de pruebas para mejorar el mantenimiento de pruebas y reducir la duplicación de código. Un *Page object* es una ***clase orientada a objetos*** que sirve de interfaz. Las pruebas utilizan los métodos de esta page object class siempre que se necesite interactuar con la interfaz de usuario de esa página. La ventaja es que si la interfaz de usuario de la página cambia, las pruebas no tienen que cambiar, sólo el código del objeto de página. Posteriormente, todos los cambios para dar soporte a esa nueva interfaz de usuario se encuentran en un único lugar.

### Ventajas
- Existe una separación clara entre el código de prueba y el código específico de la página, como los localizadores y el diseño.
- Existe un único repositorio para los servicios u operaciones que ofrece la página, en lugar de tener estos servicios dispersos a lo largo de las pruebas.
En ambos casos, esto permite que cualquier modificación necesaria debido a cambios en la interfaz de usuario se realice en un único lugar.

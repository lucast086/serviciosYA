# serviciosYA

Web en donde proveedores ofrecen sus servicios y los potenciales clientes pueden consultar contactar y calificar los mismos.

---

## Diseño de la Api

- ### Estructura de la API:

    - Se utiliza un patron MVC
    - Se utiliza nombres de recursos en plural para representar colecciones de recursos.
        - /usuarios como el recurso base para las operaciones relacionadas con los usuarios.

- ### Verbos HTTP y sus usos:

    - Se utiliza el verbo POST para crear / modificar un usuario en la base de datos.
    - Se utiliza el verbo GET para obtener información de un usuario específico.
    - Utiliza el verbo DELETE para eliminar un usuario existente.

- #### Rutas de la API:


- #### Seguridad:
Se considera la implementación de mecanismos de autenticación y autorización para proteger la API y restringir el acceso no autorizado.
Mediante Spring security y el acceso por usuario y contraseña, con control de sesion.

#

## Estructura del proyecto

#### ejemplo de estructura del proyecto

- com.serviciosYA
    - controllers
    - exceptions
    - entities
    - repositories
    - enums
    - services
        - interfaces
    - utils
        - constants
    - configs
    - security
    - **main class**

#

## Formato de Codigo

#### Lenguaje:
    el lenguaje a utilizar es el Ingles

#### Nomeclatura:
    Las clases en singular, comienzan por mayuscula y se usa CamelCase.
    Los metodos comienzan por un verbo, expresan lo que hacen y utilizan CamelCase
    Los paquetes en minusculas y de preferencia en plural y solo una palabra
    Las constantes y los Enums en mayusculas, puedo usar abreviaciones y snake_case.
    Las Interfaces llevaran como prefijo la letra I y utilizan CamelCase.
    Las clases que implementen las interfaces llevan el sufijo Impl y utilizan CamelCase.
    Las variables deben ser autoDescriptivas, sustantivas, utilizan CamelCase. Las variables temporales utilizan el prefijo temp. Las variables para bucles deben ser i,j,k,l. Las variables para denotar tamaños son n,m

#

## Colaboradores
[Turletti, Lucas](https://github.com/lucast086/)
[Martinez, Neuen](https://github.com/NeuenMartinez)
[Grey Vivas, Ingrid]()
[Ibarra, Esteban]()
[Quaglia, Tomas]()

### Tecnologias y Herramientas:

Java 8 / 17
Spring boot 2.7.12
Maven
MySQL
WorkBench
IntelliJ IDE
VScode

### Documentacion y Recursos
 
[Trello](https://trello.com/invite/b/gfW0tnFo/ATTI4d9586263ed3f81dacf34ec99eb5beaa99881028/proyecto-final-egg)
[Class Diagram](https://gitmind.com/app/docs/fg97jf2r)
[Use Case Diagram](https://gitmind.com/app/docs/f5dpc97k)

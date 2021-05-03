# Java - Streams Práctica 7

El objetivo de la siguiente práctica es consolidar los conocimientos adquiridos sobre streams en Java, además de ponerlos en práctica. Para ello, se propone realizar las siguientes actividades:

Desarrolla un programa que realice la lectura de un fichero txt que tenga el siguiente aspecto:
The old man and the gun#2019#David Lowery#93#A lo largo de sus 80 años de vida, el ladrón de bancos Forrest Tucker ha logrado librarse 18 veces de ir a prisión. Ahora retirado, Tucker vive en un hogar de jubilados y ha encontrado en Jewel el amor de su vida. Pero cuando un día ve al detective John Hunt por televisión, el ex atracador siente la necesidad de dar un último golpe y demostrar que aún puede poner en jaque a la policía.#Robert Reford, Danny Glover#21:00{Capitana Marvel#2019#Ana Boden#128#La Tierra se encuentra atrapada en medio de una guerra galáctica entre dos razas alienígenas. Esto hará que Carol Danvers se convierta en una de las heroínas más poderosas del universo.#Jude Law, Samuel L.Jackson#22:00

Como se puede observar, el fichero pertenece a la cartelera de un Cine. Nuestro objetivo es generar un fichero txt, teniendo en cuenta que existen dos tipos de marcas en el fichero origen, # que nos indica un campo nuevo y { que nos indica una nueva película. El fichero txt que se debe obtener tendrá el siguiente formato:

Cartelera de CineFBMoll

-----The old man and the gun------

Año: 2019

Director: David Lowery

Duración: 93 minutos

Sinopsis: A lo largo de sus 80 años de vida, el ladrón de bancos Forrest Tucker ha logrado librarse 18 veces de ir a prisión. Ahora retirado, Tucker vive en un hogar de jubilados y ha encontrado en Jewel el amor de su vida. Pero cuando un día ve al detective John Hunt por televisión, el ex atracador siente la necesidad de dar un último golpe y demostrar que aún puede poner en jaque a la policía.

Reparto: Robert Reford

Sesión: 21:00 horas

-----Capitana Marvel------

Año: 2019

Director: Ana Boden

Duración: 128 minutos

Sinopsis: La Tierra se encuentra atrapada en medio de una guerra galáctica entre dos razas alienígenas. Esto hará que Carol Danvers se convierta en una de las heroínas más poderosas del universo.

Reparto: Jude Law, Samuel L.Jackson

Sesión: 22:00 horas

# El programa debe tener un menú, con las siguientes opciones:

1. Lectura y escritura del fichero de cartelera byte a byte (byte Streams).

2. Lectura y escritura de fichero de cartelera carácter a carácter (character Streams).

3. Lectura y escritura de fichero línea a línea con buffers (character Streams).

4. En todas las opciones se debe solicitar al usuario la ruta del fichero de entrada y de salida.

Además de controlar las excepciones (checked) que son obligatorias, debemos crear una excepción nueva para el caso en que la ruta del fichero de entrada/salida sea inválida (no esté informada) y generando una nueva excepción, llamada RutaInvalida. Esta excepción se capturará en el main, y una vez capturada llamaremos al método imprimirError e imprimiremos el mensaje de la excepción por consola. Este método se caracteriza por:

Tener un constructor que recibe un mensaje que se utilizará para informar y personalizar que a posteriori se escribirá.

Tener un método que permitirá escribir en un fichero de errores el mensaje con la siguiente información mensaje personalizado + timestamp +getStackTrace().

Si hubiera más información en el fichero de errores, esta se debe mantener.

Continuando con el ejercicio anterior, vamos a ampliar el menú para la lectura y escritura de objetos. Concretamente añadiremos la siguiente opción:

Tratamiento de objetos.

5. Esta opción 4, nos enviará a un nuevo menú con estas opciones:

* Lectura línea a línea y escritura con objetos (obteniendo ficheroSalObj).
* Lectura de objetos (leyendo ficheroSalObj) y escritura de objetos (obteniendo ficheroSalObj2).
* Lectura de objetos (leyendo ficheroSalObj2) y escritura por consola (comprobaremos por consola que nos ha escrito bien los objetos en los pasos anteriores).
* Lectura por consola y escritura de objetos. (añadirá objetos al final del fichero existente, eliminado porque da problemas la adición de objetos)
* Volver al menú principal.
Para todas las opciones, se leerá por teclado la ruta de entrada y ruta de salida.

Tal y como hemos visto, necesitarás definir una clase con los campos adecuados para incorporar la información del fichero de cartelera. Analiza y diseña como debería ser la clase Cartelera y comparte tu opinión con tu compañero.

Aprovecha y utiliza “try-with-resources”

Ejercicio recomendable. Desarrolla un programa que realice la lectura de un fichero txt que tenga el siguiente aspecto:
Pepe García Hernández 5 7 3 7 10 c-5

Juan Sánchez García 4 5 5 7 10 7

Adrián Hernández Sánchez 9 5 5 7 10 10

Santiago García Sánchez 10 9 10 9 10 c-5

Como se puede observar, la estructura del fichero es Nombre “espacio” Primer apellido “espacio” Segundo apellido y las notas de cada uno de los módulos (Lenguaje de Marcas, Programación, Entornos de desarrollo, Base de datos, Sistemas Informáticos y FOL).

El objetivo será obtener el boletín de notas de cada uno de los alumnos en un txt diferente, siendo el nombre del txt el nombre y apellidos del alumno. El boletín de notas tendrá el siguiente diseño:

Boletín de notas CIFP FBMOLL

Alumno: Pepe García Hernández

Módulo Nota

Lenguaje de marcas 5

Programación 7

Entornos de desarrollo 3

Base de datos 7

Sistemas informáticos 10

FOL c-5

Nº de módulos aprobados: 5

Nº de módulos suspendidos: 1

Nº de módulos convalidados: 1

Fecha: 2/04/2020 (“obtener fecha actual”)

Lugar: Palma de Mallorca

A partir del ejercicio anterior, realiza (lectura/escritura de objetos):

Una clase que de soporte a los datos almacenados en el fichero de lectura del ejercicio anterior.

Crea un método que lea el fichero txt con el formato del fichero de entrada del ejercicio anterior (“Pepe García Hernández 5 7 3 7 10 c-5”), almacene la información en el objeto creado en el punto anterior y escriba en el fichero de salida el objeto.

Crea un método adicional que lea los ficheros creados con los objetos del apartado b, e imprimir en la consola el formato de las notas.

Revisa el código de tu programa de manera que en el main únicamente se realicen llamadas a métodos, y prepara estos métodos para que en caso de excepción en alguno de los métodos sean tratadas en el main.

Crea una excepción propia de manera que sea usada cuando encuentre un alumno menor de 18 años, y esta excepción se caracterizará por incluir: “El alumno nombre_del_alumno tiene xx años“. El mensaje debe ser guardado en un fichero log txt que debe almacenar los diferentes errores que se están produciendo para que esta información pueda ser tratada por el equipo de mantenimiento del S.I. En el log se debe guardar: Fecha + hora + mensaje + traza de la pila de ejecución (printStackTrace()).

Ejercicio Opcional: Crea una nueva versión del ejercicio de la práctica 6 de la simulación de loterías y primitivas, de manera que mediante buffers escriba toda la información procesada en la simulación, es decir, los datos del sorteo, la apuesta y aciertos de la apuesta en dicho sorteo.

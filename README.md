## Informacion importante:

El juego se encarga de verificar que en el archivo del cual se cargan los datos contenga solo numeros entre 1 y 9, y tambien controla
que este contenga una solucion valida, de lo contrario al iniciar el juego se mostrara un mensaje de error al iniciar el tablero.
Si el archivo tiene mas de 9 filas o columnas, solo se verifican las primeras 9, y el resto se ignoran. Fue implementado de esta forma 
porque si el archivo que contiene una solucion valida tiene caracteres fuera de las filas y columnas que necesito (algun caracter extra
por equivocacion), el juego pueda de igual forma comenzar utilizando unicamente los datos que necesita.

El juego comienza cuando clickeas el boton "Iniciar juego", al hacer click comienza a contar el tiempo y se inicia el tablero inicial.
Las celdas iniciales quedan fijas, y no se puede cambiar su valor.

<ins>Instrucciones de juego:</ins>
-Para insertar un numero en el tablero, seleccionar el numero deseado en el menu de OPCIONES (por defecto se selecciona el 1), y luego 
clickear la celda en la que se quiere insertar el numero.
-Si la celda que se inserto incumple alguna regla del Sudoku, entonces la celda insertada y la celda que hace que la regla no se cumpla
se marcan con fondo rojo.
-Si una celda cambia su validez al cambiar otra celda, esta celda vuelve a tener fondo blanco.

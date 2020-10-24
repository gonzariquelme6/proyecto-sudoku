package logica;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Random;

/**
 * Clase Juego que implementa un tablero de Sudoku y verifica sus reglas
 * @author Gonzalo M Riquelme Ludwig - LU:125469
 */
public class Juego {
	private Celda [][] tablero;
	private int [] [] verificarTablero;
	private int cantFilas,ultimoNum;
	private Celda [] lista_numeros;
	private long tiempoInicio;
	private int alto_bloque=3,ancho_bloque=3;
	private boolean tablero_valido;
	
	/*
	 * Constructor que lee y verifica el archivo para obtener el tablero inicial y inicia el juego.
	 */
	public Juego() {
		BufferedReader br;
		String linea;
		int num,filas,random;
		Scanner scanner;
		Random rnd;
		
		cantFilas = 9;
		tablero = new Celda[cantFilas][cantFilas];
		verificarTablero = new int [cantFilas][cantFilas];
		ultimoNum=1;
		tiempoInicio = System.currentTimeMillis();
		
		try {
			br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/files/path.txt")));
			rnd = new Random();
			filas = 0;
			tablero_valido = true;
			
			//Mientras falten leer lineas y haya lineas disponibles
			while( (filas<cantFilas) && (linea = br.readLine())!=null) {
				scanner = new Scanner(linea);
				
				//Por cada numero que leo del archivo lo agrego al tablero y a una matriz auxiliar que uso para verificar el contenido del archivo
				for (int i=0;i<cantFilas;i++) {
					num = scanner.nextInt();
					if (num>0 && num<10) {
						//la probabilidad de que una celda tenga un numero es de 1/2
						random = rnd.nextInt(2);
						verificarTablero[filas][i] = num;
						tablero [filas][i] = new Celda();
						if (random==0) {
							tablero [filas][i].setValor(num);
							tablero [filas][i].setValida(true);
						}
					}else {
						tablero_valido = false;
					}
				}
				filas++;
			}
			
			//Creo el arreglo de opciones de numeros para el tablero
			lista_numeros = new Celda[cantFilas];
			for (int j=0;j<cantFilas;j++) {
				lista_numeros[j] = new Celda();
				lista_numeros[j].setValor(j+1);
			}
			br.close();
			
		}catch(IOException e) {
			tablero_valido=false;
		}
	}
	
	/**
	 * Metodo que verifica que el contenido del archivo con el que se inicia el tablero contenga una solucion valida
	 * @return true si el contendio del archivo contiene una solucion valida.
	 */
	public boolean verificarTableroInicial() {
		boolean res = tablero_valido;
		
		//verifico columnas
		for (int fil=0;fil<cantFilas && res;fil++)
			for (int col=0;col<cantFilas-1 && res;col++)
				for (int aux = col+1;aux<cantFilas && res;aux++)
					if (verificarTablero[fil][col] == verificarTablero[fil][aux]) {
						res = false;
					}
		//verifico filas
		for (int col=0;col<cantFilas && res;col++)
			for (int fil=0;fil<cantFilas-1 && res;fil++)
				for (int aux = fil+1;aux<cantFilas && res;aux++)
					if (verificarTablero[fil][col] == verificarTablero[aux][col]) {
						res = false;
					}
		
		if (res) {
			res = verificarBloqueTableroInicio(0,2,0,2) && verificarBloqueTableroInicio(0,2,3,5) && verificarBloqueTableroInicio(0,2,6,8) && 
				verificarBloqueTableroInicio(3,5,0,2) && verificarBloqueTableroInicio(3,5,3,5) && verificarBloqueTableroInicio(3,5,6,8) &&
				verificarBloqueTableroInicio(6,8,0,2) && verificarBloqueTableroInicio(6,8,3,5) && verificarBloqueTableroInicio(6,8,6,8);
		}
		
		return res;
	}
	
	/**
	 * Metodo que verifica que el valor que se quiere poner en la celda cumpla con las reglas del sudoku.
	 * @param fila fila de la celda en la que se va a insertar el nuevo valor.
	 * @param col columna de la celda en la que se va a insertar el nuevo valor.
	 * @param valor valor que se va a insertar en la celda.
	 * @return true si el valor a insertar en la celda cumple con las reglas del sudoku.
	 */
	public boolean verificar(int fila, int col, int valor) {
		boolean res = true;
		int inicio_f,inicio_c;
		
		if (tablero[fila][col].getValor()!=null && tablero[fila][col].getValor()==valor) {
			res = true;
		}else {
			//si alguna celda de su misma fila o columna tiene el mismo valor entonces ambas celdas (la que ya estaba y la nueva) son invalidas
			for (int i=0;i<cantFilas;i++) {
				if (tablero[fila][i].getValor()!=null && tablero[fila][i].getValor()==valor) {
						res=false;
						tablero[fila][i].setValida(false);
				}
				if (tablero[i][col].getValor()!=null && tablero[i][col].getValor()==valor) {
					res=false;
					tablero[i][col].setValida(false);
				}
			}
			
			inicio_f = (fila/alto_bloque)*alto_bloque;
			inicio_c = (col/ancho_bloque)*ancho_bloque;
			
			//si alguna celda de su mismo subpanel tiene el mismo valor entonces ambas celdas (la que ya estaba y la nueva) son invalidas
			for (int i=inicio_f;i<inicio_f+alto_bloque;i++) {
				for (int j=inicio_c;j<inicio_c+ancho_bloque;j++) {
					if (tablero[i][j].getValor()!=null && tablero[i][j].getValor()==valor) {
						res = false;
						tablero[i][j].setValida(false);
					}
				}
			}
			
			//actualizo el valor de la celda nueva
			actualizarCelda(fila,col,valor,res);
			
			//busco las celdas de la misma fila, columna y subpanel que eran erroneas y pueden cambiar al cambiar la nueva celda
			for (int i=0;i<cantFilas;i++) {
				if (tablero[fila][i].getValor()!=null && i!=col && tablero[fila][i].getValor()!=valor && tablero[fila][i].getValida()==false) {
					revalidarCelda(fila,i);
				}
				if (tablero[i][col].getValor()!=null && i!=fila && tablero[i][col].getValor()!=valor && tablero[i][col].getValida()==false) {
					revalidarCelda(i,col);
				}
			}
			for (int i=inicio_f;i<inicio_f+alto_bloque;i++) {
				for (int j=0;j<inicio_c+ancho_bloque;j++) {
					if (tablero[i][j].getValor()!=null && i!=fila && j!=col && tablero[i][j].getValor()!=valor && tablero[i][j].getValida()==false) {
						revalidarCelda(i,j);
					}
				}
			}
		}
		return res;
	}
	
	/**
	 * Metodo que obtiene el tiempo que paso desde que se inicio la partida
	 * @return tiempo que paso desde que se inicio la partida
	 */
	public long getTiempoActual() {
		return System.currentTimeMillis()-tiempoInicio;
	}
	
	/**
	 * Metodo que retorna el tamaño de los subpaneles del tablero
	 * @return tamaño de los subpaneles del tablero.
	 */
	public int getTamanioSubpanel() {
		return alto_bloque;
	}
	
	/**
	 * Metodo que verifica si el tablero esta completo y el juego se termino.
	 * @return true si el tablero esta completo y el juego se termino.
	 */
	public boolean terminar() {
		boolean gano = false;
		int cant = 0;
		for (int i=0;i<cantFilas;i++)
			for(int j=0;j<cantFilas;j++) {
				if (tablero[i][j].getValor()!=null && tablero[i][j].getValida())
					cant++;
			}
		if (cant==81)
			gano=true;
		return gano;
	}
	
	/**
	 * Metodo que retorna la celda correspondiente a la posicion recibida por parametro
	 * @param i fila de la celda a retornar.
	 * @param j columna de la celda a retornar.
	 * @return celda con fila i y columna j.
	 */
	public Celda getCelda(int i, int j){
		return tablero[i][j];
	}
	
	/**
	 * Metodo que retorna la cantidad de filas del tablero.
	 * @return cantidad de filas del tablero.
	 */
	public int getCantFilas() {
		return cantFilas;
	}
	
	/**
	 * Metodo que retorna el ultimo numero seleccionado en el panel de opciones numericas.
	 * @return ultimo numero seleccionado en el panel de opciones numericas.
	 */
	public int getUltimoNum() {
		return ultimoNum;
	}
	
	/**
	 * Metodo que retorna la celda correspondiente al ultimo valor numerico seleccionado.
	 * @param i indice del ultimo valor numerico seleccionado.
	 * @return Celda correspondiente al indice obtenido por parametro. 
	 */
	public Celda getNumeros(int i) {
		return lista_numeros[i];
	}
	/**
	 * Metodo que actualiza el tamaño de los subpaneles del tablero
	 * @param tam nuevo tamaño de los subpaneles del tablero.
	 */
	public void setTamanioSubpanel(int tam) {
		alto_bloque = tam;
		ancho_bloque = tam;
	}
	
	/**
	 * Metodo que actualiza una celda.
	 * @param i fila de la celda a actualizar.
	 * @param j columna de la celda a actualizar
	 * @param valor valor a insertar en la celda.
	 * @param valida validez de la celda.
	 */
	public void actualizarCelda(int i, int j, int valor, boolean valida) {
		tablero[i][j].actualizar(valor);
		tablero[i][j].setValida(valida);
	}
	
	/**
	 * Metodo que actualiza el ultimo numero seleccionado en el panel de opciones numericas
	 * @param i ultimo numero seleccionado en el panel de opciones numericas
	 */
	public void setUltimoNum(int i) {
		ultimoNum=i;
	}
	
	/**
	 * Metodo que se encarga de verificar y cambiar las celdas que puedan haber cambiado su estado luego de la insercion de un valor nuevo 
	 * @param fil fila de la celda a verificar.
	 * @param col columna de la celda a verificar.
	 */
	private void revalidarCelda(int fil, int col) {
		boolean revisar = true;
		int inicio_f,inicio_c;
		
		//control de valores en la misma fila
		for (int i=0;i<cantFilas && revisar;i++) {
			if (tablero[fil][i].getValor()!=null && i!=col && tablero[fil][i].getValor()==tablero[fil][col].getValor()) {
				revisar=false;
			}
		}
		//control de valores en la misma columna
		if (revisar) {
			for (int i=0;i<cantFilas && revisar;i++) {
				if (tablero[i][col].getValor()!=null && i!=fil && tablero[i][col].getValor()==tablero[fil][col].getValor()) {
					revisar=false;
				}
			}
		}
		//control de valores en el mismo bloque
		if (revisar) {
			inicio_f = (fil/alto_bloque)*alto_bloque;
			inicio_c = (col/ancho_bloque)*ancho_bloque;
			for (int i=inicio_f;i<inicio_f+alto_bloque;i++) {
				for (int j=inicio_c;j<inicio_c+ancho_bloque;j++) {
					if (tablero[i][j].getValor()!=null && i!=fil && j!=col && tablero[i][j].getValor()==tablero[fil][col].getValor()) {
						revisar = false;
					}
				}
			}
		}
		
		//si la celda cumple con las reglas incremento el numero de correctos y seteo la celda como valida
		if (revisar) {
			tablero[fil][col].setValida(true);
		}
			
		
	}
	
	/**
	 * Metodo auxiliar privado que controla que los subpaneles del archivo con el que se crea el juego cumplan con las reglas del sudoku
	 * @param ifil fila inicial del subpanel.
	 * @param ffil fila final del subpanel.
	 * @param icol columna inicial del subpanel.
	 * @param fcol columna final del subpanel.
	 * @return true si el bloque verificado cumple con todas las reglas del sudoku.
	 */
	private boolean verificarBloqueTableroInicio(int ifil, int ffil, int icol, int fcol) {
		boolean res = true;
		int [] valores = new int [9];
		int cont=0;
		
		//Agrego a un arreglo el contenido del bloque
		for (int i=ifil;i<=ffil;i++)
			for (int j=icol;j<=fcol;j++) {
				valores[cont++] = verificarTablero[i][j];
			}
		//Verifico que en el arreglo no haya valores repetidos
		for (int i=0;i<valores.length-1 && res;i++)
			for (int j=i+1;j<valores.length && res;j++)
				if (valores[i]==valores[j]) {
					res = false;
				}
		return res;
	}
}

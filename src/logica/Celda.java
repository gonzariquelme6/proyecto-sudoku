package logica;

/**
 * Clase que modela el comportamiento de una Celda de un tablero de Sudoku
 * @author Gonzalo M Riquelme Ludwig - LU:125469
 */
public class Celda {
	private Integer valor;
	private EntidadGrafica entidadGrafica;
	private boolean valida;
	
	/**
	 * Crea una celda nueva creando una nueva entidad grafica.
	 */
	public Celda() {
		valor = null;
		entidadGrafica = new EntidadGrafica();
	}
	
	/**
	 * Metodo que retorna el valor de la Celda.
	 * @return valor de la Celda.
	 */
	public Integer getValor() {
		return this.valor;
	}
	
	/**
	 * Metodo que retorna la entidad grafica de la Celda.
	 * @return entidad grafica de la Celda.
	 */
	public EntidadGrafica getEntidadGrafica() {
		return this.entidadGrafica;
	}
	
	/**
	 * Metodo que retorna la validez de la celda.
	 * @return true si la celda es valida.
	 */
	public boolean getValida() {
		return valida;
	}
	
	/**
	 * Metodo que actualiza la celda con el valor nuevo.
	 * @param valor nuevo valor de la celda.
	 */
	public void setValor(Integer valor) {
		if (valor!=null && valor < this.getCantElementos()) {
			this.valor = valor;
			this.entidadGrafica.actualizar(this.valor);
		}else {
			this.valor = null;
		}
	}
	
	/**
	 * Metodo que actualiza la entidad grafica de la celda.
	 * @param g nueva entidad grafica.
	 */
	public void setGrafica(EntidadGrafica g) {
		this.entidadGrafica = g;
	}
	
	/**
	 * Metodo que actualiza la celda con el valor nuevo
	 * @param i nuevo valor de la celda.
	 */
	public void actualizar(int i) {
		valor = i;
		entidadGrafica.actualizar(this.valor);
	}
	
	/**
	 * Metodo que actualiza la validez de la celda
	 * @param b validez de la celda.
	 */
	public void setValida(boolean b) {
		valida=b;
		if (entidadGrafica.getEtiqueta()!=null) {
			if (b==true) {
				entidadGrafica.validarEtiqueta(true);
			}else {
				entidadGrafica.validarEtiqueta(false);
			}
		}
	}
		
	/**
	 * Retorna la cantidad de elementos de la entidad grafica de la celda
	 * @return cantidad de elementos de la entidad grafica de la celda.
	 */
	private int getCantElementos() {
		return entidadGrafica.getImagenes().length;
	}
}

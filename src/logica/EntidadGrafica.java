package logica;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Clase que implementa la entidad grafica de una celda.
 * @author Gonzalo M Riquelme Ludwig - LU:125469
 *
 */
public class EntidadGrafica {

	private ImageIcon grafico;
	private String [] imagenes;
	private JLabel etiqueta;
	
	/**
	 * Crea una entidad grafica nueva
	 */
	public EntidadGrafica() {
		grafico = new ImageIcon();
		imagenes = new String[] {"/files/num0.png","/files/num1.png", "/files/num2.png", "/files/num3.png", "/files/num4.png", "/files/num5.png", "/files/num6.png", "/files/num7.png","/files/num8.png","/files/num9.png", };
	}
	
	/**
	 * Metodo que retorna el grafico de la entidad grafica.
	 * @return grafico de la entidad grafica.
	 */
	public ImageIcon getGrafico() {
		return this.grafico;
	}
	
	/**
	 * Metodo que retorna las imagenes de la entidad grafica
	 * @return imagenes de la entidad grafica.
	 */
	public String[] getImagenes() {
		return this.imagenes;
	}
	
	/**
	 * Metodo que retorna la etiqueta de la entidad grafica.
	 * @return etiqueta de la entidad grafica.
	 */
	public JLabel getEtiqueta() {
		return etiqueta;
	}
	
	/**
	 * Metodo que actualiza el grafico de la entidad grafica. 
	 * @param indice de la imagen del nuevo grafico de la entidad grafica.
	 */
	public void actualizar(int indice) {
		if (indice < this.imagenes.length) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[indice]));
			this.grafico.setImage(imageIcon.getImage());
		}
	}
	
	/**
	 * Metodo que actualiza graficamente la etiqueta asociada a la entidad grafica
	 * @param valido si la etiqueta debe tener fondo blanco, sino el fondo es rojo
	 */
	public void validarEtiqueta(boolean valido) {
		if (!valido)
			etiqueta.setBackground(Color.red);
		else
			etiqueta.setBackground(Color.WHITE);
	}
	
	/**
	 * Metodo que actualiza el grafico de la entidad grafica
	 * @param grafico nuevo grafico de la entidad grafica.
	 */
	public void setGrafico(ImageIcon grafico) {
		this.grafico = grafico;
	}
	
	/**
	 * Metodo que actualiza las imagenes de la entidad grafica
	 * @param imagenes nuevas imagenes de la entidad grafica.
	 */
	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}
	
	/**
	 * Metodo que actualiza la etiqueta de la entidad grafica.
	 * @param etiqueta nueva etiqueta de la entidad grafica.
	 */
	public void setEtiqueta(JLabel etiqueta) {
		this.etiqueta=etiqueta;
	}
}

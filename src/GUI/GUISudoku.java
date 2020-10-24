package GUI;
import logica.Juego;
import logica.Celda;
import logica.EntidadGrafica;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.*;
import javax.swing.Timer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GUISudoku extends JFrame {

	private JPanel contentPane;
	private Juego juego;
	private Timer timer;
	private int delayTimer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUISudoku frame = new GUISudoku();
					ImageIcon icon = new ImageIcon(this.getClass().getResource("/files/icono.png"));
					frame.setIconImage(icon.getImage());
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUISudoku() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 550);
		setTitle("Sudoku");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Panel Sudoku
		JPanel panelSudoku = new JPanel();
		panelSudoku.setBorder(new LineBorder(Color.BLACK, 2));
		panelSudoku.setBackground(new Color(153, 204, 255));
		panelSudoku.setPreferredSize(new Dimension(500,400));
		contentPane.add(panelSudoku,BorderLayout.CENTER);
		
		//Panel opciones de numeros
		JPanel panelNumeros = new JPanel();
		contentPane.add(panelNumeros, BorderLayout.EAST);
		panelNumeros.setLayout(new GridLayout(10, 1, 0, 0));
		panelNumeros.setPreferredSize(new Dimension(90,500));
		
		JLabel lblNumeros = new JLabel("    Opciones: ");
		lblNumeros.setFont(new Font("Arial",Font.BOLD,13));
		panelNumeros.add(lblNumeros);
		
		//Panel Inferior
		JPanel panelInferior = new JPanel();
		panelInferior.setBorder(new LineBorder(Color.BLACK, 2));
		panelInferior.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panelInferior,BorderLayout.SOUTH);
		panelInferior.setLayout(new BorderLayout(0, 0));
				
			
		//Panel Reloj
		JPanel panelTimer = new JPanel();
		panelInferior.add(panelTimer, BorderLayout.WEST);
		panelTimer.setLayout(new BorderLayout(0, 0));
				
		JLabel lblTiempoActual = new JLabel("Tiempo actual:");
		lblTiempoActual.setFont(new Font("Arial",Font.BOLD,13));
		panelTimer.add(lblTiempoActual,BorderLayout.NORTH);
		
		JPanel panelReloj = new JPanel();
		panelReloj.setLayout(new GridLayout(1, 8, 0, 0));
		panelTimer.add(panelReloj,BorderLayout.SOUTH);
				
				
		JLabel lblHora1 = new JLabel();
		EntidadGrafica graf_hora1 = new EntidadGrafica();
		graf_hora1.actualizar(0);
		lblHora1.setIcon(graf_hora1.getGrafico());
		lblHora1.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				reDimensionar(lblHora1, graf_hora1.getGrafico());
				lblHora1.setIcon(graf_hora1.getGrafico());
			}
		});
		panelReloj.add(lblHora1);		
				
		JLabel lblHora2 = new JLabel();
		EntidadGrafica graf_hora2 = new EntidadGrafica();
		graf_hora2.actualizar(0);
		lblHora2.setIcon(graf_hora2.getGrafico());
		lblHora2.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				reDimensionar(lblHora2, graf_hora2.getGrafico());
				lblHora2.setIcon(graf_hora2.getGrafico());
			}
		});
		panelReloj.add(lblHora2);
		
		JLabel lblSeparador = new JLabel(":");
		lblSeparador.setFont(new Font("Arial",Font.BOLD,30));
		panelReloj.add(lblSeparador);
		
		JLabel lblMin1 = new JLabel();
		EntidadGrafica graf_min1 = new EntidadGrafica();
		graf_min1.actualizar(0);
		lblMin1.setIcon(graf_min1.getGrafico());
		lblMin1.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				reDimensionar(lblMin1, graf_min1.getGrafico());
				lblMin1.setIcon(graf_min1.getGrafico());
			}
		});
		panelReloj.add(lblMin1);
		
		JLabel lblMin2 = new JLabel();
		EntidadGrafica graf_min2 = new EntidadGrafica();
		graf_min2.actualizar(0);
		lblMin2.setIcon(graf_min2.getGrafico());
		lblMin2.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				reDimensionar(lblMin2, graf_min2.getGrafico());
				lblMin2.setIcon(graf_min2.getGrafico());
			}
		});
		panelReloj.add(lblMin2);
		
		JLabel lblSeparador2 = new JLabel(":");
		lblSeparador2.setFont(new Font("Arial",Font.BOLD,30));
		panelReloj.add(lblSeparador2);
		
		JLabel lblSeg1 = new JLabel();
		EntidadGrafica graf_seg1 = new EntidadGrafica();
		graf_seg1.actualizar(0);
		lblSeg1.setIcon(graf_seg1.getGrafico());
		lblSeg1.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				reDimensionar(lblSeg1, graf_seg1.getGrafico());
				lblSeg1.setIcon(graf_seg1.getGrafico());
			}
		});
		panelReloj.add(lblSeg1);	
				
		JLabel lblSeg2 = new JLabel();
		EntidadGrafica graf_seg2 = new EntidadGrafica();
		graf_seg2.actualizar(0);
		lblSeg2.setIcon(graf_seg2.getGrafico());
		lblSeg2.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				reDimensionar(lblSeg2, graf_seg2.getGrafico());
				lblSeg2.setIcon(graf_seg2.getGrafico());
			}
		});
		panelReloj.add(lblSeg2);
		
		//Timer
		delayTimer = 1000;
		timer = new Timer(delayTimer,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				long tiempoAct = juego.getTiempoActual();
				
				int segs = (int)(tiempoAct/1000)%60;
				int mins = (int)(tiempoAct/1000)/60;
				int hrs = (int)(tiempoAct/1000)/3600;
				
				graf_seg1.actualizar(segs/10);
				graf_seg2.actualizar(segs%10);
				graf_min1.actualizar(mins/10);
				graf_min2.actualizar(mins%10);
				graf_hora1.actualizar(hrs/10);
				graf_hora2.actualizar(hrs%10);
				
				reDimensionar(lblSeg1, graf_seg1.getGrafico());
				reDimensionar(lblSeg2, graf_seg2.getGrafico());
				reDimensionar(lblMin1, graf_min1.getGrafico());
				reDimensionar(lblMin2, graf_min2.getGrafico());
				reDimensionar(lblHora1, graf_hora1.getGrafico());
				reDimensionar(lblHora2, graf_hora2.getGrafico());
				
				panelReloj.repaint();
			}
		});
		
	
		//Panel Boton Iniciar
		JButton btnIniciar = new JButton("Iniciar juego");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnIniciar.setEnabled(false);
				btnIniciar.setText("Juego en curso");
				iniciarTablero(panelSudoku,panelNumeros);
			}
			
		});
		panelInferior.add(btnIniciar,BorderLayout.CENTER);


	}
	
	/**
	 * Metodo auxiliar que inicializa el tablero y el timer al apretar el boton de "Iniciar juego"
	 * @param panelTablero panel en el que se agregan las JLabels correspondientes a las celdas del tablero.
	 * @param panelOpciones panel en el que se agregan los JButtons correspondientes a las opciones de numeros.
	 */
	private void iniciarTablero(JPanel panelTablero, JPanel panelOpciones) {
		int subpanel;
		
		timer.start();
		
		//bordes de casillas
		Border borde_top = new MatteBorder(3, 1, 1, 1, Color.black);
		Border borde_bot = new MatteBorder(1, 1, 3, 1, Color.black);
		Border borde_izq = new MatteBorder(1, 3, 1, 1, Color.black);
		Border borde_top_izq = new MatteBorder(3, 3, 1, 1, Color.black);
		Border borde_bot_izq = new MatteBorder(1, 3, 3, 1, Color.black);
		Border borde_der = new MatteBorder(1, 1, 1, 3, Color.black);
		Border borde_bot_der = new MatteBorder(1, 1, 3, 3, Color.black);
		Border borde_top_der = new MatteBorder(3, 1, 1, 3, Color.black);
		
		
		//Grilla de numeros
		juego = new Juego();
		
		boolean correcto = juego.verificarTableroInicial();
		if (!correcto) {
			JOptionPane.showMessageDialog(contentPane, "Hubo un error al iniciar el tablero", "Error",
			        JOptionPane.WARNING_MESSAGE);
		}
		
		panelTablero.setLayout(new GridLayout(juego.getCantFilas(), juego.getCantFilas(), 0, 0));
		
		//creo las labels del tablero
		for (int i=0;i<juego.getCantFilas();i++) {
			for (int j=0;j<juego.getCantFilas();j++) {
				//guardo la fila y columna para usarlas en el oyente
				int fila = i;
				int col=j;
				Celda c = juego.getCelda(i, j);
				ImageIcon grafico = c.getEntidadGrafica().getGrafico();
				JLabel label = new JLabel();
				c.getEntidadGrafica().setEtiqueta(label);
				label.setOpaque(true);
				label.setBackground(Color.white);
				label.setBorder(new LineBorder(Color.BLACK, 1));
				
				subpanel = juego.getTamanioSubpanel();
				
				//segun la posicion de la label en el tablero me fijo que borde le pongo
				if (i==0)
					label.setBorder(borde_top);
				else if (i==(subpanel-1) || i==(subpanel*2)-1 || i==(subpanel*3)-1)
					label.setBorder(borde_bot);
				if (j==0) {
					if (i==0)
						label.setBorder(borde_top_izq);
					else if (i==(subpanel-1) || i==(subpanel*2)-1 || i==(subpanel*3)-1)
						label.setBorder(borde_bot_izq);
					else
						label.setBorder(borde_izq);
				}else if (j==(subpanel-1) || j==(subpanel*2)-1 || j==(subpanel*3)-1) {
					if (i==0)
						label.setBorder(borde_top_der);
					else if (i==(subpanel-1) || i==(subpanel*2)-1 || i==(subpanel*3)-1)
						label.setBorder(borde_bot_der);
					else 
						label.setBorder(borde_der);
				}
				panelTablero.add(label);
				
				
				label.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent e) {
						reDimensionar(label, grafico);
						label.setIcon(grafico);
					}
				});
				
				if (!(c.getValor()!=null)){
					label.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							boolean valido,gano;
							valido = juego.verificar(fila,col,juego.getUltimoNum());
							reDimensionar(label,grafico);
							panelTablero.repaint();
							
							if (valido) {
								gano = juego.terminar();
								if (gano) {
									timer.stop();
									JOptionPane.showMessageDialog(contentPane, "GANASTE!!!","Juego terminado",JOptionPane.NO_OPTION);
								}
							}	
						}
					});
				}
			}
		}
		
		//creo el panel de opciones posibles de numeros
		for (int i=0;i<juego.getCantFilas();i++) {
			Celda c = juego.getNumeros(i);
			ImageIcon grafico = c.getEntidadGrafica().getGrafico();
			JButton btn = new JButton(grafico);
			btn.setName(""+(i+1));
			panelOpciones.add(btn);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton btnSeleccionado = (JButton) e.getSource();
					int num=Integer.parseInt(btnSeleccionado.getName());
					juego.setUltimoNum(num);
				}
			});
		}
	}
	
	/**
	 * Metodo privado que redimensiona la imagen de una Jlabel
	 * @param label etiqueta a redimensionar su imagen.
	 * @param grafico imagen a redimensionar.	
	 */
	private void reDimensionar(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}

}

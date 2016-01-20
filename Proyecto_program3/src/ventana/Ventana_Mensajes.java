package ventana;

import javax.swing.JFrame;
import javax.swing.JPanel;











import java.awt.Dimension;



import java.awt.Toolkit;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import baseDeDatos.BaseDeDatos;
import objetos.DatoParaTabla;
import objetos.Mensaje;
import objetos.MiTableModel;
import objetos.Persona;

public class Ventana_Mensajes extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Modelo de datos propio
	private static MiTableModel datos;
	private static MiTableModel datos2;
	private static boolean bandeja;  //true --> BandejaDeEntrada     
									 //false --> Enviados

	// [02] Renderers para alinear distinto que a la izquierda
	private static DefaultTableCellRenderer rendererDerecha = new DefaultTableCellRenderer();
	private static DefaultTableCellRenderer rendererCentrado = new DefaultTableCellRenderer();
	static {
		rendererDerecha.setHorizontalAlignment(JLabel.RIGHT);
		rendererCentrado.setHorizontalAlignment(JLabel.CENTER);
	}
	// Componentes Nuevo Mensaje
	private static JPanel panelNuevoMensaje;
	private static JLabel lblPara;
	private static JTextField txtPara;
	private static JLabel lblAsunto;
	private static JTextField txtAsunto;
	private static JLabel lblMensaje;
	private static JTextArea txtMensaje;
	private static JButton btnEnviar;

	// Componentes Bandeja de Entrada
	private static JPanel panel;
	private static JButton btnBusqueda;
	private static JButton btnPerfil;
	private static JButton btnMensaje;
	private static JButton btnAtras;
	
	private static JPanel panelBotonera;
	private static JButton btnNuevoMensaje;
	private static JButton btnBandejaDeEntrada;
	private static JButton btnEnviados;
	private static JPanel panelBandejaEntrada;
	private static JButton btnEliminar;
	private static JScrollPane scrollPane;
	private static Ventana_Mensajes tabla;
	
	//Componentes ver Contenido
	private static JPanel panel_contenido;
	private static JLabel lblDe;
	private static JLabel lblAsuntoContenido;
	private static JLabel lblMensajeContenido;
	private static JTextArea txtMensajeContenido;
	
	//ArrayLists
	private static ArrayList<Mensaje> bandejaEntrada;
	private static ArrayList<Mensaje> enviados;
	private static ArrayList<Mensaje> eliminar = new ArrayList<Mensaje>();
	

	public Ventana_Mensajes(MiTableModel modelo) {
		super(modelo);
		// Fijar tamaño preferido de la tabla
		setPreferredScrollableViewportSize(new Dimension(491, 252));
		// [02] Asignar renderers de alineación horizontal
		getColumn("Asunto").setCellRenderer(rendererCentrado);
		getColumn("Hora").setCellRenderer(rendererCentrado);
		getColumn("Fecha").setCellRenderer(rendererCentrado);
		// [03] Asignar anchuras iniciales
		getColumn("Asunto").setMinWidth(170);
		getColumn("De").setMinWidth(50);
		getColumn("Tipo").setMinWidth(50);
		getColumn("Hora").setMinWidth(15);
		getColumn("Fecha").setMinWidth(50);
	}

	public void setModel(TableModel t) {
		if (t instanceof MiTableModel)
			super.setModel(t);
		else
			throw new IncompatibleClassChangeError(
					"No se puede asignar cualquier modelo de datos a una MIJTable");
	}

	public MiTableModel getMiTableModel() {
		return (MiTableModel) getModel();
	}

	public static void crearYMostrarGUI() {

		// CODIGO NUEVO MENSAJE
		bandeja = true;
		
		panelNuevoMensaje = new JPanel();
		panelNuevoMensaje.setBounds(161, 33, 517, 383);
		panelNuevoMensaje.setLayout(null);

		lblPara = new JLabel("Para: ");
		lblPara.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPara.setBounds(28, 36, 31, 14);
		panelNuevoMensaje.add(lblPara);

		txtPara = new JTextField();
		txtPara.setBounds(117, 34, 345, 20);
		panelNuevoMensaje.add(txtPara);
		txtPara.setColumns(10);

		lblAsunto = new JLabel("Asunto: ");
		lblAsunto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAsunto.setBounds(28, 100, 55, 14);
		panelNuevoMensaje.add(lblAsunto);

		txtAsunto = new JTextField();
		txtAsunto.setColumns(10);
		txtAsunto.setBounds(117, 100, 345, 20);
		panelNuevoMensaje.add(txtAsunto);

		lblMensaje = new JLabel("Mensaje:");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMensaje.setBounds(28, 174, 55, 14);
		panelNuevoMensaje.add(lblMensaje);

		btnEnviar = new JButton("ENVIAR");
		btnEnviar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEnviar.setBounds(373, 349, 89, 23);
		panelNuevoMensaje.add(btnEnviar);

		txtMensaje = new JTextArea();
		txtMensaje.setBounds(28, 199, 434, 129);
		txtMensaje.setLineWrap(true);
		txtMensaje.setWrapStyleWord(true);
		txtMensaje.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		panelNuevoMensaje.add(txtMensaje);

		// CODIGO BADEJA ENTRADA
		// Crear datos de prueba
		datos = new MiTableModel(Mensaje.nombresAtributos,
				Mensaje.atributosEditables);
		datos2 = new MiTableModel(Mensaje.nombresAtributos,
				Mensaje.atributosEditables);
		
		if (Ventana_Login.getQuien() == 1) { //ALUMNO
			bandejaEntrada = BaseDeDatos
					.bandejaEntradaAlumno(Ventana_Login.persona.getDni());
			Ventana_Login.persona.setBandejaEntrada(bandejaEntrada);

			enviados = BaseDeDatos.enviadosAlumno(Ventana_Login.persona
					.getDni());
			Ventana_Login.persona.setEnviados(enviados);
		}else{  //PROFESOR
			bandejaEntrada = BaseDeDatos
					.bandejaEntradaProfesor(Ventana_Login.persona.getDni());
			Ventana_Login.persona.setBandejaEntrada(bandejaEntrada);

			enviados = BaseDeDatos.enviadosProfesor(Ventana_Login.persona
					.getDni());
			Ventana_Login.persona.setEnviados(enviados);
		}
		for (Mensaje mensaje : Ventana_Login.persona.getBandejaEntrada()){
			datos.insertar(mensaje);
		}
		for (Mensaje mensaje : Ventana_Login.persona.getEnviados()) {
			datos2.insertar(mensaje);
		}
		//DATOS PRUEBA SIN USUSARIO INICIAL
		// datos.insertar( new Peticion( "Petición", "Piruli",
		// "Clase de hoy", "15:30", "03/12/2015", new Boolean(false) ) );
		// datos.insertar( new Mensaje( "Mensaje", "Juan",
		// "Clase de mañana", "18:00", "01/11/2015", new Boolean(false) ) );
		// datos.insertar( new Peticion( "Petición", "Lola",
		// "Cancelar clase", "11:30", "02/10/2015", new Boolean(false) ) );
		// datos.insertar( new Mensaje( "Mensaje", "Catalina",
		// "Clase de ayer", "20:00", "01/11/2015", new Boolean(false) ) );
		// datos.insertar(new Peticion("Petición", "Periquita",
		// "No quiero dar mas clase", "15:00", "12/12/2012", new
		// Boolean(false)));

		// Crear la tabla y el scrollpane
		// table = new JTable();
		// table.setBounds(491, 252, -486, -211);
		// panelBandejaEntrada.add(table);
		tabla = new Ventana_Mensajes(datos);
		tabla.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tabla.getSelectedRow();
				if (bandeja) { //Con BandejaDeEntrada
					lblDe.setText("De:  "+ datos.getValueAt(row, 1));
					lblAsuntoContenido.setText("Asunto:  "+ datos.getValueAt(row, 2));
					Mensaje mensaje = bandejaEntrada.get(row);
					String contenido = BaseDeDatos.conseguirContenido(mensaje);  
					txtMensajeContenido.setText(contenido);
					panelBandejaEntrada.setVisible(false);
					panel_contenido.setVisible(true);
				}else{ //Con Enviados
					lblDe.setText("De:  "+ datos2.getValueAt(row, 1));
					lblAsuntoContenido.setText("Asunto:  "+ datos2.getValueAt(row, 2));
					Mensaje mensaje = enviados.get(row);
					String contenido = BaseDeDatos.conseguirContenido(mensaje);
					txtMensajeContenido.setText(contenido);
					panelBandejaEntrada.setVisible(false);
					panel_contenido.setVisible(true);
				}
			}
		});
		scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(10, 11, 497, 320);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(684, 445);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana_Mensajes.class.getResource("/imagenes/Logo1.JPG")));
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		panelBandejaEntrada = new JPanel();
		panelBandejaEntrada.setBounds(161, 33, 517, 383);
		frame.getContentPane().add(panelBandejaEntrada);
		panelBandejaEntrada.setLayout(null);

		panelBandejaEntrada.add(scrollPane);
		
		// btnEliminar
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEliminar.setBounds(387, 345, 120, 23);
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Eliminar");
				int result = JOptionPane.showConfirmDialog(null,
						"¿Está seguro de que desea eliminar?", null,
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					if (tabla.getSelectedRow() >= 0
							&& tabla.getSelectedRow() < datos.getRowCount()) {
						if (bandeja) { //Con BandejaDeEntrada
							for (int i = datos.getRowCount() - 1; i >= 0; i--) {
								if ((boolean) datos.getValueAt(i, 5)) {
									datos.borrar(i);
									eliminar.add(bandejaEntrada.get(i));
									bandejaEntrada.remove(i);
									
								}
							}
						}else{ //Con Enviados
							for (int i = datos2.getRowCount() - 1; i >= 0; i--) {
								if ((boolean) datos2.getValueAt(i, 5)) {
									datos2.borrar(i);
									eliminar.add(enviados.get(i));
									enviados.remove(i);
								}
							}
						}
						BaseDeDatos.borrarMensaje(eliminar);
						tabla.updateUI();
					} else {
						JOptionPane.showMessageDialog(null,
								"Selecciona una fila antes de pulsar Eliminar",
								"Error en borrado",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				if (result == JOptionPane.NO_OPTION) {
					if (bandeja) { // Con BandejaDeEntrada
						for (int i = datos.getRowCount() - 1; i >= 0; i--) {
							if ((boolean) datos.getValueAt(i, 5)) {
								datos.setValueAt(false, i, 5);
							}
						}
					} else { //Con enviados
						for (int i = datos2.getRowCount() - 1; i >= 0; i--) {
							if ((boolean) datos2.getValueAt(i, 5)) {
								datos2.setValueAt(false, i, 5);
							}
						}
					}
				}
			}
		});
		panelBandejaEntrada.add(btnEliminar);

		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 162, 416);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		btnBusqueda = new JButton("BÚSQUEDA");
		btnBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Busqueda ventanaBusqueda = new Ventana_Busqueda();
				ventanaBusqueda.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		btnBusqueda.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBusqueda.setBounds(10, 11, 141, 80);
		panel.add(btnBusqueda);
		
		btnPerfil = new JButton("PERFIL");
		btnPerfil.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPerfil.setBounds(10, 113, 141, 86);
		btnPerfil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Ventana_Perfil ventanaPerfil = new Ventana_Perfil();
				ventanaPerfil.setVisible(true);
				frame.setVisible(false);
			}
		});
		panel.add(btnPerfil);
		
		btnMensaje = new JButton("MENSAJES");
		btnMensaje.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMensaje.setBounds(10, 217, 141, 86);
		btnMensaje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Ventana_Mensajes.crearYMostrarGUI();
				frame.setVisible(false);
			}
		});
		panel.add(btnMensaje);
		
		btnAtras = new JButton("");
		btnAtras.setIcon(new ImageIcon(Ventana_Mensajes.class 
				.getResource("/imagenes/Flecha_atras.gif")));
		btnAtras.setBounds(10, 325, 141, 80);
		btnAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Ventana_Login ventanaLogin = new Ventana_Login();
				ventanaLogin.setVisible(true);
				frame.setVisible(false);
			}
		});
		panel.add(btnAtras);
		
		panelBotonera = new JPanel();
		panelBotonera.setBackground(Color.LIGHT_GRAY);
		panelBotonera.setBounds(161, 0, 517, 33);
		frame.getContentPane().add(panelBotonera);
		panelBotonera.setLayout(null);
		
		panelNuevoMensaje.setVisible(false);
		panelBandejaEntrada.setVisible(true);
		
		btnNuevoMensaje = new JButton("NUEVO MENSAJE");
		btnNuevoMensaje.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNuevoMensaje.setBounds(32, 5, 125, 23);
		btnNuevoMensaje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelNuevoMensaje.setVisible(true);
				panelBandejaEntrada.setVisible(false);
				panel_contenido.setVisible(false);
			}
		});
		panelBotonera.add(btnNuevoMensaje);
		
		btnBandejaDeEntrada = new JButton("BANDEJA DE ENTRADA");
		btnBandejaDeEntrada.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBandejaDeEntrada.setBounds(170, 5, 170, 23);
		btnBandejaDeEntrada.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelNuevoMensaje.setVisible(false);
				panel_contenido.setVisible(false);
				panelBandejaEntrada.setVisible(true);
				tabla.setModel(datos);
				bandeja = true;
			}
		});
		panelBotonera.add(btnBandejaDeEntrada);
		frame.getContentPane().add(panelNuevoMensaje);
		
		btnEnviados = new JButton("ENVIADOS");
		btnEnviados.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEnviados.setBounds(352, 5, 120, 23);
		btnEnviados.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelBandejaEntrada.setVisible(true);
				panel_contenido.setVisible(false);
				panelNuevoMensaje.setVisible(false);
				tabla.setModel(datos2);
				bandeja = false;
				
			}
		});
		panelBotonera.add(btnEnviados);
		
		btnEnviar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if ((txtPara.getText().equals(""))
						|| (txtAsunto.getText().equals(""))
						|| (txtMensaje.getText().equals(""))) {
					JOptionPane.showMessageDialog(frame,
							"Hay un campo incompleto", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(frame,
							"El mensaje ha sido enviado", "ENVIADO",
							JOptionPane.INFORMATION_MESSAGE);
					if (Ventana_Login.getQuien() == 1){
						String id = "0"+(bandejaEntrada.size() + enviados.size() + 1);
						Calendar calendario = Calendar.getInstance();
						int hora = calendario.get(Calendar.HOUR_OF_DAY);
						int minute = calendario.get(Calendar.MINUTE);
						String horaEnvio = hora + ":" + minute;
						int dia = calendario.get(Calendar.DAY_OF_MONTH);
						int mes = calendario.get(Calendar.MONTH);
						int anyo = calendario.get(Calendar.YEAR);
						String fechaEnvio = dia + "/" + mes + "/" + anyo;
						Mensaje mensaje = new Mensaje(id, "Mensaje", BaseDeDatos.conseguirIdProfesor(txtPara.getText()), txtAsunto.getText(), txtMensaje.getText(), horaEnvio, fechaEnvio);
						enviados.add(mensaje);
						datos2.insertar(mensaje);
						BaseDeDatos.mensajeAProfesor(id, Ventana_Login
								.getPersona().getDni(), txtPara.getText(),
								txtMensaje.getText(), txtAsunto.getText(),
								"Mensaje", horaEnvio, fechaEnvio);
						
					} else { //Profesor
						String id = ((bandejaEntrada.size() + enviados.size()) + 1)
								+ "";
						Calendar calendario = Calendar.getInstance();
						int hora = calendario.get(Calendar.HOUR_OF_DAY);
						int minute = calendario.get(Calendar.MINUTE);
						String horaEnvio = hora + ":" + minute;
						int dia = calendario.get(Calendar.DAY_OF_MONTH);
						int mes = calendario.get(Calendar.MONTH);
						int anyo = calendario.get(Calendar.YEAR);
						String fechaEnvio = dia + "/" + mes + "/" + anyo;
						Mensaje mensaje = new Mensaje(id, "Mensaje", BaseDeDatos.conseguirIdAlumno(txtPara.getText()), txtAsunto.getText(), txtMensaje.getText(), horaEnvio, fechaEnvio);
						enviados.add(mensaje);
						datos2.insertar(mensaje);
						BaseDeDatos.mensajeAAlumno(id, Ventana_Login
								.getPersona().getDni(), txtPara.getText(),
								txtMensaje.getText(), txtAsunto.getText(),
								"Mensaje", horaEnvio, fechaEnvio);
					}
					txtPara.setText("");
					txtAsunto.setText("");
					txtMensaje.setText("");
					frame.getContentPane().add(panelBandejaEntrada);
					frame.getContentPane().repaint();
				}

			}
		});
		
		//Contenido
		panel_contenido = new JPanel();
		panel_contenido.setBounds(161, 33, 517, 383);
		frame.add(panel_contenido);
		panel_contenido.setLayout(null);
		
		lblDe = new JLabel("De: "); //+De
		lblDe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDe.setBounds(28, 36, 165, 14);
		panel_contenido.add(lblDe);
		
		lblAsuntoContenido = new JLabel("Asunto:"); //+Asunto
		lblAsuntoContenido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAsuntoContenido.setBounds(28, 108, 350, 30);
		panel_contenido.add(lblAsuntoContenido);
		
		lblMensajeContenido = new JLabel("Mensaje:");
		lblMensajeContenido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMensajeContenido.setBounds(28, 176, 75, 14);
		panel_contenido.add(lblMensajeContenido);
		
		txtMensajeContenido = new JTextArea(); //Contenido
		txtMensajeContenido.setEnabled(false);
		txtMensajeContenido.setEditable(false);
		txtMensajeContenido.setBounds(28, 199, 434, 129);
		txtMensajeContenido.setLineWrap(true);
		txtMensajeContenido.setWrapStyleWord(true);
		txtMensajeContenido.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		panel_contenido.add(txtMensajeContenido);
        // Muestra la ventana
        
        frame.setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
	       // Mandar trabajo a Swing
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                crearYMostrarGUI();
            }
        });
	}
	
}

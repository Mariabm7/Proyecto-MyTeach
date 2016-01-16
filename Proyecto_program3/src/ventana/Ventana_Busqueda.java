package ventana;

import javax.swing.JFrame;
import javax.swing.JPanel;


import javax.swing.JButton;

import java.awt.color.CMMException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;


import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import BaseDeDatos.BaseDeDatos;
import objetos.DatoParaTabla;
import objetos.MiTableModel;

public class Ventana_Busqueda extends JFrame implements ActionListener {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JButton btnBusqueda;
	private JButton btnPerfil;
	private JButton btnMensaje;
	private JButton btnAtras;
	private JPanel panelBotonera;
	private JLabel lblTipoDeAsignatura;
	private JComboBox<String> comboBox_tipo;
	private JPanel panel_colegio;
	private JTextField txtAsignatura;
	private JRadioButton rdbtnPrimaria;
	private JRadioButton rdbtnEso;
	private JRadioButton rdbtnBachillerato;
	private ButtonGroup bg;
	private JLabel lblIdioma;
	private JComboBox<String> comboBox_IdiomaColegio;
	private JPanel panel_escuelaDeIdiomas;
	private JLabel lblIdioma_1;
	private JComboBox<String> comboBox_Idioma;
	private JLabel lblNivel_1;
	private JRadioButton rbtnA1;
	private JRadioButton rbtnA2;
	private JRadioButton rbtnB1;
	private JRadioButton rbtnB2;
	private JRadioButton rbtnC1;
	private JRadioButton rbtnC2;
	private ButtonGroup bg2;
	private JLabel lblAsignatura;
	private JLabel lblNivel;
	private JLabel lblCiudad;
	private JTextField txtCiudad;
	private JPanel panel_tabla;
    private MiTableModel datos = new MiTableModel(Linea.nombresAtributos, Linea.atributosEditables);
    private JTable tabla;
    private JButton btnBuscar;
    private JButton btnMandarPeticion;
    
	public Ventana_Busqueda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(684, 445);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana_Busqueda.class.getResource("/imagenes/Logo1.JPG")));
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 162, 416);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		btnBusqueda = new JButton("BÚSQUEDA");
		btnBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBusqueda.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBusqueda.setBounds(10, 11, 141, 80);
		panel.add(btnBusqueda);
		
		btnPerfil = new JButton("PERFIL");
		btnPerfil.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPerfil.setBounds(10, 113, 141, 86);
		panel.add(btnPerfil);
		
		btnMensaje = new JButton("MENSAJES");
		btnMensaje.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMensaje.setBounds(10, 217, 141, 86);
		panel.add(btnMensaje);
		
		btnAtras = new JButton("");
		btnAtras.setIcon(new ImageIcon(Ventana_Busqueda.class 
				.getResource("/imagenes/Flecha_atras.gif")));
		btnAtras.setBounds(10, 325, 141, 80);
		panel.add(btnAtras);
		
		panelBotonera = new JPanel();
		panelBotonera.setBackground(Color.LIGHT_GRAY);
		panelBotonera.setBounds(161, 0, 517, 140);
		getContentPane().add(panelBotonera);
		panelBotonera.setLayout(null);
		
		lblTipoDeAsignatura = new JLabel("Tipo de asignatura:");
		lblTipoDeAsignatura.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTipoDeAsignatura.setBounds(10, 11, 123, 14);
		panelBotonera.add(lblTipoDeAsignatura);
		
		comboBox_tipo = new JComboBox<String>();
		comboBox_tipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox_tipo.setModel(new DefaultComboBoxModel<String>(new String[] {"    -  Elija tipo  -", "Colegio", "Escuela de idiomas"}));
		comboBox_tipo.setBounds(134, 9, 123, 20);
		panelBotonera.add(comboBox_tipo);
		
		panel_colegio = new JPanel();
		panel_colegio.setBackground(Color.LIGHT_GRAY);
		panel_colegio.setBounds(10, 36, 497, 70);
		panelBotonera.add(panel_colegio);
		panel_colegio.setLayout(null);
		
		lblAsignatura = new JLabel("Asignatura:");
		lblAsignatura.setBounds(10, 11, 71, 15);
		panel_colegio.add(lblAsignatura);
		lblAsignatura.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtAsignatura = new JTextField();
		txtAsignatura.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (!(txtCiudad.getText().equals("")) && !(txtAsignatura.getText().equals("")) && !(comboBox_IdiomaColegio.getSelectedItem().toString().equals("- Elija idioma -")) && (rdbtnPrimaria.isSelected() || rdbtnEso.isSelected() || rdbtnBachillerato.isSelected()) && comboBox_tipo.getSelectedItem().toString().equals("Colegio")){
					btnBuscar.setEnabled(true);
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {}
			@Override
			public void keyPressed(KeyEvent arg0) {}
		});
		txtAsignatura.setBounds(91, 9, 167, 20);
		panel_colegio.add(txtAsignatura);
		txtAsignatura.setColumns(10);
		
		lblNivel = new JLabel("Nivel:");
		lblNivel.setBounds(10, 43, 46, 14);
		panel_colegio.add(lblNivel);
		lblNivel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		rdbtnPrimaria = new JRadioButton("Primaria");
		rdbtnPrimaria.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(txtCiudad.getText().equals("")) && !(txtAsignatura.getText().equals("")) && !(comboBox_IdiomaColegio.getSelectedItem().toString().equals("- Elija idioma -")) && (rdbtnPrimaria.isSelected() || rdbtnEso.isSelected() || rdbtnBachillerato.isSelected()) && comboBox_tipo.getSelectedItem().toString().equals("Colegio")){
					btnBuscar.setEnabled(true);
				}
			}
		});
		rdbtnPrimaria.setBounds(71, 39, 77, 23);
		rdbtnPrimaria.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnPrimaria.setBackground(Color.LIGHT_GRAY);
		
		rdbtnEso = new JRadioButton("ESO");
		rdbtnEso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(txtCiudad.getText().equals("")) && !(txtAsignatura.getText().equals("")) && !(comboBox_IdiomaColegio.getSelectedItem().toString().equals("- Elija idioma -")) && (rdbtnPrimaria.isSelected() || rdbtnEso.isSelected() || rdbtnBachillerato.isSelected()) && comboBox_tipo.getSelectedItem().toString().equals("Colegio")){
					btnBuscar.setEnabled(true);
				}
			}
		});
		rdbtnEso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnEso.setBounds(181, 40, 59, 23);
		rdbtnEso.setBackground(Color.LIGHT_GRAY);
		
		rdbtnBachillerato = new JRadioButton("Bachillerato");
		rdbtnBachillerato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(txtCiudad.getText().equals("")) && !(txtAsignatura.getText().equals("")) && !(comboBox_IdiomaColegio.getSelectedItem().toString().equals("- Elija idioma -")) && (rdbtnPrimaria.isSelected() || rdbtnEso.isSelected() || rdbtnBachillerato.isSelected()) && comboBox_tipo.getSelectedItem().toString().equals("Colegio")){
					btnBuscar.setEnabled(true);
				}
			}
		});
		rdbtnBachillerato.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnBachillerato.setBounds(266, 40, 108, 23);	
		rdbtnBachillerato.setBackground(Color.LIGHT_GRAY);
		
		bg = new ButtonGroup();
		bg.add(rdbtnPrimaria);
		bg.add(rdbtnEso);
		bg.add(rdbtnBachillerato);
		panel_colegio.add(rdbtnPrimaria);
		panel_colegio.add(rdbtnEso);
		panel_colegio.add(rdbtnBachillerato);
		
		lblIdioma = new JLabel("Idioma:");
		lblIdioma.setBounds(278, 11, 46, 14);
		panel_colegio.add(lblIdioma);
		lblIdioma.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		comboBox_IdiomaColegio = new JComboBox<String>();
		comboBox_IdiomaColegio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBox_Idioma.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (!(txtCiudad.getText().equals("")) && !(comboBox_Idioma.getSelectedItem().toString().equals("- Elija idioma -")) && (rbtnA1.isSelected() || rbtnA2.isSelected() || rbtnB1.isSelected()|| rbtnB2.isSelected() || rbtnC1.isSelected() || rbtnC2.isSelected()) && comboBox_tipo.getSelectedItem().toString().equals("Escuela de idiomas")){
							btnBuscar.setEnabled(true);
						}else if (!(txtCiudad.getText().equals("")) && !(txtAsignatura.getText().equals("")) && !(comboBox_IdiomaColegio.getSelectedItem().toString().equals("- Elija idioma -")) && (rdbtnPrimaria.isSelected() || rdbtnEso.isSelected() || rdbtnBachillerato.isSelected()) && comboBox_tipo.getSelectedItem().toString().equals("Colegio")){
							btnBuscar.setEnabled(true);
						}
					}
				});
			}
		});
		comboBox_IdiomaColegio.setBounds(334, 9, 146, 20);
		panel_colegio.add(comboBox_IdiomaColegio);
		comboBox_IdiomaColegio.setModel(new DefaultComboBoxModel<String>(new String[] {"- Elija idioma -", "Euskera", "Ingl\u00E9s", "Franc\u00E9s", "Alem\u00E1n"}));
		
		panel_escuelaDeIdiomas = new JPanel();
		panel_escuelaDeIdiomas.setBounds(10, 36, 497, 70);
		panelBotonera.add(panel_escuelaDeIdiomas);
		panel_escuelaDeIdiomas.setBackground(Color.LIGHT_GRAY);
		panel_escuelaDeIdiomas.setLayout(null);
		
		lblIdioma_1 = new JLabel("Idioma:");
		lblIdioma_1.setBounds(10, 11, 46, 15);
		panel_escuelaDeIdiomas.add(lblIdioma_1);
		lblIdioma_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		comboBox_Idioma = new JComboBox<String>();
		comboBox_Idioma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(txtCiudad.getText().equals("")) && !(comboBox_Idioma.getSelectedItem().toString().equals("- Elija idioma -")) && (rbtnA1.isSelected() || rbtnA2.isSelected() || rbtnB1.isSelected()|| rbtnB2.isSelected() || rbtnC1.isSelected() || rbtnC2.isSelected()) && comboBox_tipo.getSelectedItem().toString().equals("Escuela de idiomas")){
					btnBuscar.setEnabled(true);
				}else if (!(txtCiudad.getText().equals("")) && !(txtAsignatura.getText().equals("")) && !(comboBox_IdiomaColegio.getSelectedItem().toString().equals("- Elija idioma -")) && (rdbtnPrimaria.isSelected() || rdbtnEso.isSelected() || rdbtnBachillerato.isSelected()) && comboBox_tipo.getSelectedItem().toString().equals("Colegio")){
					btnBuscar.setEnabled(true);
				}
			}
		});
		comboBox_Idioma.setModel(new DefaultComboBoxModel<String>(new String[] {"- Elija idioma -", "Euskera", "Espa\u00F1ol", "Ingl\u00E9s", "Franc\u00E9s", "Alem\u00E1n", "Italiano", "Chino", "Japon\u00E9s", "Ruso"}));
		comboBox_Idioma.setBounds(66, 9, 108, 20);
		panel_escuelaDeIdiomas.add(comboBox_Idioma);
		
		lblNivel_1 = new JLabel("Nivel:");
		lblNivel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNivel_1.setBounds(206, 11, 46, 14);
		panel_escuelaDeIdiomas.add(lblNivel_1);
		
		rbtnA1 = new JRadioButton("A1");
		rbtnA1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!(txtCiudad.getText().equals("")) && !(comboBox_Idioma.getSelectedItem().toString().equals("- Elija idioma -")) && (rbtnA1.isSelected() || rbtnA2.isSelected() || rbtnB1.isSelected()|| rbtnB2.isSelected() || rbtnC1.isSelected() || rbtnC2.isSelected()) && comboBox_tipo.getSelectedItem().toString().equals("Escuela de idiomas")){
					btnBuscar.setEnabled(true);
				}
			}
		});
		rbtnA1.setBackground(Color.LIGHT_GRAY);
		rbtnA1.setBounds(258, 8, 47, 23);

		rbtnA2 = new JRadioButton("A2");
		rbtnA2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!(txtCiudad.getText().equals("")) && !(comboBox_Idioma.getSelectedItem().toString().equals("- Elija idioma -")) && (rbtnA1.isSelected() || rbtnA2.isSelected() || rbtnB1.isSelected()|| rbtnB2.isSelected() || rbtnC1.isSelected() || rbtnC2.isSelected()) && comboBox_tipo.getSelectedItem().toString().equals("Escuela de idiomas")){
					btnBuscar.setEnabled(true);
				}
			}
		});
		rbtnA2.setBackground(Color.LIGHT_GRAY);
		rbtnA2.setBounds(258, 34, 47, 23);
	
		rbtnB1 = new JRadioButton("B1");
		rbtnB1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!(txtCiudad.getText().equals("")) && !(comboBox_Idioma.getSelectedItem().toString().equals("- Elija idioma -")) && (rbtnA1.isSelected() || rbtnA2.isSelected() || rbtnB1.isSelected()|| rbtnB2.isSelected() || rbtnC1.isSelected() || rbtnC2.isSelected()) && comboBox_tipo.getSelectedItem().toString().equals("Escuela de idiomas")){
					btnBuscar.setEnabled(true);
				}
			}
		});
		rbtnB1.setBackground(Color.LIGHT_GRAY);
		rbtnB1.setBounds(331, 8, 46, 23);

		rbtnB2 = new JRadioButton("B2");
		rbtnB2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!(txtCiudad.getText().equals("")) && !(comboBox_Idioma.getSelectedItem().toString().equals("- Elija idioma -")) && (rbtnA1.isSelected() || rbtnA2.isSelected() || rbtnB1.isSelected()|| rbtnB2.isSelected() || rbtnC1.isSelected() || rbtnC2.isSelected()) && comboBox_tipo.getSelectedItem().toString().equals("Escuela de idiomas")){
					btnBuscar.setEnabled(true);
				}
			}
		});
		rbtnB2.setBackground(Color.LIGHT_GRAY);
		rbtnB2.setBounds(331, 34, 46, 23);

		rbtnC1 = new JRadioButton("C1");
		rbtnC1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!(txtCiudad.getText().equals("")) && !(comboBox_Idioma.getSelectedItem().toString().equals("- Elija idioma -")) && (rbtnA1.isSelected() || rbtnA2.isSelected() || rbtnB1.isSelected()|| rbtnB2.isSelected() || rbtnC1.isSelected() || rbtnC2.isSelected()) && comboBox_tipo.getSelectedItem().toString().equals("Escuela de idiomas")){
					btnBuscar.setEnabled(true);
				}
			}
		});
		rbtnC1.setBackground(Color.LIGHT_GRAY);
		rbtnC1.setBounds(404, 9, 46, 20);

		rbtnC2 = new JRadioButton("C2");
		rbtnC2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!(txtCiudad.getText().equals("")) && !(comboBox_Idioma.getSelectedItem().toString().equals("- Elija idioma -")) && (rbtnA1.isSelected() || rbtnA2.isSelected() || rbtnB1.isSelected()|| rbtnB2.isSelected() || rbtnC1.isSelected() || rbtnC2.isSelected()) && comboBox_tipo.getSelectedItem().toString().equals("Escuela de idiomas")){
					btnBuscar.setEnabled(true);
				}
			}
		});
		rbtnC2.setBackground(Color.LIGHT_GRAY);
		rbtnC2.setBounds(404, 38, 46, 19);

		bg2 = new ButtonGroup();
		bg2.add(rbtnA1);
		bg2.add(rbtnA2);
		bg2.add(rbtnB1);
		bg2.add(rbtnB2);
		bg2.add(rbtnC1);
		bg2.add(rbtnC2);
		panel_escuelaDeIdiomas.add(rbtnA1);
		panel_escuelaDeIdiomas.add(rbtnA2);
		panel_escuelaDeIdiomas.add(rbtnB1);
		panel_escuelaDeIdiomas.add(rbtnB2);
		panel_escuelaDeIdiomas.add(rbtnC1);
		panel_escuelaDeIdiomas.add(rbtnC2);
		
		lblCiudad = new JLabel("Ciudad: ");
		lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCiudad.setBounds(290, 11, 50, 14);
		panelBotonera.add(lblCiudad);
		
		txtCiudad = new JTextField();
		txtCiudad.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (!(txtCiudad.getText().equals("")) && !(comboBox_Idioma.getSelectedItem().toString().equals("- Elija idioma -")) && (rbtnA1.isSelected() || rbtnA2.isSelected() || rbtnB1.isSelected()|| rbtnB2.isSelected() || rbtnC1.isSelected() || rbtnC2.isSelected()) && comboBox_tipo.getSelectedItem().toString().equals("Escuela de idiomas")){
					btnBuscar.setEnabled(true);
				}else if (!(txtCiudad.getText().equals("")) && !(txtAsignatura.getText().equals("")) && !(comboBox_IdiomaColegio.getSelectedItem().toString().equals("- Elija idioma -")) && (rdbtnPrimaria.isSelected() || rdbtnEso.isSelected() || rdbtnBachillerato.isSelected()) && comboBox_tipo.getSelectedItem().toString().equals("Colegio")){
					btnBuscar.setEnabled(true);
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {}
			@Override
			public void keyPressed(KeyEvent arg0) {}
		});
		txtCiudad.setBounds(343, 9, 149, 20);
		panelBotonera.add(txtCiudad);
		txtCiudad.setColumns(10);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setEnabled(false);
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_tabla.setVisible(true);
				hacerBusqueda();
			}
		});
		btnBuscar.setBounds(400, 111, 89, 23);
		panelBotonera.add(btnBuscar);
		
		panel_tabla = new JPanel();
		panel_tabla.setBounds(161, 141, 517, 275);
		panel_tabla.setLayout(null);
		panel_tabla.setVisible(false);
		
		tabla = new JTable(datos);
		//Ejemplo para comprobar que funciona
		//datos.insertar(new Linea("Federico", "Lorca Garcia", true));
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(32, 11, 452, 219);
		panel_tabla.add(scrollPane);
		getContentPane().add(panel_tabla);
		
		btnMandarPeticion = new JButton("MANDAR PETICI\u00D3N");
		btnMandarPeticion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tabla.getSelectedRow() >= 0 && tabla.getSelectedRow() < datos.getRowCount() ) {
					//TODO Coger el usuario y mandarle un mensaje
					//Para poner no editable la columna
					//datos.setCellEditable(2, false);
					
					//datos.borrar( tabla.getSelectedRow());
					tabla.updateUI();
				} else {
					JOptionPane.showMessageDialog( null, 
							"Selecciona una fila antes de borrarla", "Error en borrado", 
							JOptionPane.INFORMATION_MESSAGE );
				}
			}
		});
		btnMandarPeticion.setBounds(317, 241, 167, 23);
		panel_tabla.add(btnMandarPeticion);
		
		
		panel_escuelaDeIdiomas.setVisible(false);
		panel_colegio.setVisible(false);
		
		comboBox_tipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_tipo.getSelectedItem().toString().equals("    -  Elija tipo  -")){
					panel_escuelaDeIdiomas.setVisible(false);
					panel_colegio.setVisible(false);
				}else if (comboBox_tipo.getSelectedItem().toString().equals("Colegio")){
					panel_escuelaDeIdiomas.setVisible(false);
					panel_colegio.setVisible(true);
					if (!(txtCiudad.getText().equals("")) && !(txtAsignatura.getText().equals("")) && !(comboBox_IdiomaColegio.getSelectedItem().toString().equals("- Elija idioma -")) && (rdbtnPrimaria.isSelected() || rdbtnEso.isSelected() || rdbtnBachillerato.isSelected())){
						btnBuscar.setEnabled(true);
					}
				} else{
					panel_colegio.setVisible(false);
					panel_escuelaDeIdiomas.setVisible(true);
					if (!(txtCiudad.getText().equals("")) && !(comboBox_Idioma.getSelectedItem().toString().equals("- Elija idioma -")) && (rbtnA1.isSelected() || rbtnA2.isSelected() || rbtnB1.isSelected()|| rbtnB2.isSelected() || rbtnC1.isSelected() || rbtnC2.isSelected())){
						btnBuscar.setEnabled(true);
					}
				}
			}
		});
		btnBusqueda.addActionListener(this);
		btnPerfil.addActionListener(this);
		btnMensaje.addActionListener(this);
		btnAtras.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		if (o == btnBusqueda) {
			Ventana_Busqueda ventanaBusqueda = new Ventana_Busqueda();
			ventanaBusqueda.setVisible(true);
			this.setVisible(false);
		} else if (o == btnPerfil) {
			Ventana_Perfil ventanaPerfil = new Ventana_Perfil(Ventana_Login.getPersona());
			ventanaPerfil.setVisible(true);
			this.setVisible(false);
		} else if (o == btnMensaje) {
			System.out.println("Mensajes");
			Ventana_Mensajes.crearYMostrarGUI();
			this.setVisible(false);
		} else if (o == btnAtras) {
			Ventana_Login ventanaLogin = new Ventana_Login();
			ventanaLogin.setVisible(true);
			this.setVisible(false);
		}

	}
	
	public String getSelectedColegio(){
		if (rdbtnPrimaria.isSelected()){
			return rdbtnPrimaria.getText();
		}else if (rdbtnEso.isSelected()){
			return rdbtnEso.getText();
		}else if(rdbtnBachillerato.isSelected()){
			return rdbtnBachillerato.getText();
		}
		return "";
	}
	public String getSelectedEscuelaDeIdiomas(){
		if (rbtnA1.isSelected()){
			return rbtnA1.getText();
		}else if (rbtnA2.isSelected()){
			return rbtnA2.getText();
		}else if(rbtnB1.isSelected()){
			return rbtnB1.getText();
		}else if(rbtnB2.isSelected()){
			return rbtnB2.getText();
		}else if(rbtnC1.isSelected()){
			return rbtnC1.getText();
		}else if(rbtnC2.isSelected()){
			return rbtnC2.getText();
		}
		return "";
	}
	public void hacerBusqueda(){
		if (comboBox_tipo.getSelectedItem().equals("Colegio") ){
			ArrayList<String> id_prof = BaseDeDatos.busquedaColegio(txtCiudad.getText(), txtAsignatura.getText(), comboBox_IdiomaColegio.getSelectedItem().toString(), getSelectedColegio());
			ArrayList<String> id_nombre = BaseDeDatos.conseguirIdProfesorNombre(id_prof);
			ArrayList<String> id_apellido = BaseDeDatos.conseguirIdProfesorApellidos(id_prof);
			for (int i = 0; i < id_prof.size(); i++){
				System.out.println("Colegio --> id: "+ id_prof.get(i) + "nombre" + id_nombre.get(i)+ "Apellidos: " + id_apellido.get(i));
				datos.insertar(new Linea(id_nombre.get(i), id_apellido.get(i), false));
			}
			tabla.updateUI();
		}else if (comboBox_tipo.getSelectedItem().equals("Escuela de idiomas")){
			ArrayList<String> id_prof = BaseDeDatos.busquedaEscuelaDeIdiomas(txtCiudad.getText(), comboBox_Idioma.getSelectedItem().toString(), getSelectedEscuelaDeIdiomas());
			ArrayList<String> id_nombre = BaseDeDatos.conseguirIdProfesorNombre(id_prof);
			ArrayList<String> id_apellido = BaseDeDatos.conseguirIdProfesorApellidos(id_prof);
			for (int i = 0; i < id_prof.size(); i++){
				System.out.println("Escuela de Idiomas --> id: "+ id_prof.get(i) + "nombre" + id_nombre.get(i)+ "Apellidos: " + id_apellido.get(i));
				datos.insertar(new Linea(id_nombre.get(i), id_apellido.get(i), false));
			}
			tabla.updateUI();
		}
	}
	public static void main(String[] args) {
		Ventana_Busqueda ventanaBusqueda = new Ventana_Busqueda();
		ventanaBusqueda.setVisible(true);
	}
}
	 
	//[01] Clase de datos de base para el modelo
	class Linea implements DatoParaTabla {
		public static String[] nombresAtributos = new String[] {
			"NOMBRE", "APELLIDOS", "¿MANDAR PETICIÓN?"};
		public static boolean[] atributosEditables = new boolean[] {
			false, false, true};
		private String nombre;
		private String apellidos;
		private boolean peticion;
		public Linea( String nom, String ape, boolean peti) {
			nombre = nom;
			apellidos = ape;
			peticion = peti;
		}
	    public int getNumColumnas() {
	    	return 3;
	    }
	    public Object getValor(int col) {
	    	switch (col) {
		    	case 0: { return nombre; }
		    	case 1: { return apellidos; }
		    	case 2: {return peticion; }
	    	}
	    	return null;
	    }
	    public void setValor(Object value, int col) {
	    	try {
		    	switch (col) {
			    	case 0: { nombre = (String) value; break; }
			    	case 1: { apellidos = (String) value; break; }
			    	case 2: {peticion = (Boolean) value; break;}
		    	}
	    	} catch (Exception e) {
	    		// Error en conversión. Intentando asignar un tipo incorrecto
	    		e.printStackTrace();
	    	}
	    }
	}


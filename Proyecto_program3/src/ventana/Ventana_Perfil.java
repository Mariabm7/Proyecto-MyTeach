package ventana;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
//Ventana base, para que todas tengan la misma estructura
//Copiar Pegar
public class Ventana_Perfil extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	
	private JButton btnBusqueda;
	private JButton btnPerfil;
	private JButton btnMensaje;
	private JButton btnAtras;

	private JButton btnEditarNombre;
	private JButton btnEditarEdad;
	private JButton btnEditarGenero;
	private JButton btnEditarApellido1;
	private JButton btnEditarApellido2;
	private JButton btnEditarAsignaturas;
	private JButton btnEditarFoto;

	private JButton btnGuardarCambios;


	private JTextField txtNombre;
	private JTextField txtApellido2;
	private JTextField txtApellido1;
	private JTextField txtEdad;
	private JTextField txtAsignaturas;
	private JTextField txtGenero;
	
	private JComboBox<String> cbGenero;
	
	private JLabel lblApellido1;
	private JLabel lblApellido2;
	private JLabel lblNombre;
	private JLabel lblFoto;
	private JLabel lblGenero;
	private JLabel lblAsignaturas;


	private String nombre;
	private String apellido1; 
	private String apellido2;
	private String edad;
	private String genero;
	private String asignaturas;
	private String idUsuario;
	private JTextField txtApellido_2;
	private JTextField txtApellido_1;
	
	private JFileChooser fcExaminar;

	
	//TODO Mirar si es mejor pasarle la identificación de ambos usuarios(el del perfil y el que lo mira)
	public Ventana_Perfil(String nombre, String apellido1, String apellido2, String edad, String genero, String asignaturas, String idUsuario) {

		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.edad = edad;
		this.genero = genero;
		this.asignaturas = asignaturas;
		this.idUsuario = idUsuario;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(684, 445);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana_Perfil.class.getResource("/imagenes/Logo1.JPG")));
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		this.panel = new JPanel();
		this.panel.setBackground(Color.GRAY);
		this.panel.setBounds(0, 0, 162, 416);
		getContentPane().add(this.panel);
		this.panel.setLayout(null);

		this.btnBusqueda = new JButton("BÚSQUEDA");
		this.btnBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.btnBusqueda.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnBusqueda.setBounds(10, 11, 141, 80);
		this.panel.add(this.btnBusqueda);
		this.btnBusqueda.addActionListener(this);

		btnPerfil = new JButton("PERFIL");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.btnPerfil.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnPerfil.setBounds(10, 113, 141, 86);
		this.panel.add(btnPerfil);
		this.btnPerfil.addActionListener(this);

		this.btnMensaje = new JButton("MENSAJES");
		this.btnMensaje.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnMensaje.setBounds(10, 217, 141, 86);
		this.panel.add(btnMensaje);
		this.btnMensaje.addActionListener(this);

		this.btnAtras = new JButton("");
		this.btnAtras.setIcon(new ImageIcon(Ventana_MenuBase.class.getResource("/imagenes/Flecha_atras.gif")));
		this.btnAtras.setBounds(10, 325, 141, 80);
		this.panel.add(btnAtras);

		this.txtNombre = new JTextField();
		this.txtNombre.setText(nombre);
		this.txtNombre.setEditable(false);
		this.txtNombre.setBorder(null);
		this.txtNombre.setBounds(426, 46, 200, 22);
		getContentPane().add(this.txtNombre);
		this.txtNombre.setColumns(10);

		this.lblNombre = new JLabel("Nombre");
		this.lblNombre.setBounds(342, 49, 56, 16);
		getContentPane().add(this.lblNombre);

		this.lblFoto = new JLabel();
		this.lblFoto.setIcon(new ImageIcon(Ventana_Perfil.class.getResource("/imagenes/Perfil.png")));
		this.lblFoto.setBounds(187, 65, 128, 146);
		getContentPane().add(this.lblFoto);

		JLabel lblFechaDeNacimiento = new JLabel("Edad");
		lblFechaDeNacimiento.setBounds(342, 143, 41, 22);
		getContentPane().add(lblFechaDeNacimiento);

		this.txtEdad = new JTextField();
		this.txtEdad.setText(edad);
		this.txtEdad.setEditable(false);
		this.txtEdad.setBorder(null);
		this.txtEdad.setBounds(426, 143, 200, 22);
		getContentPane().add(this.txtEdad);
		this.txtEdad.setColumns(10);

		this.lblGenero = new JLabel("G\u00E9nero");
		this.lblGenero.setBounds(342, 178, 56, 16);
		getContentPane().add(this.lblGenero);

		this.lblAsignaturas = new JLabel("Asignaturas: ");
		this.lblAsignaturas.setBounds(342, 208, 78, 22);
		getContentPane().add(this.lblAsignaturas);

		this.txtAsignaturas = new JTextField();
		this.txtAsignaturas.setText(asignaturas);
		this.txtAsignaturas.setEditable(false);
		this.txtAsignaturas.setBorder(null);
		this.txtAsignaturas.setBounds(367, 247, 263, 22);
		getContentPane().add(this.txtAsignaturas);
		this.txtAsignaturas.setColumns(10);

		this.btnEditarNombre = new JButton("");
		this.btnEditarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.btnEditarNombre.setIcon(new ImageIcon(Ventana_Perfil.class.getResource("/imagenes/iconoEditar.png")));
		this.btnEditarNombre.setOpaque(false);
		this.btnEditarNombre.setContentAreaFilled(false);
		this.btnEditarNombre.setBorderPainted(false);
		this.btnEditarNombre.setBounds(638, 45, 23, 23);
		getContentPane().add(this.btnEditarNombre);
		this.btnEditarNombre.addActionListener(this);

		this.btnEditarEdad = new JButton("");
		this.btnEditarEdad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.btnEditarEdad.setIcon(new ImageIcon(Ventana_Perfil.class.getResource("/imagenes/iconoEditar.png")));
		this.btnEditarEdad.setOpaque(false);
		this.btnEditarEdad.setContentAreaFilled(false);
		this.btnEditarEdad.setBorderPainted(false);
		this.btnEditarEdad.setBounds(638, 142, 23, 23);
		getContentPane().add(this.btnEditarEdad);
		this.btnEditarEdad.addActionListener(this);

		this.btnEditarGenero = new JButton("");
		btnEditarGenero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.btnEditarGenero.setIcon(new ImageIcon(Ventana_Perfil.class.getResource("/imagenes/iconoEditar.png")));
		this.btnEditarGenero.setOpaque(false);
		this.btnEditarGenero.setContentAreaFilled(false);
		this.btnEditarGenero.setBorderPainted(false);
		this.btnEditarGenero.setBounds(638, 175, 23, 23);
		getContentPane().add(btnEditarGenero);
		this.btnEditarGenero.addActionListener(this);

		this.btnEditarAsignaturas = new JButton("");
		this.btnEditarAsignaturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.btnEditarAsignaturas.setIcon(new ImageIcon(Ventana_Perfil.class.getResource("/imagenes/iconoEditar.png")));
		this.btnEditarAsignaturas.setOpaque(false);
		this.btnEditarAsignaturas.setContentAreaFilled(false);
		this.btnEditarAsignaturas.setBorderPainted(false);
		this.btnEditarAsignaturas.setBounds(638, 208, 23, 22);
		getContentPane().add(this.btnEditarAsignaturas);
		this.btnEditarAsignaturas.addActionListener(this);

		this.btnEditarFoto = new JButton("");
		btnEditarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.btnEditarFoto.setIcon(new ImageIcon(Ventana_Perfil.class.getResource("/imagenes/iconoEditar.png")));
		this.btnEditarFoto.setOpaque(false);
		this.btnEditarFoto.setContentAreaFilled(false);
		this.btnEditarFoto.setBorderPainted(false);
		this.btnEditarFoto.setBounds(186, 224, 23, 23);
		getContentPane().add(btnEditarFoto);
		this.btnEditarFoto.addActionListener(this);

		this.lblApellido1 = new JLabel("1\u00BA Apellido");
		this.lblApellido1.setBounds(342, 81, 62, 17);
		getContentPane().add(this.lblApellido1);

		this.lblApellido2 = new JLabel("2\u00BA Apellido");
		this.lblApellido2.setBounds(342, 114, 72, 16);
		getContentPane().add(this.lblApellido2);

		this.btnEditarApellido2 = new JButton("");
		this.btnEditarApellido2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.btnEditarApellido2.setIcon(new ImageIcon(Ventana_Perfil.class.getResource("/imagenes/iconoEditar.png")));
		this.btnEditarApellido2.setOpaque(false);
		this.btnEditarApellido2.setContentAreaFilled(false);
		this.btnEditarApellido2.setBorderPainted(false);
		this.btnEditarApellido2.setBounds(638, 107, 23, 23);
		getContentPane().add(this.btnEditarApellido2);
		this.btnEditarApellido2.addActionListener(this);
		
		
		this.btnEditarApellido1 = new JButton("");
		this.btnEditarApellido1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		this.btnEditarApellido1.setIcon(new ImageIcon(Ventana_Perfil.class.getResource("/imagenes/iconoEditar.png")));
		this.btnEditarApellido1.setOpaque(false);
		this.btnEditarApellido1.setContentAreaFilled(false);
		this.btnEditarApellido1.setBorderPainted(false);
		this.btnEditarApellido1.setBounds(638, 77, 23, 23);
		getContentPane().add(btnEditarApellido1);
		this.btnEditarApellido1.addActionListener(this);
		
		this.btnGuardarCambios = new JButton("Guardar cambios");
		this.btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.btnGuardarCambios.setBounds(485, 358, 141, 25);
		getContentPane().add(this.btnGuardarCambios);
		
		this.txtGenero = new JTextField(this.genero);
		this.txtGenero.setBounds(426, 175, 107, 22);
		this.txtGenero.setEditable(false);
		this.txtGenero.setBorder(null);
		getContentPane().add(txtGenero);
		
		
		this.cbGenero = new JComboBox<String>();
		this.cbGenero.setBounds(426, 175, 107, 22);
		this.cbGenero.addItem("Hombre");
		this.cbGenero.addItem("Mujer");
		this.cbGenero.addItem("Indefinido");
		this.cbGenero.setSelectedItem(genero);
		this.cbGenero.setVisible(false);
		getContentPane().add(cbGenero);
		
		this.txtApellido2 = new JTextField(apellido2);
		this.txtApellido2.setEditable(false);
		this.txtApellido2.setBounds(426, 108, 200, 22);
		this.txtApellido2.setBorder(null);
		getContentPane().add(txtApellido2);
		this.txtApellido2.setColumns(10);
		
		this.txtApellido1 = new JTextField(apellido1);
		this.txtApellido1.setEditable(false);
		this.txtApellido1.setBounds(426, 81, 200, 22);
		this.txtApellido1.setBorder(null);
		getContentPane().add(txtApellido1);
		this.txtApellido1.setColumns(10);
		
		
		this.btnGuardarCambios.addActionListener(this);
		
		this.btnAtras.addActionListener(this);
		this.fcExaminar = new JFileChooser();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes jpg, ico y png", "jpg", "png", "ico");
		this.fcExaminar.setFileFilter(filtro);
	
		
	
	
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		if(o == btnBusqueda){
			Ventana_Busqueda ventanaBusqueda = new Ventana_Busqueda();
			ventanaBusqueda.setVisible(true);
			this.setVisible(false);
		}else if ( o == btnPerfil ){
			 //TODO los datos se tienen que mantener en un logeo estático (la id del usuario y contra o  algo)
		}else if (o == btnMensaje){
			Ventana_Mensajes.crearYMostrarGUI();
			this.setVisible(false);
		}else if(o == btnAtras){
			Ventana_Login ventanaLogin = new Ventana_Login();
			ventanaLogin.setVisible(true);
			this.setVisible(false);
		}else if(o == btnEditarNombre){
			this.txtNombre.setEditable(true);
		}else if(o == btnEditarApellido1){
			this.txtApellido1.setEditable(true);
		}else if(o == btnEditarApellido2){
			this.txtApellido2.setEditable(true);
		}else if(o == btnEditarEdad){
			this.txtEdad.setEditable(true);
		}else if(o == btnEditarGenero){
			this.txtGenero.setVisible(false);
			this.cbGenero.setVisible(true);
			
		}else if(o == btnEditarAsignaturas){
			this.txtAsignaturas.setEditable(true);
		}else if(o == btnEditarFoto){
			int opcion = fcExaminar.showOpenDialog(null);
			if (opcion == 0){
				String path = fcExaminar.getSelectedFile().getAbsolutePath();
				ImageIcon image = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(140, 150, Image.SCALE_DEFAULT));
				this.lblFoto.setIcon(image);
			}
			
		}else if(o == btnGuardarCambios){
			this.txtNombre.setEditable(false);
			this.txtApellido1.setEditable(false);
			this.txtApellido2.setEditable(false);
			this.txtEdad.setEditable(false);
			this.txtGenero.setText(this.cbGenero.getSelectedItem().toString());
			this.cbGenero.setVisible(false);
			this.txtGenero.setVisible(true);
			this.txtAsignaturas.setEditable(false);
			//TODO guardar datos en base de datos
		}
	}

	public static void main(String[] args) {
		Ventana_Perfil ventanaPerfil = new Ventana_Perfil("Ramón", "Medem", "Nomar", "23","Hombre", "Programación, Bases de datos", "78345433N"); 
		ventanaPerfil.setVisible(true);
	}
}

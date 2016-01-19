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

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPasswordField;

import baseDeDatos.BaseDeDatos;
import objetos.Persona;
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
	private JButton btnEditarCiudad;
	private JButton btnEditarApellido1;
	private JButton btnEditarApellido2;
	private JButton btnEditarTelefono;
	private JButton btnEditarFoto;	
	private JButton btnEditarUsuario;
	private JButton btnEditarContrasena;

	private JButton btnGuardarCambios;


	private JTextField txtNombre;
	private JTextField txtApellido2;
	private JTextField txtApellido1;
	private JTextField txtFechaDeNacimiento;
	private JTextField txtTelefono;
	private JTextField txtCiudad;
	private JTextField txtDni;
	private JTextField txtUsuario;
	
	private JPasswordField pwdContrasena1;
	private JPasswordField pwdContrasena2;
	
	private JLabel lblApellido1;
	private JLabel lblApellido2;
	private JLabel lblNombre;
	private JLabel lblFechaDeNacimiento; 
	private JLabel lblDni;
	private JLabel lblFoto;
	private JLabel lblCiudad;
	private JLabel lblTelefono;
	private JLabel lblUsusario;
	private JLabel lblContrasena1;
	private JLabel lblContrasena2;


	private String nombre;
	private String apellido1; 
	private String apellido2;
	private String dni;
	private String fechaNacimiento;
	private String ciudad;
	private String telefono;
	private String idUsuario;
	private String contrasena;
	
	private JFileChooser fcExaminar;

	private Persona p;
	
	//TODO Mirar si es mejor pasarle la identificación de ambos usuarios(el del perfil y el que lo mira)
	public Ventana_Perfil() {
		p = Ventana_Login.getPersona();
		
		this.nombre = p.getNombre();
		this.apellido1 = p.getApellido1();
		this.apellido2 = p.getApellido2();
		this.fechaNacimiento = p.getFechaNacimiento();
		this.dni = p.getDni();
		this.ciudad = p.getCiudad();
		this.telefono = p.getTelefono();
		this.idUsuario = p.getUserName();
		this.contrasena = p.getPassword();

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
		this.btnBusqueda.addActionListener(this);
		this.btnBusqueda.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnBusqueda.setBounds(10, 11, 141, 80);
		this.panel.add(this.btnBusqueda);
		this.btnBusqueda.addActionListener(this);

		btnPerfil = new JButton("PERFIL");
		btnPerfil.addActionListener(this);
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

		this.txtNombre = new JTextField(nombre);
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

		lblFechaDeNacimiento = new JLabel("Fecha de nacimiento: ");
		lblFechaDeNacimiento.setBounds(342, 172, 133, 22);
		getContentPane().add(lblFechaDeNacimiento);

		this.txtFechaDeNacimiento = new JTextField(fechaNacimiento);
		this.txtFechaDeNacimiento.setEditable(false);
		this.txtFechaDeNacimiento.setBorder(null);
		this.txtFechaDeNacimiento.setBounds(485, 172, 136, 22);
		getContentPane().add(this.txtFechaDeNacimiento);
		this.txtFechaDeNacimiento.setColumns(10);

		this.lblCiudad = new JLabel("Ciudad");
		this.lblCiudad.setBounds(342, 207, 56, 16);
		getContentPane().add(this.lblCiudad);

		this.lblTelefono = new JLabel("Tel\u00E9fono");
		this.lblTelefono.setBounds(342, 237, 78, 22);
		getContentPane().add(this.lblTelefono);

		this.txtTelefono = new JTextField(telefono);
		this.txtTelefono.setEditable(false);
		this.txtTelefono.setBorder(null);
		this.txtTelefono.setBounds(426, 237, 200, 22);
		getContentPane().add(this.txtTelefono);
		this.txtTelefono.setColumns(10);

		this.btnEditarNombre = new JButton("");
		this.btnEditarNombre.addActionListener(this);
		this.btnEditarNombre.setIcon(new ImageIcon(Ventana_Perfil.class.getResource("/imagenes/iconoEditar.png")));
		this.btnEditarNombre.setOpaque(false);
		this.btnEditarNombre.setContentAreaFilled(false);
		this.btnEditarNombre.setBorderPainted(false);
		this.btnEditarNombre.setBounds(638, 45, 23, 23);
		getContentPane().add(this.btnEditarNombre);
		this.btnEditarNombre.addActionListener(this);

		this.btnEditarEdad = new JButton("");
		this.btnEditarEdad.addActionListener(this);
		this.btnEditarEdad.setIcon(new ImageIcon(Ventana_Perfil.class.getResource("/imagenes/iconoEditar.png")));
		this.btnEditarEdad.setOpaque(false);
		this.btnEditarEdad.setContentAreaFilled(false);
		this.btnEditarEdad.setBorderPainted(false);
		this.btnEditarEdad.setBounds(638, 171, 23, 23);
		getContentPane().add(this.btnEditarEdad);
		this.btnEditarEdad.addActionListener(this);

		this.btnEditarCiudad = new JButton("");
		btnEditarCiudad.addActionListener(this);
		this.btnEditarCiudad.setIcon(new ImageIcon(Ventana_Perfil.class.getResource("/imagenes/iconoEditar.png")));
		this.btnEditarCiudad.setOpaque(false);
		this.btnEditarCiudad.setContentAreaFilled(false);
		this.btnEditarCiudad.setBorderPainted(false);
		this.btnEditarCiudad.setBounds(638, 204, 23, 23);
		getContentPane().add(btnEditarCiudad);
		this.btnEditarCiudad.addActionListener(this);

		this.btnEditarTelefono = new JButton("");
		this.btnEditarTelefono.addActionListener(this);
		this.btnEditarTelefono.setIcon(new ImageIcon(Ventana_Perfil.class.getResource("/imagenes/iconoEditar.png")));
		this.btnEditarTelefono.setOpaque(false);
		this.btnEditarTelefono.setContentAreaFilled(false);
		this.btnEditarTelefono.setBorderPainted(false);
		this.btnEditarTelefono.setBounds(638, 237, 23, 22);
		getContentPane().add(this.btnEditarTelefono);
		this.btnEditarTelefono.addActionListener(this);

		this.btnEditarFoto = new JButton("");
		btnEditarFoto.addActionListener(this);
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
		this.btnEditarApellido2.addActionListener(this);
		this.btnEditarApellido2.setIcon(new ImageIcon(Ventana_Perfil.class.getResource("/imagenes/iconoEditar.png")));
		this.btnEditarApellido2.setOpaque(false);
		this.btnEditarApellido2.setContentAreaFilled(false);
		this.btnEditarApellido2.setBorderPainted(false);
		this.btnEditarApellido2.setBounds(638, 107, 23, 23);
		getContentPane().add(this.btnEditarApellido2);
		this.btnEditarApellido2.addActionListener(this);
		
		
		this.btnEditarApellido1 = new JButton("");
		this.btnEditarApellido1.addActionListener(this);
		this.btnEditarApellido1.setIcon(new ImageIcon(Ventana_Perfil.class.getResource("/imagenes/iconoEditar.png")));
		this.btnEditarApellido1.setOpaque(false);
		this.btnEditarApellido1.setContentAreaFilled(false);
		this.btnEditarApellido1.setBorderPainted(false);
		this.btnEditarApellido1.setBounds(638, 77, 23, 23);
		getContentPane().add(btnEditarApellido1);
		this.btnEditarApellido1.addActionListener(this);
		
		this.btnGuardarCambios = new JButton("Guardar cambios");
		this.btnGuardarCambios.addActionListener(this);
		this.btnGuardarCambios.setBounds(485, 358, 141, 25);
		getContentPane().add(this.btnGuardarCambios);
		
		this.txtCiudad = new JTextField(this.ciudad);
		this.txtCiudad.setBounds(426, 204, 200, 22);
		this.txtCiudad.setEditable(false);
		this.txtCiudad.setBorder(null);
		getContentPane().add(txtCiudad);
		
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
		
		lblUsusario = new JLabel("Usuario");
		lblUsusario.setBounds(187, 270, 49, 22);
		getContentPane().add(lblUsusario);
		
		txtUsuario = new JTextField(idUsuario);
		txtUsuario.setEditable(false);
		txtUsuario.setColumns(10);
		txtUsuario.setBorder(null);
		txtUsuario.setBounds(246, 270, 137, 22);
		getContentPane().add(txtUsuario);
		
		lblContrasena1 = new JLabel("Contrase\u00F1a");
		lblContrasena1.setBounds(187, 303, 78, 22);
		getContentPane().add(lblContrasena1);
		
		pwdContrasena1 = new JPasswordField(contrasena);
		this.pwdContrasena1.setEditable(false);
		pwdContrasena1.setBounds(256, 303, 121, 20);
		getContentPane().add(pwdContrasena1);
		
		lblContrasena2 = new JLabel("Repetir contrase\u00F1a");
		this.lblContrasena2.setVisible(false);
		lblContrasena2.setBounds(421, 303, 126, 22);
		getContentPane().add(lblContrasena2);
		
		pwdContrasena2 = new JPasswordField(contrasena);
		this.pwdContrasena2.setVisible(false);
		pwdContrasena2.setBounds(540, 304, 121, 20);
		getContentPane().add(pwdContrasena2);
		
		
		btnEditarContrasena = new JButton("");
		btnEditarContrasena.setIcon(new ImageIcon(Ventana_Perfil.class.getResource("/imagenes/iconoEditar.png")));
		btnEditarContrasena.setOpaque(false);
		btnEditarContrasena.setContentAreaFilled(false);
		btnEditarContrasena.setBorderPainted(false);
		btnEditarContrasena.addActionListener(this);
		btnEditarContrasena.setBounds(387, 303, 23, 22);
		getContentPane().add(btnEditarContrasena);
		
		btnEditarUsuario = new JButton("");
		btnEditarUsuario.setIcon(new ImageIcon(Ventana_Perfil.class.getResource("/imagenes/iconoEditar.png")));
		btnEditarUsuario.setOpaque(false);
		btnEditarUsuario.setContentAreaFilled(false);
		btnEditarUsuario.setBorderPainted(false);
		btnEditarUsuario.addActionListener(this);
		btnEditarUsuario.setBounds(393, 269, 23, 23);
		getContentPane().add(btnEditarUsuario);
		
		lblDni = new JLabel("DNI");
		lblDni.setBounds(342, 141, 41, 22);
		getContentPane().add(lblDni);
		
		txtDni = new JTextField(dni);
		txtDni.setEditable(false);
		txtDni.setColumns(10);
		txtDni.setBorder(null);
		txtDni.setBounds(421, 142, 200, 22);
		getContentPane().add(txtDni);
		
		
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
			this.txtFechaDeNacimiento.setEditable(true);
		}else if(o == btnEditarCiudad){
			this.txtCiudad.setVisible(true);
		}else if(o == btnEditarTelefono){
			this.txtTelefono.setEditable(true);
		}else if(o == btnEditarUsuario){
			this.txtUsuario.setEditable(true);
		}else if(o == btnEditarContrasena){
			this.pwdContrasena1.setEditable(true);
			this.lblContrasena2.setVisible(true);
			this.pwdContrasena2.setVisible(true);
		}else if(o == btnEditarFoto){
			int opcion = fcExaminar.showOpenDialog(null);
			if (opcion == 0){
				String path = fcExaminar.getSelectedFile().getAbsolutePath();
				ImageIcon image = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(140, 150, Image.SCALE_DEFAULT));
				this.lblFoto.setIcon(image);
			}
			
		}else if(o == btnGuardarCambios){
			if(txtNombre.getText().equals("") || txtApellido1.getText().equals("")|| txtApellido2.getText().equals("")|| txtDni.getText().equals("")
					|| txtCiudad.getText().equals("")|| txtUsuario.getText().equals("") || !pwdContrasena1.getText().equals(pwdContrasena2.getText())){
				if (!pwdContrasena1.getText().equals(pwdContrasena2.getText())){
					JOptionPane
					.showMessageDialog(
							Ventana_Perfil.this,
							"Las contraseñas no coinciden",
							"Contraseña erroneo",
							JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane
					.showMessageDialog(
							Ventana_Perfil.this,
							"Rellene todos los campos por favor",
							"Campos vacios",
							JOptionPane.ERROR_MESSAGE);
				}
			}else{
				this.txtNombre.setEditable(false);
				this.txtApellido1.setEditable(false);
				this.txtApellido2.setEditable(false);
				this.txtFechaDeNacimiento.setEditable(false);
				this.txtDni.setEditable(false);
				this.txtCiudad.setEditable(false);
				this.txtTelefono.setEditable(false);
				this.txtUsuario.setEditable(false);
				this.pwdContrasena1.setEditable(false);
			
				this.pwdContrasena2.setVisible(false);
				this.lblContrasena2.setVisible(false);
				
				p.setNombre(txtNombre.getText());
				p.setApellido1(txtApellido1.getText());
				p.setApellido2(txtApellido2.getText());
				p.setFechaNacimiento(txtFechaDeNacimiento.getText());
				p.setDni(txtDni.getText());
				p.setCiudad(txtCiudad.getText());
				p.setTelefono(txtTelefono.getText());
				p.setUserName(txtUsuario.getText());
				p.setPassword(pwdContrasena2.getText());
				Ventana_Login.setPersona(p);
				//TODO guardar datos en base de datos
				if (Ventana_Login.getQuien() == 1){
					BaseDeDatos.guardarAlumno(Ventana_Login.getPersona());
				}else{
					BaseDeDatos.guardarProfesor(Ventana_Login.getPersona());
				}
			}
		}
	}

	public static void main(String[] args) {
		Ventana_Perfil ventanaPerfil = new Ventana_Perfil(); 
		ventanaPerfil.setVisible(true);
	}
}

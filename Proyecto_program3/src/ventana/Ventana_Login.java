package ventana;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.SpringLayout;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.JButton;

import objetos.Persona;

public class Ventana_Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Usuario en uso
	protected static Persona persona;


	private JLabel lblLogo;
	private JPanel panel;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JLabel lblContrasea;
	private JPasswordField txtContrasea;
	private JButton btnNew;
	private JButton btnIniciar;

	public Ventana_Login() {
		setResizable(false);
		setSize(550, 445);
		setTitle("INICIAR SESIÓN");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				Ventana_Login.class.getResource("/imagenes/Logo1.JPG")));
		setBounds(100, 100, 550, 346);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setBounds(76, 96, 54, 17);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblUsuario);

		lblContrasea = new JLabel("Contraseña: ");
		lblContrasea.setBounds(76, 155, 79, 17);
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblContrasea);

		txtUsuario = new JTextField();
		txtUsuario.setBackground(Color.LIGHT_GRAY);
		txtUsuario.setBounds(177, 96, 224, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtContrasea = new JPasswordField();
		txtContrasea.setBackground(Color.LIGHT_GRAY);
		txtContrasea.setBounds(177, 155, 224, 20);
		panel.add(txtContrasea);
		txtContrasea.setColumns(10);

		btnNew = new JButton("CREAR NUEVO USUARIO");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_NewPersona.main(null);
				Ventana_Login.this.dispose();
			}
		});
		btnNew.setBounds(40, 222, 186, 23);
		panel.add(btnNew);

		btnIniciar = new JButton("INICIAR SESIÓN");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarSesion();
			}
		});
		btnIniciar.setBounds(315, 222, 130, 23);
		panel.add(btnIniciar);

		lblLogo = new JLabel();
		lblLogo.setIcon(new ImageIcon(Ventana_Login.class
				.getResource("/imagenes/Logo1.JPG")));
		lblLogo.setBounds(110, 11, 315, 305);
		panel.add(lblLogo);

		KeyListener enter = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					iniciarSesion();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		};
		txtContrasea.addKeyListener(enter);
		txtUsuario.addKeyListener(enter);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		Ventana_Loading ventanaLoading = new Ventana_Loading();
		ventanaLoading.setVisible(true);
		ventanaLoading.iterate();

		// Despues de x tiempo, para que consiga rellenarse la Progress barra
		Ventana_Login ventanaLogin = new Ventana_Login();
		ventanaLoading.setVisible(false);
		ventanaLogin.setVisible(true);
	}

	public int compruebaContrasenya(String usuario, String contrasenya) {
		int log = 0;
		if (usuario.equals("bd2a") && contrasenya.equals("bd2a")) {
			log = 1;
		} else if (usuario.equals("bd2b") && contrasenya.equals("bd2b")) {
			log = 2;
		}
		return log;
	}

	public void iniciarSesion() {
		String usuario = Ventana_Login.this.txtUsuario.getText();
		String contrasenya = Ventana_Login.this.txtContrasea.getText();
		int log = compruebaContrasenya(usuario, contrasenya);
		// if(log == 1){
		// Ventana_PrincipalProf ventanaPrincipal = new
		// Ventana_PrincipalProf(usuario);
		// Ventana_Login.this.dispose();
		// }else
		if (log == 2) {
			Ventana_Busqueda ventanaPrincipal = new Ventana_Busqueda();
			Ventana_Login.this.dispose();
		} else {
			JOptionPane
					.showMessageDialog(
							Ventana_Login.this,
							"El usuario introducido o la contraseña son erroneos, por favor reviselos.",
							"Usuario o contraseña erroneo",
							JOptionPane.ERROR_MESSAGE);
			Ventana_Login.this.txtContrasea.setText(null);
		}

	}
}

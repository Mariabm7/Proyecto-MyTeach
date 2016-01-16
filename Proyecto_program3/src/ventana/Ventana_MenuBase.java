package ventana;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
//Ventana base, para que todas tengan la misma estructura
//Copiar Pegar
public class Ventana_MenuBase extends JFrame implements ActionListener {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JButton btnBusqueda;
	private JButton btnPerfil;
	private JButton btnMensaje;
	private JButton btnAtras;
	
	public Ventana_MenuBase() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(684, 445);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

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
		btnBusqueda.addActionListener(this);
		
		btnPerfil = new JButton("PERFIL");
		btnPerfil.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPerfil.setBounds(10, 113, 141, 86);
		panel.add(btnPerfil);
		btnPerfil.addActionListener(this);
		
		btnMensaje = new JButton("MENSAJES");
		btnMensaje.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMensaje.setBounds(10, 217, 141, 86);
		panel.add(btnMensaje);
		btnMensaje.addActionListener(this);
		
		btnAtras = new JButton("");
		btnAtras.setIcon(new ImageIcon(Ventana_MenuBase.class 
				.getResource("/imagenes/Flecha_atras.gif")));
		btnAtras.setBounds(10, 325, 141, 80);
		panel.add(btnAtras);
		btnAtras.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		if(o == btnBusqueda){
			Ventana_Busqueda ventanaBusqueda = new Ventana_Busqueda();
			ventanaBusqueda.setVisible(true);
			this.setVisible(false);
		}else if ( o == btnPerfil ){
			Ventana_Perfil ventanaPerfil = new Ventana_Perfil(Ventana_Login.getPersona());
			ventanaPerfil.setVisible(true);
			this.setVisible(false);
		}else if (o == btnMensaje){
			Ventana_Mensajes.crearYMostrarGUI();
			this.setVisible(false);
		}else if(o == btnAtras){
			Ventana_Login ventanaLogin = new Ventana_Login();
			ventanaLogin.setVisible(true);
			this.setVisible(false);
		}
	}

	public static void main(String[] args) {
		Ventana_MenuBase ventanaMenu = new Ventana_MenuBase();
		ventanaMenu.setVisible(true);

	}
}

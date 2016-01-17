package ventana;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.JPanel;
import java.awt.Toolkit;

public class Ventana_Loading extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblLogo;
	private JPanel panelBarra;
	private JProgressBar current;
	private int num = 0;

	public Ventana_Loading() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana_Loading.class.getResource("/imagenes/Logo1.JPG")));
		setResizable(false);
		setSize(550, 445);
		setLocationRelativeTo(null);
		setTitle("MyTeach");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		getContentPane().setForeground(Color.BLACK);
		setResizable(false);
		getContentPane().setLayout(null);

		lblLogo = new JLabel("");
		lblLogo.setBounds(141, 26, 239, 314);
		lblLogo.setIcon(new ImageIcon(Ventana_Loading.class
				.getResource("/imagenes/Logo1.JPG")));
		getContentPane().add(lblLogo);

		panelBarra = new JPanel();
		panelBarra.setBounds(21, 346, 497, 60);
		panelBarra.setBackground(Color.WHITE);
		panelBarra.setForeground(Color.WHITE);
		
		panelBarra.setLayout(new FlowLayout());
		//progress = new Progress();
		current = new JProgressBar(0, 2000);
		current.setValue(500);
		current.setStringPainted(true);
		panelBarra.add(current);
		
		getContentPane().add(panelBarra);
		//iterate();
	}
	
	/** Método para que la JProgressBar avance en x tiempo
	 * 
	 */
	public void iterate() {
		while (num < 2000) {
			current.setValue(num);
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
			}
			num += 95;
		}
	}

	public static void main(String[] args) {
		
		Ventana_Loading ventanaLoading = new Ventana_Loading();
		ventanaLoading.setVisible(true);
		ventanaLoading.iterate();
		
		//Despues de x tiempo, para que consiga rellenarse la Progress barra
		Ventana_Login ventanaLogin = new Ventana_Login();
		ventanaLoading.setVisible(false);
		ventanaLogin.setVisible(true);
	}

}
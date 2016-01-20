package objetos;
//Clase Basica de Jorge y Javier
public class Profesor extends Persona{
	private Persona persona;
	private int precioHora; // Sería interesante que fuera un double.
	private String curriculum;
	private int valoracion;
	
	public Profesor(String userName, String password, String dni,
			String nombre, String apellido1, String apellido2, String telefono,
			String ciudad, String fechaNacimiento) {
		super(userName, password, dni, nombre, apellido1, apellido2, telefono,
				ciudad, fechaNacimiento);
	}
	public Profesor(Persona persona, int precioHora, String curriculum){
		this.persona = persona;
		this.curriculum = curriculum;
		this.precioHora = precioHora;
	}
	
}

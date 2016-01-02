package objetos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Alumno extends Persona{

	public Alumno() {
		super();
	}
	
	


	public Alumno(int id, String userName, String password, String dni,
			String nombre, String apellido1, String apellido2, int telefono,
			String direccion, String ciudad, String fechaNacimiento) {
		super(id, userName, password, dni, nombre, apellido1, apellido2, telefono,
				direccion, ciudad, fechaNacimiento);
	}




	public static void main(String[] args) {
		Alumno persona = new Alumno(01, "PepaPig", "pipi", "12345678A", "Pepa", "Pig", "Pam", 944444444, "La republica independiente de mi casa", "Mi Casa", "07/07/1996");
		System.out.println(persona.getEdad());
	}
}

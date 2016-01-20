package objetos;

import java.util.ArrayList;

public class Persona {
	
	private String userName;
	private String password;
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String telefono; 
	private String ciudad;
	private String fechaNacimiento;
	
	private ArrayList<Mensaje> bandejaEntrada = new ArrayList<Mensaje>();
	private ArrayList<Mensaje> enviados = new ArrayList<Mensaje>();
	
	//Constructores 
	
	public Persona(){
		//Mensajes de prueba para antes de usar el mismo usuario desde Ventana_Login
//		bandejaEntrada.add( new Mensaje( "Mensaje", "Piruli",
//   	         "Clase de hoy","Prefiero dar la clase a las 19:00", "15:30", "03/12/2015" ) );
//		bandejaEntrada.add( new Mensaje( "Mensaje", "Juan",
//  	         "Clase de mañana", "No voy a poder, prefiero dar la clase pasado mañana a la misma hora. Gracias", "18:00", "01/11/2015"));
//		bandejaEntrada.add( new Mensaje( "Mensaje", "Lola",
//  	         "Cancelar clase", "Me ha surgido un imprevisto y no podre dar clase", "11:30", "02/10/2015" ) );
//		bandejaEntrada.add( new Mensaje( "Mensaje", "Catalina",
//     	         "Clase de ayer", "En la clase de ayer me explicaste Las leyes de Newton. ¿Podrías hacerme unos apuntes con todo lo explicado? Gracias Profe", "20:00", "01/11/2015"));
//		bandejaEntrada.add(new Mensaje("Mensaje", "Periquita", "No quiero dar mas clase", "He notado cierto desinteres en mi asignatura. Asi que he decidido no darle más clases.", "15:00", "12/12/2012"));
//		
//		enviados.add( new Mensaje( "Mensaje", "Catalina",
//    	         "Clase de ayer", "Se me habia olvidado que hoy tengo que ir a entrenar a futbol. Disculpe las molestias Profe", "20:00", "01/11/2015"));
//		enviados.add(new Peticion("Petición", "Periquita", "Quiero dar clase con usted", "Tengo interes en dar clase con usted", "15:00", "12/12/2012" ));
	}
	
	public Persona( String userName, String password, String dni, String nombre, String apellido1, String apellido2, String telefono,
	 String ciudad, String fechaNacimiento){

		this.userName = userName;
		this.password = password;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.fechaNacimiento = fechaNacimiento;	
	}
	
	//Getters y setters
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public ArrayList<Mensaje> getBandejaEntrada() {
		return bandejaEntrada;
	}
	

	public ArrayList<Mensaje> getEnviados() {
		return enviados;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setBandejaEntrada(ArrayList<Mensaje> bandejaEntrada) {
		this.bandejaEntrada = bandejaEntrada;
	}

	public void setEnviados(ArrayList<Mensaje> enviados) {
		this.enviados = enviados;
	}
		
	public static void main(String[] args) {
		Persona persona = new Persona( "PepaPig", "pipi", "12345678A", "Pepa", "Pig", "Pam", "944444444", "Mi Casa", "07/07/1996");
		System.out.println(persona.getFechaNacimiento().toString());
		
	}


	
	
}

package baseDeDatos;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objetos.Mensaje;
import objetos.Persona;

/** Métodos útiles para base de datos.
 * Clase con métodos estáticos para gestionar una sola base de datos
 * @author Andoni Eguíluz Morán
 * Facultad de Ingeniería - Universidad de Deusto
 */
public class BaseDeDatos {

	// ------------------------------------
	// VALIDO PARA CUALQUIER BASE DE DATOS
	// ------------------------------------
	
	private static Connection connection = null;
	private static Statement statement = null;

	/** Inicializa una BD SQLITE y devuelve una conexión con ella. Debe llamarse a este 
	 * método antes que ningún otro, y debe devolver no null para poder seguir trabajando con la BD.
	 * @param nombreBD	Nombre de fichero de la base de datos
	 * @return	Conexión con la base de datos indicada. Si hay algún error, se devuelve null
	 */
	public static Connection initBD( String nombreBD ) {
		try {
		    Class.forName("org.sqlite.JDBC");
		    connection = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			statement = connection.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
		    return connection;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/** Cierra la conexión con la Base de Datos
	 */
	public static void close() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** Devuelve la conexión si ha sido establecida previamente (#initBD()).
	 * @return	Conexión con la BD, null si no se ha establecido correctamente.
	 */
	public static Connection getConnection() {
		return connection;
	}
	
	/** Devuelve una sentencia para trabajar con la BD,
	 * si la conexión si ha sido establecida previamente (#initBD()).
	 * @return	Sentencia de trabajo con la BD, null si no se ha establecido correctamente.
	 */
	public static Statement getStatement() {
		return statement;
	}

	public static void ejecutarSentencia(String sql){
		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	// ------------
	// PARTICULAR 
	// ------------
	
	//Ventana_NewPersona
	
	
	/** Crea/Registra un alumno y la base de datos y guarda todos sus atributos con un insert
	 * @param id String
	 * @param nombre String
	 * @param apellido1 String
	 * @param apellido2 String
	 * @param ciudad String
	 * @param telefono String
	 * @param usuario String
	 * @param contrasena String
	 * @param fecha String
	 */
	public static void crearAlumno(String id, String nombre, String apellido1, String apellido2, String ciudad, String telefono, String usuario, String contrasena, String fecha){
		Connection c = initBD("data/bd.db");
		
		try {
			PreparedStatement s = c.prepareStatement("INSERT INTO alumnos VALUES (?,?,?,?,?,?,?,?,?)");
			
			s.setString(1,id);
			s.setString(2,nombre);
			s.setString(3,apellido1);
			s.setString(4,apellido2);
			s.setString(5,ciudad);
			s.setString(6,telefono);
			s.setString(7,usuario);
			s.setString(8,contrasena);
			s.setString(9, fecha);
			
			s.executeUpdate();
			s.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/** Crea/Registra un profesor y la base de datos y guarda todos sus atributos con un insert
	 * @param id String
	 * @param nombre String
	 * @param apellido1 String
	 * @param apellido2 String
	 * @param ciudad String
	 * @param telefono String
	 * @param usuario String
	 * @param contrasena String
	 * @param fecha String
	 */
	public static void crearProfesor(String id, String nombre, String apellido1, String apellido2, String ciudad, String telefono, String usuario, String contrasena, String fecha){
		Connection c = initBD("data/bd.db");
		
		try {
			PreparedStatement s = c.prepareStatement("INSERT INTO profesor VALUES (?,?,?,?,?,?,?,?,?)");
			
			s.setString(1,id);
			s.setString(2,nombre);
			s.setString(3,apellido1);
			s.setString(4,apellido2);
			s.setString(5,ciudad);
			s.setString(6,telefono);
			s.setString(7,usuario);
			s.setString(8,contrasena);
			s.setString(9, fecha);
			
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//Ventana_Perfil
	
	
	/**Guarda todos los campos editables d eun alumno en la BD al clickar guardar cambios
	 * @param p Persona
	 */
	public static void guardarAlumno(Persona p) {
		Connection c = initBD("data/bd.db");

		try {
			PreparedStatement s = c.prepareStatement("UPDATE alumnos SET nombre = ?, apellido1 = ?, apellido2 = ?, ciudad = ?, telefono = ?, usuario = ?, contrasena = ? WHERE id = ?");
			s.setString(1, p.getNombre());
			s.setString(2, p.getApellido1());
			s.setString(3, p.getApellido2());
			s.setString(4, p.getCiudad());
			s.setString(5, p.getTelefono());
			s.setString(6, p.getUserName());
			s.setString(7, p.getPassword());
			s.setString(8, p.getDni());
			
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		
	}
	/**Guarda todos los campos editables d eun alumno en la BD al clickar guardar cambios
	 * @param p Persona
	 */
	public static void guardarProfesor(Persona p) {
		Connection c = initBD("data/bd.db");

		try {
			PreparedStatement s = c.prepareStatement("UPDATE profesor SET nombre = ?, apellido1 = ?, apellido2 = ?, ciudad = ?, telefono = ?, usuario = ?, contrasena = ? WHERE id = ?");
			s.setString(1, p.getNombre());
			s.setString(2, p.getApellido1());
			s.setString(3, p.getApellido2());
			s.setString(4, p.getCiudad());
			s.setString(5, p.getTelefono());
			s.setString(6, p.getUserName());
			s.setString(7, p.getPassword());
			s.setString(8, p.getDni());
			
			s.executeUpdate();
			s.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}	
	}
	
	//Ventana_Login
	
	/**Comprueba que el Alumno haya introducido correctamente su usuario y contraseña
	 * para poder acceder desde login
	 * @param usuario String
	 * @param contrasena String
	 * @return true/false
	 */
	public static boolean comprobarContrasenaAlumno(String usuario, String contrasena){
		Connection c = initBD("data/bd.db");
		boolean correcto = false ;
		ResultSet rs = null;
		try {
			PreparedStatement s1 = c.prepareStatement("SELECT contrasena FROM alumnos WHERE usuario = ? ");
			s1.setString(1, usuario);
			rs = s1.executeQuery();
			 if(rs.next()){
				 if(rs.getString("contrasena").equals(contrasena)){
					 correcto = true;
				 }
			 }
			 s1.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return correcto;
			
	}
	/**Comprueba que el Profesor haya introducido correctamente su usuario y contraseña
	 * para poder acceder desde login
	 * @param usuario String
	 * @param contrasena String
	 * @return true/false
	 */
	public static boolean comprobarContrasenaProfesor(String usuario, String contrasena){
		Connection c = initBD("data/bd.db");
		boolean correcto = false ;
		ResultSet rs = null;
		try {
			PreparedStatement s1 = c.prepareStatement("SELECT contrasena FROM profesor WHERE usuario = ? ");
			s1.setString(1, usuario);
			rs = s1.executeQuery();
			 if(rs.next()){
				 if(rs.getString("contrasena").equals(contrasena)){
					 correcto = true;
				 }
			 }
			 s1.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return correcto;
			
	}
	
	/**Crea una Persona con toda la informacion del Alumno que ha accedido
	 * desde la Ventana_Login
	 * @param usuario String
	 * @param contrasena String
	 * @return Persona
	 */
	public static Persona ConseguirPersonaAlumno (String usuario, String contrasena){
		Persona persona = new Persona();
		Connection c = initBD("data/bd.db");
		ResultSet rs = null;
		try {
			PreparedStatement s1 = c.prepareStatement("SELECT nombre, apellido1, apellido2, id, ciudad, telefono, fechaNcto FROM alumnos WHERE usuario = ? AND contrasena = ?");
			s1.setString(1, usuario);
			s1.setString(2, contrasena);
			rs = s1.executeQuery();
			persona.setNombre(rs.getString("nombre"));
			persona.setApellido1(rs.getString("apellido1"));
			persona.setApellido2(rs.getString("apellido2"));
			persona.setDni(rs.getString("id"));
			persona.setCiudad(rs.getString("ciudad"));
			persona.setTelefono(rs.getString("telefono"));
			persona.setUserName(usuario);
			persona.setPassword(contrasena);
			persona.setFechaNacimiento(rs.getString("fechaNcto"));
			s1.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return persona;

	}
	/**Crea una Persona con toda la informacion del Profesor que ha accedido
	 * desde la Ventana_Login
	 * @param usuario String
	 * @param contrasena String
	 * @return Persona
	 */
	public static Persona ConseguirPersonaProfesor (String usuario, String contrasena){
		Persona persona = new Persona();
		Connection c = initBD("data/bd.db");
		ResultSet rs = null;
		try {
			PreparedStatement s1 = c.prepareStatement("SELECT nombre, apellido1, apellido2, id, ciudad, telefono, fechaNcto FROM profesor WHERE usuario = ? AND contrasena = ?");
			s1.setString(1, usuario);
			s1.setString(2, contrasena);
			rs = s1.executeQuery();
			persona.setNombre(rs.getString("nombre"));
			persona.setApellido1(rs.getString("apellido1"));
			persona.setApellido2(rs.getString("apellido2"));
			persona.setDni(rs.getString("id"));
			persona.setCiudad(rs.getString("ciudad"));
			persona.setTelefono(rs.getString("telefono"));
			persona.setUserName(usuario);
			persona.setPassword(contrasena);
			persona.setFechaNacimiento(rs.getString("fechaNcto"));
			s1.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return persona;

	}
	//Ventana_Busqueda
	
	/**Devuelve un arrayList con todos los Profesores que cumplen las
	 * caracteristicas especificadas por el alumno de Colegio
	 * @param ciudad String 
	 * @param asignatura String
	 * @param idioma String 
	 * @param nivel String
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> busquedaColegio(String ciudad, String asignatura, String idioma, String nivel){
		Connection c = initBD("data/bd.db");
		ResultSet rs = null;
		ArrayList<String> id_profs = conseguirIdProfesorCiudad(ciudad);
		ArrayList<String> id_profs_correctos = new ArrayList<String>();
		try {
			for(String id : id_profs){
				PreparedStatement s = c.prepareStatement("SELECT id_prof FROM clases WHERE id_prof = ? AND asignatura = ? AND idioma = ? AND nivel = ? ");
				s.setString(1, id);
				s.setString(2, asignatura);
				s.setString(3, idioma);
				s.setString(4, nivel);
				rs = s.executeQuery();
				if(rs.next()){
					id_profs_correctos.add(id);
				}
				s.close();
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return id_profs_correctos;
	}
	
	/**Devuelve un arrayList con todos los Profesores que cumplen las
	 * caracteristicas especificadas por el alumno de Escuela de Idiomas
	 * @param ciudad String
	 * @param idioma String
	 * @param nivel String
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> busquedaEscuelaDeIdiomas(String ciudad, String idioma, String nivel){
		Connection c = initBD("data/bd.db");
		ResultSet rs = null;
		ArrayList<String> id_profs = conseguirIdProfesorCiudad(ciudad);
		ArrayList<String> id_profs_correctos = new ArrayList<String>();
		try {
			for(String id : id_profs){
				PreparedStatement s = c.prepareStatement("SELECT id_prof FROM clases WHERE id_prof = ? AND idioma = ? AND nivel = ? ");
				s.setString(1, id);
				s.setString(2, idioma);
				s.setString(3, nivel);
				rs = s.executeQuery();
				if(rs.next()){
					id_profs_correctos.add(id);
				}
				s.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return id_profs_correctos;
	}
	
	
	/**Devuelve un arrayList de id de todos los Profesores que son de la ciudad
	 * especificada por el Alumno
	 * @param ciudad String
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> conseguirIdProfesorCiudad(String ciudad){
		Connection c = initBD("data/bd.db");
		PreparedStatement s = null;
		ResultSet rs = null;
		ArrayList<String> ids = new ArrayList<String>();
		try {
			s = c.prepareStatement("SELECT id FROM profesor WHERE ciudad = ?"); 
			s.setString(1, ciudad);
			rs = s.executeQuery();
			while(rs.next()){
				ids.add(rs.getString("id"));
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ids;
	}
	


	/**Devuelve un ArrayList con los nombres de los profesores de la ciudad 
	 * especificada por el Alumno
	 * @param id_prof ArrayLis<String>
	 * @return ArrayLis<String>
	 */
	public static ArrayList<String> conseguirIdProfesorNombre(ArrayList<String> id_prof){
		Connection c = initBD("data/bd.db");
		PreparedStatement s = null;
		ResultSet rs = null;
		ArrayList<String> nombres = new ArrayList<String>();
		try {
			for (String id : id_prof) {
				s = c.prepareStatement("SELECT nombre FROM profesor WHERE id = ?");
				s.setString(1, id);
				rs = s.executeQuery();
				nombres.add(rs.getString("nombre"));
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nombres;
	}
	/**Devuelve un ArrayList con los nombres de los profesores de la ciudad 
	 * especificada por el Alumno
	 * @param id_prof ArrayLis<String>
	 * @return ArrayLis<String>
	 */
	public static ArrayList<String> conseguirIdProfesorApellidos(ArrayList<String> id_prof){
		Connection c = initBD("data/bd.db");
		PreparedStatement s = null;
		ResultSet rs = null;
		ArrayList<String> apellidos = new ArrayList<String>();
		try {
			for (String id : id_prof) {
				s = c.prepareStatement("SELECT apellido1, apellido2 FROM profesor WHERE id = ?");
				s.setString(1, id);
				rs = s.executeQuery();
				apellidos.add(rs.getString("apellido1")+" "+ rs.getString("apellido2"));
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apellidos;
	}
	
	//Ventana_Mensaje
	//Bandeja de entrada
	
	/**Devuelve un ArrayList con id de los mensajes en los que el usuario 
	 * esta en id_recibe
	 * @param idUsuario String
	 * @return ArrayList <String>
	 */
	public static ArrayList<String> conseguirIdRecibidos(String idUsuario){
		Connection c = initBD("data/bd.db");
		PreparedStatement s = null;
		ResultSet rs = null;
		ArrayList<String>idRecibidos = new ArrayList<String>();
		try {
			s = c.prepareStatement("SELECT id FROM mensajes WHERE id_recibe = ?"); 
			s.setString(1, idUsuario);
			rs = s.executeQuery();
			while (rs.next()){
				idRecibidos.add(rs.getString("id"));
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return idRecibidos;
	}
	
	/**Devuelve un ArrayList con los mensajes de los id resultado del 
	 * metodo conseguirIdRecibidos(String idUsuario) 
	 * En el caso de que los id sean de Alumnos
	 * @param idUsuario String
	 * @return ArrayList<Mensaje>
	 */
	public static ArrayList<Mensaje> bandejaEntradaAlumno(String idUsuario){
		Connection c = initBD("data/bd.db");
		PreparedStatement s = null;
		ResultSet rs = null;
		ArrayList<String> idRecibidos = conseguirIdRecibidos(idUsuario);
		ArrayList<Mensaje> mensajesRecibidos = new ArrayList<Mensaje>();
		String nombre;
		String idProfesor;
		try {
			for (String id : idRecibidos) {
				s = c.prepareStatement("SELECT id_envia, contenido, asunto, tipo, hora, fecha FROM mensajes WHERE id = ?");
				s.setString(1, id);
				rs = s.executeQuery();
				idProfesor = rs.getString("id_envia");
				nombre = conseguirNombreProfesor(idProfesor);
				while(rs.next()){
					mensajesRecibidos.add(new Mensaje(id, rs.getString("tipo"), nombre , rs.getString("asunto"), rs.getString("contenido"), rs.getString("hora"), rs.getString("fecha")));
				}
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mensajesRecibidos;
	}
	/**Devuelve un ArrayList con los mensajes de los id resultado del 
	 * metodo conseguirIdRecibidos(String idUsuario) 
	 * En el caso de que los id sean de Profesor
	 * @param idUsuario String
	 * @return ArrayList<Mensaje>
	 */
	public static ArrayList<Mensaje> bandejaEntradaProfesor(String idUsuario){
		Connection c = initBD("data/bd.db");
		PreparedStatement s = null;
		ResultSet rs = null;
		ArrayList<String> idRecibidos = conseguirIdRecibidos(idUsuario);
		ArrayList<Mensaje> mensajesRecibidos = new ArrayList<Mensaje>();
		String nombre;
		String idAlumno;
		try {
			for (String id : idRecibidos) {
				s = c.prepareStatement("SELECT id_envia, contenido, asunto, tipo, hora, fecha FROM mensajes WHERE id = ?");
				s.setString(1, id);
				rs = s.executeQuery();
				idAlumno = rs.getString("id_envia");
				nombre = conseguirNombreAlumno(idAlumno);
				while(rs.next()){
					mensajesRecibidos.add(new Mensaje(id, rs.getString("tipo"), nombre , rs.getString("asunto"), rs.getString("contenido"), rs.getString("hora"), rs.getString("fecha")));
				}
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mensajesRecibidos;
	}
	
	//Enviados
	/**Devuelve un ArrayList con id de los mensajes en los que el usuario 
	 * esta en id_envia
	 * @param idUsuario String
	 * @return ArrayList <String>
	 */
	public static ArrayList<String> conseguirIdEnviados(String idUsuario){
		Connection c = initBD("data/bd.db");
		PreparedStatement s = null;
		ResultSet rs = null;
		ArrayList<String>idEnviados = new ArrayList<String>();
		try {
			s = c.prepareStatement("SELECT id FROM mensajes WHERE id_envia = ?"); 
			s.setString(1, idUsuario);
			rs = s.executeQuery();
			while (rs.next()){
				idEnviados.add(rs.getString("id"));
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return idEnviados;
	}
	/**Devuelve un ArrayList con los mensajes de los id resultado del 
	 * metodo conseguirIdEnviados(String idUsuario) 
	 * En el caso de que los id sean de Alumnos
	 * @param idUsuario String
	 * @return ArrayList<Mensaje>
	 */
	public static ArrayList<Mensaje> enviadosAlumno(String idUsuario){
		Connection c = initBD("data/bd.db");
		PreparedStatement s = null;
		ResultSet rs = null;
		ArrayList<String> idEnviados = conseguirIdEnviados(idUsuario);
		ArrayList<Mensaje> mensajesEnviados = new ArrayList<Mensaje>();
		String nombre;
		String idProfesor;
		try {
			for (String id : idEnviados) {
				s = c.prepareStatement("SELECT id_recibe, contenido, asunto, tipo, hora, fecha FROM mensajes WHERE id = ?");
				s.setString(1, id);
				rs = s.executeQuery();
				
				while (rs.next()) {
					idProfesor = rs.getString("id_recibe");
					nombre = conseguirNombreProfesor(idProfesor);
					mensajesEnviados.add(new Mensaje(id, rs.getString("tipo"),
							nombre, rs.getString("asunto"), rs
									.getString("contenido"), rs
									.getString("hora"), rs.getString("fecha")));
				}
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mensajesEnviados;
	}
	/**Devuelve un ArrayList con los mensajes de los id resultado del 
	 * metodo conseguirIdEnviados(String idUsuario) 
	 * En el caso de que los id sean de Profesor
	 * @param idUsuario String
	 * @return ArrayList<Mensaje>
	 */
	public static ArrayList<Mensaje> enviadosProfesor(String idUsuario){
		Connection c = initBD("data/bd.db");
		PreparedStatement s = null;
		ResultSet rs = null;
		ArrayList<String> idEnviados = conseguirIdEnviados(idUsuario);
		ArrayList<Mensaje> mensajesEnviados = new ArrayList<Mensaje>();
		String nombre;
		String idAlumno;
		try {
			for (String id : idEnviados) {
				s = c.prepareStatement("SELECT id_recibe, contenido, asunto, tipo, hora, fecha FROM mensajes WHERE id = ?");
				s.setString(1, id);
				rs = s.executeQuery();
				idAlumno = rs.getString("id_recibe");	
				nombre = conseguirNombreAlumno(idAlumno);
				while (rs.next()) {

					mensajesEnviados.add(new Mensaje(id, rs.getString("tipo"),
							nombre, rs.getString("asunto"), rs
									.getString("contenido"), rs
									.getString("hora"), rs.getString("fecha")));
					
				}
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mensajesEnviados;
	}
	
	/**Devuelve el nombre del alumno correspondiente al id proporcionado
	 * @param id String
	 * @return String
	 */
	public static String conseguirNombreAlumno(String id){
		Connection c = initBD("data/bd.db");
		PreparedStatement s = null;
		ResultSet rs = null;
		String nombre = null;
		try {
				s = c.prepareStatement("SELECT nombre FROM alumno WHERE id = ?");
				s.setString(1, id);
				rs = s.executeQuery();
				nombre = rs.getString("nombre");
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nombre;
	}
	/**Devuelve el nombre del profesor correspondiente al id proporcionado
	 * @param id String
	 * @return String
	 */
	public static String conseguirNombreProfesor(String id){
		Connection c = initBD("data/bd.db");
		PreparedStatement s = null;
		ResultSet rs = null;
		String nombre = null;
		try {
				s = c.prepareStatement("SELECT nombre FROM profesor WHERE id = ?");
				s.setString(1, id);
				rs = s.executeQuery();
				while (rs.next()){
					nombre = rs.getString("nombre");
				}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nombre;
	}
	
	/**Devuelve el contenido de un Mensaje 
	 * @param mensaje Mensaje
	 * @return String
	 */
	public static String conseguirContenido(Mensaje mensaje){
		Connection c = initBD("data/bd.db");
		PreparedStatement s = null;
		ResultSet rs = null;
		String id = mensaje.getId();
		String contenido = null;
		try {
				s = c.prepareStatement("SELECT contenido FROM mensajes WHERE id = ?");
				s.setString(1, id);
				rs = s.executeQuery();
				while (rs.next()){
					contenido = rs.getString("contenido");
				}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contenido;
	}
	
	
	/**Elimina de la BD un arraylist donde se guardan todos los id 
	 * d elos mensajes que se desean eliminar por el usuario
	 * @param eliminar ArrayList <Mensaje>
	 */
	public static void borrarMensaje (ArrayList<Mensaje> eliminar){
		Connection c = initBD("data/bd.db");
		PreparedStatement s = null;
		ArrayList<String> idEliminar = new ArrayList<String>();
		for (Mensaje mens: eliminar){
			idEliminar.add(mens.getId());
		}
		try {
			for (String id : idEliminar) {
				s = c.prepareStatement("DELETE FROM mensajes WHERE id = ?");
				s.setString(1, id);
				s.executeUpdate();
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Mandar Mensajes
	
	//Mensajes a enviar por el Alumno
	
	/**El alumno introduce el nombre del Profesor para enviarle un mensaje
	 * Este metodo transforma el nombre en un Id 
	 * para guardarlo en la tabla de Mensaje
	 * @param nombre
	 * @return String
	 */
	public static String conseguirIdProfesor(String nombre){
		Connection c = initBD("data/bd.db");
		PreparedStatement s = null;
		ResultSet rs = null;
		String id = null;
		try {
				s = c.prepareStatement("SELECT id FROM profesor WHERE nombre = ?");
				s.setString(1, nombre);
				rs = s.executeQuery();
				while (rs.next()){
					id = rs.getString("id");
				}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	/**Crea/Guarda el mensaje en la BD con sus respectivos atributos al Profesor
	 * @param id String
	 * @param idUsuario String
	 * @param nombreProf String
	 * @param contenido String
	 * @param asunto String
	 * @param tipo String
	 * @param hora String
	 * @param fecha String
	 */
	public static void mensajeAProfesor(String id, String idUsuario, String nombreProf, String contenido, String asunto, String tipo, String hora, String fecha){
		Connection c = initBD("data/bd.db");
		String idProfesor = conseguirIdProfesor(nombreProf);
		try {
			PreparedStatement s = c.prepareStatement("INSERT INTO mensajes VALUES (?,?,?,?,?,?,?,?)"); 
			s.setString(1, id);
			s.setString(2, idUsuario);
			s.setString(3, idProfesor);
			s.setString(4, contenido);
			s.setString(5, asunto);
			s.setString(6, tipo);
			s.setString(7, hora);
			s.setString(8, fecha);
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	//Mensajes a enviar por el Profesor
	/**El Profesor introduce el nombre del Alumno para enviarle un mensaje
	 * Este metodo transforma el nombre en un Id 
	 * para guardarlo en la tabla de Mensaje
	 * @param nombre
	 * @return String
	 */
	public static String conseguirIdAlumno(String nombre){
		Connection c = initBD("data/bd.db");
		PreparedStatement s = null;
		ResultSet rs = null;
		String id = null;
		try {
				s = c.prepareStatement("SELECT id FROM alumnos WHERE nombre = ?");
				s.setString(1, nombre);
				rs = s.executeQuery();
				while (rs.next()){
					id = rs.getString("id");
				}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	/**Crea/Guarda el mensaje en la BD con sus respectivos atributos al Alumno
	 * @param id String
	 * @param idUsuario String
	 * @param nombreProf String
	 * @param contenido String
	 * @param asunto String
	 * @param tipo String
	 * @param hora String
	 * @param fecha String
	 */
	public static void mensajeAAlumno(String id, String idUsuario, String nombreAlum, String contenido, String asunto, String tipo, String hora, String fecha){
		Connection c = initBD("data/bd.db");
		String idAlumno = conseguirIdAlumno(nombreAlum);
		try {
			PreparedStatement s = c.prepareStatement("INSERT INTO mensajes VALUES (?,?,?,?,?,?,?,?)"); 
			s.setString(1, id);
			s.setString(2, idUsuario);
			s.setString(3, idAlumno);
			s.setString(4, contenido);
			s.setString(5, asunto);
			s.setString(6, tipo);
			s.setString(7, hora);
			s.setString(8, fecha);
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
}

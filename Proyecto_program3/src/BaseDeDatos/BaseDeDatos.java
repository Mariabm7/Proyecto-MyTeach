package BaseDeDatos;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	public static void guardarAlumno(Persona p) {
		Connection c = initBD("data/bd.db");

		try {
			PreparedStatement s = c.prepareStatement("UPDATE alumnos SET nombre = ?, apellido1 = ?, apellido2 = ?, ciudad = ?, telefono = ?, usuario = ?, contrasena = ? WHERE id = ?");
//TODO En la clase Ventana_Perfil hacer set para coger directamente con get ya cambiadas
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
	public static void guardarProfesor(Persona p) {
		Connection c = initBD("data/bd.db");

		try {
			PreparedStatement s = c.prepareStatement("UPDATE profesor SET nombre = ?, apellido1 = ?, apellido2 = ?, ciudad = ?, telefono = ?, usuario = ?, contrasena = ? WHERE id = ?");
//TODO En la clase Ventana_Perfil hacer set para coger directamente con get ya cambiadas
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
			persona.setFechaNacimiento("fechaNcto");
			s1.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return persona;

	}
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
			persona.setFechaNacimiento("fechaNcto");
			s1.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return persona;

	}
	//Ventana_Busqueda
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
	
	private ArrayList<String> prof_nombre;
	private ArrayList<String> prof_Apellido;
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
	//TODO mensajes bandeja de entrada y enviados
	public static String conseguirIdAlumno(String usuario){
		Connection c = initBD("data/bd.db");
		PreparedStatement s = null;
		ResultSet rs = null;
		try {
			s = c.prepareStatement("SELECT id FROM alumnos WHERE usuario = ?"); 
			s.setString(1, usuario);
			rs = s.executeQuery();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		try {
			return rs.getString("id");
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String conseguirIdProfesor(String usuario){
		Connection c = initBD("data/bd.db");
		PreparedStatement s = null;
		ResultSet rs = null;
		try {
			s = c.prepareStatement("SELECT id FROM profesor WHERE usuario = ?"); 
			s.setString(1, usuario);
			rs = s.executeQuery();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			return rs.getString("id");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void mensajeAProfesor(String usuarioAlum, String usuarioProf, String contenido, String asunto, String tipo){
		String idAlumno = conseguirIdAlumno(usuarioAlum);
		String idProfesor = conseguirIdAlumno(usuarioProf);
		Connection c = initBD("data/bd.db");
		try {
			PreparedStatement s = c.prepareStatement("INSERT INTO mensaje VALUES (?,?,?,?,?)"); 
			s.setString(1, idAlumno);
			s.setString(2, idProfesor);
			s.setString(3, contenido);
			s.setString(4, asunto);
			s.setString(5, tipo);
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	public static void mensajeAAlumno(String usuarioAlum, String usuarioProf, String contenido, String asunto, String tipo){
		String idAlumno = conseguirIdAlumno(usuarioAlum);
		String idProfesor = conseguirIdAlumno(usuarioProf);
		Connection c = initBD("data/bd.db");
		try {
			PreparedStatement s = c.prepareStatement("INSERT INTO mensaje VALUES (?,?,?,?,?)"); 
			s.setString(1, idProfesor);
			s.setString(2, idAlumno);
			s.setString(3, contenido);
			s.setString(4, asunto);
			s.setString(5, tipo);
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	public static void mensajeId(String id){
		
	}
	
	//TODO borrar mensaje de bd y buscar id de mensaje
	public static void borrarMensaje (int id){
		Connection c = initBD("data/bd.db");
		try {
			PreparedStatement s = c.prepareStatement("DELETE FROM mensaje WHERE id = ?"); 
			s.setInt(1, id);
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

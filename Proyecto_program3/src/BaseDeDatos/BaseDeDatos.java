package BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	public static void busquedaBD(){
		
	}
	
	public static void crearPersona(int id, String nombre, String apellido1, String apellido2, String ciudad, int telefono, String usuario, String contrasena){
		Connection c = initBD("bd.db");
		
		try {
			PreparedStatement s = c.prepareStatement("INSERT INTO alumnos VALUES (?,?,?,?,?,?,?,?)");
			
			s.setString(1,Integer.toString(id));
			s.setString(2,nombre);
			s.setString(3,apellido1);
			s.setString(4,apellido2);
			s.setString(5,ciudad);
			s.setString(6,Integer.toString(telefono));
			s.setString(7,usuario);
			s.setString(8,contrasena);
			
			
			s.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	public static void guardarAlumno(Persona p) {
		Connection c = initBD("bd.db");

		try {
			PreparedStatement s = c.prepareStatement("UPDATE alumnos SET nombre = ?, apellido1 = ?, apellido2 = ?, ciudad = ?, telefono = ?, usuario = ?, contrasena = ? WHERE id = ? )");
//TODO En la clase Ventana_Perfil hacer set para coger directamente con get ya cambiadas
			s.setString(1, p.getNombre());
			s.setString(2, p.getApellido1());
			s.setString(3, p.getApellido2());
			s.setString(4, p.getCiudad());
			s.setString(5, Integer.toString(p.getTelefono()));
			s.setString(6, p.getUserName());
			s.setString(7, p.getPassword());
			s.setString(8, Integer.toString(p.getId()));
			
			s.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		
	}
	public static boolean comprobarContrasena(String usuario, String contrasena){
		Connection c = initBD("bd.db");
		boolean correcto = false ;
		try {
			PreparedStatement s1 = c.prepareStatement("SELECT usuario FROM alumnos WHERE usuario = ? ");
			s1.setString(1, usuario);
			if (s1.equals(usuario)){
				PreparedStatement s2 = c.prepareStatement("SELECT contrasena FROM alumnos WHERE contrasena = ? ");
				s2.setString(1, contrasena);
				if (s2.equals(contrasena)){
					correcto = true;
				}else{
					correcto = false;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return correcto;
			
	}
	//TODO mensajes bandeja de entrada y enviados
	public static void busqueda(String tipo, String ciudad, String asignatura, String idioma, String nivel){
		Connection c = initBD("bd.bd");
		
		try {
			PreparedStatement s = c.prepareStatement("");
			PreparedStatement s1 = c.prepareStatement("SELECT tipo FROM alumnos WHERE tipo = ?");
			s.setString(1, tipo);
			//TODO inacabado
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
}

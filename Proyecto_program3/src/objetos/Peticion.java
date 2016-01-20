package objetos;
//Herencia de Mensaje
public class Peticion extends Mensaje{
	//Constructores
	public Peticion(String id, String tipo, String de, String asunto,
			String contenido, String hora, String fecha) {
		super(id, tipo, de, asunto, contenido, hora, fecha);	
	}

	public Peticion(String tipo, String de, String asunto, String contenido,
			String hora, String fecha) {
		super(tipo, de, asunto, contenido, hora, fecha);
	}

	public Peticion() {
	}



}

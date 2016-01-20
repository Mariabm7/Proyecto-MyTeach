package objetos;

public class Mensaje implements DatoParaTabla{
	private String id;
	private String tipo;
	private String para;
	private String de;
	private String asunto;
	private String contenido;
	private String hora;
	private String fecha;
	private boolean eliminar;
	
	//Elementos para JTable
	public static String[] nombresAtributos = new String[] {
		"Tipo", "De", "Asunto", "Hora", "Fecha", "Eliminar" };
	public static boolean[] atributosEditables = new boolean[] {
		false, false, false, false, false, true };
	//Constructores
	public Mensaje(){}
	
	public Mensaje(String tipo ,String de, String asunto, String contenido, String hora, String fecha){
		this.tipo = tipo;
		this.de = de;
		this.asunto = asunto;
		this.contenido = contenido;
		this.hora = hora;
		this.fecha = fecha;
	}
	
	public Mensaje(String id, String tipo, String de, String asunto,
			String contenido, String hora, String fecha) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.de = de;
		this.asunto = asunto;
		this.contenido = contenido;
		this.hora = hora;
		this.fecha = fecha;
	}
	//Getters and setters
	
	public String getTipo(Mensaje mensaje) {
		if ( mensaje instanceof Peticion ){
			tipo = "Peticio9n";
		}else if (mensaje instanceof Mensaje){
			tipo = "Mensaje";	
		}
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPara() {
		return para;
	}
	public void setPara(String para) {
		this.para = para;
	}
	public String getDe() {
		return de;
	}
	public void setDe(String de) {
		this.de = de;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
		public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	//Metodos de la interfaz para la JTable
	@Override
	public int getNumColumnas() {
		return 6;
	}

	@Override
	public Object getValor(int col) {
		switch (col) {
    	case 0: { return tipo; }
    	case 1: { return de; }
    	case 2: { return asunto; }
    	case 3: { return hora; }
    	case 4: { return fecha; }
    	case 5: { return eliminar; }
	}
	return null;
	}

	@Override
	public void setValor(Object value, int col) {
		try {
	    	switch (col) {
		    	case 0: { tipo = (String) value; break; }
		    	case 1: { de = (String) value; break; }
		    	case 2: { asunto = (String) value; break; }
		    	case 3: { hora = (String) value; break; }
		    	case 4: { fecha = (String) value; break; }
		    	case 5: { eliminar = (Boolean) value; break; }
	    	}
    	} catch (Exception e) {
    		// Error en conversión. Intentando asignar un tipo incorrecto
    		e.printStackTrace();
    	}
	}
}

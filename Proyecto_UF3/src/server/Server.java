package server;
import java.io.*;  
import java.net.*;
import java.util.ArrayList;  

public class Server {
	
	// Configuraci�n del servidor proporcionada por la actividad.
	private final int PORT = 9876;
	private ServerSocket servsock;
	private Socket socket;
	// Creaci�n del servidor.
	public Server() throws IOException{
		servsock = new ServerSocket(PORT);
		socket = new Socket();
	}
	
	public void iniciarsv() throws IOException {
		
		System.out.println("Esperando cliente");
		
		// Aceptar comunicaci�n con el cliente.
		socket = servsock.accept();
		
		// Los DataInput/OutputStream que se usan para el servidor.
		DataOutputStream msgacl = new DataOutputStream(socket.getOutputStream());
		DataInputStream msgpcl = new DataInputStream(socket.getInputStream());
		
		// Preguntar por el nombre del cliente.
		msgacl.writeUTF("�Bienvenido como te llamas?");
		// Resuesta del cliente.
		System.out.println("Encantado de verte, " + msgpcl.readUTF());
		// Preguntar por el n�mero de tareas.
		msgacl.writeUTF("�Cuantas tareas has de realizar?");
		// La respuesta es transformada en int y guardada para el uso en bucles y en operaciones ternarias.
		int ntareas = Integer.parseInt(msgpcl.readUTF());
		// Operaciones ternarias para determinar que palabras mostrar por pantalla..
		String tareatxt = (ntareas > 1) ? "tareas" : "tarea";
		String htxt = (ntareas > 1) ? "han" : "ha";
		System.out.println("Se "+htxt+" recibido " + ntareas + " "+tareatxt+".");
		
		int i = 0;
		// Array de 2 dimensiones para poder almacenar las descripciones y estados de cada tarea.
		String[][] tareas = new String[ntareas+1][2];
		// Lista para guardar cada tarea despu�s de crearla en Tarea.java.
		ArrayList<Tarea> lista = new ArrayList<Tarea>();
		// Bucle para preguntar la descripci�n y el estado de cada tarea.
		do {
			// Preguntar por descripci�n.
			msgacl.writeUTF("Introducci�n de la tarea : "+(i+1)+"\nIntroduce la descripci�n:");
			// Guardar respuesta (descripci�n).
			tareas [i][0]= msgpcl.readUTF();
			System.out.println("Descripci�n recibida: " + tareas [i][0]);
			// Preguntar por estado.
			msgacl.writeUTF("Introduce el estado de la tarea:");
			tareas [i][1]= msgpcl.readUTF();
			System.out.println("Estado recibido: " + tareas [i][1]);
			// Crear nueva tarea en Tarea.java y a�adir a la lista de tareas.
			Tarea tarea = new Tarea(tareas [i][0], tareas [i][1]);
			lista.add(tarea);
			i++;
		}while(ntareas > i);
		
		// Lista de tareas.
		System.out.println("Enviando listado de tareas");
		msgacl.writeUTF("Listado de tareas:");
		// Enviar lista de tareas al cliente. Se podria haber hecho directamente desde el array de 2 dimensiones.
		// Pero he usado la lista para usar los getters.
		for(int x = 0; x < lista.size(); x++) {
			msgacl.writeUTF("Tarea n�"+(x+1)+": "+lista.get(x).getDescripcion()+", "+lista.get(x).getEstado());
        }
	}
	// Cerrar el servidor.
	public void finServ() throws IOException {
		servsock.close();
    }
}

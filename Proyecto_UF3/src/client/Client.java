package client;

import java.io.*;  
import java.net.*;
import java.util.Scanner;  

public class Client {

	// Configuración proporcionada.
	private final String HOST = "localhost";
	private final int PORT = 9876;
	private Socket socket;
	// Scanner para poder leer los inputs del cliente.
	Scanner sc = new Scanner(System.in);
	// Creación de un Client.
	public Client() throws IOException {
		
		socket = new Socket(HOST, PORT);
		
	}
	
	public void iniciarcl() throws IOException {

		// Los DataInput/OutputStream que se usan para el cliente.
		DataInputStream entradasv = new DataInputStream(socket.getInputStream());
		DataOutputStream salidasv = new DataOutputStream(socket.getOutputStream());
		
		// Primera pregunta del servidor (Nombre de usuario).
		System.out.println(entradasv.readUTF());
		// Almacenar el nombre del cliente.
		String name = sc.nextLine();
		// Enviar el nombre.
		salidasv.writeUTF(name);
		// Segunda pregunta del servidor (Tareas deseadas).
		System.out.println(entradasv.readUTF());
		// Almacenar la respuesta y convertirala en String para el envió y volver a convertirla en Int para usarlo en bucles.
		String tasks = Integer.toString(sc.nextInt());
		int ntasks = Integer.parseInt(tasks);
		// Enviar el número de tareas.
		salidasv.writeUTF(tasks);
		
		// Inicio de bucle que se repite hasta recoger los datos de las tareas deseadas.
		int i = 0;
		String descrip;
		String estado;
		do {
			// Mensaje del servidor pidiendo la descripción
			System.out.println(entradasv.readUTF());
			// Bucle para controlar un error del scanner
			while(i == 0) {
				sc.nextLine();
				break;
			}
			// Recoger datos de descripción del cliente.
			descrip = sc.nextLine();
			// Comprobar que no esta vacío y en caso de estarlo hacer la pregunta hasta recibir una respuesta.
			while (descrip.isEmpty()) {
				System.out.println("Introduce una descripción:");
				descrip = sc.nextLine();
			}
			// Enviar descripción
			salidasv.writeUTF(descrip);
			
			// Mensaje del servidor pidiendo el estado.
			System.out.println(entradasv.readUTF());
			// Recoger datos de estado.
			estado = sc.nextLine();
			// Comprobar que no esta vacío como en la descripción.
			while (estado.isEmpty()) {
				System.out.println("Introduce una estado:");
				estado = sc.nextLine();
			}
			// Enviar estado.
			salidasv.writeUTF(estado);
			i++;
		} while(ntasks > i);
		
		// Recibir lista de tareas y mostrarla por pantalla.
		System.out.println(entradasv.readUTF());
		for(int x = 0; ntasks > x; x++) {
			System.out.println(entradasv.readUTF());
		}
		// Cerrar las conexiones y notificar al cliente.
		System.out.println("Cerrando cliente");
		salidasv.close();
		entradasv.close();
		socket.close();
	}
	
}

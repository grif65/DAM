package server;
import java.io.*;  

public class Main {

	public static void main(String[] args) throws IOException  {

		// Crear servidor
		Server sv = new Server();
		System.out.println("El servidor se ha iniciado");
		// Iniciar servidor
		sv.iniciarsv();
		// Finalizar servidor
		sv.finServ();
	}

}

package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

	ServerSocket myServerSocket;
	Socket myClientSocket;
	Boolean serverON;

	public void Arrancar() {

		serverON = true;

		try {
			myServerSocket = new ServerSocket(4000);
		} catch (IOException ioe) {
			System.out
					.println("No se pudo cargar el socket en el puerto 4000.");
			System.exit(-1);
		}
		
		try {
			// Accept incoming connections.
			while (serverON) {
				Socket myClientSocket = myServerSocket.accept();

				ClientServiceThread cliThread = new ClientServiceThread(
						myClientSocket);
				cliThread.start();
			}
		} catch (IOException ioe) {
			System.out
					.println("Error al aceptar la conexion.");
			ioe.printStackTrace();
		}

		try {
			myServerSocket.close();
			System.out.println("El server se detuvo");
		} catch (Exception ioe) {
			System.out.println("Error al detener el socket");
			System.exit(-1);
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Arrancar();
	}
}
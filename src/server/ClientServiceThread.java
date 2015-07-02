package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import model.Cliente;
import repository.ClienteRepository;

public class ClientServiceThread extends Thread {
	Socket myClientSocket;
	ObjectOutputStream outToClient = null;
	// BufferedReader inFromClient = null;
	DataInputStream inFromClient = null;

	public ClientServiceThread() {
		super();
	}

	ClientServiceThread(Socket s) {
		myClientSocket = s;

	}

	public void run() {
		ClienteRepository repository = new ClienteRepository();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		try {
				outToClient = new ObjectOutputStream(
						myClientSocket.getOutputStream());
				// inFromClient = new BufferedReader(new InputStreamReader(
				// myClientSocket.getInputStream()));
				inFromClient = new DataInputStream(
						myClientSocket.getInputStream());

				String comando = inFromClient.readUTF();
				System.out.println(comando);
				System.out.println("Antes de Clientes");
				if (comando.equals("Clientes")) {
					System.out.println("Clientes");

					 clientes = repository.ListadoArchivo();
//					clientes.add(new Cliente());
//					clientes.add(new Cliente());
//					clientes.add(new Cliente());

					for (Cliente c : clientes) {
						outToClient.writeObject(c);

					}
				}
				System.out.println("Salio Clientes");
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				outToClient.close();
				inFromClient.close();
				myClientSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}

package api;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App {

	public static final int PORT = 2000;

	void startServer() {
		ServerSocket serversocket = null;
		try {

			serversocket = new ServerSocket(PORT);
			System.out.println("Waiting for connections");
			while (true) {
				Socket socket = serversocket.accept();
				System.out.println("New client manager started");
				new ClientManagerThread(socket).start();
			}

		} catch (IOException ex) {
			System.err.println("Error :" + ex.getMessage());
		} finally {
			try {
				serversocket.close();
			} catch (IOException ex2) {
			}
		}
	}

	public static void main(String[] args) {
		App app = new App();
		app.startServer();
	}

}

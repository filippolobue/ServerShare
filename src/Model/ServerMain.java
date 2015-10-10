package Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	public static void main(String[] args) throws IOException {	  
		  
	    // Porta sulla quale ascolta il server
	    int port = -1;

	    // controllo argomenti
	    try {
	      if (args.length == 1) {
	        port = Integer.parseInt(args[0]);
	        // controllo che la porta sia nel range consentito 1024-65535
	        if (port < 1024 || port > 65535) {
	          System.out.println("Usage: java ServerMain [serverPort>1024]");
	          System.exit(1);
	        }
	      } else {
	        System.out.println("Usage: java ServerMain port");
	        System.exit(1);
	      }
	    } // try
	    catch (Exception e) {
	      System.out.println("Problemi, i seguenti: ");
	      e.printStackTrace();
	      System.out.println("Usage: java ServerMain port");
	      System.exit(1);
	    }

	    ServerSocket serverSocket = null;
	    Socket clientSocket = null;

	    try {
	      serverSocket = new ServerSocket(port);
	      serverSocket.setReuseAddress(true);
	      System.out.println("PutFileServer: avviato ");
	      System.out.println("Server: creata la server socket: " + serverSocket);
	    } catch (Exception e) {
	      System.err.println("Server: problemi nella creazione della server socket: " + e.getMessage());
	      e.printStackTrace();
	      System.exit(1);
	    }

	    try {
	      while (true) {
	        System.out.println("Server: in attesa di richieste...\n");

	        try {
	          // bloccante finchè non avviene una connessione
	          clientSocket = serverSocket.accept();
	          clientSocket.setSoTimeout(60000);
	          
	          System.out.println("Server: connessione accettata: " + clientSocket);
	        } catch (Exception e) {System.err.println("Server: problemi nella accettazione della connessione: "+ e.getMessage()); e.printStackTrace();
	          continue;
	        }

	        // serizio delegato ad un nuovo thread
	        try {
	          new ServerThread(clientSocket).start();
	        } catch (Exception e) {System.err.println("Server: problemi nel server thread: " + e.getMessage());e.printStackTrace();
	          continue;
	        }

	      } //fine while; teoricamente dura per sempre!
	    }
	    catch (Exception e) {//eccezioni non catturate all'interno del while
	      e.printStackTrace();
	      // chiusura di stream e socket
	      System.out.println("ServerMain: termino...");
	      System.exit(2);
	    }

	  }
}

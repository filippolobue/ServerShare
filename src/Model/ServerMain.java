package Model;

import java.net.ServerSocket;
import java.net.Socket;

import Presenter.MainPresenter;
import View.MainView;

public class ServerMain {
	
	public static void main(String[] args) throws Exception {	  
		  
	    // Porta sulla quale ascolta il server
	    int port = -1;
	    Document.getIstance().updateRootsFS();
//	    Document.getIstance().report("prova", "asdasdasdasd");
//	    Document.getIstance().close();
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
				MainView window = new MainView();
	    		MainPresenter mp = new MainPresenter(window);
	    		
				window.open(mp);
		
	      while (true) {
	        System.out.println("Server: in attesa di richieste...\n");
	        
	        try {
	          // bloccante finchè non avviene una connessione
	          clientSocket = serverSocket.accept();
	          clientSocket.setSoTimeout(30000);
	          
	          System.out.println("Server: connessione accettata: " + clientSocket);
	          //LOG CONNESSIONE !!
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
	    }finally{
	    	serverSocket.close();
	    }

	  }
}

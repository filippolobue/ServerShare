package Model;

import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread{
	
	private Socket clientSocket = null;
	private Ricevitore ricevitore = null;
	
	  public ServerThread(Socket clientSocket) throws IOException {
	    this.clientSocket = clientSocket;
	    this.ricevitore = new Ricevitore(this.clientSocket);
	  }

	  public void run() {

	    System.out.println("Attivazione figlio: " + Thread.currentThread().getName());
	    try {
	    	Document.getIstance().report("ServerThread", "Avviato Thread per cliente " + this.clientSocket.getInetAddress());

	      try {
	    	  //prima cosa che deve fare il cliente è l'autenticazione!!!
	    	  if(!this.ricevitore.autentica())
	    	  {
	    		  System.out.println("Autenticazione Errore");
	    		  return;
	    	  }
    		  System.out.println("Autenticazione Successo");
	    	  
    		  //Ciclo infinito 
	        while (true) {
	          this.ricevitore.decodifica();
	        } //while
	      } catch (Exception e) {//Exception lanciata quando un client digita ctrl z perchè non vuole più inviare Direttori(file contenuti in esso)
	        System.out.println("Fine invio da parte del Client: ");
	        //e.printStackTrace();
	        System.out.println("Chiudo ed esco...");
	        clientSocket.close();
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	      // chiusura di stream e socket
	      System.out.println("Errore irreversibile, ServerThread: termino...");
	    }
	    System.out.println("Terminazione figlio: " + Thread.currentThread().getName());
	  } // run
}

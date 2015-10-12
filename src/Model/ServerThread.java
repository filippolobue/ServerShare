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
	   // DataInputStream inSock;
	   // DataOutputStream outSock;

	    System.out.println("Attivazione figlio: " + Thread.currentThread().getName());
	    try {
	      /*try {
	        // creazione stream di input e out da socket
	        inSock = new DataInputStream(clientSocket.getInputStream());
	        outSock = new DataOutputStream(clientSocket.getOutputStream());
	      } catch (IOException ioe) {
	        System.out.println("Problemi nella creazione degli stream di input/output " + "su socket: ");
	        ioe.printStackTrace();
	        // il server continua l'esecuzione riprendendo dall'inizio del ciclo
	        return;
	      } catch (Exception e) {
	        System.out.println("Problemi nella creazione degli stream di input/output " + "su socket: ");
	        e.printStackTrace();
	        // il server continua l'esecuzione riprendendo dall'inizio del ciclo
	        return;
	      }*/

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

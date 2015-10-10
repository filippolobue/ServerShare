package Model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerThread extends Thread{
	private Socket clientSocket = null;
	  
	  // costruttore
	  public ServerThread(Socket clientSocket) {
	    this.clientSocket = clientSocket;
	  }

	  public void run() {
	    DataInputStream inSock;
	    DataOutputStream outSock;

	    //Creazione Cartella destinazione di tutti i file inviati dai tanti client al server
	    File desktopSave = new File(System.getProperty("user.home"), "Desktop");
		desktopSave = new File(desktopSave.getAbsolutePath(), "SERVER");

		  if (!desktopSave.exists()) {
		    	desktopSave.mkdir();
		  }
		//-----------------------------------------------------
	    System.out.println("Attivazione figlio: " + Thread.currentThread().getName());
	    try {
	      try {
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
	      }

	      try {
	        String nomeFileRicevuto;
	        long numeroByte;
	        File fileCorr;
	        FileOutputStream outFileCorr;
	        // leggo il nome del file
	        while (true) {
	          while ((nomeFileRicevuto = inSock.readUTF()) != null) {
	            fileCorr = new File(desktopSave.getAbsolutePath(),nomeFileRicevuto);//desktopSave.getAbsolutePath(),
	            System.out.println("fileCorr: "+fileCorr.getAbsolutePath());
	            if (fileCorr.exists()) {
	              outSock.writeUTF("File gia' presente, NON sovrascrivo");
	            } else {
	              outSock.writeUTF("attiva");
	              // leggo il numero di byte
	              numeroByte = inSock.readLong();
	              System.out.println("Scrivo il file " + nomeFileRicevuto + " di " + numeroByte + " byte");
	              outFileCorr = new FileOutputStream(fileCorr.getAbsolutePath());
	              // trasferimento file
	              //FileUtility.trasferisci_N_byte_file_binario_fast(inSock,new DataOutputStream(outFileCorr),100, (numeroByte/100),(int)(numeroByte%100));
	              // chiusura file
	              outFileCorr.close();
	            }
	          } // while
	        } // while

	        /*
	         * NOTA: in caso di raggiungimento dell'EOF, la readUTF lancia una
	         * eccezione che viene gestita qui sotto chiudendo la socket e
	         * terminando il client con successo.
	         */
	      } catch (EOFException eof) {
	        System.out.println("Raggiunta la fine delle ricezioni, chiudo...");
	        // e.printStackTrace();
	        // finito il ciclo di ricezioni termino la comunicazione
	        clientSocket.close();
	        // Esco con indicazione di successo
	        System.out.println("PutFileServerThread: termino...");
	      } catch (SocketTimeoutException ste) {
	        System.out.println("Timeout scattato: ");
	        ste.printStackTrace();
	        clientSocket.close();
	      } catch (Exception e) {//Exception lanciata quando un client digita ctrl z perchè non vuole più inviare Direttori(file contenuti in esso)
	        System.out.println("Fine invio da parte del Client: ");
	        //e.printStackTrace();
	        System.out.println("Chiudo ed esco...");
	        clientSocket.close();
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	      // chiusura di stream e socket
	      System.out.println("Errore irreversibile, PutFileServerThread: termino...");
	    }
	    System.out.println("Terminazione figlio: " + Thread.currentThread().getName());
	  } // run
}

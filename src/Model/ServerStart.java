package Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStart extends Thread {
	
	private int port;
	private ServerSocket serverSocket = null;
    private Socket clientSocket = null;
    
	public ServerStart(int port) throws IOException
	{
		if(port < 1024 || port > 65535)
			this.port = 1212;
		else{	
			this.port = port;
		}
		this.serverSocket = new ServerSocket(this.port);
		this.serverSocket.setReuseAddress(true);
	}
	
	public void ooo() throws IOException
	{
		this.serverSocket.close();
	}
	
	public void run()
	{
		try{
		while (!Thread.currentThread().isInterrupted()) {
	        System.out.println("Server: in attesa di richieste...\n");
	        
	        try {
	          // bloccante finchè non avviene una connessione
	          this.clientSocket = serverSocket.accept();
	          this.clientSocket.setSoTimeout(30000);
	          
	          System.out.println("Server: connessione accettata: " + clientSocket);
	          //LOG CONNESSIONE !!
	        } catch (Exception e) {System.err.println("Server: problemi nella accettazione della connessione: "+ e.getMessage()); e.printStackTrace();
	          continue;
	        }

	        // serizio delegato ad un nuovo thread
	        try {
	          new ServerThread(clientSocket).start();
	        } catch (Exception e) {
	        	System.err.println("Server: problemi nel server thread: " + e.getMessage());e.printStackTrace();
	          continue;
	        }

	      } //fine while; teoricamente dura per sempre!	
		} catch (Exception e) {//eccezioni non catturate all'interno del while
		      e.printStackTrace();
		      // chiusura di stream e socket
		      System.out.println("ServerMain: termino...");
		      System.exit(2);
		    }finally
				{
		    		try {
		    			this.serverSocket.close();
		    		} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		    			}
				}
	}//run
	
	
}

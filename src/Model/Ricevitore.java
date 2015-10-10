package Model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/*
 * Riceve, decodifica ed esegue le richieste provenienti dal Cliente
 */

public class Ricevitore {
	private DataInputStream inSock;
    private DataOutputStream outSock;
    
	public Ricevitore(Socket clientSocket) throws IOException
	{
		this.inSock = new DataInputStream(clientSocket.getInputStream());
        this.outSock = new DataOutputStream(clientSocket.getOutputStream());
	}
	
	/*
	 * Aspetta StringaString corrispondenti a username e password
	 */
	public boolean autentica() throws IOException
	{
		System.out.println("[Ricevitore] Autenticazione avviata...");
		String username = this.inSock.readUTF();
		String pw = this.inSock.readUTF();
		System.out.println("[Ricevitore] username: " + username + " pw: " + pw);
		return (Clienti.getInstance().autentica(username, pw));
	}
	
	public void decodifica() throws IOException
	{
		System.out.println("[Ricevitore] Decodifica avviata...");
		Byte request = this.inSock.readByte();
		//request.
	}
}

package Model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/*
 * Riceve, decodifica ed esegue le richieste provenienti dal Cliente
 */

public class Ricevitore {
	private Socket clientSocket;
	private DataInputStream inSock;
    private DataOutputStream outSock;
    
	public Ricevitore(Socket clientSocket) throws IOException
	{
		this.clientSocket = clientSocket;
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
	
	private void disconnetti() throws IOException
	{
		System.out.println("[Ricevitore] Disconnessione");
		this.clientSocket.close();
	}
	
	private void outputFS()
	{
		
	}
/*
 * 00000001 -> disconnetti
 * 00000010 -> tutto FS
 */
	public void decodifica() throws IOException
	{
		System.out.println("[Ricevitore] Decodifica avviata...");
		byte[] b = new byte[1];
		int temp = inSock.read(b, 0, 1);
		if(temp < 0)
		{
			System.out.println("Errore imprevisto!! chiusura cliente forzata");
			this.disconnetti();
		}
		String req = String.format("%8s", Integer.toBinaryString(b[0] & 0xFF)).replace(' ', '0');
		switch(req)
		{
		case "00000001":
			this.disconnetti();
			break;
		case "00000010":
			this.outputFS();
			break;
		}
	}
}

package Model;

import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import Tree.FileMultimediale;

/*
 * Riceve, decodifica ed esegue le richieste provenienti dal Cliente
 */

public class Ricevitore {
	private Socket clientSocket;
	private DataInputStream inSock;
    private DataOutputStream outSock;
    private Esecutore execute;
    
	public Ricevitore(Socket clientSocket) throws IOException
	{
		this.clientSocket = clientSocket;
		this.inSock = new DataInputStream(clientSocket.getInputStream());
        this.outSock = new DataOutputStream(clientSocket.getOutputStream());
        this.execute = new Esecutore(this.clientSocket);
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
	
	
	
/*
 * 00000001 -> disconnetti
 * 00000010 -> tutto FS
 */
	public void decodifica() throws Exception
	{
		System.out.println("[Ricevitore] Decodifica avviata...");
		byte[] b = new byte[1];
		int temp = inSock.read(b, 0, 1);
		if(temp < 0)//ricezione errata
		{
			System.out.println("Errore imprevisto!! chiusura cliente forzata");
			this.execute.disconnetti(this.clientSocket);
		}
		String req = String.format("%8s", Integer.toBinaryString(b[0] & 0xFF)).replace(' ', '0');
		switch(req)
		{
		case "00000001":
			this.execute.disconnetti(this.clientSocket);
			break;
		case "00000010":
			System.out.println("[Esecutore] outpusFS");
			this.execute.outputFS(Document.getIstance().getRoot());
			this.outSock.writeInt(-1);System.out.println(-1);
			this.outSock.writeInt(-1);this.outSock.writeInt(-1);this.outSock.writeInt(-1);this.outSock.writeInt(-1);
			break;
		}
	}
	
	public class Esecutore{
		
		private Socket clientSocket;
	    private DataOutputStream outSock;
	    
		public Esecutore(Socket clientSocket) throws IOException
		{
			this.clientSocket = clientSocket;
	        this.outSock = new DataOutputStream(clientSocket.getOutputStream());
		}

		public void disconnetti(Socket cS) throws IOException
		{
			System.out.println("[Esecutore] Disconnessione");
			cS.close();
		}
		
		public void outputFS(FileMultimediale fm) throws Exception 
		{
			/*
			 * Cosa si invia al cliente:
			 * 0-	<intero -1>: Fine FS Server
			 * 1-	<Titolo><data><percorso_assoluto><estensione><intero num immagini>
			 * 2-	<dim1><imm1><dim2><imm2>etc...
			 * 3-	<intero num file contenuti>
			 */
			this.outSock.writeInt(1);System.out.print(1+" ");
			this.outSock.writeUTF(fm.getTitolo());System.out.print(fm.getTitolo()+" ");
			//this.outSock.writeUTF(fm.getData()+"");
			this.outSock.writeUTF(fm.getPath());System.out.print(fm.getPath()+" ");
			if(fm.isComposite()){
				this.outSock.writeUTF("composite");System.out.print("composite ");}
			else{
				this.outSock.writeUTF(FileUtility.getExtensione(fm.getPath()));System.out.print(FileUtility.getExtensione(fm.getPath())+" ");}
			
			List<Image> images = fm.getImages();
			int numIm = 0;
			if(images != null)
				numIm = images.size();
			this.outSock.writeInt(numIm);System.out.print(numIm+" ");
			/*
			 * INVIO DELLE IMMAGINI !!!
			 * 
			*/
			if(fm.isComposite())
			{
				this.outSock.writeInt(fm.getChildren().size());System.out.println(fm.getChildren().size());
				for(FileMultimediale f : fm.getChildren())
				{
					outputFS(f);
				}
			}else{
				this.outSock.writeInt(0);System.out.println(0);	//Un component non può contenere altri Component
			}
			
		}
	}
}

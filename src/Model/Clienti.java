package Model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Clienti {
	
	private static Clienti istanza;
	
	Map<String,String> login;

	private Clienti()
	{
		this.login = new HashMap<String,String>();
		//riempimento valori di prova
		this.login.put("c1", "p");
		this.login.put("c2", "p");
		System.out.println("Ho creato l'isntaza!! =)");
	}
	
	public static Clienti getInstance()
	{
		if(istanza == null)
		{
			istanza = new Clienti();
		}
		return istanza;
	}
	
	public void add(String username, String pw) throws IOException
	{
		if(pw == null || this.login.keySet().contains(username))
		{
			throw new IOException("Impossibile aggiungere Cliente[password==null o Cliente già registrato]");
		}
		
		this.login.put(username, pw);
	}
	
	public void delete(String username) throws Exception
	{
		if(!this.login.keySet().contains(username))
		{
			throw new Exception("Non esiste l'username come Cliente.");
		}
		this.login.remove(username);
	}
	
	public boolean autentica(String username, String pw)
	{
		if(username == null || pw == null)
			return false;
		
		if(this.login.get(username).equals(pw))
			return true;
		
		return false;
	}
}

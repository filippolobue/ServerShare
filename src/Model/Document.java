package Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Tree.*;


public class Document {
	
	private static Document istanza;
	private Roots roots;
	private Clienti clienti;
	
	private static FileMultimediale root = FMFactory.createComposite("root", true, "root");

	private Document()
	{
		this.roots = Roots.getInstance();
		this.clienti = Clienti.getInstance();
	}
	
	Roots getRoots()
	{
		return this.roots;
	}
	
	Clienti getClienti()
	{
		return this.clienti;
	}
	
	public static Document getIstance()
	{
		if(istanza == null)
		{
			istanza = new Document();
		}
		return istanza;
	}
	
	public void updateRootsFS() throws Exception
	{
		for(String dirName : this.roots.getRoots())
		{
			System.out.println("[Document] dirName: " + dirName);
			File dirCorr = new File(dirName);
	          if (dirCorr.exists() && dirCorr.isDirectory()) {
	        	  FileUtility.buildFS(root, dirCorr);
	          } else {
	            System.out.print(dirName + " e' un direttorio non esistente o Non è un direttorio");
	            return;
	          }	
		}
	    System.out.println(root.toString());
	}
}

class Clienti {
	
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

class Roots {
	//deve essere un singleton che contiene tutti i root di partenza contenitrici di tutto cio' che io voglio condividere con i client
	
	private static Roots istanza;
	private List<String> roots;

	private Roots()
	{
		roots = new ArrayList<String>();
		//inserimento automatio di una cartella di prova per motivi di test
		this.roots.add("C:\\Users\\FilippoLB\\Desktop\\Progetto Java Personale\\Prototitpo\\Server\\ServerShare\\provafolder");
	}
	
	public List<String> getRoots()
	{
		return this.roots;
	}
	
	public static Roots getInstance()
	{
		if(istanza == null)
		{
			istanza = new Roots();
		}
		return istanza;
	}
	
	public void add(String root) throws IOException
	{
		if(root == null || this.roots.contains(root))
		{
			throw new IOException("Impossibile aggiungere Cliente[password==null o Cliente già registrato]");
		}
		//MANCA IL CONTROLLO CHE IL PATH NON SIA UNA SOTTOCARTELLA DI UN'ALTRA ROOT
		this.roots.add(root);
	}
	
	public void delete(String root) throws Exception
	{
		if(!this.roots.contains(root))
		{
			throw new Exception("Non esiste l'username come Cliente.");
		}
		this.roots.remove(root);
	}
	
}

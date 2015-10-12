package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Roots {
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

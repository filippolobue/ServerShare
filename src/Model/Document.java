package Model;

import java.io.File;

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

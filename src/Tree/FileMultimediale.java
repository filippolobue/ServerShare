package Tree;

import java.awt.Image;
import java.nio.file.attribute.FileTime;
import java.util.List;

/** "Component" */
public abstract class FileMultimediale{
	
	protected String titolo;
	protected FileTime data_creazione;
	protected boolean condivisibile;
	protected String percorso_assoluto;
	protected List<Image> immagini;
	protected FileMultimediale parent;
	
	public abstract boolean isComposite();
	
	public void add(FileMultimediale fm) throws Exception{
		throw new Exception("Impossibile aggiungere in questo component");
	}
	
	public void remove(FileMultimediale fm)throws Exception{
		throw new Exception("Impossibile eliminare in questo component");
	}
	
	public List<FileMultimediale> getChildren() throws Exception
	{
		throw new Exception("Impossibile ottenere FileMultimediali figli");
	}
	
	public FileMultimediale(String titolo,boolean cond,String perc)
	{
		this.titolo = titolo;
		this.condivisibile = cond;
		this.percorso_assoluto = perc;
		this.data_creazione = null;
		this.parent = null;
	}

	public FileMultimediale getParent()
	{
		return this.parent;
	}
	
	public String getTitolo()
	{
		return this.titolo;
	}
	
	public boolean isCondivisibile()
	{
		return this.condivisibile;
	}
	
	public FileTime getData()
	{
		return this.data_creazione;
	}
	
	public String getPath()
	{
		return this.percorso_assoluto;
	}
	
	public  List<Image> getImages() throws Exception
	{
		return this.immagini;
	}
	
	public boolean equals(Object obj)
	{
		if (this == obj) 
		{
			        return true;
		}
		if (obj == null) 
		{
			        return false;
		}
		if (getClass() != obj.getClass()) 
		{
			        return false;
		}
		FileMultimediale other = (FileMultimediale)obj;
		return ( (this.titolo.equals(other.getTitolo())) && (this.percorso_assoluto.equals(other.getPath())) );
	}
	
	public String toString()
	{
		return (this.getTitolo()+"["+this.getClass()+"]");
	}
}

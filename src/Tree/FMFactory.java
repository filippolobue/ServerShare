package Tree;

import java.awt.Image;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;

public class FMFactory{
	
	public static FileMultimediale createComposite(String tit,boolean b,String perc){
		return new Cartella(tit,b,perc);
	}
	public static FileMultimediale createComposite(String tit,boolean b,String perc, FileTime data){
		return new Cartella(tit,b,perc,data);
	}
	
	public static FileMultimediale createLeaf(String tit,boolean b,String perc,String estensione){
		
		switch(estensione.toLowerCase())
		{
		case "mp3":
		case "wav":
		case "mp4":
			return new Audio(tit,b,perc);
		case "avi":
		case "mpeg":
		case "flv":
			return new Videoclip(tit,b,perc);
		default:
			return new Unknown(tit,b,perc);
		}
	}

	public static FileMultimediale createLeaf(String tit,boolean b,String perc,String estensione,FileTime data){
		
		switch(estensione.toLowerCase())
		{
		case "mp3":
		case "wav":
		case "mp4":
			return new Audio(tit,b,perc,data);
		case "avi":
		case "mpeg":
		case "flv":
			return new Videoclip(tit,b,perc,data);
		default:
			return new Unknown(tit,b,perc,data);
		}
	}
}

class Audio extends FileMultimediale {
	
	private List<Image> immagini;
	
	public Audio(String titolo, boolean cond, String perc)
	{
		super(titolo,cond,perc);
		this.immagini = new ArrayList<Image>();
		System.out.println("creata AUDIO " + titolo);
	}
	public Audio(String titolo, boolean cond, String perc,FileTime ft)
	{
		super(titolo,cond,perc);
		this.immagini = new ArrayList<Image>();
		this.data_creazione = ft;
		System.out.println("creata AUDIO " + titolo + "[" + this.getData() + "]");
	}
	
	public List<Image> getImmagini()
	{
		return this.immagini;
	}
	
	@Override
	public boolean isComposite() 
	{return false;}
}

class Videoclip extends FileMultimediale {
	
	private List<Image> immagini;
	
	public Videoclip(String titolo, boolean cond, String perc)
	{
		super(titolo,cond,perc);
		this.immagini = new ArrayList<Image>();
		System.out.println("creata VIDEOCLIP " + titolo);
	}
	public Videoclip(String titolo, boolean cond, String perc,FileTime ft)
	{
		super(titolo,cond,perc);
		this.immagini = new ArrayList<Image>();
		this.data_creazione = ft;
		System.out.println("creata VIDEOCLIP " + titolo + "[" + this.getData() + "]");
	}
	
	public List<Image> getImmaggini()
	{
		return this.immagini;
	}
	
	@Override
	public boolean isComposite() 
	{return false;}
}

class Unknown extends FileMultimediale {
	
	private List<Image> immagini;
	
	public Unknown(String titolo, boolean cond, String perc)
	{
		super(titolo,true,perc);
		this.immagini = new ArrayList<Image>();
		System.out.println("creata UNKNOWN " + titolo);
	}
	
	public Unknown(String titolo, boolean cond, String perc,FileTime data)
	{
		super(titolo,true,perc);
		this.immagini = new ArrayList<Image>();
		this.data_creazione = data;
		System.out.println("creata CARTELLA " + titolo+"["+this.getData()+"]");
	}
	
	public List<Image> getImmaggini()
	{
		return this.immagini;
	}
	
	@Override
	public boolean isComposite() 
	{return false;}
}

/** "Composite" */
class Cartella extends FileMultimediale{

	private List<FileMultimediale> files = new ArrayList<FileMultimediale>();
	
	@Override
	public boolean isComposite() {return true;}
	
	public Cartella(String titolo,boolean cond,String perc)
	{
		super(titolo,cond,perc);
		System.out.println("creata CARTELLA " + titolo);
	}
	
	public Cartella(String tit, boolean cond, String perc, FileTime data) {
		super(tit,cond,perc);
		this.data_creazione = data;
		System.out.println("creata CARTELLA " + titolo+"["+this.getData()+"]");
	}

	public void add(FileMultimediale fm)
	{
		if(fm.parent != null)
			throw new IllegalArgumentException("Impossibile aggiungere un FileMultimediale che ha già un padre");

			this.files.add(fm);
			fm.parent = this;
	}
	
	public void remove(FileMultimediale fm)
	{
		if(fm.parent != this)
			throw new IllegalArgumentException("Impossibile eliminare FileMultimediale per problema di parent");
		if(!this.files.contains(fm))
			throw new IllegalArgumentException("Impossibile eliminare FileMultimediale perchè non è presente tra i figli");
		this.files.remove(fm);
		fm.parent = null;
	}

	public String toString()
	{	String ret = this.getTitolo()+"["+this.getClass()+"]";
		for(FileMultimediale fm : this.files)
		{
			ret +=  fm.toString();
		}
		
		return ret;
	}
}


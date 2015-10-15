package Tree;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class FMFactory{
	
	public static FileMultimediale createComposite(String tit,boolean b,String perc){
		return new Cartella(tit,b,perc);
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
}

class Audio extends FileMultimediale {
	
	private List<Image> immagini;
	
	public Audio(String titolo, boolean cond, String perc)
	{
		super(titolo,cond,perc);
		this.immagini = new ArrayList<Image>();
		System.out.println("creata AUDIO " + titolo);
	}
	
	public List<Image> getImmaggini()
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


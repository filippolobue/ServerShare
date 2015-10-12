package Tree;

public class Element {
	private String nome;
	private TipoFile tipo_file;
	private String path;		//utile per trovare la risorsa sul FS locale e poterlo inviare tramite socket
	
	public Element (String nome, TipoFile tipo_file, String path)
	{
		this.nome = nome;
		this.tipo_file = tipo_file;
		this.path = path;
	}
	
	String getNome()
	{
		return this.nome;
	}
	
	TipoFile getTipoFile()
	{
		return this.tipo_file;
	}
	
	String getPath()
	{
		return this.path;
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
		Element other = (Element)obj;
		return ((this.nome == other.getNome()) && (this.path.equalsIgnoreCase(other.getPath())) && (this.tipo_file == other.getTipoFile()));
	}
	
	public String toString()
	{
		return (this.nome+"["+this.tipo_file+"]");
	}
}

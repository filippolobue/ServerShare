package Model;

public class Roots {
	//deve essere un singleton che contiene tutti i root di partenza contenitrici di tutto cio' che io voglio condividere con i client
	
private static Roots istanza;
	

	private Roots()
	{
	}
	
	public static Roots getInstance()
	{
		if(istanza == null)
		{
			istanza = new Roots();
		}
		return istanza;
	}
	
}

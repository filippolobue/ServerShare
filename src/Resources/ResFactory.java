package Resources;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import Model.FileUtility;
import Tree.FileMultimediale;
import View.Provider.FileMultimedialeTableCLProvider;

/*FLYWEIGHT*/
public class ResFactory {
	
	private static ResFactory instance = null;
	private LocalResourceManager jfaceRsManager;
	private Map<String,Image> images;
	
	private ResFactory()
	{
		 this.jfaceRsManager = new LocalResourceManager(
	                JFaceResources.getResources(),
	                Display.getCurrent().getShells()[0]);
		 
		 this.images = new HashMap<String,Image>();
		 
		 
	}
	
	public static ResFactory getInstance()
	{
		if(instance == null)
		{
			instance = new ResFactory();	
		}
		return instance;
	}
	
public Image giveIcon(FileMultimediale fm){
		
	String estensione = FileUtility.getExtensione(fm.getPath()).toLowerCase();
	String path = null;
	
	System.out.println("estensione: " + estensione);
	
	if(this.images.containsKey(estensione))
		return this.images.get(estensione);
	
	
	if(fm.isComposite())
	{
		path = "/Resources/folder.png";
	}else
	{
		switch(estensione)
		{
		case "pdf":
			path = "/Resources/pdf.png";
		case "txt":
			path = "/Resources/txt.png";
		default:
			path = null;
		}
	}
	if(path == null)return null;
	
	ImageDescriptor imageDescriptor = ImageDescriptor.createFromFile(
	        FileMultimedialeTableCLProvider.class,
	        path);
	this.images.put(estensione, jfaceRsManager.createImage(imageDescriptor));
	return this.images.get(estensione);
	}
}

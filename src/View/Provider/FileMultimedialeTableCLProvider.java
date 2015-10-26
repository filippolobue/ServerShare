package View.Provider;

import java.util.List;
 
import org.eclipse.swt.graphics.Image;

import Resources.ResFactory;
import Tree.FileMultimediale;
 
public class FileMultimedialeTableCLProvider extends AbstractTableContentLabelProvider {
 
    public FileMultimedialeTableCLProvider() {
    }
 
    @Override
    public String getColumnText(Object element, int columnIndex) {
        FileMultimediale fm = (FileMultimediale) element;
        switch (columnIndex) {
         // 0 - For first column
        case 0:
            return fm.getTitolo();
		// 1 - For Second column
        case 1:
        	if(fm.getData() != null)
        		return fm.getData().toString();
        	else return "...";
        case 2:
        	if(fm.isCondivisibile())
        		return "Si";
        	else return "No";
        default:
            return null;
        }
    }
 
    @Override
    public Image getColumnImage(Object element, int columnIndex) {
    	FileMultimediale article = (FileMultimediale) element;
  
    	switch (columnIndex) {
  
        // 0 - For first column
        case 0:
            if (article.isCondivisibile()) {
//                return image;
            	return ResFactory.getInstance().giveIcon(article);
            }
        default:
            return null;
        }
    }
 
  
    // see: viewer.setInput(..)
    @Override
    public Object[] getElements(Object input) {
        List<FileMultimediale> list = (List<FileMultimediale>) input;
        return list.toArray();
    }
 
}

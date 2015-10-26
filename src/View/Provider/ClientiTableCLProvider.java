package View.Provider;

import java.util.List;

import org.eclipse.swt.graphics.Image;

public class ClientiTableCLProvider extends AbstractTableContentLabelProvider{

	 public ClientiTableCLProvider() {
	    }
	 
	    @Override
	    public String getColumnText(Object element, int columnIndex) {
	    	String[] cl = (String[]) element;
	        switch (columnIndex) { 
	         // 0 - For first column
	        case 0:
	            return cl[0];
			// 1 - For Second column
	        case 1:
	        	return cl[1];
	        default:
	            return null;
	        }
	    }
	 
	    @Override
	    public Image getColumnImage(Object element, int columnIndex) {
	    	String[] cl = (String[]) element;
	    	switch (columnIndex) {
	  
	        // 0 - For first column
	        case 0:
	            return null;
	        default:
	            return null;
	        }
	    }
	 
	  
	    // see: viewer.setInput(..)
	    @Override
	    public Object[] getElements(Object input) {
	        List<String[]> list = (List<String[]>) input;
	        return list.toArray();
	    }
}

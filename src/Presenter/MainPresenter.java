package Presenter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import Model.Document;
import View.MainView;
import Tree.*;

public class MainPresenter extends SelectionAdapter implements ISelectionChangedListener{

	private MainView mv;
	
	public MainPresenter(MainView mv)
	{
		this.mv = mv;
//		this.mv.addAscoltatore(this);
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		System.out.println("CLICKKKKKKKK");
	}

	@Override
	public void selectionChanged(final SelectionChangedEvent event) {
        IStructuredSelection selection = (IStructuredSelection)event.getSelection();
        TableViewer tv = this.mv.getTableViewer();
        System.out.println("Cliccata row! " + selection.toString());
        FileMultimediale selected = ((FileMultimediale)selection.getFirstElement());
        if(selected !=null)
        {
        if(selected.isComposite())
        {
        	System.out.println("Cartella");
        	try {
        		List<FileMultimediale> input = null;
        		if(selected.getParent() != null)
        		{
        			input = new ArrayList<FileMultimediale>();
        			if(selected.getParent().getData()!=null)	//solo root non ha data, FORSE NON E' PROPRIO CORRETTO
        			input.add(selected.getParent());
        			input.addAll(Document.getIstance().getTableElement(selected));
        		}else{
        			input = Document.getIstance().getTableElement(selected);
        		}
        		        		
				tv.setInput(input);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else
        	System.out.println("file");
        }
        
	}
}

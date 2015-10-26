package Presenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MenuItem;

import Model.Document;
import View.MainView;
import View.MyTableViewer;
import View.Provider.FileMultimedialeTableCLProvider;
import Tree.*;

public class MainPresenter extends SelectionAdapter implements ISelectionChangedListener{

	private MainView mv;
	private ClientiPresenter clientiPresenter;
	private RootPresenter rootPresenter;
	
	public MainPresenter(MainView mv) throws IOException, Exception
	{
		this.mv = mv;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e){
		MenuItem mi = ((MenuItem)e.widget);
		System.out.println("CLICKKKKKKKK " + mi.getText());
		this.deleteAllControls();

		switch(mi.getText())
		{
		case "CLIENTI":
			try {
				this.clientiClicked();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case "ROOT":
			try {
				this.rootClicked();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case "":
			try {
				this.homeClicked();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		}
//		this.mv.getShell().layout();//pack();
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
	
	private void deleteAllControls()
	{
		for (Control kid : this.mv.getShell().getChildren()) {
	          kid.dispose();
	        }
	}
	
	private void homeClicked() throws IOException, Exception
	{
		MyTableViewer tableViewer = new MyTableViewer(this.mv.getShell(), SWT.BORDER | SWT.FULL_SELECTION);
		
		FileMultimedialeTableCLProvider provider = new FileMultimedialeTableCLProvider();
		tableViewer.setContentProvider(provider);
		tableViewer.setLabelProvider(provider);
				
		tableViewer.createColumn("Titotlo", 135, SWT.CENTER);
		tableViewer.createColumn("Data", 110, SWT.CENTER);
		tableViewer.createColumn("Condivisibile", 125, SWT.CENTER);

		List<FileMultimediale> fmList = Document.getIstance().getTableElement(Document.getIstance().getRoot());
		tableViewer.setInput(fmList);
		this.mv.setTableViewer(tableViewer);
		tableViewer.addSelectionChangedListener((ISelectionChangedListener) this);
		
		this.mv.getShell().layout();
	}
	
	private void clientiClicked() throws IOException
	{
		this.clientiPresenter = new ClientiPresenter(this.mv);
		this.mv.getShell().layout();
	}
	
	private void rootClicked() throws IOException
	{
		this.rootPresenter = new RootPresenter(this.mv);
		this.mv.getShell().layout();
	}
}

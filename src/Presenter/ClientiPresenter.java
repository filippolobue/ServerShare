package Presenter;

import java.io.IOException;
import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;

import Model.Document;
import View.MainView;
import View.MyTableViewer;
import View.Provider.ClientiTableCLProvider;

public class ClientiPresenter implements ISelectionChangedListener{

	private MainView mv;
	
	public ClientiPresenter(MainView mv) throws IOException
	{
		this.mv = mv;
		this.inizializzazione();
	}

	private void inizializzazione() throws IOException {
		MyTableViewer tableViewer = new MyTableViewer(this.mv.getShell(), SWT.BORDER | SWT.FULL_SELECTION);
		
		ClientiTableCLProvider provider = new ClientiTableCLProvider();
		tableViewer.setContentProvider(provider);
		tableViewer.setLabelProvider(provider);
				
		tableViewer.createColumn("Username", 135, SWT.CENTER);
		tableViewer.createColumn("Password", 135, SWT.CENTER);

		List<String[]> clienti = Document.getIstance().getClienti();
		tableViewer.setInput(clienti);
//		this.mv.setTableViewer(tableViewer);
//		tableViewer.addSelectionChangedListener((ISelectionChangedListener) this);
		
	}

	@Override
	public void selectionChanged(SelectionChangedEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

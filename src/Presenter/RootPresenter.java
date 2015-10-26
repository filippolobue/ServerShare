package Presenter;

import java.io.IOException;
import java.util.List;

import org.eclipse.swt.SWT;

import Model.Document;
import View.MainView;
import View.MyTableViewer;
import View.Provider.RootTableCLProvider;

public class RootPresenter {

	private MainView mv;
	
	public RootPresenter(MainView mv) throws IOException
	{
		this.mv = mv;
		this.inizializzazione();
	}

	private void inizializzazione() throws IOException {
		MyTableViewer tableViewer = new MyTableViewer(this.mv.getShell(), SWT.BORDER | SWT.FULL_SELECTION);
		
		RootTableCLProvider provider = new RootTableCLProvider();
		tableViewer.setContentProvider(provider);
		tableViewer.setLabelProvider(provider);
				
		tableViewer.createColumn("PathAssoluti", 900, SWT.CENTER);

		List<String> roots = Document.getIstance().getRoots();
		tableViewer.setInput(roots);
//		this.mv.setTableViewer(tableViewer);
//		tableViewer.addSelectionChangedListener((ISelectionChangedListener) this);
		
	}

//	@Override
//	public void selectionChanged(SelectionChangedEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
}

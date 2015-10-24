package View;

import org.eclipse.swt.widgets.Display;

import java.util.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.Table;

import java.io.IOException;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;

import Model.Document;
import Tree.FileMultimediale;
import View.Provider.FileMultimedialeTableCLProvider;

public class MainView {

	protected Shell shell;
	private Table table;
	private MenuBar menuBar;
	private TableViewer tableViewer;
	
	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
//	public static void main(String[] args) {
//		try {
//			MainView window = new MainView();
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public MainView() {
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	
	
	public void open(SelectionAdapter sa) throws IOException, Exception {
//		Document.getIstance().updateRootsFS();
		Display display = Display.getDefault();
		createContents(sa);
//		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents(SelectionAdapter sa) throws IOException, Exception {
		shell = new Shell();
		shell.setSize(800, 420);
		shell.setText("ServerShare");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		menuBar = new MenuBar(shell, SWT.BAR);//, sa);
		shell.setMenuBar(menuBar);		

		tableViewer = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
		
		FileMultimedialeTableCLProvider provider = new FileMultimedialeTableCLProvider();
		tableViewer.setContentProvider(provider);
		tableViewer.setLabelProvider(provider);
				
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnTitolo = new TableColumn(table, SWT.CENTER);
		tblclmnTitolo.setWidth(135);
		tblclmnTitolo.setText("Titolo");
		
		TableColumn tblclmnData = new TableColumn(table, SWT.NONE);
		tblclmnData.setWidth(112);
		tblclmnData.setText("Data");
		
		TableColumn tblclmnCondivisibile = new TableColumn(table, SWT.NONE);
		tblclmnCondivisibile.setWidth(125);
		tblclmnCondivisibile.setText("Condivisibile");

		List<FileMultimediale> fmList = Document.getIstance().getTableElement(Document.getIstance().getRoot().getChildren().get(0));
		tableViewer.setInput(fmList);
		this.addAscoltatore(sa);
//		this.tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
//		    public void selectionChanged(final SelectionChangedEvent event) {
//		        IStructuredSelection selection = (IStructuredSelection)event.getSelection();
//		        System.out.println("Cliccata row! " + selection.toString());
//		        if(((FileMultimediale)selection.getFirstElement()).isComposite())
//		        	System.out.println("Cartella");
//		        else
//		        	System.out.println("file");
//		    }
//		});
		
//		fmList = ((List<FileMultimediale>)tableViewer.getInput());
//		fmList.remove(0);
//		
//		tableViewer.refresh();
		
	}
	
	public void addAscoltatore(SelectionAdapter sa)
	{
		System.out.println("addAscoltatore");
		this.menuBar.addAscoltatore(sa);
		
		
		this.tableViewer.addSelectionChangedListener((ISelectionChangedListener) sa);
	}

	public TableViewer getTableViewer()
	{
		return this.tableViewer;
	}
	public Shell getShell()
	{
		return this.shell;
	}
}

package View;

import org.eclipse.swt.widgets.Display;
import java.util.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import java.io.IOException;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.TableViewer;

import Model.Document;
import Tree.FileMultimediale;
import View.Provider.FileMultimedialeTableCLProvider;

public class MainView {

	protected Shell shell;
	private MenuBar menuBar;
	private MyTableViewer tableViewer;
	
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
		shell = new Shell();
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
		shell.setSize(800, 420);
		shell.setText("ServerShare");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		menuBar = new MenuBar(shell, SWT.BAR);
		shell.setMenuBar(menuBar);		

		tableViewer = new MyTableViewer(this.shell, SWT.BORDER | SWT.FULL_SELECTION);
		
		FileMultimedialeTableCLProvider provider = new FileMultimedialeTableCLProvider();
		tableViewer.setContentProvider(provider);
		tableViewer.setLabelProvider(provider);
				
		tableViewer.createColumn("Titotlo", 135, SWT.CENTER);
		tableViewer.createColumn("Data", 110, SWT.CENTER);
		tableViewer.createColumn("Condivisibile", 125, SWT.CENTER);

		List<FileMultimediale> fmList = Document.getIstance().getTableElement(Document.getIstance().getRoot());//.getChildren().get(0));
		tableViewer.setInput(fmList);
		this.addAscoltatore(sa);
		
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
	public void setTableViewer(MyTableViewer tv)
	{
		this.tableViewer = tv;
	}
	
	public Shell getShell()
	{
		return this.shell;
	}
}

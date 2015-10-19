package View;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;

public class MainView {

	protected Shell shell;
	protected MenuItem mntmClienti;
	protected MenuItem mntmRoot;
	
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open(SelectionAdapter sa) {
		Display display = Display.getDefault();
		createContents(sa);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(SelectionAdapter sa) {
		shell = new Shell();
		shell.setSize(800, 420);
		shell.setText("ServerShare");
		
		Menu menu = new Menu(shell, SWT.BAR);
		menu.setLocation(new Point(0, 0));
		shell.setMenuBar(menu);
		
		this.mntmClienti = new MenuItem(menu, SWT.NONE);
//		mntmClienti.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				System.out.println("clickkkkkkk");
//			}
//		});
		mntmClienti.setText("CLIENTI");
		
		this.mntmRoot = new MenuItem(menu, SWT.NONE);
//		mntmRoot.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//			}
//		});
		mntmRoot.setText("ROOT");
		this.addAscoltatore(sa);
	}
	
	private void addAscoltatore(SelectionAdapter sa)
	{
		System.out.println("addAscoltatore");
		this.mntmClienti.addSelectionListener(sa);
		this.mntmRoot.addSelectionListener(sa);
	}
}

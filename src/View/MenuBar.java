package View;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.wb.swt.SWTResourceManager;

public class MenuBar extends Menu {

	protected MenuItem mntmHome;
	protected MenuItem mntmClienti;
	protected MenuItem mntmRoot;
	
	public MenuBar(Decorations parent, int style) {
		super(parent, style);
		this.setLocation(new Point(0, 0));
		
		this.mntmHome = new MenuItem(this, SWT.NONE);
		mntmHome.setImage(SWTResourceManager.getImage(temp.class, "/Resources/tick.png"));
		this.mntmClienti = new MenuItem(this, SWT.NONE);
		mntmClienti.setText("CLIENTI");
		this.mntmRoot = new MenuItem(this, SWT.NONE);
		mntmRoot.setText("ROOT");
	}

	public MenuBar(Decorations parent, int style, SelectionAdapter sa) {
		this(parent,style);
		this.addAscoltatore(sa);
	}
	
	public void addAscoltatore(SelectionAdapter sa)
	{
		System.out.println("addAscoltatore");
		this.mntmHome.addSelectionListener(sa);
		this.mntmClienti.addSelectionListener(sa);
		this.mntmRoot.addSelectionListener(sa);
	}
	
	protected void checkSubclass() { 
	}
}

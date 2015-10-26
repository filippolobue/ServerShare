package View;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class MyTableViewer extends TableViewer {
	
	private Table table;
	
	public MyTableViewer(Composite parent, int style) {
		super(parent, style);
		this.inizializzazione();
	}
	
	public MyTableViewer(Composite parent) {
		super(parent);
		this.inizializzazione();
	}
	
	private void inizializzazione()
	{
		this.table = this.getTable();
		this.table.setHeaderVisible(true);
		this.table.setLinesVisible(true);
	}
	
	public void createColumn(String testo, int width, int modelIndex)
	{
		TableColumn tblclmnTitolo = new TableColumn(this.table, modelIndex);
		tblclmnTitolo.setWidth(width);
		tblclmnTitolo.setText(testo);
	}
}

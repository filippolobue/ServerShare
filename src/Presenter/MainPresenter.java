package Presenter;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import View.MainView;

public class MainPresenter extends SelectionAdapter{

	private MainView mv;
	
	public MainPresenter(MainView mv)
	{
		this.mv = mv;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		System.out.println("CLICKKKKKKKK");
	}

}

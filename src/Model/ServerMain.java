package Model;

import Presenter.MainPresenter;
import View.MainView;

public class ServerMain {
	
	public static void main(String[] args) throws Exception {	  
		ServerStart ss = null;
	    Document.getIstance().updateRootsFS();
	    Document.getIstance().report("ServerMain", "Avviato ServerShare");

	    try {
	          ss = new ServerStart(1212);
	          ss.start();
	        } catch (Exception e) {
	        	System.err.println("Server: problemi nel server thread: " + e.getMessage());e.printStackTrace();
	        	System.exit(0);
	        }
	    
				MainView window = new MainView();
	    		MainPresenter mp = new MainPresenter(window);
	    		
				window.open(mp);
				ss.interrupt();
	 }
}

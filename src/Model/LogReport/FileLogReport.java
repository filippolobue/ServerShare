package Model.LogReport;

import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;

public class FileLogReport implements ILogReport {

	private PrintWriter output;
	DateFormat dateFormat;
	Calendar cal = Calendar.getInstance();

	public FileLogReport(String path) throws IOException
	{
		this.output = new PrintWriter(new BufferedWriter(new FileWriter(path, true))); 	
		this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
	}
	
	public FileLogReport(PrintWriter out)
	{
		this.output = out;
	}
	
	@Override
	public void report(String who, String message) throws IOException {
		System.out.println("[FileLogReport] SCRIVO");
		this.output.println("[" + who + "];" + dateFormat.format(cal.getTime()) +
				";" + message);
		this.output.flush();
	}

	@Override
	public void close() throws IOException {
		this.output.close();
	}

}

package Model.LogReport;

import java.io.Writer;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;

public class FileLogReport implements ILogReport {

	private PrintWriter output;
	
	public FileLogReport(String path) throws IOException
	{
		this.output = new PrintWriter(new BufferedWriter(new FileWriter(path, true))); 	
	}
	
	public FileLogReport(PrintWriter out)
	{
		this.output = out;
	}
	
	@Override
	public void report(String who, String message) throws IOException {
		System.out.println("[FileLogReport] SCRIVO");
		this.output.println("[" + who + "] " + message);
		this.output.flush();
	}

	@Override
	public void close() throws IOException {
		this.output.close();
	}

}

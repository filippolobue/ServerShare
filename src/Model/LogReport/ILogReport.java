package Model.LogReport;

import java.io.IOException;

public interface ILogReport {

	public void report(String who, String message) throws IOException;
	public void close() throws IOException;
}

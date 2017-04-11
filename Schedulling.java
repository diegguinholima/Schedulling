import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Queue;
import java.util.LinkedList;

public class Schedulling{
	
	public static void main(String[] args) {
		
		String filePath = "/home/diego-lima/Schedulling/"; 
	    BufferedReader readFile = new BufferedReader(new FileReader(filePath));

	    String line;
	    String[] part = new String[3];
	    Queue<Processo> queue = new LinkedList<Processo>();
	    int ident = 0;

	    while ((line = readFile.readLine()) != null) {
	        	part = line.split(" ");        	
	        	queue.add(new Processo(ident++, Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
	        	PID++;
	        }
	        readFile.close();
	}


}
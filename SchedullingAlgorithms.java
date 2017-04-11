import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class SchedullingAlgorithms{
	
	public static void main(String[] args) {
		// parsing da entrada
		try{
		String filePath = "/home/diego-lima/Schedulling/entrada.in/"; 
	    BufferedReader readFile = new BufferedReader(new FileReader(filePath));

	    String line;
	    String[] parts = new String[3];
	    Queue<Processo> queue = new LinkedList<Processo>();
	    int ident = 0;

	    while ((line = readFile.readLine()) != null) {
	        	parts = line.split(" ");        	
	        	queue.add(new Processo(ident, Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
	        	ident++;
	        }
	        readFile.close();
		
	        FCFS(new LinkedList<Processo>(queue));
	     	
		}

		catch (IOException ex) {
	        System.err.print(ex.getMessage());
	    } 
	    catch (Exception e) {
	        System.err.print(e.getMessage());
	    }			
	}

	public static void FCFS(Queue<Processo> queue) {


		float espera = 0.0f, retorno = 0.0f; 				// metricas solicitadas
		int length = queue.size(); 							// salvando a quantidade de processos na fila
		int tempo_de_duracao = 0, clock = 0, sum = 0;		// variaveis para controle do tempo dos processos
		Processo aux = queue.peek();						// salva o primeiro processo da fila, em um objeto auxiliar, mas não remove da fila
		int offset = aux.gettempo_de_chegada(); 			// salva o tempo de chegada do primeiro processo
		int i=0;
	
		while(!queue.isEmpty()){							// será executado até que todos os processos tenham sido retirados da fila
			
			aux = queue.poll();								// pega o primeiro elemento da fila, removendo da fila
			tempo_de_duracao = aux.gettempo_de_duracao();   // tempo de duração, é o tempo do processo corrente
			sum = (clock + offset) - aux.gettempo_de_chegada(); // diminui o tempo de chegada, do tempo total de duração dos n processos
			espera += sum;									// acumula o tempo de espera
			retorno += sum + tempo_de_duracao;				// acumula o tempo de retorno
			clock += tempo_de_duracao;						// acumula o tempo de duração dos n processos
		}
		
		espera/=length;										// calcula a media do tempo de espera
		retorno/=length;									// calcula a media do tempo de retorno
		
		System.out.printf("FCFS %.1f %.1f %.1f\n", retorno, espera, espera);
	}
}
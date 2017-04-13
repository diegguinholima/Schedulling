import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class SchedullingAlgorithms{
	
	/*
	*	A imprementação a seguir contempla 3 algoritimos de escalonamento:
	*		1. First Come First Served - FCFS	
	*		2. Shortest	Job First - SJF
	*		3. Round Robin (quantum = 2)
	*
	*
	*	Foi solicitado a analise de 3 metricas de avaliação de cada algoritmo, sendo os valores exibidos as medias
	*	dessas metricas, na seguinte ordem:
	*		1. Tempo de Retorno
	*		2. Tempo de Resposta
	*		3. Tempo de Espera
	*
	*/	
	public static void main(String[] args) {
		// parsing da entrada
		try{
		String filePath = "/home/diego-lima/Schedulling/entrada.in/"; 
	    BufferedReader readFile = new BufferedReader(new FileReader(filePath));

	    String line;
	    String[] parts = new String[3];
	    Queue<Processo> queue = new LinkedList<Processo>();			// Lista de processos prontos para serem escalonados
	    int ident = 0;

	    while ((line = readFile.readLine()) != null) { 
	        	parts = line.split(" ");        	
	        	queue.add(new Processo(ident, Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
	        	ident++;
	        }
	        readFile.close();
		
	        FCFS(new LinkedList<Processo>(queue));
	     	SJF(new LinkedList<Processo>(queue));
			RR(new LinkedList<Processo>(queue));
		}

		catch (IOException ex) {
	        System.err.print(ex.getMessage());
	    } 
	    catch (Exception e) {
	        System.err.print(e.getMessage());
	    }			
	}
	/*
	*	Implementação do algoritmo de escalonamento "First Come First Served"  
	*	@param queue - Fila de processos a serem escalonados
	*	@return - Exibi o resultado das metricas solicitadas
	*/
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

	/*
	*	Implementação do algoritmo de escalonamento "Shortest Job First"  
	*	@param queue - Fila de processos a serem escalonados
	*	@return - Exibi o resultado das metricas solicitadas
	*/
	public static void SJF(Queue<Processo> queue){

		Queue<Processo> priority = new PriorityQueue<Processo>(); // fila de prioridades
		float espera = 0.0f, retorno = 0.0f;				// metricas solicitadas
		int length = queue.size();							// salvando a quantidade de processos na fila
		int tempo_de_duracao = 0, clock = 0, sum = 0; 		// variaveis para controle do tempo dos processos
		Processo aux = queue.peek();						// salva o primeiro processo da fila, em um objeto auxiliar, mas não remove da fila
		int offset = aux.gettempo_de_chegada(); 			// salva o tempo de chegada do primeiro processo
	
		while (!priority.isEmpty() || !queue.isEmpty()) { 	// executa até que a fila de processos ou a fila de prioridade estejam varias.
			// adiciona o processo a fila de prioridade caso, queue não esteja vazia e tempo de chegada seja menor ou igual												
			while(!queue.isEmpty() && queue.peek().gettempo_de_chegada() <= clock + offset)// ao tempo corrente  
				priority.add(queue.poll());							

			aux = priority.poll();							// pega o primeiro elemento da fila, removendo da fila
			tempo_de_duracao = aux.gettempo_de_duracao();	// tempo de duração, é o tempo do processo corrente
			sum = (clock + offset) - aux.gettempo_de_chegada(); // diminui o tempo de chegada, do tempo total de duração dos n processos
			espera += sum;									// acumula o tempo de espera
			retorno += sum + tempo_de_duracao;				// acumula o tempo de retorno
			clock += tempo_de_duracao;						// acumula o tempo de duração dos n processos
					
		}
		
		espera/=length;										// calcula a media do tempo de espera
		retorno/=length;									// calcula a media do tempo de retorno
		
		System.out.printf("SJF %.1f %.1f %.1f\n", retorno, espera, espera);
	} 

	/*
	*	Implementação do algoritmo de escalonamento "Round Robin" com quantum igual a 2  
	*	@param queue - Fila de processos a serem escalonados
	*	@return - Exibi o resultado das metricas solicitadas
	*/
	public static void RR(Queue<Processo> queue){

		List<Processo> queueRR = new ArrayList<Processo>(queue);	// Lista de Processos
		float espera = 0.0f, retorno = 0.0f, resposta = 0.0f;		// metricas solicitadas 
		int length = queue.size();									// salvando a quantidade de processos na fila
		Processo aux = queueRR.get(0);								// salva o primeiro processo da lista
		int clock = aux.gettempo_de_chegada();						// tempo do sistema é igual ao tempo de chegada do primeiro processo
		int exec = 0, index = 0;									// variaveis para controle do tempo de duração e identifcação dos processos
		int quantum = 2;											// medida fixa 

		while(!queueRR.isEmpty()){									// executa até a lista estar vazia

			aux = queueRR.get(0);									// pega o primeiro processo

			if (quantum > aux.gettempo_de_duracao())				// verifica se a duração do processo é menor que o quantum
				exec = aux.gettempo_de_duracao();					
			else
				exec = quantum;
			
			if(!aux.getflag()){											
				aux.setflag(true);
				espera -= aux.gettempo_de_duracao();
				resposta += clock - aux.gettempo_de_chegada();		// estabelece o tempo de resposta dos processos
			}

			aux.duraçãorestante(exec);								// diminui a duração do processo em um quantum de tempo
			clock += exec;											// atualiza o tempo do sistema
			
			if(aux.gettempo_de_duracao() > 0){					
				index = 0;
				for(int i = 0 ; i < queueRR.size() ; ++i){			// define onde o processo não terminado, será colocado na lista 
					if(queueRR.get(i).gettempo_de_chegada() > clock) 
						break;
					index++;
				}
				queueRR.add(index, aux);							// adiciona o processo novamente no fim da fila
			} else {					
				retorno += clock - aux.gettempo_de_chegada();		// estabelece o tempo de retorno de um processo, quando ele chega ao fim		 
				espera += clock - aux.gettempo_de_chegada(); 		// estabelece o tempo de espera de um processo, quando ele chega ao fim
			}

			queueRR.remove(0);										// remove a porção do processo executado da fila
		}	
		espera/=length;												// calcula a media do tempo de espera
		retorno/=length;											// calcula a media do tempo de retorno
		resposta/=length;											// calcula a media do tempo de resposta

		System.out.printf("RR %.1f %.1f %.1f\n", retorno, resposta, espera);
		}

}
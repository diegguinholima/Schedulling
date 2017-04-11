public class Processo implements Comparable<Processo>{

	private int tempo_de_chegada;	// tempo de chegada de um processo
	private int tempo_de_duracao;	// tempo de duração de um processo
	private int id;					// identificador de um processo			

	// Construtor da classe Processo
	public Processo(int id, int tempo_de_chegada, int tempo_de_duracao){
		this.id = id;
		this.tempo_de_chegada = tempo_de_chegada;
		this.tempo_de_duracao = tempo_de_duracao;
	}
	@Override
	// Metodo para comparação dos tempos de duração dos processos
	public int compareTo(Processo j){
		if(tempo_de_duracao > j.tempo_de_duracao)
			return 1;
		else if (tempo_de_duracao < j.tempo_de_duracao)
			return -1;
		else 
			return 0;
	}
	//get tempo de chegada
	public int gettempo_de_chegada(){
		return tempo_de_chegada;
	}
	//get tempo_de_duracao
	public int gettempo_de_duracao(){
		return tempo_de_duracao;
	}
	//get identificador
	public int getid(){
		return id;
	}
	//set identificador
	public void setID(int index){
		id = id==0 ? index : id;
	}
	//return string formatted
	public String toString(){
		return "Chegada: "+ tempo_de_chegada +" | Tempo: "+ tempo_de_duracao;
	} 


}
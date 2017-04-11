public class Processo implements Comparable<Processo>{

	private int tempo_de_chegada;
	private int tempo_de_duracao;
	private int id;
	private boolean flag;

	public Processo(int id, int tempo_de_chegada, int tempo_de_duracao){
		this.id = id;
		this.tempo_de_chegada = tempo_de_chegada;
		this.tempo_de_duracao = tempo_de_duracao;
		flag = false;
	}


}
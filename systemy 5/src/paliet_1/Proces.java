package paliet_1;

import java.util.concurrent.ThreadLocalRandom;

public class Proces {
	
	private int iloscCykliDoKonca;	//jak d³ugo bedzie wykonywany dany proces
	private int obicazenieProcesora;
	
	
	public Proces() {
		this.obicazenieProcesora = ThreadLocalRandom.current().nextInt(1, 10);
		this.iloscCykliDoKonca = ThreadLocalRandom.current().nextInt(100, 1000);
	}//koniec koknstruktora
	
	
	
	
	
	
	//GETTERS AND SETTERS
	public int getIloscCykliDoKonca() {
		return iloscCykliDoKonca;
	}


	public void setIloscCykliDoKonca(int iloscCykliDoKonca) {
		this.iloscCykliDoKonca = iloscCykliDoKonca;
	}


	public int getObicazenieProcesora() {
		return obicazenieProcesora;
	}


	public void setObicazenieProcesora(int obicazenieProcesora) {
		this.obicazenieProcesora = obicazenieProcesora;
	}
	
	
	
}//koniec klasy




















package paliet_1;

import java.util.concurrent.ThreadLocalRandom;

public class Proces {
	
	private int iloscCykliDoKonca;	//jak d³ugo bedzie wykonywany dany proces
	private int obicazenieProcesora;
	
	
	public Proces(int ilosccyklidokonca, int obciazenie ) {
		this.iloscCykliDoKonca = ilosccyklidokonca;
		this.obicazenieProcesora = obciazenie;
	}//koniec konstruktora
	
	public Proces() {
		this.obicazenieProcesora = ThreadLocalRandom.current().nextInt(1, 10 + 1);
		this.iloscCykliDoKonca = ThreadLocalRandom.current().nextInt(50, 200 + 1);
	}//koniec koknstruktora
	
	public Proces( Proces p ) {
		this.iloscCykliDoKonca = p.getIloscCykliDoKonca();
		this.obicazenieProcesora = p.getObicazenieProcesora();
	}//koniec kopiuj¹cego
	
	
	public void zmniejszIloscCykliDoKoncaOJeden() {
		this.iloscCykliDoKonca--;
	}//koniec zmniejsz ilosc cykli do konca
	
	
	
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




















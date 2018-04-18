package sposoby;

import java.util.ArrayList;

import inne.ciagGlobalny;
import inne.proces;

public class rowny {
	
	private ArrayList<Integer> listaOdwolan;
	private ArrayList<proces> listaProcesow;
	private int liczbaRamek;
	
	public rowny( ciagGlobalny c, int LiczbaRamek ) {
		this.liczbaRamek = LiczbaRamek;
		this.listaOdwolan = c.getListaWszystkichOdwolan();
		this.listaProcesow = c.getLisatProcesow();
	}//koniec konstruktora
	
	public int[] run() {
		//bedzie tyle wynikow ile procesow w liscie plus 1 wynik jako wynik globalny
		int[] wyniki = new int[listaProcesow.size() + 1];
		
		int ramkiNaProces = liczbaRamek/listaProcesow.size();
		
		int i = 0;
		
		for ( proces p : listaProcesow ) {
			LRU lru = new LRU(ramkiNaProces, p.getStrony(), p.getOdwolania());
			wyniki[i] = lru.liczBledyStrony();
			i++;
		}//koniec for
		
		//licze globalne bledy strony i umieszczam na ostatniej pozycji w tablicy wyników ---
		int czajnik = 0;
		
		for (int j = 0; j < wyniki.length-1; j++) {
			czajnik = czajnik + wyniki[j];
		}//koniec for
		
		wyniki[wyniki.length-1] = czajnik;
		//-----------------------
		
		return wyniki;
	}//koniec run
	
	
}//koniec klasy	



























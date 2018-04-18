package sposoby;

import java.util.ArrayList;

import inne.ciagGlobalny;
import inne.proces;

public class proporcjonalny {
	
	private ArrayList<Integer> listaOdwolan;
	private ArrayList<proces> listaProcesow;
	private int liczbaRamek;
	
	public proporcjonalny( ciagGlobalny c, int LiczbaRamek ) {
		this.liczbaRamek = LiczbaRamek;
		this.listaOdwolan = c.getListaWszystkichOdwolan();
		this.listaProcesow = c.getLisatProcesow();
	}//koniec konstruktora
	
	public int[] run() {
		//bedzie tyle wynikow ile procesow w liscie plus 1 wynik jako wynik globalny
		int[] wyniki = new int[listaProcesow.size() + 1];
		
		// obliczam ramki na proces -----------------
		int iloscWszystkichStron = 0;			//za³ó¿my ze 1 strona to 1 jednostka pamieci
		
		
		for ( proces p : listaProcesow ) {
			iloscWszystkichStron = iloscWszystkichStron + p.getIloscStron();
		}//koniec for
		//System.out.println("ilosc wszystkich stron = " + iloscWszystkichStron);
		int ramkiNaProces;
		
		//-----------------------------
		
		//zapisuje bledy kazdego z procesow w tabeli ----------
		int i = 0;
		
		for ( proces p : listaProcesow ) {
			ramkiNaProces = p.getIloscStron()* liczbaRamek /iloscWszystkichStron;
			//System.out.println(p + "ramki na proces= " + ramkiNaProces);
			LRU lru = new LRU(ramkiNaProces, p.getStrony(), p.getOdwolania());
			wyniki[i] = lru.liczBledyStrony();
			i++;
		}//koniec for
		//---------------------------------------------------
		
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
















package sposoby;

import java.util.ArrayList;

import inne.*;

public class strefowy {
	
	private final static int delta = 10;
	private ArrayList<Integer> listaWszystkichOdwolan;
	private ArrayList<proces> listaWszystkichProcesow;
	private ArrayList<proces> listaAktualnychProcesow;
	private ArrayList<Integer> stronyWRamie;
	private int liczbaRamek;
	
	public strefowy( ciagGlobalny c, int LiczbaRamek ) {
		this.stronyWRamie = new ArrayList<>();
		this.listaAktualnychProcesow = new ArrayList<>();
		this.liczbaRamek = LiczbaRamek;
		this.listaWszystkichOdwolan = c.getListaWszystkichOdwolan();
		this.listaWszystkichProcesow = c.getLisatProcesow();
	}//koniec konstruktora
	
	
	public int[] run() {
		//bedzie tyle wynikow ile procesow w liscie plus 1 wynik jako wynik globalny
		int[] wyniki = new int[listaWszystkichProcesow.size() + 1];
		
		while ( !listaWszystkichOdwolan.isEmpty() ) {
			
			
			
			
			
			
			
			
			//jesli proces wykonal juz wszystkie swoje odwolania to usowam go z listy
			for ( int a=0; a < listaWszystkichProcesow.size(); a++ ) {
				if ( listaWszystkichProcesow.get(a).getOdwolania().isEmpty() ) {
					listaWszystkichProcesow.remove(a);
					a--;
				}//koniec if
			}//koniec for
			
			
		}//koniec while is empty
		
		
		
		//licze globalne bledy strony i umieszczam na ostatniej pozycji w tablicy wyników ---
		int czajnik = 0;
		
		for (int j = 0; j < wyniki.length-1; j++) {
			czajnik = czajnik + wyniki[j];
		}//koniec for
		
		wyniki[wyniki.length-1] = czajnik;
		//-----------------------
		
		return wyniki;
		
	}//koniec run
	
	
	
	
	private void generujListeWszystkichOdwolan( ArrayList<proces> listaAktualnychProcesow ) {
		ArrayList<proces> p =(ArrayList<proces>)listaAktualnychProcesow.clone();
		int i = 0;
		//pierw dodajemy zgloszenie o indeksie 0 z kazdego procesu, potem o indeksie 1 z kazdego procesu itd...
		while ( !p.isEmpty() ) {
		for ( int j = 0; j < p.size(); j++ ) {
			proces aktualny = p.get(j);
				if ( i < aktualny.getOdwolania().size() ) {
					listaWszystkichOdwolan.add(aktualny.getOdwolania().get(i));		
				}//koniec if
				else {
					p.remove(aktualny);		//jesli wszystkie odwolania od danego procesu zosta³ dodane do ciagu to usun proces
				}//koniec else
			}//koniec for
		i++;
		}//koniec while
	}//koniec generuj liste wszystkich odwolan
	
	
	public static int getDelta() {return delta;} 
	
}//koniec klasy





















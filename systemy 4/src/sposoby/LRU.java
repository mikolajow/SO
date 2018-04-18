package sposoby;

import java.util.ArrayList;

import inne.*;

public class LRU {
	// LAST RECENTY USED
	//wywal strone ktora nie by³a uzywana najd³u¿ej
	
	private strona[] strony;
	private int liczbaRamek;
	private ArrayList<Integer> ListaOdwolan;
	
	public LRU( int liczbaRamek, strona[] strony, ArrayList<Integer> listaOdwolan ) {
		this.liczbaRamek = liczbaRamek;
		this.strony = strony;
		this.ListaOdwolan = listaOdwolan;
	}//koniec konstruktora
	
	public int liczBledyStrony() {
		int bledyStrony = 0;
		
		ArrayList<strona> stronyWRamie = new ArrayList<>();
		
		while ( !ListaOdwolan.isEmpty() ) {
			int aktualneOdwolanie = ListaOdwolan.get(0);
			boolean czyJuzJest = false;
			
			for ( strona s : stronyWRamie ) {
				if( s.getNumer() == aktualneOdwolanie ) {		//sprawdzam czy aktualnie potrzebna strona juz jest w pamieci fizycznej
					czyJuzJest = true;
					s.setCzasOstatniegoUzycia(0);				//zeruje czas ostatniego uzycia danej strony
				}//koniec if
			}//koniec for
			
			if ( czyJuzJest ) {
				ListaOdwolan.remove(0);
				for ( strona s : stronyWRamie ) {
					int czajnik = s.getCzasOstatniegoUzycia();
					s.setCzasOstatniegoUzycia(czajnik +1);			//zwiêkszam czas ostatniego uzycia kazdej strony o 1
				}//koniec for
				
			}//jak jest to usówam odwolanie z listy i przechodze do nastepnego
			else {
				bledyStrony++;
				if ( stronyWRamie.size() == liczbaRamek ) {
					
					//miejsce na algorytm usówania strony
					
					int najdawniejszyCzasUzycia = 0;
					int odwolanieNajpozniejsze = stronyWRamie.get(0).getNumer(); 
					
					//przeszukuje pamiec fizyczna aby znalezc najdawniej uzywana strone
					for ( strona s : stronyWRamie ) {
						if ( s.getCzasOstatniegoUzycia() > najdawniejszyCzasUzycia ) {
							najdawniejszyCzasUzycia = s.getCzasOstatniegoUzycia();
							odwolanieNajpozniejsze = s.getNumer();
						}//koniec if
					}//koniec for
					
					//szukam i usówam 
					for ( strona s : stronyWRamie ) {
						if ( s.getNumer() == odwolanieNajpozniejsze ) {
							stronyWRamie.remove(s);
							break;
						}//koniec if
					}//koniec for
						
				}//koniec if wewnatrznego
				
				for ( strona s : stronyWRamie ) {
					int czajnik = s.getCzasOstatniegoUzycia();
					s.setCzasOstatniegoUzycia(czajnik +1);			//zwiêkszam czas ostatniego uzycia kazdej strony o 1
				}//koniec for
				
				//po ziwêkszeniu czasu ostatniego uzycia kazdej strony dodaje najnowsz¹
				
				int indexStronyOAktualnymOdwolaniu = 0;
				
				for( int i=0; i<strony.length;i++ ) {
					if ( strony[i].getNumer() == aktualneOdwolanie ) {
						indexStronyOAktualnymOdwolaniu = i;
						break;
					}//koniec if
				}//koniec for
				
				stronyWRamie.add(strony[indexStronyOAktualnymOdwolaniu]);	//dodaje ramke potrzebna do aktualnego odwlania
			}//koniec else
			
		}//koniec while
		
		return bledyStrony;
	}//koniec run
	

}//koniec klasy


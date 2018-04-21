package inne;

import java.util.ArrayList;

public class ciagGlobalny {
	
	private ArrayList<Integer> listaWszystkichOdwolan;
	private ArrayList<proces> lisatProcesow;
	
	public ArrayList<proces> getLisatProcesow() {return lisatProcesow;}


	public ciagGlobalny( ArrayList<proces> p ) {
		this.lisatProcesow = new ArrayList<>();
		for(proces pro : p) {
			this.lisatProcesow.add(new proces(pro));
		}//koniec for
		this.listaWszystkichOdwolan = new ArrayList<>();
		int i = 0;
		ArrayList<proces> listaPomocnicza = new ArrayList<>();
		for(proces pro : p) {
			listaPomocnicza.add(new proces(pro));
		}//koniec for
		//pierw dodajemy zgloszenie o indeksie 0 z kazdego procesu, potem o indeksie 1 z kazdego procesu itd...
		while ( !listaPomocnicza.isEmpty() ) {
		for ( int j = 0; j < listaPomocnicza.size(); j++ ) {
			proces aktualny = listaPomocnicza.get(j);
				if ( i < aktualny.getOdwolania().size() ) {
					listaWszystkichOdwolan.add(aktualny.getOdwolania().get(i));		
				}//koniec if
				else {
					listaPomocnicza.remove(aktualny);		//jesli wszystkie odwolania od danego procesu zosta³ dodane do ciagu to usun proces
				}//koniec else
			}//koniec for
		i++;
		}//koniec while
	}//koniec konstruktora
	
	
	
	@Override
	public String toString() {
		return "ciagGlobalny [listaWszystkichOdwolan=" + listaWszystkichOdwolan + "]";
	}//koniec to string


	// GETERY SETERY
	public ArrayList<Integer> getListaWszystkichOdwolan() {return listaWszystkichOdwolan;}
	public void setListaWszystkichOdwolan(ArrayList<Integer> listaWszystkichOdwolan) {this.listaWszystkichOdwolan = listaWszystkichOdwolan;}
	
	
}//koniec klasy































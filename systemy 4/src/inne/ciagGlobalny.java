package inne;

import java.util.ArrayList;

public class ciagGlobalny {
	
	private ArrayList<Integer> listaWszystkichOdwolan;
	private ArrayList<proces> lisatProcesow;
	
	public ArrayList<proces> getLisatProcesow() {return lisatProcesow;}


	public ciagGlobalny( ArrayList<proces> p ) {
		this.lisatProcesow =(ArrayList<proces>)p.clone();
		this.listaWszystkichOdwolan = new ArrayList<>();
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
	}//koniec konstruktora
	
	
	//konstruktor kopiuj¹cy
	//konstruktor kopiujacy
	public ciagGlobalny( ciagGlobalny c ) {
		this.lisatProcesow = c.getLisatProcesow();
		this.listaWszystkichOdwolan = new ArrayList<>();
		for ( Integer i : c.getListaWszystkichOdwolan() ) {
			listaWszystkichOdwolan.add(i);
		}//koniec for
	}//koniec konstruktora kopiujacego
	
	
	
	@Override
	public String toString() {
		return "ciagGlobalny [listaWszystkichOdwolan=" + listaWszystkichOdwolan + "]";
	}//koniec to string


	// GETERY SETERY
	public ArrayList<Integer> getListaWszystkichOdwolan() {return listaWszystkichOdwolan;}
	public void setListaWszystkichOdwolan(ArrayList<Integer> listaWszystkichOdwolan) {this.listaWszystkichOdwolan = listaWszystkichOdwolan;}
	
	
}//koniec klasy































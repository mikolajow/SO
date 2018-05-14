package paliet_1;

import java.util.ArrayList;

public class Procesor {
	
	//zalozenia do zmiany
	private static int iloscProcesorowWSystemie = 50;				// N
	private static int maxymalneObciazenie = 60;					//p
	private static int minimalneObciazenie = 20;					//r
	private static int ileRazyLosujemyInnyProcesor = 8;				//z
	private static int ileProcesorowPytamyOAktualneObciazenie = 5;	//w przypadku strategii 3
	
	//zmienne procesorów
	private int aktualneObciazenie;
	private ArrayList<Proces> listaProcesow;	//aktualnie wykonywanych 
	private Wyniki wyniki;
	
	
	public Procesor(  ) {
		this.wyniki = new Wyniki();
		this.listaProcesow = new ArrayList<>();
		this.listaProcesow.add(new Proces(100, 5));
		this.aktualneObciazenie = 0;
	}//koniec konstruktora
	
	
	
	public void aktualizujHistorieObciazeniaProcesora() {
		wyniki.getHistoriaObciazeniaProcesora().add(aktualneObciazenie);
	}//koniec aktualizuj historie
	
	
	
	public void usunWykonaneProcesy( ) {
		for( int i = 0; i < listaProcesow.size(); i++ ) {
			if( listaProcesow.get(i).getIloscCykliDoKonca() == 0 ) {
				aktualneObciazenie = aktualneObciazenie - listaProcesow.get(i).getObicazenieProcesora();
				this.listaProcesow.remove(i);
				i--;
			}//koniec if
		}//koniec for
	}//koniec usun proces
	
	
	
	public void dodajProces( Proces p ) {
		this.listaProcesow.add(p);
		this.aktualneObciazenie = aktualneObciazenie + p.getObicazenieProcesora();
	}//koniec dodaj proces
	
	
	// GETTERS AND SETTERS
	public static int getIloscProcesorowWSystemie() {
		return iloscProcesorowWSystemie;
	}
	public static void setIloscProcesorowWSystemie(int iloscProcesorowWSystemie) {
		Procesor.iloscProcesorowWSystemie = iloscProcesorowWSystemie;
	}
	public static int getMaxymalneObciazenie() {
		return maxymalneObciazenie;
	}
	public static void setMaxymalneObciazenie(int maxymalneObciazenie) {
		Procesor.maxymalneObciazenie = maxymalneObciazenie;
	}
	public static int getMinimalneObciazenie() {
		return minimalneObciazenie;
	}
	public static void setMinimalneObciazenie(int minimalneObciazenie) {
		Procesor.minimalneObciazenie = minimalneObciazenie;
	}
	public static int getIleRazyLosujemyInnyProcesor() {
		return ileRazyLosujemyInnyProcesor;
	}
	public static void setIleRazyLosujemyInnyProcesor(int ileRazyLosujemyInnyProcesor) {
		Procesor.ileRazyLosujemyInnyProcesor = ileRazyLosujemyInnyProcesor;
	}
	public static int getIleProcesorowPytamyOAktualneObciazenie() {
		return ileProcesorowPytamyOAktualneObciazenie;
	}
	public static void setIleProcesorowPytamyOAktualneObciazenie(int ileProcesorowPytamyOAktualneObciazenie) {
		Procesor.ileProcesorowPytamyOAktualneObciazenie = ileProcesorowPytamyOAktualneObciazenie;
	}
	public int getAktualneObciazenie() {
		return aktualneObciazenie;
	}
	public void setAktualneObciazenie(int aktualneObciazenie) {
		this.aktualneObciazenie = aktualneObciazenie;
	}
	public ArrayList<Proces> getListaProcesow() {
		return listaProcesow;
	}
	public void setListaProcesow(ArrayList<Proces> listaProcesow) {
		this.listaProcesow = listaProcesow;
	}
	// KONIEC GETERÓW I SETTERÓW


	public Wyniki getWyniki() {
		return wyniki;
	}


	public void setWyniki(Wyniki wyniki) {
		this.wyniki = wyniki;
	}
}//koniec klasy



















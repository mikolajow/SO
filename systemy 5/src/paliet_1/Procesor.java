package paliet_1;

import java.util.ArrayList;

import metody.Metoda1;
import metody.Metoda2;
import metody.Metoda3;
import metody.Strategia;

public class Procesor {
	
	//zalozenia do zmiany
	private static int iloscProcesorowWSystemie = 50;				// N
	private static int maxymalneObciazenie = 60;					//p
	private static int minimalneObciazenie = 20;					//r
	private static int ileRazyLosujemyInnyProcesor = 8;				//z
	private static int ileProcesorowPytamyOAktualneObciazenie = 5;	//w przypadku strategii 3
	private static ArrayList<Procesor> listaWszystkichProcesorow;
	
	private static int ileNowychProcesow = 500;		//ile procesow pojawi sie jeszcze na procesorach
	
	private int aktualneObciazenie;
	private ArrayList<Proces> listaProcesow;	//aktualnie wykonywanych
	private int numerMetody;
	
	
	
	public Procesor( int numerMetody ) {
		this.numerMetody = numerMetody;
		this.aktualneObciazenie = 0;
		this.listaProcesow = new ArrayList<>();
		this.listaProcesow.add(new Proces());
	}//koniec konstruktora
	
	
	//metoda zwróci wyniki dla jednego procesora 
	//w mainie w arryliscie zbierzemy wyniki dla wszystkich procesorów
	//na tej podstawie policzymy koñcowe wyniki dla ca³ej symulacji
	public Wyniki run() {
		
		Strategia metoda;
		
		if (numerMetody == 1) {
			metoda = new Metoda1(this);
		}else if (numerMetody == 2) {
			metoda = new Metoda2(this);
		}else {
			metoda = new Metoda3(this);
		}
		
		Wyniki wyniki = metoda.run();
		
		return wyniki;
	}//koniec run
	
	
	
	
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
	public static int getIleNowychProcesow() {
		return ileNowychProcesow;
	}
	public static void setIleNowychProcesow(int ileNowychProcesow) {
		Procesor.ileNowychProcesow = ileNowychProcesow;
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


	public static ArrayList<Procesor> getListaWszystkichProcesorow() {
		return listaWszystkichProcesorow;
	}


	public static void setListaWszystkichProcesorow(ArrayList<Procesor> listaWszystkichProcesorow) {
		Procesor.listaWszystkichProcesorow = listaWszystkichProcesorow;
	}
}//koniec klasy



















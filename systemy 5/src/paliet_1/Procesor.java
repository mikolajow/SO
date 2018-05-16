package paliet_1;

import java.util.ArrayList;

public class Procesor {
	
	//zalozenia do zmiany
	private static int iloscProcesorowWSystemie = 50;				// N
	private static int maxymalneObciazenie = 50;					//p
	private static int minimalneObciazenie = 20;					//r - dolny prog
	private static int ileRazyLosujemyInnyProcesor = 8;				//z
	private static int ileProcesorowPytamyOAktualneObciazenie = 5;	//w przypadku strategii 3
	
	//zmienne procesorów
	private int aktualneObciazenie;
	private ArrayList<Proces> listaProcesow;	//aktualnie wykonywanych 
	private Wyniki wyniki;
	private Proces nowoDodany;			//nowe procesy najpierw trafiaja do tej zmiennej , potem 
											//przydzielamy im odpowiedni pocesor w strategi
	
	
	public Procesor(  ) {
		this.nowoDodany = null;
		this.wyniki = new Wyniki();
		this.listaProcesow = new ArrayList<>();
		this.listaProcesow.add(new Proces(40, 5));
		this.aktualneObciazenie = 5;
	}//koniec konstruktora
	
	
	
	public void aktualizujHistorieObciazeniaProcesora() {
		if( aktualneObciazenie != 0 ) {
			wyniki.getHistoriaObciazeniaProcesora().add(aktualneObciazenie);
		}//koniec if
	}//koniec aktualizuj historie
	
	
	
	public void usunWykonaneProcesy( ) {
		for( int i = 0; i < listaProcesow.size(); i++ ) {
			if( listaProcesow.get(i).getIloscCykliDoKonca() <= 0 ) {
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
	
	
	//przerzuca czesc procesow z jednego procesora na drugi i zwieksza ilosc przeslanych procesow w wynikach jedenego z procesorow
	public void przejmijCzescProcesow(Procesor p) {
		
		//przerzucam procesy tak dlugo az na procesorze, na ktorym bylo za duze obciazenie, aktualne obciazenie bedzie mniejsze
		//niz na procesorze ktory mial obciazenie ponizej minimum, w ten sposob na obu procesorach pod koniec metody
		//bedzie podobne obciazenie
		while ( p.getAktualneObciazenie() > this.aktualneObciazenie ) {
			
			Proces temp = p.getListaProcesow().get(0);
			p.getListaProcesow().remove(0);
			p.setAktualneObciazenie(p.getAktualneObciazenie() - temp.getObicazenieProcesora());
			
			this.aktualneObciazenie = aktualneObciazenie + temp.getObicazenieProcesora();
			this.listaProcesow.add(temp);
			
		}//koniec while
		
	}//koniec przejmij czesc procesow
	
	
	
	
	
	
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
	public Proces getNowoDodany() {
		return nowoDodany;
	}
	public void setNowoDodany(Proces nowoDodany) {
		this.nowoDodany = nowoDodany;
	}
}//koniec klasy



















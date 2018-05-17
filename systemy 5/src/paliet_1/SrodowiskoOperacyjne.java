package paliet_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;



public class SrodowiskoOperacyjne {
	
	private  ArrayList<Procesor> listaWszystkichProcesorow;
	private  int ileNowychProcesow = 400;		//ile procesow pojawi sie jeszcze na procesorach
	private boolean czyWszystkieProcesoryWolne;
	
	
	public SrodowiskoOperacyjne() {
		this.czyWszystkieProcesoryWolne = false;
		this.listaWszystkichProcesorow = new ArrayList<>();
		for (int i = 0; i < Procesor.getIloscProcesorowWSystemie(); i++) {
			listaWszystkichProcesorow.add(new Procesor());
		}//koniec for
	}//koniec konstruktora
	
	
	
	public void czyWszystkieProcesoryWolne() {
		int ileProcesorowWolnych = 0;
		for (Procesor p : listaWszystkichProcesorow) {
			if ( p.getAktualneObciazenie() == 0 ) {
				ileProcesorowWolnych++;
			}//koniec if
			if ( ileProcesorowWolnych == Procesor.getIloscProcesorowWSystemie() ) {
				czyWszystkieProcesoryWolne = true;
			}//koniec if
		}//koniec for
	}//koniec czy
	
	
	//MIEJSCE NA STRATEGIE ZARZADZANIA PROCESAMI 
	
	//metoda bedzie  
	//1 zwiekszac zmienne w wynikach - ilosc przesylania procesow do innych procesorow, ilosc zapytan o obciazenie procesorow
	//2 dodawac proces ze zmiennej nowododany do listy procesow w wybranym procesorze 
	//3 ustawiac zmienna nowo dodany na null
	
	public void metoda1() {
		
		for ( Procesor p : listaWszystkichProcesorow ) {
			if( p.getNowoDodany() != null ) {
				
				Proces doDodania = p.getNowoDodany();
				Procesor kandydantNaRobola = p; //na poczatku to nasz aktualnie sprawdzany procesor ktory dostal nowy proces
				
				for (int iloscPozostalychZapytan = Procesor.getIleRazyLosujemyInnyProcesor(); iloscPozostalychZapytan > 0; iloscPozostalychZapytan-- ) {
					
					//losuje jakis procesor i sprawdzam czy popnizej progu
					Procesor tymczasowy = listaWszystkichProcesorow.get(ThreadLocalRandom.current().nextInt(0, listaWszystkichProcesorow.size()));
					
					if( tymczasowy.hashCode() == kandydantNaRobola.hashCode() ) {
						iloscPozostalychZapytan++;
					}//koniec if - jesli wylosowany procesor jest naszym procesorem to losujemy inny
					else if ( tymczasowy.getAktualneObciazenie() < Procesor.getMaxymalneObciazenie() ){
						kandydantNaRobola = tymczasowy;
						break;
					}//koniec else
					
					
				}//koniec for - szukanie procesora o progu ponizej maxa
				
				if( p.hashCode() != kandydantNaRobola.hashCode() ) {
					p.getWyniki().zwiekszIloscPrzemmieszczenProcesow( 1 );
				}//jak proces bedzie sie wykonowyal na innym procesorze niz ten na ktorym byl na poczatku
				//to zanczy ze zostal gdzies przeslany
				
				kandydantNaRobola.dodajProces(doDodania);
				
			}//koniec if czy nowododany rozny od null
			
			p.setNowoDodany(null);
			
		}//koniec for
	}//koniec metody 1
	
	
	public void metoda2() {
		
		for ( Procesor p : listaWszystkichProcesorow ) {
			if( p.getNowoDodany() != null ) {
				
				Proces doDodania = p.getNowoDodany();
				
				if( p.getAktualneObciazenie() < Procesor.getMaxymalneObciazenie() ) {
					p.dodajProces(doDodania);
				}//koniec if - czy aktualne obciazenie mniejsze niz max
				else {//na procesorze na ktorym pojawil sie nowy proces przekroczono maxymalne obciazenie, szukamy zastepcy
					
					Procesor kandydatNaRobola = p;
					
					//jesli wszystkie procesory beda mialy aktualne obciazenie powyzej maxymalnego progu
					//to proces bedzie sie wykonywal na ostatnim sprawdzanym procesorze niezaleznie od jego aktualnego obciazenia
					//w tym celu tworze jedna pomocnicz¹ listê procesorów, która bedzie kopi¹ lisy wszystkich procesorów w systemie
					//gdy wylosuje jakis procesor z listy-kopii usune go z niej i zwieksze couter,
					//gdy counter  bedzie rowny liczbie procesorow w so-1 znaczy ze aktualnie sprawdzam ostatni pozostaly procesor
					
					int counter = 0;
					ArrayList<Procesor> term = (ArrayList<Procesor>)listaWszystkichProcesorow.clone();
					
					
					while ( kandydatNaRobola.getAktualneObciazenie() >= Procesor.getMaxymalneObciazenie() && counter < Procesor.getIloscProcesorowWSystemie() ) {
						
						
						//losuje jakis procesor i sprawdzam czy ponizej progu
						int index = ThreadLocalRandom.current().nextInt(0, term.size());
						Procesor tymczasowy = term.get(index);
						
						if( tymczasowy.hashCode() != kandydatNaRobola.hashCode() ) {
							kandydatNaRobola = tymczasowy;
						}//koniec if - jesli wylosowany procesor nie jest naszym procesorem
						
						p.getWyniki().zwiekszIloscPrzemmieszczenProcesow( 1 );
						
						term.remove(index);
						counter++;
						
					}//koniec while - szukanie procesora o progu ponizej maxa	
					
					kandydatNaRobola.dodajProces(doDodania);
					
					
				}//koniec else - szukamy zastepcy
			}//koniec if czy nowododany rozny od null
			p.setNowoDodany(null);
		}//koniec for
	}//koniec metody 2
	
	
	public void metoda3() {
		
		metoda2();
		
		for ( Procesor p : listaWszystkichProcesorow ) {
			if ( p.getAktualneObciazenie() < Procesor.getMinimalneObciazenie() ) {
				
				p.getWyniki().zwiekszIloscZapytanOObciazenie(Procesor.getIleProcesorowPytamyOAktualneObciazenie());
				
				for ( int i = Procesor.getIleProcesorowPytamyOAktualneObciazenie(); i > 0; i-- ) {
					
					//losuje jakis procesor i sprawdzam czy jest powyzej progu
					Procesor tymczasowy = listaWszystkichProcesorow.get(ThreadLocalRandom.current().nextInt(0, listaWszystkichProcesorow.size()));
					
					if( tymczasowy.hashCode() == p.hashCode() ) {
						i++;
					}//koniec if - jesli wylosowany procesor jest naszym procesorem to losujemy inny
					else if ( tymczasowy.getAktualneObciazenie() > Procesor.getMaxymalneObciazenie() ){
						p.przejmijCzescProcesow( p );
						break;
					}//koniec else
				}//koniec for - pytamy R procesow o obciazenie
			}//koniec if
		}//koniec for
	}//koniec metody 3
	
	
	
	public void redukujIloscCykliProcesowDoZakonczenia( ) {
		for( Procesor p : listaWszystkichProcesorow ) {
			
			/*
			if( p.getListaProcesow().size() != 0 ) {
				p.getListaProcesow().get(0).zmniejszIloscCykliDoKoncaOJeden();
			}
			*/
			
			for ( Proces proc : p.getListaProcesow() ) {
				proc.zmniejszIloscCykliDoKoncaOJeden();
			}//koniec for dla procesu
			
			
		}//koniec for
	}//koniec reujuj ilosc cykli
	
	
	
	//GETTERS AND SETTERS
	public ArrayList<Procesor> getListaWszystkichProcesorow() {
		return listaWszystkichProcesorow;
	}
	public void setListaWszystkichProcesorow(ArrayList<Procesor> listaWszystkichProcesorow) {
		this.listaWszystkichProcesorow = listaWszystkichProcesorow;
	}
	public int getIleNowychProcesow() {
		return ileNowychProcesow;
	}
	public void setIleNowychProcesow(int ileNowychProcesow) {
		this.ileNowychProcesow = ileNowychProcesow;
	}
	public boolean getCzyWszystkieProcesoryWolne() {
		return czyWszystkieProcesoryWolne;
	}
	public void setCzyWszystkieProcesoryWolne(boolean czyWszystkieProcesoryWolne) {
		this.czyWszystkieProcesoryWolne = czyWszystkieProcesoryWolne;
	}
}//koniec klasy

























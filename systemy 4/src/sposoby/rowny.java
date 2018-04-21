package sposoby;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import inne.*;


public class rowny {
	
	private ArrayList<proces> listaWszystkichProcesow;
	private ArrayList<strona> stronyWRamie;
	private int liczbaRamek;							//liczba wszystkich dostepnych ramek
	
	public rowny( ciagGlobalny c, int LiczbaRamek ) {
		this.stronyWRamie = new ArrayList<>();
		this.liczbaRamek = LiczbaRamek;
		this.listaWszystkichProcesow = c.getLisatProcesow();
	}//koniec konstruktora
	

	
	public int[] run() {
		ArrayList<proces> listaZakonczonychProcesow = new ArrayList<>();
		
		//bedzie tyle wynikow ile procesow w liscie plus 1 wynik jako wynik globalny
		int[] wyniki = new int[listaWszystkichProcesow.size() + 1];
		
		int liczbaRamekNaProces = liczbaRamek/listaWszystkichProcesow.size();
		if ( liczbaRamekNaProces == 0 ) { return null; }
		

		
		while ( !listaWszystkichProcesow.isEmpty() ) {
			
			//liczba ramek na proces bedzie sie zmieniac w zaleznosci ile procesow zostalo jeszcze od wykonania
			liczbaRamekNaProces = liczbaRamek/listaWszystkichProcesow.size();
			
			//ustawiam kazdemu procesowi liczbe ramek
			for ( int i = 0; i < listaWszystkichProcesow.size(); i++) {
				listaWszystkichProcesow.get(i).setAktualnaLiczbaRamek(liczbaRamekNaProces);
			}//koniec for
			
			//teœcik
			if ( stronyWRamie.size() > liczbaRamek ) {
				System.out.println("b³¹d, liczba stron w ramie = " + stronyWRamie.size() );
			}//koniec if
			
			
//			System.out.println("wchodze");
			
			for ( proces p : listaWszystkichProcesow ) {
				if ( p.getAktualnaLiczbaRamek() > 0 ) { //jeœli proces ma jakies dostêpne ramki
					
					int aktualneOdwolanie = p.getOdwolania().get(0);
					boolean czyJuzJest = false;
					
					for ( strona s : stronyWRamie ) {
						if( s.getNumer() == aktualneOdwolanie ) {		//sprawdzam czy aktualnie potrzebna strona juz jest w pamieci fizycznej
							czyJuzJest = true;
							s.setCzasOstatniegoUzycia(0);				//zeruje czas ostatniego uzycia danej strony
						}//koniec if
					}//koniec for
					
					if ( czyJuzJest ) {
//						System.out.println("strona juz jest w ramie");
						p.getOdwolania().remove(0);
						for ( strona s : stronyWRamie ) {
							int czajnik = s.getCzasOstatniegoUzycia();
							s.setCzasOstatniegoUzycia(czajnik +1);			//zwiêkszam czas ostatniego uzycia kazdej strony o 1
						}//koniec for
					}//koniec if ( czy juz jest )
					
					else {//jak strony nie ma w ramie
						p.setIloscBledowStrony(p.getIloscBledowStrony() + 1);
//						System.out.println("strony nie ma w ramie");
						//sprawdzam ile stron w ramie ma proces p, jesli wykorzysta³ wszystkie przydzielone mu ramki to musi ktor¹œ zastapic
						int ileMaStronWRamie = 0;
						for ( strona s : stronyWRamie ) {
							if ( s.getNumer() >= p.getNumerPrierwszejStrony() && s.getNumer() <= p.getNumerOstatniejStrony() ) {
								ileMaStronWRamie++;
							}//koniec if
						}//koniec for
						
//						System.out.println("proces " + listaWszystkichProcesow.indexOf(p)  + " ma " + ileMaStronWRamie + " stron w ramie");
						
						//pierw sortuje strony w ramie po czasie ostatniego uzycia tak zeby najdawniejsze byly na poczatku
						Collections.sort(stronyWRamie, new Comparator<strona>() {
							@Override
							public int compare(strona o1, strona o2) {
								return o2.getCzasOstatniegoUzycia() - o1.getCzasOstatniegoUzycia();
							}
						});
						
						//jak max to usówam najstarsz¹
						if ( ileMaStronWRamie == p.getAktualnaLiczbaRamek() ) {
//							System.out.println("proces ma max w ramie");
							//szukam najdawniejszej ramki danego procesu i usówam j¹
							for ( int i = 0; i < stronyWRamie.size(); i++ ) {
								if ( stronyWRamie.get(i).getNumer() >= p.getNumerPrierwszejStrony() && stronyWRamie.get(i).getNumer() <= p.getNumerOstatniejStrony() ) {
									stronyWRamie.remove(i);
									break;
								}//koniec if
							}//koniec for
						}//koniec if
						
						//dodaje now¹
						int indexStronyOAktualnymOdwolaniu = 0;
						for( int j=0; j < p.getStrony().length; j++ ) {
							if ( p.getStrony()[j].getNumer() == aktualneOdwolanie ) {
								indexStronyOAktualnymOdwolaniu = j;
//								System.out.println("dodaje nowa strone do ramu o numerze = " + p.getStrony()[j].getNumer() );
								break;
							}//koniec if
						}//koniec for
						
					//zwiêkszam czas ostatniego uzycia kazdej strony o 1
						for ( strona s : stronyWRamie ) {
							int czajnik = s.getCzasOstatniegoUzycia();
							s.setCzasOstatniegoUzycia(czajnik +1);
						}//koniec for
						
						stronyWRamie.add(p.getStrony()[indexStronyOAktualnymOdwolaniu]);
						
						p.getOdwolania().remove(0);
						
					}//koniec else
				}//koniec if (czy ma dostêpne ramki)
			}//koniec for ( dla kazdego procesu )
					
			//jesli proces wykonal juz wszystkie swoje odwolania to usowam go z listy razem z jego stronami zapisanymi w ramie
			for ( int a=0; a < listaWszystkichProcesow.size(); a++ ) {
				if ( listaWszystkichProcesow.get(a).getOdwolania().isEmpty() ) {
//					System.out.println("Usówam zakonczony proces o numerze = " + a);
					//usówam ramki z ramu
					for ( int s = 0; s < stronyWRamie.size(); s++ ) {
						if( stronyWRamie.get(s).getNumer() >= listaWszystkichProcesow.get(a).getNumerPrierwszejStrony() && stronyWRamie.get(s).getNumer() <= listaWszystkichProcesow.get(a).getNumerOstatniejStrony() ) {
							stronyWRamie.remove(s);
							s--;
						}//koniec if
					}//koniec for
					listaZakonczonychProcesow.add(listaWszystkichProcesow.get(a));
					listaWszystkichProcesow.remove(a);
					a--;
				}//koniec if
			}//koniec for
			
		}//koniec while is empty
		
		
		for ( int a = 0; a < listaZakonczonychProcesow.size(); a++ ) {
			wyniki[a] = listaZakonczonychProcesow.get(a).getIloscBledowStrony();
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
































/*
public int[] run() {
	//bedzie tyle wynikow ile procesow w liscie plus 1 wynik jako wynik globalny
	int[] wyniki = new int[listaProcesow.size() + 1];
	
	int ramkiNaProces = liczbaRamek/(listaProcesow.size()+1);
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

*/


















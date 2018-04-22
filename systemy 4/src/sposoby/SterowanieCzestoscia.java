package sposoby;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import inne.*;

public class SterowanieCzestoscia {
	
	private ArrayList<proces> listaWszystkichProcesow;
	private ArrayList<strona> stronyWRamie;
	private int liczbaRamek;							//liczba wszystkich dostepnych ramek
	private static int gornaGranica = 50;				//jeœli czêstoœæ powy¿ej gornej granicy to dodje procesowi kolejn¹ ramkê
	private static int dolnaGranica = 10;				//jeœli czêstoœæ poni¿ej dolnej granicy to zabieram procesowi jedn¹ z jego ramek
	
	public SterowanieCzestoscia( ciagGlobalny c, int LiczbaRamek ) {
		this.stronyWRamie = new ArrayList<>();
		this.liczbaRamek = LiczbaRamek;
		this.listaWszystkichProcesow = c.getLisatProcesow();
	}//koniec konstruktora
	
	
	public int[] run() {
		ArrayList<proces> listaZakonczonychProcesow = new ArrayList<>();
		
		//bedzie tyle wynikow ile procesow w liscie plus 1 wynik jako wynik globalny
		int[] wyniki = new int[listaWszystkichProcesow.size() + 1];
		
		//daje procesom po jednej ramce dopóki nie skoncz¹ mi siê ramki
		for ( int i = 0; i < listaWszystkichProcesow.size(); i++) {
			if ( i+1 <= liczbaRamek ) {
				listaWszystkichProcesow.get(i).setAktualnaLiczbaRamek(1);
			}//koniec if
		}//koniec for
		
		while ( !listaWszystkichProcesow.isEmpty() ) {
			
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
						//uaktualniam historiê b³êdów
						//jeœli strona by³a w ramie i nie wywo³a³a b³êdu dodaje true
						p.getHistoriaBledow().add(true);
						p.getOdwolania().remove(0);
						//zwiêkszam czas ostatniego uzycia kazdej strony o 1
						for ( strona s : stronyWRamie ) {
							int czajnik = s.getCzasOstatniegoUzycia();
							s.setCzasOstatniegoUzycia(czajnik +1);
						}//koniec for
					}//koniec if ( czy juz jest )
					else {//jak strony nie ma w ramie
						p.setIloscBledowStrony(p.getIloscBledowStrony() + 1);
						//dodaje false do historii b³êdów
						p.getHistoriaBledow().add(false);
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
			
			//auktualniam liczbe ramek kazdego procesu ( który nie wykona³ jeszcze wszystkich swoich odwo³añ ) na podstawie nowej historii b³êdów
//			System.out.println("Uaktualniam liczbe ramek");
			
			int ileRamekPrzydzielono = 0;
//			System.out.println("uaktualniam ramki");
			for ( int i = 0; i < listaWszystkichProcesow.size(); i++) {
				
				proces p = listaWszystkichProcesow.get(i);
				
				int czyProcesPotrzebujeWiecejRamek =  czyPotrzebuje(p);
				
				if ( czyProcesPotrzebujeWiecejRamek == 1 ) {//czyli potrzebuje
//					System.out.println("proces potrzebuje wiecej ramek");
					if ( p.getAktualnaLiczbaRamek() + 1 + ileRamekPrzydzielono <= liczbaRamek ) {//jeœli jest wystarczaj¹co wolnych ramek
						p.setAktualnaLiczbaRamek(p.getAktualnaLiczbaRamek() + 1);
//						System.out.println("aktualna liczb ramek = " + p.getAktualnaLiczbaRamek() );
						ileRamekPrzydzielono = ileRamekPrzydzielono + p.getAktualnaLiczbaRamek();
					}//koniec czy wystarczajaco wolnych ramek
					else {//jeœli ramek nie wystarczy ju¿ dla tego procesu
						//to zabieram jego ramki, i usówam jego strony z ramu
						wywalProces(p);
					}//koniec else - nie wystarczy ramek dla tego procesu
					
					
				} else if ( czyProcesPotrzebujeWiecejRamek == -1 ) {//jeœli proces nie potrzebuje a¿ tylu ramek
//					System.out.println("proces nie potrzebuje az tylu ramek");	
					if ( p.getAktualnaLiczbaRamek() - 1 + ileRamekPrzydzielono <= liczbaRamek && p.getAktualnaLiczbaRamek() > 0 ) {//jeœli jest wystarczaj¹co wolnych ramek
						p.setAktualnaLiczbaRamek(p.getAktualnaLiczbaRamek() - 1);
						//usówam jedn¹ stronê tego procesu z ramu
						
						
						Collections.sort(stronyWRamie, new Comparator<strona>() {
							@Override
							public int compare(strona o1, strona o2) {
								return o2.getCzasOstatniegoUzycia() - o1.getCzasOstatniegoUzycia();
							}
						});
						//szukam najdawniejszej strony danego procesu i usówam j¹
						for ( int u = 0; u < stronyWRamie.size(); u++ ) {
							if ( stronyWRamie.get(u).getNumer() >= p.getNumerPrierwszejStrony() && stronyWRamie.get(u).getNumer() <= p.getNumerOstatniejStrony() ) {
								stronyWRamie.remove(u);
								break;
							}//koniec if
						}//koniec for
						ileRamekPrzydzielono = ileRamekPrzydzielono + p.getAktualnaLiczbaRamek();
					}//koniec czy wystarczajaco wolnych ramek
					else {//jeœli ramek nie wystarczy ju¿ dla tego procesu
//						System.out.println("ramek nie wystarczy dla danego procesu");
						//to zabieram jego ramki, i usówam jego strony z ramu
						wywalProces(p);
					}//koniec else - nie wystarczy ramek dla tego procesu
					
				} else if ( czyProcesPotrzebujeWiecejRamek == 0 ) {// jeœli proces ma odpowiednia liczbê ramek
					if ( p.getAktualnaLiczbaRamek() + ileRamekPrzydzielono <= liczbaRamek ) {//jeœli jest wystarczaj¹co wolnych ramek
						ileRamekPrzydzielono = ileRamekPrzydzielono + p.getAktualnaLiczbaRamek();
					}//koniec czy wystarczajaco wolnych ramek
					else {//jeœli ramek nie wystarczy ju¿ dla tego procesu
						//to zabieram jego ramki, i usówam jego strony z ramu
						wywalProces(p);
					}//koniec else - nie wystarczy ramek dla tego procesu
				}//koniec else
			}//koniec for ( uaktualnianie liczy ramek )
			
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
	
	
	
	private void wywalProces(proces p) {
		p.setAktualnaLiczbaRamek(0);
		p.getHistoriaBledow().clear();
//		System.out.println("nie wystarczy ramek dla procesu o numerze " + i + " zatrzymuje proces i zwalniam ram" );
		//usówam jego strony z ramu
		for ( int s = 0; s < stronyWRamie.size(); s++ ) {
			if( stronyWRamie.get(s).getNumer() >= p.getNumerPrierwszejStrony() && stronyWRamie.get(s).getNumer() <= p.getNumerOstatniejStrony() ) {
				stronyWRamie.remove(s);
				s--;
			}//koniec if
		}//koniec for
	}//koniec wywal proces


	//metoda zwraca 1 gdy proces potrzebuje nowej ramki, -1 gdy ma za du¿o ramek i 0 gdy jest pomiêdzy granicami ( nie trzeba ani dodawaæ ani zabieraæ ramek )
	private int czyPotrzebuje(proces p) {
		int iloscFalse = 0;
		for ( Boolean b : p.getHistoriaBledow() ) {
			if ( !b ) {
				iloscFalse++;
			}//koniec if
		}//koniec for
		if ( p.getHistoriaBledow().size() != 0 ) {
			int czestoscBledow = (iloscFalse*100)/p.getHistoriaBledow().size();	
			if( czestoscBledow >= gornaGranica ) {
				return 1;
			}else if( czestoscBledow <= dolnaGranica ) {
					return -1;
			}else {
				return 0;}
		}//koniec if
		else {//jeœli historia b³êdów jest pusta to zwracam 1 - proces potrzebuje ramek
			return 1;
		}//koniec else
	}//koniec czy potrzebuje
	
	
	
	
	
}//koniec klasy































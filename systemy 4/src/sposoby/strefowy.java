package sposoby;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

import inne.*;

public class strefowy {
	
	private final static int delta = 10;
	private ArrayList<proces> listaWszystkichProcesow;
	private ArrayList<strona> stronyWRamie;
	private int liczbaRamek;							//liczba wszystkich dostepnych ramek
	
	public strefowy( ciagGlobalny c, int LiczbaRamek ) {
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
				listaWszystkichProcesow.get(i).getZbiorRoboczy().add(-1);	//dodaje do zbioru roboczego -1 ¿eby w pierwszym obiegu pêtli 
			}//koniec if													dobrze zadzia³a³o aktualizowanie iloœci ramek na proces
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
						//uaktualniam zbiór roboczy
						if( p.getZbiorRoboczy().size() == delta ) {
							p.getZbiorRoboczy().remove(0);
							p.getZbiorRoboczy().add(aktualneOdwolanie);
						}//koniec if
						else {//jak zbiór roboczy nie pe³ny
							p.getZbiorRoboczy().add(aktualneOdwolanie);
						}//koniec else
						p.getOdwolania().remove(0);
						//zwiêkszam czas ostatniego uzycia kazdej strony o 1
						for ( strona s : stronyWRamie ) {
							int czajnik = s.getCzasOstatniegoUzycia();
							s.setCzasOstatniegoUzycia(czajnik +1);
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
						
						//uaktualniam zbior roboczy
						if( p.getZbiorRoboczy().size() == delta ) {
							p.getZbiorRoboczy().remove(0);
							p.getZbiorRoboczy().add(aktualneOdwolanie);
						}//koniec if
						else {//jak zbiór roboczy nie pe³ny
							p.getZbiorRoboczy().add(aktualneOdwolanie);
						}//koniec else
						
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
			
			//auktualniam liczbe ramek kazdego procesu ( który nie wykona³ jeszcze wszystkich swoich odwo³añ ) na podstawie nowego zbioru roboczego
//			System.out.println("Uaktualniam liczbe ramek");
			int ileRamekPrzydzielono = 0;
			for ( int i = 0; i < listaWszystkichProcesow.size(); i++) {
				int ileRamekPotrzebujeProces =  zapotrzebowanieProcesu(listaWszystkichProcesow.get(i));
//				System.out.println( "Proces o numerze " + i + " potrzebuje " + ileRamekPotrzebujeProces + " ramek" );
				if ( ileRamekPrzydzielono + ileRamekPotrzebujeProces <= liczbaRamek ) {
					ileRamekPrzydzielono = ileRamekPrzydzielono + ileRamekPotrzebujeProces;
					listaWszystkichProcesow.get(i).setAktualnaLiczbaRamek(ileRamekPotrzebujeProces);
					
					//jesli proces potrzebuje teraz mniej ramek niz ostatnio (o 1 mniej bo maxymalnie o 1 zmniejszy³o siê zapotrzebowanie po 
					//usuniêciu 1 wartoœci ze zbioru roboczego) to musze usunæ jedn¹ z jego stron znajduj¹cych sie w ramie
					int ileMaStronWRamie = 0;
					for ( strona s : stronyWRamie ) {
						if ( s.getNumer() >= listaWszystkichProcesow.get(i).getNumerPrierwszejStrony() && s.getNumer() <= listaWszystkichProcesow.get(i).getNumerOstatniejStrony() ) {
							ileMaStronWRamie++;
						}//koniec if
					}//koniec for
					
					if ( ileMaStronWRamie > ileRamekPotrzebujeProces ) {
						//pierw sortuje strony w ramie po czasie ostatniego uzycia tak zeby najdawniejsze byly na poczatku
						Collections.sort(stronyWRamie, new Comparator<strona>() {
							@Override
							public int compare(strona o1, strona o2) {
								return o2.getCzasOstatniegoUzycia() - o1.getCzasOstatniegoUzycia();
							}
						});
					
						for ( int g = 0; g < stronyWRamie.size(); g++ ) {
							if ( stronyWRamie.get(g).getNumer() >= listaWszystkichProcesow.get(i).getNumerPrierwszejStrony() && stronyWRamie.get(g).getNumer() <= listaWszystkichProcesow.get(i).getNumerOstatniejStrony() ) {
								stronyWRamie.remove(g);
								break;
							}//koniec if
						}//koniec for	
					}//koniec if	
					
				}//koniec if
				else {//jeœli nie wystarczy ramek dla tego procesu to zatrzymujemy go zwalniaj¹c zajmowane przez niego ramki
					listaWszystkichProcesow.get(i).setAktualnaLiczbaRamek(0);
//					System.out.println("nie wystarczy ramek dla procesu o numerze " + i + " zatrzymuje proces i zwalniam ram" );
					//usówam jego strony z ramu
					for ( int s = 0; s < stronyWRamie.size(); s++ ) {
						if( stronyWRamie.get(s).getNumer() >= listaWszystkichProcesow.get(i).getNumerPrierwszejStrony() && stronyWRamie.get(s).getNumer() <= listaWszystkichProcesow.get(i).getNumerOstatniejStrony() ) {
							stronyWRamie.remove(s);
							s--;
						}//koniec if
					}//koniec for
				}//koniec else ( jeœli nie wystarczy ramek )
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
	
	
	
	
	private int zapotrzebowanieProcesu(proces p) {
		TreeSet<Integer> zbior = new TreeSet<>();
		for ( Integer i : p.getZbiorRoboczy() ) {
			zbior.add(i);
		}//koniec for
		int liczbaRoznychWartosciWzbiorzeRoboczym = zbior.size();
		return liczbaRoznychWartosciWzbiorzeRoboczym;
	}//koniec metody zapotrzebowanie procesu
	

	
	
	public static int getDelta() {return delta;} 
	
}//koniec klasy




























































/*
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
*/







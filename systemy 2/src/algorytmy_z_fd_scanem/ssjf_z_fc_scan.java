package algorytmy_z_fd_scanem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import test.dysk;
import test.zgloszenie;

public class ssjf_z_fc_scan {
	
	private ArrayList<zgloszenie> lista;
	
	public ssjf_z_fc_scan( dysk d ) {
		this.lista = d.getLista();
	}//koniec konstruktora
	
	
	public int run() {	
		
		ArrayList<zgloszenie> listaAktualna = new ArrayList<>();
		ArrayList<zgloszenie> listaPriorytetowa = new ArrayList<>();
		
		
		Collections.sort(lista, new Comparator<zgloszenie>() {	//sortuje po czasie wejscia
			@Override
			public int compare(zgloszenie o1, zgloszenie o2) {
				return o1.getCzasWejscia() - o2.getCzasWejscia();
			}//koniec compare
		});//koniec klasy zagniezdzonej
		
		int iloscZgloszenNaStart = lista.size()*6/10;
		int counter = 0;
		int iloscPozostalychZgloszen = lista.size() - iloscZgloszenNaStart;
		
		for ( int i = 0; i < iloscZgloszenNaStart; i++  ) {
			listaAktualna.add(lista.get(i));				///dodaje w��cznie z elem ostatniem
		}
		
		int przemieszczenia = 0;

		Collections.sort(listaAktualna, new Comparator<zgloszenie>() {	//sortuje po miejscu na dysku
			@Override
			public int compare(zgloszenie o1, zgloszenie o2) {
				return o1.getMiejsceNaDysku() - o2.getMiejsceNaDysku();
			}//koniec compare
		});//koniec klasy zagniezdzonej
		
		//System.out.println("Przed dodaniem ");
		//for (zgloszenie z : listaAktualna) {
		//	System.out.println(z);
		//}
		
		
		boolean czyDodane = false;
		
		int aktualnyIndex = listaAktualna.size()/2;								//zaczniemy ze srodka ( jak w ksi��ce)
		int aktualnaPozycja;
		
		while ( listaAktualna.size() != 1) {		//bo jak zostanie 1 zgloszenie to konczymy ( g�owica juz sie nie przemieszcza )
			//dodaje elementy gdy lista jest prawie pusta lub gdy ilo�c wykonanych zg�osze� jest r�wna ilo�ci pozosta�ych w liscie 1
			if ( (listaAktualna.size() == 2 || counter >= iloscPozostalychZgloszen) && !czyDodane ) {
				zgloszenie aktualnyElement = listaAktualna.get(aktualnyIndex);
				for ( int i = iloscZgloszenNaStart; i < lista.size(); i++ ) {
					listaAktualna.add(lista.get(i));
				}//koniec for
				
				Collections.sort(listaAktualna, new Comparator<zgloszenie>() {	//sortuje po miejscu na dysku
					@Override
					public int compare(zgloszenie o1, zgloszenie o2) {
						return o1.getMiejsceNaDysku() - o2.getMiejsceNaDysku();
					}//koniec compare
				});//koniec klasy zagniezdzonej
				
				aktualnyIndex = listaAktualna.indexOf(aktualnyElement);
				czyDodane = true;			//ustawiam czyDodane na true �eby nie dodawa� w niesko�czono��
				
				//System.out.println("Po dodaniu ");
				//for (zgloszenie z : listaAktualna) {
				//	System.out.println(z);
				//}
				
			}//koniec if
			
			
			
			
			//fd-scan
			
			
			for ( int z = 0 ; z < listaAktualna.size(); z++ ) {
				
				if ( listaAktualna.get(z).getPriorytet() ) {
					listaPriorytetowa.add(listaAktualna.get(z));
					listaAktualna.remove(z);
					if ( z <= aktualnyIndex  ) {
						aktualnyIndex--; 	//jak us�wamy cos z wczesniejszych index�w to nasz aktualny przesunie sie o 1
					}//koniec if
					z--;
				}//koniec if
				
			}//koniec for
			
			aktualnaPozycja = listaAktualna.get(aktualnyIndex).getMiejsceNaDysku();
			
			//sort po czsie wejscia, najstarsze priorytety na pocz�tku
			Collections.sort(listaPriorytetowa, new Comparator<zgloszenie>() {
				@Override
				public int compare(zgloszenie o1, zgloszenie o2) {
					// TODO Auto-generated method stub
					return o1.getCzasWejscia()-o2.getCzasWejscia();
				}//koniec compare to
			});
			
			for ( int z = 0; z < listaPriorytetowa.size()-1; z++ ) {
				przemieszczenia = przemieszczenia + Math.abs(aktualnaPozycja - listaPriorytetowa.get(z).getMiejsceNaDysku());
				
				//us�wam wszystkie zgloszenia zwyk�e, kt�re sa pomi�dzy priorytetowymi
				for ( int i = 0; i <listaAktualna.size()-1; i++ ) {
					if ( aktualnaPozycja < listaPriorytetowa.get(z).getMiejsceNaDysku()) {
						if ( listaAktualna.get(i).getMiejsceNaDysku() > aktualnaPozycja && listaAktualna.get(i).getMiejsceNaDysku() < listaPriorytetowa.get(z).getMiejsceNaDysku()) {
							listaAktualna.remove(i);
							if ( i <= aktualnyIndex  && aktualnyIndex !=0 ) {
								aktualnyIndex--; //jak us�wamy cos z wczesniejszych index�w to nasz aktualny przesunie sie o 1
								counter++;
							}//koniec if
							if ( listaAktualna.size()==1 && listaPriorytetowa.isEmpty() ) {
								return przemieszczenia;
							}
							i--;
						}//koniec if wewnetrznego
					}//koniec if
					else {
						if ( listaAktualna.get(i).getMiejsceNaDysku() < aktualnaPozycja && listaAktualna.get(i).getMiejsceNaDysku() > listaPriorytetowa.get(z).getMiejsceNaDysku()) {
							listaAktualna.remove(i);
							if ( i <= aktualnyIndex  && aktualnyIndex !=0 ) {
								aktualnyIndex--; //jak us�wamy cos z wczesniejszych index�w to nasz aktualny przesunie sie o 1
								counter++;
							}//koniec if
							if ( listaAktualna.size()==1 && listaPriorytetowa.isEmpty() ) {
								return przemieszczenia;
							}
							i--;
						}//koniec if wewnetrznego
					}//koniec else ( aktualna pozycja jest mniejsza niz miejsce w kt�rym jest z
				}//koniec for
				
				
				//us�wam wszystkie zgloszenia prio, kt�re sa pomi�dzy prio
				for ( int i = 0; i <listaPriorytetowa.size()-1; i++ ) {
					if ( aktualnaPozycja < listaPriorytetowa.get(z).getMiejsceNaDysku()) {
						if ( listaPriorytetowa.get(i).getMiejsceNaDysku() > aktualnaPozycja && listaPriorytetowa.get(i).getMiejsceNaDysku() < listaPriorytetowa.get(z).getMiejsceNaDysku()) {
							listaPriorytetowa.remove(i);
							i--;
						}//koniec if wewnetrznego
					}//koniec if
					else {
						if ( listaPriorytetowa.get(i).getMiejsceNaDysku() < aktualnaPozycja && listaPriorytetowa.get(i).getMiejsceNaDysku() > listaPriorytetowa.get(z).getMiejsceNaDysku()) {
							listaPriorytetowa.remove(i);
							i--;
						}//koniec if wewnetrznego
					}//koniec else ( aktualna pozycja jest mniejsza niz miejsce w kt�rym jest z
				}//koniec for
				
				
				aktualnaPozycja = listaPriorytetowa.get(z).getMiejsceNaDysku();
				listaPriorytetowa.remove(z);
				z--;
			}//koniec for
			
			if ( listaAktualna.isEmpty() || listaAktualna.size() == 1) {
				return przemieszczenia;
			}//jak opr�nimy ca�� list� fdscanem
			
			
			
			//ZWYK�Y SSTF
			
			//szczeg�lny przypadek wprowadzony �eby uchronic si� przed Null`em gdy wywo�uje index+1 lub index-1
			if( aktualnyIndex != 0 && aktualnyIndex != (listaAktualna.size()-1) ) {
				if ( Math.abs(listaAktualna.get(aktualnyIndex).getMiejsceNaDysku() - listaAktualna.get(aktualnyIndex-1).getMiejsceNaDysku() ) < Math.abs(listaAktualna.get(aktualnyIndex).getMiejsceNaDysku() - listaAktualna.get(aktualnyIndex+1).getMiejsceNaDysku() ) ) {					
					przemieszczenia = przemieszczenia + Math.abs(listaAktualna.get(aktualnyIndex).getMiejsceNaDysku() - listaAktualna.get(aktualnyIndex-1).getMiejsceNaDysku());
					listaAktualna.remove(aktualnyIndex);
					aktualnyIndex--;
				}//koniec if
				else {
					przemieszczenia = przemieszczenia + Math.abs(listaAktualna.get(aktualnyIndex).getMiejsceNaDysku() - listaAktualna.get(aktualnyIndex+1).getMiejsceNaDysku());
					listaAktualna.remove(aktualnyIndex);
					//aktualnyIndex++;
				}//koniec else
			}//koniec if zwen�trznego
			
			else if ( aktualnyIndex == 0 ) {
				przemieszczenia = przemieszczenia + Math.abs(listaAktualna.get(aktualnyIndex).getMiejsceNaDysku() - listaAktualna.get(aktualnyIndex+1).getMiejsceNaDysku());
				listaAktualna.remove(aktualnyIndex);		//jestem na poczatku lisy, us�wam zakonczone zgloszenie i kolejne staje na indeksie 0 wiec nie musze zmieniac aktualnego indexu
			}//koniec else if
			else {
				przemieszczenia = przemieszczenia + Math.abs(listaAktualna.get(aktualnyIndex).getMiejsceNaDysku() - listaAktualna.get(aktualnyIndex-1).getMiejsceNaDysku());
				listaAktualna.remove(aktualnyIndex);
				aktualnyIndex--;
			}//koniec else dla indexu r�wnego size-1
			counter++;
		}//koniec while
		return przemieszczenia;
	}//koniec run
	
}//koniec klasy






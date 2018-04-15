package algorytmy_z_edf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import test.dysk;
import test.zgloszenie;

public class sstf_edf {
	
	private ArrayList<zgloszenie> lista;
	
	public sstf_edf( dysk d ) {
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
		
		int iloscZgloszenNaStart = lista.size()*8/10;
		int counter = 0;
		int iloscPozostalychZgloszen = lista.size() - iloscZgloszenNaStart;
		
		for ( int i = 0; i < iloscZgloszenNaStart; i++  ) {
			listaAktualna.add(lista.get(i));				///dodaje w³¹cznie z elem ostatniem
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
		
		int aktualnyIndex = listaAktualna.size()/2;								//zaczniemy ze srodka ( jak w ksi¹¿ce)
		int aktualnaPozycja;
		
		while ( listaAktualna.size() != 1) {		//bo jak zostanie 1 zgloszenie to konczymy ( g³owica juz sie nie przemieszcza )
			//dodaje elementy gdy lista jest prawie pusta lub gdy iloœc wykonanych zg³oszeñ jest równa iloœci pozosta³ych w liscie 1
			if ( (listaAktualna.size() == 2 || counter == iloscPozostalychZgloszen) && !czyDodane ) {
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
				czyDodane = true;			//ustawiam czyDodane na true ¿eby nie dodawaæ w nieskoñczonoœæ
				
				//System.out.println("Po dodaniu ");
				//for (zgloszenie z : listaAktualna) {
				//	System.out.println(z);
				//}
				
			}//koniec if
			
			//EDF
			
			for ( int z = 0 ; z < listaAktualna.size(); z++ ) {
				
				if ( listaAktualna.get(z).getPriorytet() ) {
					listaPriorytetowa.add(listaAktualna.get(z));
					listaAktualna.remove(z);
					if ( z <= aktualnyIndex  ) {
						aktualnyIndex--; 	//jak usówamy cos z wczesniejszych indexów to nasz aktualny przesunie sie o 1
					}//koniec if
					z--;
				}//koniec if
			}//koniec for
			
			aktualnaPozycja = listaAktualna.get(aktualnyIndex).getMiejsceNaDysku();
			
			//sort po czsie wejscia
			Collections.sort(listaPriorytetowa, new Comparator<zgloszenie>() {
				@Override
				public int compare(zgloszenie o1, zgloszenie o2) {
					// TODO Auto-generated method stub
					return o1.getCzasWejscia()-o2.getCzasWejscia();
				}//koniec compare to
			});
			
			
			for ( int z = 0; z < listaPriorytetowa.size(); z++ ) {
				przemieszczenia = przemieszczenia + Math.abs(aktualnaPozycja - listaPriorytetowa.get(z).getMiejsceNaDysku());
				aktualnaPozycja = listaPriorytetowa.get(z).getMiejsceNaDysku();
				listaPriorytetowa.remove(z);
				z--;
			}//koniec for
			
			
			
			//ZWYK£Y SSTF
			
			//szczególny przypadek wprowadzony ¿eby uchronic siê przed Null`em gdy wywo³uje index+1 lub index-1
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
			}//koniec if zwenêtrznego
			
			else if ( aktualnyIndex == 0 ) {
				przemieszczenia = przemieszczenia + Math.abs(listaAktualna.get(aktualnyIndex).getMiejsceNaDysku() - listaAktualna.get(aktualnyIndex+1).getMiejsceNaDysku());
				listaAktualna.remove(aktualnyIndex);		//jestem na poczatku lisy, usówam zakonczone zgloszenie i kolejne staje na indeksie 0 wiec nie musze zmieniac aktualnego indexu
			}//koniec else if
			else {
				przemieszczenia = przemieszczenia + Math.abs(listaAktualna.get(aktualnyIndex).getMiejsceNaDysku() - listaAktualna.get(aktualnyIndex-1).getMiejsceNaDysku());
				listaAktualna.remove(aktualnyIndex);
				aktualnyIndex--;
			}//koniec else dla indexu równego size-1
			counter++;
		}//koniec while
		return przemieszczenia;
	}//koniec run
	
}//koniec klasy

























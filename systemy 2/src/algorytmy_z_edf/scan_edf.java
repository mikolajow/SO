package algorytmy_z_edf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import test.dysk;
import test.zgloszenie;

public class scan_edf {
	
	private ArrayList<zgloszenie> lista;
	private int rozmiarDysku;
	
	public scan_edf( dysk d ) {
		this.lista = d.getLista();
		this.rozmiarDysku = d.getRozmiar();
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
		
		int aktualnyIndex = listaAktualna.size()/2;		//zak�adam �e zaczynam od "�rodka" tak jak w ksi��ce
		int aktualnaPozycja = lista.get(aktualnyIndex).getMiejsceNaDysku();
		String kierunek = "prawo";						//zak�adamy ze kierunkiem pocz�tkowym b�dzie "prawo" ->
		
		while ( listaAktualna.size() != 1 ) {
			
			//dodawanie element�w analogicznie co w sstf
			
			if ( (listaAktualna.size() == 2 || counter == iloscPozostalychZgloszen) && !czyDodane ) {//dodaje elementy gdy lista jest prawie pusta lub gdy ilo�c wykonanych zg�osze�
				zgloszenie aktualnyElement = listaAktualna.get(aktualnyIndex);						//jest r�wna ilo�ci pozosta�ych i liscie 1
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
				czyDodane = true;
				
				//System.out.println("Przemieszczenia przed dodaniem = " + przemieszczenia);
				//System.out.println("Po dodaniu ");
				//for (zgloszenie z : listaAktualna) {
				//	System.out.println(z);
				//}
				
			}//koniec if
			
			
			
			
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
				//System.out.println("po obliczeniu priorytetowych = " + przemieszczenia);
			}//koniec for
			
			
			
			
			// zwyk�y scan
			
			switch (kierunek) {
			case "prawo" :
				
				if ( aktualnyIndex != listaAktualna.size()-1 ) {//je�li nie jestesmy na ostatnim indeksie to
					przemieszczenia = przemieszczenia + Math.abs( listaAktualna.get(aktualnyIndex).getMiejsceNaDysku() -listaAktualna.get(aktualnyIndex + 1).getMiejsceNaDysku()  );
					listaAktualna.remove(aktualnyIndex);	//aktualny index bez zmian jako ze reszta listy przesunie sie o 1
				}//koniec if
				else {//jak jestem na ostatnim to
					przemieszczenia = przemieszczenia + rozmiarDysku - listaAktualna.get(aktualnyIndex).getMiejsceNaDysku(); //bo scan dociera do konca dysku
					kierunek = "lewo";
				}//koniec else
				
				break;
			default:
				
				if ( aktualnyIndex == 0 ) {//je�li jeste�my na pierwszym indeksie
					przemieszczenia = przemieszczenia + listaAktualna.get(0).getMiejsceNaDysku();	//bo scan dociera do konca dysku
					kierunek = "prawo";
				}//koniec if
				else {
					przemieszczenia = przemieszczenia + Math.abs( listaAktualna.get(aktualnyIndex).getMiejsceNaDysku() -listaAktualna.get(aktualnyIndex -1).getMiejsceNaDysku()  );			
					listaAktualna.remove(aktualnyIndex);	
					aktualnyIndex--;
				}//koniec else
				
				break;
			}//koniec switch	
			
			counter++;
		}//koniec while
		
		//System.out.println("Licznik =  " + counter);
		
		return przemieszczenia;
	}//koniec run
	
}//koniec klasy






















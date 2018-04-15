package algorytmy_podstawowe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import test.dysk;
import test.zgloszenie;

public class C_SCAN {
	
	private ArrayList<zgloszenie> lista;
	
	public C_SCAN( dysk d ) {
		this.lista = d.getLista();
	}//koniec konstruktora
	
	
	public int run() {	
		
		
		ArrayList<zgloszenie> listaAktualna = new ArrayList<>();
		
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
			listaAktualna.add(lista.get(i));				///dodaje w³¹cznie z elem ostatniem
		}
		
		int przemieszczenia = 0;

		Collections.sort(listaAktualna, new Comparator<zgloszenie>() {	//sortuje po miejscu na dysku
			@Override
			public int compare(zgloszenie o1, zgloszenie o2) {
				return o1.getMiejsceNaDysku() - o2.getMiejsceNaDysku();
			}//koniec compare
		});//koniec klasy zagniezdzonej
		
		/*
		System.out.println("Przed dodaniem ");
		for (zgloszenie z : listaAktualna) {
			System.out.println(z);
		}
		*/
		
		
		boolean czyDodane = false;
		
		int aktualnyIndex = listaAktualna.size()/2;	
		
		while ( listaAktualna.size() != 1 ) {
			
			
			if ( (listaAktualna.size() == 2 || counter == iloscPozostalychZgloszen) && !czyDodane ) {//dodaje elementy gdy lista jest prawie pusta lub gdy iloœc wykonanych zg³oszeñ
				zgloszenie aktualnyElement = listaAktualna.get(aktualnyIndex);						//jest równa iloœci pozosta³ych i liscie 1
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
				
				/*
				System.out.println("Przemieszczenia przed dodaniem = " + przemieszczenia);
				System.out.println("Po dodaniu ");
				for (zgloszenie z : listaAktualna) {
					System.out.println(z);
				}*/
				
			}//koniec if
			
			
				if ( aktualnyIndex != listaAktualna.size()-1 ) {//jeœli nie jestesmy na ostatnim indeksie to
																//dodaje do sumu przemieszczen ró¿nice odleg³oœci miêdzy 
																//aktualnym indexem a nastêpnym
					przemieszczenia = przemieszczenia + Math.abs( listaAktualna.get(aktualnyIndex).getMiejsceNaDysku() -listaAktualna.get(aktualnyIndex + 1).getMiejsceNaDysku()  );
					listaAktualna.remove(aktualnyIndex);	//aktualny index bez zmian jako ze reszta listy przesunie sie o 1
				}//koniec if
				else {		//jak jesteœmy na koñcu listy ( index = size -1  )
					przemieszczenia++; //przy "skoku" mieliœmy dodaæ 1 do przesuniêæ
					listaAktualna.remove(aktualnyIndex);
					aktualnyIndex = 0;
				}//koniec else
				
				
			
			counter++;
		}//koniec while
		
		//System.out.println("Licznik =  " + counter);
		
		return przemieszczenia;
	}//koniec run
	
}//koniec klasy c-scan





























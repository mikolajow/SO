package algorytmy_podstawowe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import test.dysk;
import test.zgloszenie;


public class FD_SCAN {
	
	private ArrayList<zgloszenie> lista;
	private int rozmiarDysku;
	
	public FD_SCAN( dysk d ) {//priorytety generowane w edf
		this.lista = d.getLista();
		this.rozmiarDysku = d.getRozmiar();
	}//koniec konstruktora
	
	public int run () {
		
		int przemieszczenia = 0;
		
		ArrayList<zgloszenie> listaAktualna = new ArrayList<>();
		ArrayList<zgloszenie> listaPriorytetowa = new ArrayList<>();
		
		//nie ma sensu "dosypywaæ" zg³oszeñ bo tak czy siak by³yby wykonywane jako ostatnie
		//jeœli dosypalibyœmy zg³oszenie priorytetowe w czasie to nie wp³yne³oby to znacz¹co na wynik jako ¿e g³owica i tak znajdowa³aby siê w nieprzewidywalnym miejscu
		
		for ( zgloszenie z : lista ) {
			if ( z.getPriorytet() == true ) {
				listaPriorytetowa.add(z);
				//System.out.println("dodano priorytet");
			}//koniec if
			else {
				listaAktualna.add(z);
			}//koniec else
		}//koniec for
		
		
		zgloszenie zgl1 = listaPriorytetowa.get(0);
		
		
			Collections.sort(listaPriorytetowa, new Comparator<zgloszenie>() {	//sortuje po miejscu na dysku
				@Override
				public int compare(zgloszenie o1, zgloszenie o2) {
					return o1.getMiejsceNaDysku() - o2.getMiejsceNaDysku();
				}//koniec compare
			});//koniec klasy zagniezdzonej
		
			Collections.sort(listaAktualna, new Comparator<zgloszenie>() {	//sortuje po miejscu na dysku
				@Override
				public int compare(zgloszenie o1, zgloszenie o2) {
					return o1.getMiejsceNaDysku() - o2.getMiejsceNaDysku();
				}//koniec compare
			});//koniec klasy zagniezdzonej
			
			String kierunek = "prawo";
			
			int aktualnyIndex = listaPriorytetowa.indexOf(zgl1);	//zaczynamy od pierwszego zg³oszenia priorytetowego które wp³yne³o do listy ( sortowanej poczasie wejscia )
			
			zgloszenie ostatnie = null;		//potrzebne do znalezienia indexu pocz¹tkowego wykorzystywanego potem w scanie do niepriorytetowych zg³oszen
			
			// priorytety lookiem
			while ( listaPriorytetowa.size() != 1 ) {
				
				switch (kierunek) {
				case "prawo" :
					
					if ( aktualnyIndex != listaPriorytetowa.size()-1 ) {//jeœli nie jestesmy na ostatnim indeksie to
						przemieszczenia = przemieszczenia + Math.abs( listaPriorytetowa.get(aktualnyIndex).getMiejsceNaDysku() -listaPriorytetowa.get(aktualnyIndex + 1).getMiejsceNaDysku()  );
						ostatnie = listaPriorytetowa.get(aktualnyIndex);
						listaPriorytetowa.remove(aktualnyIndex);	//aktualny index bez zmian jako ze reszta listy przesunie sie o 1
					}//koniec if
					else {
						kierunek = "lewo";
					}//koniec else
					
					break;
				default:
					
					if ( aktualnyIndex == 0 ) {//jeœli jesteœmy na pierwszym indeksie
						kierunek = "prawo";
					}//koniec if
					else {
						przemieszczenia = przemieszczenia + Math.abs( listaPriorytetowa.get(aktualnyIndex).getMiejsceNaDysku() -listaPriorytetowa.get(aktualnyIndex -1).getMiejsceNaDysku()  );	
						ostatnie = listaPriorytetowa.get(aktualnyIndex);
						listaPriorytetowa.remove(aktualnyIndex);	
						aktualnyIndex--;
					}//koniec else
					break;
				}//koniec switch
			}//koniec while
		
		//zwyk³e scanem
		
		kierunek = "prawo";
		
		int indexOstatniego = lista.indexOf(ostatnie);
		zgloszenie zgloszenieOindeksiePozniejszymNizOstatni;
		
		if ( indexOstatniego == lista.size()-1 ) {
			zgloszenieOindeksiePozniejszymNizOstatni = lista.get(indexOstatniego -1);
			aktualnyIndex = listaAktualna.indexOf(zgloszenieOindeksiePozniejszymNizOstatni);
		}//koniec if
		else {
			zgloszenieOindeksiePozniejszymNizOstatni = lista.get(indexOstatniego + 1);
			aktualnyIndex = listaAktualna.indexOf(zgloszenieOindeksiePozniejszymNizOstatni);
		}//koniec else
		
		while ( listaAktualna.size() != 1 ) {
			
			switch (kierunek) {
			case "prawo" :
					
				if ( aktualnyIndex != listaAktualna.size()-1 ) {//jeœli nie jestesmy na ostatnim indeksie to
					przemieszczenia = przemieszczenia + Math.abs( listaAktualna.get(aktualnyIndex).getMiejsceNaDysku() -listaAktualna.get(aktualnyIndex + 1).getMiejsceNaDysku()  );
					listaAktualna.remove(aktualnyIndex);	//aktualny index bez zmian jako ze reszta listy przesunie sie o 1
				}//koniec if
				else {//jak jestem na ostatnim to
					przemieszczenia = przemieszczenia + rozmiarDysku - listaAktualna.get(aktualnyIndex).getMiejsceNaDysku(); //bo scan dociera do konca dysku
					kierunek = "lewo";
				}//koniec else
					
				break;
			default:
					
				if ( aktualnyIndex == 0 ) {//jeœli jesteœmy na pierwszym indeksie
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
		}//koniec while
		
		return przemieszczenia;
	}//koniec run	
}//koniec klasy



























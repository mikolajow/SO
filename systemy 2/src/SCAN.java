import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SCAN {
	
	private ArrayList<zgloszenie> lista;
	private int rozmiarDysku;
	
	public SCAN( dysk d ) {
		this.lista = d.getLista();
		this.rozmiarDysku = d.getRozmiar();
	}//koniec konstruktora
	
	
	public int run() {	
		
		ArrayList<zgloszenie> listaAktualna = new ArrayList<>();
		
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
		
		int aktualnyIndex = listaAktualna.size()/2;	
		String kierunek = "prawo";						//zak³adamy ze kierunkiem pocz¹tkowym bêdzie "prawo" ->
		
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
				
				//System.out.println("Przemieszczenia przed dodaniem = " + przemieszczenia);
				//System.out.println("Po dodaniu ");
				//for (zgloszenie z : listaAktualna) {
				//	System.out.println(z);
				//}
				
			}//koniec if
			
			
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
			
			counter++;
		}//koniec while
		
		//System.out.println("Licznik =  " + counter);
		
		return przemieszczenia;
	}//koniec run
	
}//koniec klasy scan























import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SSTF {
	
	private ArrayList<zgloszenie> lista;
	
	public SSTF( dysk d ) {
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
		
		int iloscZgloszenNaStart = lista.size()*8/10;
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
		
		while ( listaAktualna.size() != 1) {		//bo jak zostanie 1 zgloszenie to konczymy ( g�owica juz sie nie przemieszcza )
			
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
				
				//System.out.println("Po dodaniu ");
				//for (zgloszenie z : listaAktualna) {
				//	System.out.println(z);
				//}
				
			}//koniec if
			
			
			
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

























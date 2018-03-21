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
		
		
		int przemieszczenia = 0;
		int aktualnyIndex = lista.size()/2;								//zaczniemy ze srodka ( jak w ksi¹¿ce)
		
		
		Collections.sort(lista, new Comparator<zgloszenie>() {	//sortuje po miejscu na dysku
			@Override
			public int compare(zgloszenie o1, zgloszenie o2) {
				return o1.getMiejsceNaDysku() - o2.getMiejsceNaDysku();
			}//koniec compare
		});//koniec klasy zagniezdzonej
		
		for ( zgloszenie z : lista ) {
			listaAktualna.add(z);
		}
		
		
		
		while ( listaAktualna.size() != 1) {		//bo jak zostanie 1 zgloszenie to konczymy ( g³owica juz sie nie przemieszcza )
			
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
		}//koniec while
		return przemieszczenia;
	}//koniec run
	
}//koniec klasy

























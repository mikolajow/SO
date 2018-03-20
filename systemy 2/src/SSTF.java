import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SSTF {
	
	private ArrayList<zgloszenie> lista;
	
	public SSTF( dysk d ) {
		this.lista = d.getLista();
		
	}//koniec konstruktora
	
	
	public int run() {
		//sortuje po czasie wejscia, potem dodam pierwsze 80% ze zgloszen do wykonywanej listy 
		Collections.sort(lista, new Comparator<zgloszenie>() {	//sortuje po czasie wejscia
			@Override
			public int compare(zgloszenie o1, zgloszenie o2) {
				return o1.getCzasWejscia() - o2.getCzasWejscia();
			}//koniec compare
		});//koniec klasy zagniezdzonej
		
		ArrayList<zgloszenie> listaAktualna = new ArrayList<>();
		
		int indexOstatniego = (lista.size()-1)*8/10;
		
		for ( int i=0; i< indexOstatniego; i++ ) {			//dodaje pierwsze 80% zg³oszeñ
			listaAktualna.add(lista.get(i));
		}//koniec for
		
		int ileZostalo = lista.size()-listaAktualna.size();	//licze ile procesów zosta³o mi do dodania
		int counter = 0;									//gdy counter bedzie równy liczbie zgloszen które zosta³y to dodam te zg³oszenia
															//czyli dodam je gdy wykona sie ilosc zgloszen równa iloœci pozosta³ych zg³oszeñ
		
		int przemieszczenia = 0;
		int aktualneMiejsce = lista.get(0).getMiejsceNaDysku() ;		//zaczniemy od pierwszego zg³oszenia na liscie ( zak³adamy ze tam znajduje sie na poczatku nasz g³owica )
		int aktualnyIndex = 0;
		
		
		Collections.sort(lista, new Comparator<zgloszenie>() {	//sortuje po miejscu na dysku
			@Override
			public int compare(zgloszenie o1, zgloszenie o2) {
				return o1.getMiejsceNaDysku() - o2.getMiejsceNaDysku();
			}//koniec compare
		});//koniec klasy zagniezdzonej
		
		
		while ( !listaAktualna.isEmpty() ) {
			if (counter == ileZostalo) {
				for ( int i=indexOstatniego; i< lista.size(); i++ ) {			//dodaje 20% pozosta³ych zg³oszeñ
					listaAktualna.add(lista.get(i));
					Collections.sort(lista, new Comparator<zgloszenie>() {	//sortuje po miejscu na dysku
						@Override
						public int compare(zgloszenie o1, zgloszenie o2) {
							return o1.getMiejsceNaDysku() - o2.getMiejsceNaDysku();
						}//koniec compare
					});//koniec klasy zagniezdzonej
				}//koniec for
			}//koniec if
			
			
			if( aktualnyIndex != 0 && aktualnyIndex != listaAktualna.size()-1 ) {
				if ( Math.abs(listaAktualna.get(aktualnyIndex).getMiejsceNaDysku() - listaAktualna.get(aktualnyIndex-1).getMiejsceNaDysku() ) < Math.abs(listaAktualna.get(aktualnyIndex).getMiejsceNaDysku() - listaAktualna.get(aktualnyIndex+1).getMiejsceNaDysku() ) ) {					
					przemieszczenia = przemieszczenia + Math.abs(listaAktualna.get(aktualnyIndex).getMiejsceNaDysku() - listaAktualna.get(aktualnyIndex-1).getMiejsceNaDysku());
					aktualnyIndex--;
				}//koniec if
				else {
					przemieszczenia = przemieszczenia + Math.abs(listaAktualna.get(aktualnyIndex).getMiejsceNaDysku() - listaAktualna.get(aktualnyIndex+1).getMiejsceNaDysku());
					aktualnyIndex++;
				}//koniec else
			}//koniec if zwenêtrznego
			else if ( aktualnyIndex == 0 ) {
				przemieszczenia = przemieszczenia + Math.abs(listaAktualna.get(aktualnyIndex).getMiejsceNaDysku() - listaAktualna.get(aktualnyIndex+1).getMiejsceNaDysku());
				aktualnyIndex++;
			}//koniec else if
			else {
				przemieszczenia = przemieszczenia + Math.abs(listaAktualna.get(aktualnyIndex).getMiejsceNaDysku() - listaAktualna.get(aktualnyIndex-1).getMiejsceNaDysku());
				aktualnyIndex--;
			}//koniec else dla indexu równego size-1
			
			
			
			counter++;
		}//koniec while
		
		
		
		return przemieszczenia;
	}//koniec run
	
	
	
}//koniec klasy

























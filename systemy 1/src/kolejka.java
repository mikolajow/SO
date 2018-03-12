import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class kolejka {
	
	private ArrayList<proces> lista = new ArrayList<>();
	
	public void setLista(ArrayList<proces> lista) {
		this.lista = lista;
	}
	
	public kolejka( ArrayList<proces> dana ) {
		this.lista = dana;
	}
	
	
	
	//KONSTRUKTOR GENERUJE LOSOW¥ KOLEJKE PROCESÓW
	public kolejka() {
		this.lista.add(new proces(11, 7, 4));
		this.lista.add(new proces(22, 3, 7));
		this.lista.add(new proces(33, 9, 8));
	}
	
	public kolejka(int rozmiar) {
		Random generator = new Random();
		for (int i = 0; i < rozmiar ; i++) {
			this.lista.add(new proces(generator.nextInt(100)+30, generator.nextInt(10)+1, generator.nextInt(7) ) );
		//generujemy procesy, czas trwania min 1 jednostka czasu, czas wejscia od 0
		}//koniec for
		int zmienna = rozmiar/10;
		for (int i = 0; i < zmienna ; i++) {
			this.lista.add(new proces(generator.nextInt(100)+30, generator.nextInt(200)+1, generator.nextInt(10) ) );
		// tworzymy ma³y zbior procesów d³ugich
		}//koniec for
		
	}//koniec konstruktora

	public ArrayList<proces> getLista() { return lista;}
	
	//konstruktor kopiuj¹cy ( ¿eby ka¿da kolekcja dzia³a³a na nieposortowanej wczeœniej liœcie procesów i dane lisy byy takie same )
	public kolejka(kolejka wzor) {
		for( proces p : wzor.getLista() ) {
			proces a = new proces(p);
			this.lista.add(a);
		}//koniec for
	}//koniec kopiuj¹cego
	
	//SORTOWANIA
	public void sortujPoCzasieWejscia() {
		Collections.sort(lista, new Comparator<proces>() {
			@Override
			public int compare(proces o1, proces o2) {
				return o1.getCzasWej() - o2.getCzasWej();
			}
		});;
	}//koniec sortuj
	
	/*public void sortujPoCzasieTrwania() {
		Collections.sort(lista, new Comparator<proces>() {

			@Override
			public int compare(proces o1, proces o2) {
				if ( ( o1.getCzasWej() - o2.getCzasWej() ) == 0 ) {
					return o1.getCzasTrw() - o2.getCzasTrw();
				}
				return o1.getCzasWej() - o2.getCzasWej();
			}
		});
	}//koniec sortowania
	
	*/

		
		
		
		

	
}//koniec klasy
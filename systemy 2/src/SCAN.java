import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SCAN {
	
	private ArrayList<zgloszenie> lista;
	
	public SCAN( dysk d ) {
		this.lista = d.getLista();
		
	}//koniec konstruktora
	
	
	public int run() {	
		
		
		
		int przemieszczenia = 0;
		int aktualnyIndex = lista.size()/2;							//zaczniemy ze srodka ( jak w ksi¹¿ce)
		
		
		Collections.sort(lista, new Comparator<zgloszenie>() {	//sortuje po miejscu na dysku
			@Override
			public int compare(zgloszenie o1, zgloszenie o2) {
				return o1.getMiejsceNaDysku() - o2.getMiejsceNaDysku();
			}//koniec compare
		});//koniec klasy zagniezdzonej
		
		for (zgloszenie z : lista) {
			System.out.println(z);
		}
		
		//zak³adaj¹c kierunek pocz¹tkowy "w lewo" przemiescimy siê z miejsca na dysku w którym znajduje siê zg³oszenie 1 do miejsca "0"  dysku a potem do 
		//miejsca w którym znajduje siê ostatnie zg³oszenie
		
		przemieszczenia = lista.get(aktualnyIndex).getMiejsceNaDysku() + lista.get( lista.size()-1 ).getMiejsceNaDysku();
		// miejsce na dysku indexu aktualnego to liczba przemieszczen z aktualnego miejsca na miejsce "0" 
		//dodaje do tego miejsce na którym znajduje siê ostatnie zg³oszenie
		
		return przemieszczenia;
	}//koniec run
	
}//koniec klasy scan























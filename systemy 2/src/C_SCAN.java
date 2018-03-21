import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class C_SCAN {
	
	private ArrayList<zgloszenie> lista;
	private dysk d;
	
	public C_SCAN( dysk d ) {
		this.lista = d.getLista();
		this.d = d;
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
		
		przemieszczenia = d.getRozmiar() - lista.get(aktualnyIndex).getMiejsceNaDysku() ;
		
		
		return przemieszczenia;
	}//koniec run
	
}//koniec klasy c-scan





























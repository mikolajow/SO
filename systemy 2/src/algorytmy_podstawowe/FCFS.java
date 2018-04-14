package algorytmy_podstawowe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import test.dysk;
import test.zgloszenie;

public class FCFS {
	
	private ArrayList<zgloszenie> lista;
	
	public FCFS( dysk d ) {
		this.lista = d.getLista();
	}//koniec konstruktora
	
	
	public int run () {
		
		int przemieszczenie = 0;
		
		ArrayList<zgloszenie> aktualnalista = new ArrayList<>();
		
		for ( zgloszenie z : lista ) {
			aktualnalista.add(z);
		}
		
		Collections.sort(aktualnalista, new Comparator<zgloszenie>() {	//sortuje po czasie wejscia
			@Override
			public int compare(zgloszenie o1, zgloszenie o2) {
				return o1.getCzasWejscia() - o2.getCzasWejscia();
			}//koniec compare
		});//koniec klasy zagniezdzonej
		
		int ostatniePolozenie= aktualnalista.get(0).getMiejsceNaDysku() ;
		
		for ( zgloszenie z : aktualnalista ) {
			przemieszczenie = przemieszczenie + Math.abs(ostatniePolozenie - z.getMiejsceNaDysku());
			ostatniePolozenie = z.getMiejsceNaDysku();
		}//koniec for
		
		return przemieszczenie;
	}//koniec run
	//for (zgloszenie z : lista) {
	//	System.out.println(z);
	//}
}//koniec klasy






























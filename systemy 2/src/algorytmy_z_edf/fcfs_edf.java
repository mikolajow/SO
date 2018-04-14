package algorytmy_z_edf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import test.dysk;
import test.zgloszenie;

public class fcfs_edf {
	
	
	
	private ArrayList<zgloszenie> lista;
	
	public fcfs_edf( dysk d ) {
		
		this.lista = d.getLista();
	}//koniec konstruktora
	
	
	public int run () {
		
		int przemieszczenie = 0;
		
		
		
		Collections.sort(lista, new Comparator<zgloszenie>() {	//sortuje po czasie wejscia, priorytety pierwsze
			@Override
			public int compare(zgloszenie o1, zgloszenie o2) {
				
				if ( o1.getCzasWejscia() - o2.getCzasWejscia() == 0 && o2.getPriorytet() == true ) {
					return -1;
				}else
					return o1.getCzasWejscia() - o2.getCzasWejscia();
			}//koniec compare
		});//koniec klasy zagniezdzonej
		
		int ostatniePolozenie= lista.get(0).getMiejsceNaDysku() ;
		
		for ( zgloszenie z : lista ) {
			przemieszczenie = przemieszczenie + Math.abs(ostatniePolozenie - z.getMiejsceNaDysku());
			ostatniePolozenie = z.getMiejsceNaDysku();
		}//koniec for
		
		return przemieszczenie;
		
		
		
		
		
	}//koniec run
	
}//KONIEC KLASY




















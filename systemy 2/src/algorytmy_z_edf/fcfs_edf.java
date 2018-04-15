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
		
		ArrayList<zgloszenie> listaaktualna = new ArrayList<>();
		ArrayList<zgloszenie> listaPriorytetowa = new ArrayList<>();
		
		for ( zgloszenie z : lista ) {
			listaaktualna.add(z);
		}
		
		for (int i = 0; i<listaaktualna.size()-1; i++ ) {
			if (listaaktualna.get(i).getPriorytet()) {
				listaPriorytetowa.add(listaaktualna.get(i));
				listaaktualna.remove(i);
				i--;
			}//koniec if
		}//koniec for
		
		Collections.sort(listaPriorytetowa, new Comparator<zgloszenie>() {	//sortuje po czasie wejscia, priorytety pierwsze
			@Override
			public int compare(zgloszenie o1, zgloszenie o2) {
				return o1.getCzasWejscia() - o2.getCzasWejscia();
			}//koniec compare
		});//koniec klasy zagniezdzonej
		
		Collections.sort(listaaktualna, new Comparator<zgloszenie>() {	//sortuje po czasie wejscia
			@Override
			public int compare(zgloszenie o1, zgloszenie o2) {
				return o1.getCzasWejscia() - o2.getCzasWejscia();
			}//koniec compare
		});//koniec klasy zagniezdzonej
		
		int ostatniePolozenie= listaPriorytetowa.get(0).getMiejsceNaDysku() ;
		
		for ( zgloszenie z : listaPriorytetowa ) {
			przemieszczenie = przemieszczenie + Math.abs(ostatniePolozenie - z.getMiejsceNaDysku());
			ostatniePolozenie = z.getMiejsceNaDysku();
		}//koniec for
		
		for ( zgloszenie z : listaaktualna ) {
			przemieszczenie = przemieszczenie + Math.abs(ostatniePolozenie - z.getMiejsceNaDysku());
			ostatniePolozenie = z.getMiejsceNaDysku();
		}//koniec for
		
		return przemieszczenie;
		
	}//koniec run
	
}//KONIEC KLASY




















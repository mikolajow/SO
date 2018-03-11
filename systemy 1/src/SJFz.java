import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SJFz {
	
	
	private kolejka kol;
	private int liczbaProcesow;
	private ArrayList<proces> lista;
	
	public SJFz(kolejka a) {
		this.kol = a;
		this.liczbaProcesow = kol.getLista().size();
		this.lista = kol.getLista();
	}
	
	public double run() {
		int czasOczekiwania = 0;
		int czasPracy = 0;
		
		kol.sortujPoCzasieWejscia();
		
		ArrayList<proces> lista2 = new ArrayList<>();			//jako lista procesów gotowych
		ArrayList<proces> lista3 = new ArrayList<>();			//jako miejsce dla procesów zakoñczonych
		
		while (! ( lista.isEmpty() && lista2.isEmpty() )  ) {		//kontynuuj az lista wszystkich i lista gotowych procesó bêd¹ puste
			
			for ( proces p : lista) {
				if ( czasPracy == p.getCzasWej() ) { lista2.add(p); }	//kazdy proces gotowy dodjemy do list2
			}
			
			for ( proces p : lista2) {
				lista.remove(p); 							//usówamy z lisy pierwszej powtarzaj¹ce siê procesy
			}
			
			
			Collections.sort(lista2, new Comparator<proces>() {
				@Override
				public int compare(proces o1, proces o2) {			//sortujemy kolekcje procesów gotowych, po czasie trwania
					// TODO Auto-generated method stub
					return o1.getCzasTrw() - o2.getCzasTrw();
				}
			});
			
			if(!lista2.isEmpty()) {
				lista2.get(0).zmniejszCzasTrwaniaOJeden();			//wykonaj proces
			
				for ( int i = 1; i < lista2.size(); i++ ) {
					lista2.get(i).zwiekszCzasOczekiwania(1); 		//dla kazdego procesu który jest gotowy i nie wykonuje sie aktualnie zwieksz czas oczekiwania o 1
				}
			
				if ( lista2.get(0).getCzasTrw() == 0 ) { 
				lista3.add(lista2.get(0));			//jeœli proces wykonany to usuñ z listy2 i dodaj do listy3
					lista2.remove(0);
				}	
			}
			
			czasPracy = czasPracy + 1;						//inkrementuj czas pracy
		}//koniec while
		
		for ( proces p : lista3 ) {
			czasOczekiwania = czasOczekiwania + p.getCzasOczek();
		}
		/*
		System.out.println("Kolejka zakonczonych z SJFz");
		for ( proces p : lista3 ) {
			System.out.println(p);
		}*/
		
		return  (double)czasOczekiwania/liczbaProcesow;
	}//koniec run 
	
	
}//koniec klasy






















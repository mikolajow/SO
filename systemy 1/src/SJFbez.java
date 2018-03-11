import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SJFbez {
	
	
	private kolejka kol;
	private int liczbaProcesow;
	private ArrayList<proces> lista;
	
	public SJFbez(kolejka a) {
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
			
			if (!lista.isEmpty()) {
				if ( czasPracy < lista.get(0).getCzasWej() ) {
					czasPracy = lista.get(0).getCzasWej();		//jesli nie ma procesu w kolejce to poczekaj na niego
					}
			}
			
			for ( proces p : lista) {
				if ( czasPracy >= p.getCzasWej() ) { lista2.add(p); }	//kazdy proces gotowy dodjemy do list2
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
			
			if (!lista2.isEmpty()) {
				proces aktualny = lista2.get(0);
				//System.out.println("Przed " + aktualny);
				czasPracy = czasPracy + aktualny.getCzasTrw();	//wykonuje proces
			
				aktualny.setCzasOczek( czasPracy - aktualny.getCzasWej() - aktualny.getCzasTrw() ); 
				//czas oczekiwania równy czasowi pracy procesora - czas wejscia procesu - czas trwania procesu
			
				lista3.add(aktualny);		//dodaje wykonany proces do listy3 
				lista2.remove(aktualny);				//i usowam z listy2
				//System.out.println("PO " + aktualny + "\n");
			}
			
			
			
		}//koniec while
		
		for ( proces p : lista3 ) {
			czasOczekiwania = czasOczekiwania + p.getCzasOczek();
		}
		
		
		return  (double)czasOczekiwania/liczbaProcesow;
	}//koniec run 
	
	
}//koniec klasy






















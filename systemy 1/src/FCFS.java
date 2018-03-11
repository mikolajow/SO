import java.util.ArrayList;

public class FCFS {
	
	private kolejka kol;
	private int liczbaProcesow;
	private ArrayList<proces> lista;
	
	public FCFS( kolejka a) { this.kol=a;
	this.liczbaProcesow = a.getLista().size();
	this.lista = a.getLista();}
	
	
	public double run() {
		
		int czasOczekiwania = 0;
		kol.sortujPoCzasieWejscia();
		int czasPracy=0;
		
		while (!lista.isEmpty()) {
			if ( czasPracy < lista.get(0).getCzasWej() ) {
				czasPracy = lista.get(0).getCzasWej();		//jesli nie ma procesu w kolejce to poczekaj na niego
				}
			
			czasPracy = czasPracy + lista.get(0).getCzasTrw();	//wykonuje proces
			
			lista.get(0).setCzasOczek( czasPracy - lista.get(0).getCzasWej() - lista.get(0).getCzasTrw() ); 
			
			//czas oczekiwania równy czasowi pracy procesora - czas wejscia procesu - czas trwania procesu
			
			czasOczekiwania = czasOczekiwania + lista.get(0).getCzasOczek();
			
			lista.remove(0);					//usówam proces  kolejki
			
			
		}//koniec while
		
		return  (double)czasOczekiwania/liczbaProcesow;
	}//koniec run
	
	
}//koniec klasy



















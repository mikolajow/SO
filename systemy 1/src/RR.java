import java.util.ArrayList;

public class RR {
	
	private int kwantCzasu;
	private kolejka kol;
	private int liczbaProcesow;
	private ArrayList<proces> lista;
	
	public RR( kolejka a, int kwant) { 
	this.kwantCzasu = kwant;
	this.kol=a;
	this.liczbaProcesow = a.getLista().size();
	this.lista = a.getLista();}
	
	public double run() {
		
		int czasOczekiwania = 0;
		int czasPracy = 0;
		ArrayList<proces> lista2 = new ArrayList<>();  // lista na procesy gotowe
		ArrayList<proces> lista3 = new ArrayList<>();  //lista na procesy gotowe
		
		kol.sortujPoCzasieWejscia();
		proces ostatni = null;
		
		while ( ! ( lista3.size()==liczbaProcesow ) ) {		//dopóki lista procesów zakonczonych nie jest pe³na
			
			if ( !lista.isEmpty() && lista2.isEmpty() && ostatni == null ) {
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
			
			if ( ostatni != null ) {		//jeœli ostatni proces nie zosta³ zakonczony to dodajemy na koniec kolejki
			lista2.add(ostatni); }
			
			
			if ( !lista2.isEmpty() ) {
				
				proces aktualny = lista2.get(0);			//biore aktualny proces z listy procesów gotowych
				
				
				
				
				
				
				if ( aktualny.getCzasTrw() > kwantCzasu ) {
					czasPracy = czasPracy + kwantCzasu;
					aktualny.zmniejszCzasTrwania(kwantCzasu);		//proces nie zd¹¿y³ siê wykonaæ wiêc l¹duje na koñcu kolejki skrócony o kwant czasu
					lista2.remove(aktualny);
					ostatni = aktualny;
				}
				else {
					czasPracy = czasPracy + aktualny.getCzasTrw();
					aktualny.setCzasTrw(0);
					aktualny.setCzasZakonczenia(czasPracy);
					lista3.add(aktualny);						//zakoñczony proces l¹duje w liœcie procesów zakoñczonych
					lista2.remove(aktualny);
					ostatni = null;
				}
			}
			
		}//koniec while
		
		for ( proces p : lista3 ) {
			p.setCzasTAT(p.getCzasZakonczenia() - p.getCzasWej() );
			p.setCzasOczek( p.getCzasTAT() - p.getCzasTrwania1());
			czasOczekiwania = czasOczekiwania + p.getCzasOczek();
		}
		
			
		return  (double)czasOczekiwania/liczbaProcesow;
	}//koniec run
	
}//koniec klasy































import java.util.ArrayList;

public class LRU {
	// LAST RECENTY USED
	//wywal strone ktora nie by³a uzywana najd³u¿ej
	
	private strona[] strony;
	private int liczbaRamek;
	private ArrayList<Integer> ListaOdwolan;
	
	public LRU(zalozenia z) {
		this.liczbaRamek = z.getLiczbaRamek();
		this.strony = z.getStrony();
		this.ListaOdwolan = z.getOdwolania();
	}//koniec konstruktora
	
	public int run() {
		int bledyStrony = 0;
		
		ArrayList<strona> stronyWRamie = new ArrayList<>();
		
		while ( !ListaOdwolan.isEmpty() ) {
			int aktualneOdwolanie = ListaOdwolan.get(0);
			boolean czyJuzJest = false;
			
			for ( strona s : stronyWRamie ) {
				if( s.getNumer() == aktualneOdwolanie ) {		//sprawdzam czy aktualnie potrzebna strona juz jest w pamieci fizycznej
					czyJuzJest = true;
				}//koniec if
			}//koniec for
			
			if ( czyJuzJest ) {
				ListaOdwolan.remove(0);
			}//jak jest to usówam odwolanie z listy i przechodze do nastepnego
			else {
				bledyStrony++;
				if ( stronyWRamie.size() == liczbaRamek ) {
					//miejsce na algorytm usówania strony
					
					
					
					
					
				}//koniec if wewnatrznego
				stronyWRamie.add(strony[aktualneOdwolanie]);	//dodaje ramke potrzebna do aktualnego odwlania
			}//koniec else
			
		}//koniec while
		
		
		return bledyStrony;
	}//koniec run
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//koniec klasy














